package com.github.tehfishey.spawnedit.controller.helpers;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

import com.github.tehfishey.spawnedit.controller.ControllerManager;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory;
import com.github.tehfishey.spawnedit.controller.dialogs.TextDialogFactory;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory.ExceptionType;
import com.github.tehfishey.spawnedit.controller.dialogs.AlertDialogFactory.SaveType;
import com.github.tehfishey.spawnedit.model.Model;
import com.github.tehfishey.spawnedit.model.exceptions.BatchIOException;
import com.github.tehfishey.spawnedit.model.objects.PathTreeNode;
import com.github.tehfishey.spawnedit.model.objects.PathTreeNode.NodeType;
import com.google.common.io.Files;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.DirectoryChooser;

public class TreeCellFactory extends TreeCell<PathTreeNode> {
	ControllerManager manager;
	Model model;
	TreeDragboard dragboard;
	
	
	public TreeCellFactory(TreeView<PathTreeNode> fileTreeView, TreeDragboard dragboard, ControllerManager manager, Model model) {
		this.model = model;
		this.manager = manager;
		this.dragboard = dragboard;
		
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
		NodeType nodeType = getTreeItem().getValue().getNodeType();
		String name = getTreeItem().getValue().getLocalPath().toString();
		if (nodeType == NodeType.File) name = Files.getNameWithoutExtension(name);
		
		TextInputDialog dialog = TextDialogFactory.nameInputDialog(nodeType, name);
		Optional<String> newName = dialog.showAndWait();
		if (!newName.isEmpty()) name = newName.get();
		if (nodeType == NodeType.File) name += ".json";
		
		getTreeItem().getValue().setLocalPath(Paths.get(name));
		this.updateItem(getTreeItem().getValue(), false);
	}
	
	private void newDirectory() {
		PathTreeNode parentNode = getTreeItem().getValue();
		PathTreeNode newNode;
		TreeItem<PathTreeNode> newItem;
		
		TextInputDialog dialog = TextDialogFactory.nameInputDialog(NodeType.Directory, "");
		Optional<String> name = dialog.showAndWait();
		if (name.isEmpty()) return;
		
		newNode = parentNode.newChildDirectory(Paths.get(name.get()));
		newItem = new TreeItem<PathTreeNode>(newNode);
		newItem.setExpanded(true);
		this.getTreeItem().getChildren().add(newItem);
	}
	
	private void newFile() {
		PathTreeNode parentNode = getTreeItem().getValue();
		PathTreeNode newNode;
		TreeItem<PathTreeNode> newItem;
		
		TextInputDialog dialog = TextDialogFactory.nameInputDialog(NodeType.File, "");
		Optional<String> name = dialog.showAndWait();
		if (name.isEmpty()) return;
		
		newNode = parentNode.newChildFile(Paths.get(name.get() + ".json"), name.get());
		newItem = new TreeItem<PathTreeNode>(newNode);
		this.getTreeItem().getChildren().add(newItem);
	}
	
	private ContextMenu buildContextMenu(NodeType type) {
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
		
		if (type == NodeType.File)
			return new ContextMenu(rename, save, close);
		else if (type == NodeType.Directory)
			return new ContextMenu(newItem, rename, save, close);
		else
			return new ContextMenu(newItem);
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
			setContextMenu(buildContextMenu(getTreeItem().getValue().getNodeType()));
		}
	}

	private String getString() {
		if (getItem() == null) return "";
		else if (getItem().getNodeType() == NodeType.Root) return "Directory Tree";
		else return getItem().getLocalPath().toString();
	}
}
