package com.github.tehfishey.spawnedit.controller.commands;

import java.nio.file.Paths;
import java.util.Optional;

import com.github.tehfishey.spawnedit.controller.dialogs.TextDialogFactory;
import com.github.tehfishey.spawnedit.controller.helpers.TreeCellFactory;
import com.github.tehfishey.spawnedit.model.objects.PathTreeNode;
import com.google.common.io.Files;

import javafx.scene.control.TextInputDialog;

public class PathTreeRename extends Command {
	PathTreeNode node;
	TreeCellFactory cell;
	String oldName;
	String newName;
	
	public PathTreeRename(TreeCellFactory cell, PathTreeNode node) {
		this.canUndo = true;
		this.node = node;
		this.cell = cell;
	}
	
	@Override
	public void execute() {
		oldName = node.getLocalPath().toString();
		String cleanedName = oldName;
		if (node.isFile()) cleanedName = Files.getNameWithoutExtension(oldName);
		
		TextInputDialog dialog = TextDialogFactory.nameInputDialog(node.isDirectory(), cleanedName);
		Optional<String> entry = dialog.showAndWait();
		if (!entry.isEmpty()) newName = entry.get();
		if (node.isFile()) newName += ".json";
		
		node.setLocalPath(Paths.get(newName));
		cell.updateItem(node, false);
	}

	@Override
	public void undo() {
		if (oldName == null) return;
		node.setLocalPath(Paths.get(oldName));
		cell.updateItem(node, false);
	}

	@Override
	public void redo() {
		if (newName == null) return;
		node.setLocalPath(Paths.get(newName));
		cell.updateItem(node, false);
	}

}
