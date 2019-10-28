package com.github.tehfishey.spawnedit.controller.commands;

import java.nio.file.Paths;
import java.util.Optional;

import com.github.tehfishey.spawnedit.controller.dialogs.TextDialogFactory;
import com.github.tehfishey.spawnedit.model.objects.PathTreeNode;

import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;

public class PathTreeNewFile extends Command {
	TreeItem<PathTreeNode> parentItem;
	TreeItem<PathTreeNode> newItem;
	PathTreeNode newNode;
	
	public PathTreeNewFile(TreeItem<PathTreeNode> parentItem) {
		canUndo = true;
		this.parentItem = parentItem;
	}
	
	@Override
	public void execute() {
		PathTreeNode parentNode = parentItem.getValue();

		TextInputDialog dialog = TextDialogFactory.nameInputDialog(false, "");
		Optional<String> name = dialog.showAndWait();
		if (name.isEmpty()) return;
		
		newNode = parentNode.newChildFile(Paths.get(name.get() + ".json"), name.get());
		newItem = new TreeItem<PathTreeNode>(newNode);
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
