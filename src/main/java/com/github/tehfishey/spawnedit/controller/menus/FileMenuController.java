package com.github.tehfishey.spawnedit.controller.menus;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map.Entry;

import com.github.tehfishey.spawnedit.controller.ControllerManager;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory.ExceptionType;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory.SaveType;
import com.github.tehfishey.spawnedit.model.Model;
import com.github.tehfishey.spawnedit.model.exceptions.BatchIOException;
import com.github.tehfishey.spawnedit.model.exceptions.BatchJsonException;
import com.github.tehfishey.spawnedit.model.helpers.Enums.ColumnId;

import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class FileMenuController {
	
	private final ControllerManager manager;
	private final Model model;
	private final FileChooser fileChooser;
	private final DirectoryChooser directoryChooser;

	@FXML private Menu fileMenu;
	
    public FileMenuController(ControllerManager manager, Model model) {
    	this.manager = manager;
    	this.model = model;
    	this.fileChooser = new FileChooser();
        this.directoryChooser = new DirectoryChooser();
        configureFileChoosers(fileChooser, directoryChooser);
    }
    
    public void initialize() { 	
    }
    
    public void exit(ActionEvent event) {
		 java.lang.System.exit(0);
	}
	
	public void loadFile(ActionEvent event) {
		Path file = fileChooser.showOpenDialog(manager.getRoot().getScene().getWindow()).toPath();
		if (file != null)  
			try { 
				model.getFileManager().loadFile(file); 
			} catch (BatchIOException e) {
				Alert alert = AlertDialogFactory.loadExceptionAlert(e.getExceptedPaths(), ExceptionType.BatchIOException);
				alert.show();
			} catch (BatchJsonException e) {
				Alert alert = AlertDialogFactory.loadExceptionAlert(e.getExceptedPaths(), ExceptionType.BatchJsonException);
				alert.show();
			}
		fileChooser.setInitialDirectory(file.getParent().toFile());
		directoryChooser.setInitialDirectory(file.getParent().toFile());
	}
	
	public void loadDirectory(ActionEvent event) {
		Path directory = directoryChooser.showDialog(manager.getRoot().getScene().getWindow()).toPath();
		if (directory != null) 
			try { 
				model.getFileManager().loadDirectory(directory); 
			} catch (BatchIOException e) {
				Alert alert = AlertDialogFactory.loadExceptionAlert(e.getExceptedPaths(), ExceptionType.BatchIOException);
				alert.show();
			} catch (BatchJsonException e) {
				Alert alert = AlertDialogFactory.loadExceptionAlert(e.getExceptedPaths(), ExceptionType.BatchJsonException);
				alert.show();
			}
		fileChooser.setInitialDirectory(directory.getParent().toFile());
		directoryChooser.setInitialDirectory(directory.getParent().toFile());
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
		Path directory = directoryChooser.showDialog(manager.getRoot().getScene().getWindow()).toPath();
		
		if (directory != null)  {
			Alert confirmation = AlertDialogFactory.saveWarningAlert(SaveType.SaveDirectory);
			confirmation.showAndWait();
			
			if (confirmation.getResult() == ButtonType.YES) {
				try { 
					model.getFileManager().saveAllToRoot(directory); 
				} catch (BatchIOException e) {
					Alert alert = AlertDialogFactory.saveExceptionAlert(e.getExceptedPaths(), ExceptionType.BatchIOException);
					alert.show();
				}
			}
		fileChooser.setInitialDirectory(directory.getParent().toFile());
		directoryChooser.setInitialDirectory(directory.getParent().toFile());
		}
	}

	private static void configureFileChoosers(final FileChooser fileChooser, final DirectoryChooser directoryChooser) {                           
       fileChooser.setTitle("Load File");
       fileChooser.setInitialDirectory(
           new File(System.getProperty("user.home"))
       ); 
       fileChooser.getExtensionFilters().addAll(
       		new FileChooser.ExtensionFilter("JSON", "*.json")
           );
       
       directoryChooser.setTitle("Load Directory");
       directoryChooser.setInitialDirectory(
               new File(System.getProperty("user.home"))
           ); 
	}
}
