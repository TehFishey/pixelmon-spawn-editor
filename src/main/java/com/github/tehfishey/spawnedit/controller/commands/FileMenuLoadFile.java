package com.github.tehfishey.spawnedit.controller.commands;

import java.nio.file.Path;

import com.github.tehfishey.spawnedit.controller.ControllerManager;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory.ExceptionType;
import com.github.tehfishey.spawnedit.model.Model;
import com.github.tehfishey.spawnedit.model.exceptions.BatchDuplicateIDException;
import com.github.tehfishey.spawnedit.model.exceptions.BatchIOException;
import com.github.tehfishey.spawnedit.model.exceptions.BatchJsonException;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

	// Loads SpawnEntries/PathTreeNodes from a user-selected .json file into the Model, throwing various 
	// exceptions when they arise. Called from FileMenuController.

	// Because this command interacts with the file system, it cannot be undone.

public class FileMenuLoadFile extends Command {

	Model model;
	ControllerManager manager;
	
	public FileMenuLoadFile(ControllerManager manager, Model model) {
		canUndo = false;
		this.model = model;
		this.manager = manager;
	}
	
	@Override
	public void execute() {
		FileChooser fileChooser = manager.getFileChooser();
		fileChooser.setTitle("Load File");
		Path file = fileChooser.showOpenDialog(manager.getRoot().getScene().getWindow()).toPath();
		if (file != null) {
			try { 
				model.getFileManager().loadFile(file); 
			} catch (BatchIOException e) {
				Alert alert = AlertDialogFactory.loadExceptionAlert(e.getExceptedPaths(), ExceptionType.BatchIOException);
				alert.show();
			} catch (BatchJsonException e) {
				Alert alert = AlertDialogFactory.loadExceptionAlert(e.getExceptedPaths(), ExceptionType.BatchJsonException);
				alert.show();
			} catch (BatchDuplicateIDException e) {
				Alert alert = AlertDialogFactory.loadExceptionAlert(e.getExceptedPaths(), ExceptionType.BatchIDException);
				alert.show();
			}
			manager.setChooserDirectory(file.getParent().toFile());
		}
	}

	@Override
	public void undo() {}

	@Override
	public void redo() {}

}
