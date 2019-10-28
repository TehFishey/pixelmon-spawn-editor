package com.github.tehfishey.spawnedit.controller.commands;

import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory;
import com.github.tehfishey.spawnedit.model.Model;
import com.github.tehfishey.spawnedit.model.objects.PathTreeNode;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeItem;

public class PathTreeCloseFile extends Command {

	Model model;
	TreeItem<PathTreeNode> item;
	
	public PathTreeCloseFile(Model model, TreeItem<PathTreeNode> item) {
		canUndo = false;
		this.model = model;
		this.item = item;
	}
	
	@Override
	public void execute() {
		Alert confirmation = AlertDialogFactory.closeWarningAlert();
		confirmation.showAndWait();
		if (confirmation.getResult() == ButtonType.YES)
			model.removeSpawnPath(item.getValue());
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		
	}

}
