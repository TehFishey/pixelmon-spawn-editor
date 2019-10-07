package com.github.tehfishey.spawnedit.controller.dialogs;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertDialogFactory {
	
	private AlertDialogFactory() {}
	
	public static enum ExceptionType {
		BatchIOException,
		BatchJsonException;
	}
	
	public static Alert createLoadAlert(ArrayList<String> fileNames, ExceptionType exceptionType) {
		String text = new String();
		String explanation = new String();
		String fileNameText = new String();
		
		if (exceptionType.equals(ExceptionType.BatchIOException))
			explanation = "IOException\n\nThe following file(s) could not be loaded:\n\n";
		else if (exceptionType.equals(ExceptionType.BatchJsonException))
			explanation = "JsonParseException\n\nThe following file(s) could not be parsed:\n\n";
		
		for (String key : fileNames) fileNameText += (key + "\n");
		
		text = explanation + fileNameText;
		
		Alert newAlert = new Alert(AlertType.WARNING, text, ButtonType.OK);
		newAlert.setTitle("Load Error");
		return newAlert;
	}
	
}
