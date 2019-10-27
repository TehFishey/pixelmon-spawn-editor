package com.github.tehfishey.spawnedit.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.nio.file.Path;
import java.util.Objects;

import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory.ExceptionType;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory.SaveType;
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
	private final CustomDragboard dragboard;
	private PathTreeNode pathTreeRoot;
	private TreeItem<PathTreeNode> treeViewRoot;
	
	@FXML private TreeView<PathTreeNode> fileTreeView;
			
	public MainTreeController(ControllerManager manager, Model model) {
	    this.manager = manager;
	    this.model = model;
	    this.pathTreeRoot = model.getFilePathTree();
	    this.treeViewRoot = nodeToTreeItem(pathTreeRoot);
	    this.dragboard = new CustomDragboard();
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
                return new TreeCellFactory();
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
	
	private final class TreeCellFactory extends TreeCell<PathTreeNode> {

		public TreeCellFactory() {
			setOnDragDetected((MouseEvent event) -> { dragDetected(event, this, fileTreeView); });
			setOnDragOver((DragEvent event) -> { dragOver(event, this, fileTreeView); });
			setOnDragDropped((DragEvent event) -> { dragDropped(event, this, fileTreeView); });
		}
	 
		private void dragDetected(MouseEvent event, TreeCell<PathTreeNode> treeCell, TreeView<PathTreeNode> treeView) {
			// JavaFX's in-built dragboard works by serializing & deserializing objects; we can't use it to copy our model references. 
		    // It must be initialized for the drag event to execute, so we pass it a dummy value and store the reference in a variable.
			TreeItem<PathTreeNode> draggedTreeItem = treeCell.getTreeItem();
			PathTreeNode draggedModelItem = draggedTreeItem.getValue();
		    if (draggedModelItem.getNodeType() == NodeType.Root) return;

		    Dragboard db = treeCell.startDragAndDrop(TransferMode.MOVE);
		    ClipboardContent content = new ClipboardContent();
		    content.putString(draggedTreeItem.toString());
		    db.setContent(content);
		    db.setDragView(treeCell.snapshot(null, null));
		    dragboard.setTreeItem(draggedTreeItem);
		    dragboard.setModelItem(draggedModelItem);
		    
		    event.consume();
		}
		
		private void dragOver(DragEvent event, TreeCell<PathTreeNode> treeCell, TreeView<PathTreeNode> treeView) {
			if (!dragboard.hasItems()) return;
			
		    TreeItem<PathTreeNode> draggedTreeItem = dragboard.getTreeItem();
		    TreeItem<PathTreeNode> thisItem = treeCell.getTreeItem();
		    
		    
		    if (draggedTreeItem == null || thisItem == null || thisItem == draggedTreeItem) 
		    	return;
		    else if (draggedTreeItem.getValue().getNodeType() == NodeType.Root) {
		    	dragboard.clear();
		    	return;
		    }
		    else
		    	 event.acceptTransferModes(TransferMode.MOVE);

		    event.consume();
		}
		
		private void dragDropped(DragEvent event, TreeCell<PathTreeNode> treeCell, TreeView<PathTreeNode> treeView) {
	        boolean success = false;
	        if (!dragboard.hasItems()) return;
	        
	        TreeItem<PathTreeNode> draggedTreeItem = dragboard.getTreeItem();
	        PathTreeNode draggedModelItem = dragboard.getModelItem();
	        TreeItem<PathTreeNode> draggedItemParent = draggedTreeItem.getParent();
	        TreeItem<PathTreeNode> dropTarget = treeCell.getTreeItem();
	        
	        if (!Objects.equals(draggedItemParent, dropTarget)) {
	        	draggedItemParent.getChildren().remove(draggedTreeItem);
	        	
	        	if (dropTarget.getValue().getNodeType() == NodeType.File) {
	        		int indexInParent = dropTarget.getParent().getChildren().indexOf(dropTarget);
	        		dropTarget.getParent().getChildren().add(indexInParent + 1, draggedTreeItem);
	        		draggedModelItem.migrate(dropTarget.getParent().getValue());
	        	}
	        	else {
	        		int childSize = dropTarget.getChildren().size();
	        		dropTarget.getChildren().add(childSize, draggedTreeItem);
	        		draggedModelItem.migrate(dropTarget.getValue());
	        	}
	            success = true;
	            treeView.getSelectionModel().select(draggedTreeItem);
	        }

	        dragboard.clear();
	        event.setDropCompleted(success);
	        event.consume();
	    }
		
		private void saveItem() {
			PathTreeNode node = getTreeItem().getValue();
			if (node.getNodeType() == NodeType.Root) return;
			
			DirectoryChooser directoryChooser = manager.getDirectoryChooser();
			directoryChooser.setTitle("Save");
    		Path directory = directoryChooser.showDialog(manager.getRoot().getScene().getWindow()).toPath();
    		
    		if (directory != null)  {
    			Alert confirmation = AlertDialogFactory.saveWarningAlert(node.getNodeType() == NodeType.Directory ? SaveType.SaveDirectory : SaveType.SaveFile );
    			confirmation.showAndWait();
    			
    			if (confirmation.getResult() == ButtonType.YES) {
    				try { 
    					model.getFileManager().saveFile(directory, node); 
    				} catch (BatchIOException e) {
    					Alert alert = AlertDialogFactory.saveExceptionAlert(e.getExceptedPaths(), ExceptionType.BatchIOException);
    					alert.show();
    				}
    			}
    		manager.setChooserDirectory(directory.getParent().toFile());
    		}
        }

		private void closeItem() {
			Alert confirmation = AlertDialogFactory.closeWarningAlert();
			confirmation.showAndWait();
			if (confirmation.getResult() == ButtonType.YES)
				model.removeSpawnPath(getTreeItem().getValue());
		}
		
		private void renameItem() {
			// Open dialog window prompting new name, return string for name
				// pass in GetItem.getLocalPath.ToString; if NodeType is File, cut the .json from the end
			// if NodeType is File and new path doesn't end in .json, append .json to the end of new path
			// set local path = new name
			// recalculate paths
			// (be sure to include input validation for characters in dialog.
		}
		
		private void newDirectory() {
			// open dialog window prompting new name, return string for name
			// create new directory at parent with local path = string
		}
		
		private void newFile() {
			// open dialog window prompting new name, return string for name
			// create new file at parent with local path = string
		}
		
		private ContextMenu buildContextMenu() {
			MenuItem newDirectory = new MenuItem("Directory");
			MenuItem newFile = new MenuItem("File");
			MenuItem rename = new MenuItem("Rename");
			MenuItem save = new MenuItem("Save");
			MenuItem close = new MenuItem("Close");
			Menu newItem = new Menu("New...", null, newFile, newDirectory);
			
			newDirectory.setOnAction(new EventHandler() {
	            public void handle(Event t) { newDirectory(); }
	        });
			newFile.setOnAction(new EventHandler() {
	            public void handle(Event t) { newFile(); }
	        });
			rename.setOnAction(new EventHandler() {
	            public void handle(Event t) { renameItem(); }
	        });
			save.setOnAction(new EventHandler() {
	            public void handle(Event t) { saveItem(); }
	        });
			close.setOnAction(new EventHandler() {
	            public void handle(Event t) { closeItem(); }
	        });
			
			if (getItem().getNodeType() == NodeType.File)
				return new ContextMenu(rename, save, close);
			else
				return new ContextMenu(newItem, rename, save, close);
		}
		
		@Override
		public void updateItem(PathTreeNode item, boolean empty) {
			super.updateItem(item, empty);
	 
			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				setText(getString());
				setGraphic(getTreeItem().getGraphic());
				if (item.getNodeType() == NodeType.Directory) setContextMenu(buildContextMenu());
				else if (item.getNodeType() == NodeType.File) setContextMenu(buildContextMenu());
			}
		}

		private String getString() {
			if (getItem() == null) return "";
			else if (getItem().getNodeType() == NodeType.Root) return "Directory Tree";
			else return getItem().getLocalPath().toString();
		}
	}
	
	private class CustomDragboard {
		TreeItem<PathTreeNode> draggedTreeItem;
		PathTreeNode draggedModelItem;
		
		public TreeItem<PathTreeNode> getTreeItem() { return draggedTreeItem; }
		public void setTreeItem(TreeItem<PathTreeNode> draggedTreeItem) { this.draggedTreeItem = draggedTreeItem; }
		public PathTreeNode getModelItem() { return draggedModelItem; }
		public void setModelItem(PathTreeNode draggedModelItem) { this.draggedModelItem = draggedModelItem; }
		
		public boolean hasItems() {
			return (!(draggedTreeItem == null) && !(draggedModelItem == null));
		}
		
		public void clear() {
			draggedTreeItem = null;
			draggedModelItem = null;
		}
		
	}
}