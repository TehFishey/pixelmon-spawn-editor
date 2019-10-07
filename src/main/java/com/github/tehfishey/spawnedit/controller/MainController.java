package com.github.tehfishey.spawnedit.controller;


import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory.ExceptionType;
import com.github.tehfishey.spawnedit.model.Model;
import com.github.tehfishey.spawnedit.model.exceptions.BatchIOException;
import com.github.tehfishey.spawnedit.model.exceptions.BatchJsonException;
import com.google.gson.JsonParseException;

public class MainController {
	private final Model model;
	private final ControllerStateManager manager;
	private final FileChooser fileChooser;
	private final DirectoryChooser directoryChooser;
	
	@FXML private AnchorPane root;
	
    public MainController(ControllerStateManager manager, Model model) {
    	this.manager = manager;
        this.model = model;
        this.fileChooser = new FileChooser();
        this.directoryChooser = new DirectoryChooser();
    }
	
    public void initialize() {
    }
    
	public void exit(ActionEvent event) {
		 java.lang.System.exit(0);
	}
	
	public void loadFile(ActionEvent event) {
		configureFileChooser(fileChooser);
		File file = fileChooser.showOpenDialog(root.getScene().getWindow());
		if (file != null)  
			try { 
				model.getFileManager().loadFile(file); 
			} catch (BatchIOException e) {
				Alert alert = AlertDialogFactory.createLoadAlert(e.getExceptionPaths(), ExceptionType.BatchIOException);
				alert.show();
			} catch (BatchJsonException e) {
				Alert alert = AlertDialogFactory.createLoadAlert(e.getErrorPaths(), ExceptionType.BatchJsonException);
				alert.show();
			}
	}
	
	public void loadDirectory(ActionEvent event) {
		configureDirectoryChooser(directoryChooser);
		File directory = directoryChooser.showDialog(root.getScene().getWindow());
		if (directory != null) 
			try { 
				model.getFileManager().loadDirectory(directory); 
			} catch (BatchIOException e) {
				Alert alert = AlertDialogFactory.createLoadAlert(e.getExceptionPaths(), ExceptionType.BatchIOException);
				alert.show();
			} catch (BatchJsonException e) {
				Alert alert = AlertDialogFactory.createLoadAlert(e.getErrorPaths(), ExceptionType.BatchJsonException);
				alert.show();
			}
	}
	
	public void saveAll(ActionEvent event) {
		model.getFileManager().saveAll();
	}

	private static void configureFileChooser(final FileChooser fileChooser){                           
        fileChooser.setTitle("Load File");
        fileChooser.setInitialDirectory(
            new File(System.getProperty("user.home"))
        ); 
        fileChooser.getExtensionFilters().addAll(
        		new FileChooser.ExtensionFilter("JSON", "*.json")
            );
	}
	
	private static void configureDirectoryChooser(final DirectoryChooser directoryChooser){                           
		directoryChooser.setTitle("Load Directory");
		directoryChooser.setInitialDirectory(
            new File(System.getProperty("user.home"))
        );
	}
}
