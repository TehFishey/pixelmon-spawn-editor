package com.github.tehfishey.spawnedit.controller.menus;

import com.github.tehfishey.spawnedit.controller.ControllerManager;
import com.github.tehfishey.spawnedit.controller.commands.FileMenuLoadDirectory;
import com.github.tehfishey.spawnedit.controller.commands.FileMenuLoadFile;
import com.github.tehfishey.spawnedit.controller.commands.FileMenuSaveAll;
import com.github.tehfishey.spawnedit.model.Model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;

	// Controller for elements in the FileMenu.fxml file.
	// Manages general FileManager save and load commands.

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
		manager.execute(new FileMenuLoadFile(manager, model));
	}
	
	public void loadDirectory(ActionEvent event) {
		manager.execute(new FileMenuLoadDirectory(manager, model));
	}
	
	public void saveToDirectory(ActionEvent event) {
		manager.execute(new FileMenuSaveAll(manager, model));
	}
}
