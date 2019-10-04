package com.github.tehfishey.spawnedit.controller;


import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import com.github.tehfishey.spawnedit.model.Model;


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
		if (file != null) model.getFileManager().loadFile(file);
	}
	
	public void loadDirectory(ActionEvent event) {
		configureDirectoryChooser(directoryChooser);
		File directory = directoryChooser.showDialog(root.getScene().getWindow());
		if (directory != null) model.getFileManager().loadDirectory(directory);
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
