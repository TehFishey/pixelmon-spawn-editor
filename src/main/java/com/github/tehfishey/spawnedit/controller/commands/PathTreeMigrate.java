package com.github.tehfishey.spawnedit.controller.commands;

import java.util.Objects;

import com.github.tehfishey.spawnedit.model.objects.PathTreeNode;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

	// Command which migrates a specified node (file or directory) in the model's/controller's virtual 
	// path tree to a specified new parent. Called from TreeCellFactory.

public class PathTreeMigrate extends Command {

	TreeView<PathTreeNode> treeView;
	TreeItem<PathTreeNode> draggedItem;
	TreeItem<PathTreeNode> oldParent;
	TreeItem<PathTreeNode> newParent;
	Integer oldIndex;
	Integer newIndex;
	Boolean success;
	
	public PathTreeMigrate(TreeView<PathTreeNode> treeView, TreeItem<PathTreeNode> draggedItem, TreeItem<PathTreeNode> dropTarget, Boolean success) {
		canUndo = true;
		this.treeView = treeView;
		this.draggedItem = draggedItem;
		this.oldParent = draggedItem.getParent();
		this.newParent = dropTarget;
		this.success = success;
	}
	
	@Override
	public void execute() {
		PathTreeNode draggedModelItem = draggedItem.getValue();
        oldIndex = draggedItem.getParent().getChildren().indexOf(draggedItem);
		
        if (!Objects.equals(oldParent, newParent)) {
        	oldParent.getChildren().remove(draggedItem);
        	
        	if (newParent.getValue().isFile()) {
        		newIndex = newParent.getParent().getChildren().indexOf(newParent) + 1;
        		newParent = newParent.getParent();
        		newParent.getChildren().add(newIndex, draggedItem);
        		draggedModelItem.migrate(newParent.getParent().getValue());
        	}
        	else {
        		newIndex = newParent.getChildren().size();
        		newParent.getChildren().add(newIndex, draggedItem);
        		draggedModelItem.migrate(newParent.getValue());
        	}
            success = true;
            treeView.getSelectionModel().select(draggedItem);
        }
	}

	@Override
	public void undo() {
		PathTreeNode draggedModelItem = draggedItem.getValue();
		
		newParent.getChildren().remove(draggedItem);
		oldParent.getChildren().add(oldIndex, draggedItem);
		draggedModelItem.migrate(oldParent.getValue());
	}

	@Override
	public void redo() {
		PathTreeNode draggedModelItem = draggedItem.getValue();
		
		oldParent.getChildren().remove(draggedItem);
		newParent.getChildren().add(newIndex, draggedItem);
		draggedModelItem.migrate(newParent.getValue());
		System.out.println("redo: moving " + draggedItem.getValue().toString() + " from " + oldParent.getValue().toString() + " to " + newParent.getValue().toString()); 
	}

}
