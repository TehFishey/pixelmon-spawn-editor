package com.github.tehfishey.spawnedit.controller.commands;

import java.nio.file.Paths;
import java.util.Optional;

import com.github.tehfishey.spawnedit.controller.dialogs.TextDialogFactory;
import com.github.tehfishey.spawnedit.model.objects.PathTreeNode;

import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;

public class PathTreeNewDirectory extends Command {
	TreeItem<PathTreeNode> parentItem;
	TreeItem<PathTreeNode> newItem;
	PathTreeNode newNode;
	
	public PathTreeNewDirectory(TreeItem<PathTreeNode> parentItem) {
		canUndo = true;
		this.parentItem = parentItem;
	}
	
	@Override
	public void execute() {
		PathTreeNode parentNode = parentItem.getValue();
		
		TextInputDialog dialog = TextDialogFactory.nameInputDialog(true, "");
		Optional<String> name = dialog.showAndWait();
		if (name.isEmpty()) return;
		
		newNode = parentNode.newChildDirectory(Paths.get(name.get()));
		newItem = new TreeItem<PathTreeNode>(newNode);
		newItem.setExpanded(true);
		parentItem.getChildren().add(newItem);
	}

	@Override
	public void undo() {
		newNode.getParent().getChildren().remove(newNode);
		newItem.getParent().getChildren().remove(newItem);
	}

	@Override
	public void redo() {
		newNode.getParent().getChildren().add(newNode);
		newItem.getParent().getChildren().add(newItem);
	}

}
