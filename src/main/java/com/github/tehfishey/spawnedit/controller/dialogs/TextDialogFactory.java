package com.github.tehfishey.spawnedit.controller.dialogs;

import com.github.tehfishey.spawnedit.model.objects.PathTreeNode.NodeType;

import javafx.scene.control.TextInputDialog;

public class TextDialogFactory {

	private TextDialogFactory() {}
	
	public static TextInputDialog nameInputDialog(NodeType type, String defaultInput) {
		String title = new String();
		String header = new String();
		
		if (type == NodeType.Directory) {
			title = "Name Directory";
			header = "Enter Directory Name";
		}
		else if (type == NodeType.File) {
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
