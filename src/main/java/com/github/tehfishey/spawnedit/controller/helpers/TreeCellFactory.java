package com.github.tehfishey.spawnedit.controller.helpers;

import java.util.Objects;

import com.github.tehfishey.spawnedit.controller.ControllerManager;
import com.github.tehfishey.spawnedit.controller.commands.PathTreeCloseFile;
import com.github.tehfishey.spawnedit.controller.commands.PathTreeMigrate;
import com.github.tehfishey.spawnedit.controller.commands.PathTreeNewDirectory;
import com.github.tehfishey.spawnedit.controller.commands.PathTreeNewFile;
import com.github.tehfishey.spawnedit.controller.commands.PathTreeRename;
import com.github.tehfishey.spawnedit.controller.commands.PathTreeSaveFile;
import com.github.tehfishey.spawnedit.model.Model;
import com.github.tehfishey.spawnedit.model.objects.PathTreeNode;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

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
	    if (draggedModelItem.isRoot()) return;

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
	    else if (draggedTreeItem.getValue().isRoot()) {
	    	dragboard.clear();
	    	return;
	    }
	    else
	    	 event.acceptTransferModes(TransferMode.MOVE);

	    event.consume();
	}
	
	private void dragDropped(DragEvent event, TreeCell<PathTreeNode> treeCell, TreeView<PathTreeNode> treeView) {
		
		Boolean success = false;
        if (!dragboard.hasItems()) return;
        
        if (!dragboard.getModelItem().contains(treeCell.getItem()))
        	manager.execute(new PathTreeMigrate(treeView, dragboard.getTreeItem(), treeCell.getTreeItem(), success));
        
        dragboard.clear();
        event.setDropCompleted(success);
        event.consume();
    }
	
	private void saveItem() {
		manager.execute(new PathTreeSaveFile(model, manager, getItem()));
    }

	private void closeItem() {
		manager.execute(new PathTreeCloseFile(model, getItem()));
	}
	
	private void renameItem() {
		manager.execute(new PathTreeRename(this, getItem()));
	}
	
	private void newDirectory() {
		manager.execute(new PathTreeNewDirectory(getTreeItem()));
	}
	
	private void newFile() {
		manager.execute(new PathTreeNewFile(getTreeItem()));
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
		
		if (getItem().isFile())
			return new ContextMenu(rename, save, close);
		else if (getItem().isDirectory())
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
			setContextMenu(buildContextMenu());
		}
	}

	private String getString() {
		if (getItem() == null) return "";
		else if (getItem().isRoot()) return "Directory Tree";
		else return getItem().getLocalPath().toString();
	}
}
