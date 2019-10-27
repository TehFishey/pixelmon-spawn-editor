package com.github.tehfishey.spawnedit.controller.menus;

import java.nio.file.Path;

import com.github.tehfishey.spawnedit.controller.ControllerManager;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory.ExceptionType;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory.SaveType;
import com.github.tehfishey.spawnedit.model.Model;
import com.github.tehfishey.spawnedit.model.exceptions.BatchDuplicateIDException;
import com.github.tehfishey.spawnedit.model.exceptions.BatchIOException;
import com.github.tehfishey.spawnedit.model.exceptions.BatchJsonException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class FileMenuController {
	
	private final ControllerManager manager;
	private final Model model;

	@FXML private Menu fileMenu;
	
    public FileMenuController(ControllerManager manager, Model model) {
    	this.manager = manager;
    	this.model = model;
    }
    
    public void initialize() { 	
    }
    
    public void exit(ActionEvent event) {
		 java.lang.System.exit(0);
	}
	
	public void loadFile(ActionEvent event) {
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
	
	public void loadDirectory(ActionEvent event) {
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
	
	/*public void saveAllFiles(ActionEvent event) {
		Alert confirmation = AlertDialogFactory.saveWarningAlert(SaveType.SaveAll);
		confirmation.showAndWait();
		
		if (confirmation.getResult() == ButtonType.YES) {
			try {
				model.getFileManager().saveAllToMap();
			} catch (BatchIOException e) {
				Alert alert = AlertDialogFactory.saveExceptionAlert(e.getExceptedPaths(), ExceptionType.BatchIOException);
				alert.show();
			}	
		}
	}*/
	
	public void saveToDirectory(ActionEvent event) {
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
}
