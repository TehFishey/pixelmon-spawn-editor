package com.github.tehfishey.spawnedit.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.github.tehfishey.spawnedit.model.Model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController {
	private final Model model;
	
    public MainController(Model model) {
        this.model = model;
    }
	
    public void initialize() {
    }
    
	public void Exit(ActionEvent event) {
		 java.lang.System.exit(0);
	}

}
