package com.github.tehfishey.spawnedit.controller.dialogs;

import javafx.scene.control.TextInputDialog;

	// Utility class for building various dialogs which prompt the user to input text.

public class TextDialogFactory {

	private TextDialogFactory() {}
	
	public static TextInputDialog nameInputDialog(boolean isDirectory, String defaultInput) {
		String title = new String();
		String header = new String();
		
		if (isDirectory) {
			title = "Name Directory";
			header = "Enter Directory Name";
		} else {
			title = "Name File";
			header = "Enter File Name";
		}
		
		TextInputDialog newDialog = new TextInputDialog(defaultInput);
		newDialog.setTitle(title);
		newDialog.setHeaderText(header);
		newDialog.setContentText("Name:");
		return newDialog;
	}
}
