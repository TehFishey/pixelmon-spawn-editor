package com.github.tehfishey.spawnedit.controller.commands;

import java.nio.file.Path;

import com.github.tehfishey.spawnedit.controller.ControllerManager;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory.ExceptionType;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory.SaveType;
import com.github.tehfishey.spawnedit.model.Model;
import com.github.tehfishey.spawnedit.model.exceptions.BatchIOException;
import com.github.tehfishey.spawnedit.model.objects.PathTreeNode;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.DirectoryChooser;

	// Command which saves a specified node (object or directory) from the model's/controller's 
	// virtual path tree to a location. Called from TreeCellFactory. 

	// Because this command interacts with the file system, it cannot be undone.

public class PathTreeSaveFile extends Command {

	Model model;
	ControllerManager manager;
	PathTreeNode node;
	
	public PathTreeSaveFile(Model model, ControllerManager manager, PathTreeNode node) {
		canUndo = false;
		this.model = model;
		this.manager = manager;
		this.node = node;
	}
	
	@Override
	public void execute() {
		if (node.isRoot()) return;
		
		DirectoryChooser directoryChooser = manager.getDirectoryChooser();
		directoryChooser.setTitle("Save");
		Path directory = directoryChooser.showDialog(manager.getRoot().getScene().getWindow()).toPath();
		
		if (directory != null)  {
			Alert confirmation = AlertDialogFactory.saveWarningAlert(node.isDirectory() ? SaveType.SaveDirectory : SaveType.SaveFile );
			confirmation.showAndWait();
			
			if (confirmation.getResult() == ButtonType.YES) {
				try { 
					model.getFileManager().saveFile(directory, node); 
				} catch (BatchIOException e) {
					Alert alert = AlertDialogFactory.saveExceptionAlert(e.getExceptedPaths(), ExceptionType.BatchIOException);
					alert.show();
				}
			}
		manager.setChooserDirectory(directory.getParent().toFile());
		}
	}

	@Override
	public void undo() {}

	@Override
	public void redo() {}

}
