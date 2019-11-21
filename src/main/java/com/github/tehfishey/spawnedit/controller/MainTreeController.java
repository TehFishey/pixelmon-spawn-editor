package com.github.tehfishey.spawnedit.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.github.tehfishey.spawnedit.controller.helpers.TreeDragboard;
import com.github.tehfishey.spawnedit.controller.helpers.TreeCellFactory;
import com.github.tehfishey.spawnedit.model.Model;
import com.github.tehfishey.spawnedit.model.objects.PathTreeNode;

import javafx.util.Callback;
import javafx.fxml.FXML;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
	
	// Controller for elements in the MainTree.fxml file.
	// Governs the GUI for viewing & manipulating the model's virtual path tree. Individual tree entries (and their respective Commands)
	// are instantiated in the TreeCellFactory helper class. Maintains a custom 'dragboard' to facilitate cut/paste (migration) operations
	// within the tree, as well as references to the Model and ControllerManager and a listener that tracks path updates in the Model.

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