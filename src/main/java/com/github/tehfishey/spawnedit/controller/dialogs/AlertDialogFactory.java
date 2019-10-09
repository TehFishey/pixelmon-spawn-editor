package com.github.tehfishey.spawnedit.controller.dialogs;

import java.nio.file.Path;
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
	
	public static enum SaveType {
		SaveAll,
		SaveDirectory
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
			descriptionText = "The following file(s) were either formatted improperly or did not contain Pokemon SpawnSet information:\n\n";
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
		
		if (type.equals(SaveType.SaveAll)) {
			header = "Save All";
			bodyText = "This action will permanently overwrite all files currently open in Pixelmon Spawn Editor. Do you wish to continue?";
		}
		else if (type.equals(SaveType.SaveDirectory)) {
			header = "Save to Directory";
			bodyText = "This action can potentially overwrite files in the selected directory or its sub-directories. Do you wish to continue?";
		}

		Alert newAlert = new Alert(AlertType.CONFIRMATION, bodyText, ButtonType.YES, ButtonType.CANCEL);
		newAlert.setTitle("Confirm Save All");
		newAlert.setHeaderText(header);
		return newAlert;
	}
}
