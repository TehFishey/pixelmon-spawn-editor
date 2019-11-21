package com.github.tehfishey.spawnedit.controller.commands;

import java.util.ArrayList;

import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory;
import com.github.tehfishey.spawnedit.model.Model;
import com.github.tehfishey.spawnedit.model.objects.PathTreeNode;
import com.github.tehfishey.spawnedit.model.objects.SpawnEntry;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

	// Command which closes/removes a specified node (file or directory) from the model's/controller's
	// virtual path tree. Called from TreeCellFactory.

public class PathTreeCloseFile extends Command {

	Model model;
	PathTreeNode item;
	PathTreeNode itemParent;
	Integer itemIndex;
	ArrayList<SpawnEntry> associatedEntries;
	
	public PathTreeCloseFile(Model model, PathTreeNode item) {
		canUndo = true;
		this.model = model;
		this.item = item;
		this.itemParent = this.item.getParent();
		this.itemIndex = itemParent.getChildren().indexOf(this.item);
	}
	
	@Override
	public void execute() {
		Alert confirmation = AlertDialogFactory.closeWarningAlert();
		confirmation.showAndWait();
		if (confirmation.getResult() == ButtonType.YES)
			associatedEntries = model.removeSpawnPath(item);
	}

	@Override
	public void undo() {
		model.addSpawnEntries(associatedEntries);
		itemParent.getChildren().add(itemIndex, item);
		// LOL seriously, this is the best way you can think of to do this? What a mess.
		model.notifyListeners("fileTreeUpdated", null, null);
	}

	@Override
	public void redo() {
		model.removeSpawnEntries(associatedEntries);
		itemParent.getChildren().remove(item);
		model.notifyListeners("fileTreeUpdated", null, null);
	}

}
