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
import javafx.stage.DirectoryChooser;

public class FileMenuLoadDirectory extends Command {

	Model model;
	ControllerManager manager;
	
	public FileMenuLoadDirectory(ControllerManager manager, Model model) {
		canUndo = false;
		this.model = model;
		this.manager = manager;
	}
	
	@Override
	public void execute() {
		DirectoryChooser directoryChooser = manager.getDirectoryChooser();
		directoryChooser.setTitle("Load Directory");
		
		Path directory = directoryChooser.showDialog(manager.getRoot().getScene().getWindow()).toPath();
		if (directory != null) {
			try { 
				model.getFileManager().loadDirectory(directory); 
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
			manager.setChooserDirectory(directory.getParent().toFile());
		}
	}

	@Override
	public void undo() {}

	@Override
	public void redo() {}

}
