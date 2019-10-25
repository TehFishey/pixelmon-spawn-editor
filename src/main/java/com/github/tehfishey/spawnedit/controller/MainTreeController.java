package com.github.tehfishey.spawnedit.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.github.tehfishey.spawnedit.model.Model;
import com.github.tehfishey.spawnedit.model.objects.PathTreeNode;
import com.github.tehfishey.spawnedit.model.objects.SpawnEntry;
import com.github.tehfishey.spawnedit.model.objects.PathTreeNode.NodeType;

import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.util.Callback;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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
        		case "spawnFilesUpdated" :
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
		for (PathTreeNode node : rootNode.getChildren()) {
			TreeItem<PathTreeNode> item = nodeToTreeItem(node);
			populateTree(node, item);
			rootItem.getChildren().add(item);
		}
		return rootItem;
	}
	
	private final class TreeCellFactory extends TreeCell<PathTreeNode> {

		public TreeCellFactory() {
			setOnDragEntered(e -> {
	            System.out.println(" Entered ");
	            e.consume();
	        });
			setOnDragDetected((MouseEvent event) -> { dragDetected(event, this, fileTreeView); });
			setOnDragOver((DragEvent event) -> { dragOver(event, this, fileTreeView); });
			setOnDragDropped((DragEvent event) -> { dragDropped(event, this, fileTreeView); });
	        setOnDragDone(e -> { e.consume(); });
	        setOnDragExited(e -> { e.consume(); });
			
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
		
		@Override
		public void updateItem(PathTreeNode item, boolean empty) {
			super.updateItem(item, empty);
	 
			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				setText(getString());
				setGraphic(getTreeItem().getGraphic());
			}
		}

		private String getString() {
			return getItem() == null ? "" : getItem().getLocalPath().toString();
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


	/*
	public class CopyOfTreeViewSample extends Application {

	    private TreeView<Path> treeView;

	    public static void main(String[] args) {
	        launch(args);
	    }

	    @Override
	    public void start(Stage stage) {
	        stage.setTitle("Sample");
	        stage.setWidth(300);
	        stage.setHeight(500);

	        VBox vbox = new VBox();
	        vbox.setPadding(new Insets(20));

	        TreeItem<Path> root = new SimpleFileTreeItem(
	                Paths.get(System.getProperty("user.home")));
	        root.setExpanded(true);
	        treeView = new TreeView<Path>(root);

	        treeView.setCellFactory(treeView -> new TreeCell<Path>() {
	            @Override
	            public void updateItem(Path path, boolean empty) {
	                super.updateItem(path, empty);
	                if (empty) {
	                    setText(null);
	                } else {
	                    setText(path.getFileName().toString());
	                }
	            }
	        });

	        Button b = new Button("Change");
	        b.disableProperty().bind(Bindings.isNull(treeView.getSelectionModel().selectedItemProperty()));

	        b.setOnAction(event ->  {
	            Path selectedPath = treeView.getSelectionModel().getSelectedItem().getValue() ;
	            // do something with selectedPath...
	            System.out.println(selectedPath);
	        });

	        vbox.getChildren().addAll(treeView, b);
	        vbox.setSpacing(10);

	        Scene scene = new Scene(vbox);

	        stage.setScene(scene);
	        stage.show();
	    }

	    public class SimpleFileTreeItem extends TreeItem<Path> {

	        private boolean isFirstTimeChildren = true;
	        private boolean isFirstTimeLeaf = true;
	        private boolean isLeaf;

	        public boolean isDirectory() {
	            return Files.isDirectory(getValue());
	        }


	        public SimpleFileTreeItem(Path f) {
	            super(f);
	        }

	        @Override
	        public ObservableList<TreeItem<Path>> getChildren() {
	            if (isFirstTimeChildren) {
	                isFirstTimeChildren = false;

	                super.getChildren().setAll(buildChildren());
	            }
	            return super.getChildren();
	        }

	        @Override
	        public boolean isLeaf() {
	            if (isFirstTimeLeaf) {
	                isFirstTimeLeaf = false;
	                isLeaf = Files.exists(getValue()) && ! Files.isDirectory(getValue());
	            }
	            return isLeaf;
	        }


	        private ObservableList<TreeItem<Path>> buildChildren() {
	            if (Files.isDirectory(getValue())) {
	                try {

	                    return Files.list(getValue())
	                            .map(SimpleFileTreeItem::new)
	                            .collect(Collectors.toCollection(() -> FXCollections.observableArrayList()));

	                } catch (IOException e) {
	                    e.printStackTrace();
	                    return FXCollections.emptyObservableList();
	                } 
	            }

	            return FXCollections.emptyObservableList();
	        }
	    }
	}
	*/