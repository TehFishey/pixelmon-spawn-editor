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

import com.github.tehfishey.spawnedit.controller.commands.FileMenuLoadFile;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory.ExceptionType;
import com.github.tehfishey.spawnedit.model.Model;
import com.github.tehfishey.spawnedit.model.exceptions.BatchIOException;
import com.github.tehfishey.spawnedit.model.exceptions.BatchJsonException;
import com.google.gson.JsonParseException;

public class MainController {
	private final Model model;
	private final ControllerManager manager;
	
	@FXML private AnchorPane root;
	
    public MainController(ControllerManager manager, Model model) {
    	this.manager = manager;
        this.model = model;
    }
	
    public void initialize() {
    	manager.setRoot(root);
    }
    
    public void undo() {
		manager.undo();
	}
    public void redo() {
		manager.undo();
	}
}
