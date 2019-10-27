package com.github.tehfishey.spawnedit.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.nio.file.Path;
import java.util.Objects;

import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory.ExceptionType;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory.SaveType;
import com.github.tehfishey.spawnedit.controller.helpers.TreeDragboard;
import com.github.tehfishey.spawnedit.controller.helpers.TreeCellFactory;
import com.github.tehfishey.spawnedit.model.Model;
import com.github.tehfishey.spawnedit.model.exceptions.BatchIOException;
import com.github.tehfishey.spawnedit.model.objects.PathTreeNode;
import com.github.tehfishey.spawnedit.model.objects.PathTreeNode.NodeType;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
	
public class MainTreeController {
	private final Model model;
	private final ControllerManager manager;
	private final PropertyChangeListener modelListener;
	private final TreeDragboard dragboard;
	private PathTreeNode pathTreeRoot;
	private TreeItem<PathTreeNode> treeViewRoot;
	
	@FXML private TreeView<PathTreeNode> fileTreeView;
			
	public MainTreeController(ControllerManager manager, Model model) {
	    this.manager = manager;
	    this.model = model;
	    this.pathTreeRoot = model.getFilePathTree();
	    this.treeViewRoot = nodeToTreeItem(pathTreeRoot);
	    this.dragboard = new TreeDragboard();
	    this.modelListener = new PropertyChangeListener() {
        	@Override
        	public void propertyChange(PropertyChangeEvent evt) {
        		switch (evt.getPropertyName()) {
        		case "fileTreeUpdated" :
        			treeViewRoot = populateTree(pathTreeRoot, treeViewRoot);
        			System.out.println("populating...");
        		}
        	}
        };
        model.registerListener(modelListener);
	}
			
	public void initialize() {
		fileTreeView.setEditable(true);
		fileTreeView.setCellFactory(new Callback<TreeView<PathTreeNode>,TreeCell<PathTreeNode>>(){
            @Override
            public TreeCell<PathTreeNode> call(TreeView<PathTreeNode> p) {
                return new TreeCellFactory(fileTreeView, dragboard, manager, model);
            }
        });
		treeViewRoot = populateTree(pathTreeRoot, treeViewRoot);
		fileTreeView.setRoot(treeViewRoot);
	}
	
	private TreeItem<PathTreeNode> nodeToTreeItem(PathTreeNode node) {
		TreeItem<PathTreeNode> item = new TreeItem<PathTreeNode>(node);
		item.setExpanded(true);
		return item;
	}
	
	private TreeItem<PathTreeNode> populateTree(PathTreeNode rootNode, TreeItem<PathTreeNode> rootItem) {
		rootItem.getChildren().removeAll(rootItem.getChildren());
		
		for (PathTreeNode node : rootNode.getChildren()) {
			TreeItem<PathTreeNode> item = nodeToTreeItem(node);
			populateTree(node, item);
			rootItem.getChildren().add(item);
		}
		return rootItem;
	}
}