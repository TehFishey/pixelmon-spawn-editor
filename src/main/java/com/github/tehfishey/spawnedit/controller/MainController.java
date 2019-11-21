package com.github.tehfishey.spawnedit.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import com.github.tehfishey.spawnedit.model.Model;

	// Controller for elements in the Main.fxml file. 

	// Currently (temporarily?) handles operations for the Edit menu (executing Undo and Redo).

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
		manager.redo();
	}
}
