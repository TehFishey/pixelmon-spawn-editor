package com.github.tehfishey.spawnedit.controller.commands;

import java.nio.file.Path;

import com.github.tehfishey.spawnedit.controller.ControllerManager;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory.ExceptionType;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory.SaveType;
import com.github.tehfishey.spawnedit.model.Model;
import com.github.tehfishey.spawnedit.model.exceptions.BatchIOException;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.DirectoryChooser;

public class FileMenuSaveAll extends Command {

	Model model;
	ControllerManager manager;
	
	public FileMenuSaveAll(ControllerManager manager, Model model) {
		canUndo = false;
		this.model = model;
		this.manager = manager;
	}
	
	@Override
	public void execute() {
		DirectoryChooser directoryChooser = manager.getDirectoryChooser();
		directoryChooser.setTitle("Save");
		
		Path directory = directoryChooser.showDialog(manager.getRoot().getScene().getWindow()).toPath();
		
		if (directory != null)  {
			Alert confirmation = AlertDialogFactory.saveWarningAlert(SaveType.SaveDirectory);
			confirmation.showAndWait();
			
			if (confirmation.getResult() == ButtonType.YES) {
				try { 
					model.getFileManager().saveAll(directory); 
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
