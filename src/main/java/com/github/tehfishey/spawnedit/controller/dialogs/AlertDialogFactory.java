package com.github.tehfishey.spawnedit.controller.dialogs;

import java.nio.file.Path;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

	// Utility class for building various Alert/warning dialogs.

public class AlertDialogFactory {
	
	private AlertDialogFactory() {}
	
	public static enum ExceptionType {
		BatchIOException,
		BatchJsonException,
		BatchIDException;
	}
	
	public static enum SaveType {
		SaveFile,
		SaveDirectory,
		SaveAll;
	}
	
	public static Alert loadExceptionAlert(ArrayList<Path> filePaths, ExceptionType exceptionType) {
		String bodyText = new String();
		String header = new String();
		String descriptionText = new String();
		String fileNameText = new String();
		
		if (exceptionType.equals(ExceptionType.BatchIOException)) {
			header = "Warning: IOException(s)";
			descriptionText = "The following file(s) could not be loaded from the file system:\n\n";
		}
		else if (exceptionType.equals(ExceptionType.BatchJsonException)) {
			header = "Warning: JsonParseException(s)";
			descriptionText = "The following file(s) were either formatted improperly or did not contain Pixelmon SpawnSet information:\n\n";
		}
		else if (exceptionType.equals(ExceptionType.BatchIDException)) {
			header = "Warning: DuplicateIDException(s)";
			descriptionText = "The following file(s) could not be loaded because they contain duplicate SpawnSet ids:\n\n";
		}
		
		for (Path path : filePaths) fileNameText += (path.getFileName().toString() + "\n");
		bodyText = descriptionText + fileNameText;
		
		Alert newAlert = new Alert(AlertType.WARNING, bodyText, ButtonType.OK);
		newAlert.setTitle("Load Error");
		newAlert.setHeaderText(header);
		return newAlert;
	}
	
	public static Alert saveExceptionAlert(ArrayList<Path> filePaths, ExceptionType exceptionType) {
		String bodyText = new String();
		String header = new String();
		String descriptionText = new String();
		String fileNameText = new String();
		
		if (exceptionType.equals(ExceptionType.BatchIOException)) {
			header = "Warning: IOException(s)";
			descriptionText = "The following file(s) could not be saved to the file system:\n\n";
		}
		
		for (Path path : filePaths) fileNameText += (path.getFileName().toString() + "\n");
		bodyText = descriptionText + fileNameText;
		
		Alert newAlert = new Alert(AlertType.WARNING, bodyText, ButtonType.OK);
		newAlert.setTitle("Save Error");
		newAlert.setHeaderText(header);
		return newAlert;
	}
	
	public static Alert saveWarningAlert(SaveType type) {
		String bodyText = new String();
		String header = new String();
		String title = new String();
		
		if (type.equals(SaveType.SaveAll)) {
			header = "Save All";
			bodyText = "You are about to save all loaded files and directories to the selected location. If the location is already populated, one or more files may be overwritten. Do you wish to continue?";
			title = "Confirm Save All";
		}
		if (type.equals(SaveType.SaveDirectory)) {
			header = "Save Directory";
			bodyText = "You are about to save this directory to the selected location. If the location is already populated, multiple files may be overwritten. Do you wish to continue?";
			title = "Confirm Save Directory";
		}
		if (type.equals(SaveType.SaveFile)) {
			header = "Save File";
			bodyText = "You are about to save this file to the selected location. If the location already contains a file with this name, it will be overwritten. Do you wish to continue?";
			title = "Confirm Save File";
		}

		Alert newAlert = new Alert(AlertType.CONFIRMATION, bodyText, ButtonType.YES, ButtonType.CANCEL);
		newAlert.setTitle(title);
		newAlert.setHeaderText(header);
		return newAlert;
	}
	
	public static Alert closeWarningAlert() {
		String bodyText = new String();
		String header = new String();
		

		bodyText = "You are about to close the selected file. Any unsaved changes will be lost.";
		header = "Confirm Close";

		Alert newAlert = new Alert(AlertType.CONFIRMATION, bodyText, ButtonType.YES, ButtonType.CANCEL);
		newAlert.setTitle("Close File");
		newAlert.setHeaderText(header);
		return newAlert;
	}
}
