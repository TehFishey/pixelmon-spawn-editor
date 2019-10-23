package com.github.tehfishey.spawnedit.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.github.tehfishey.spawnedit.model.Model;
import com.github.tehfishey.spawnedit.model.objects.PathTreeNode;
import com.github.tehfishey.spawnedit.model.objects.SpawnEntry;
import com.github.tehfishey.spawnedit.model.objects.PathTreeNode.NodeType;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
	private PathTreeNode pathTreeRoot;
	private TreeItem<PathTreeNode> treeViewRoot;
	
	@FXML private TreeView<PathTreeNode> fileTreeView;
			
	public MainTreeController(ControllerManager manager, Model model) {
	    this.manager = manager;
	    this.model = model;
	    this.pathTreeRoot = model.getFilePathTree();
	    this.treeViewRoot = nodeToTreeItem(pathTreeRoot);
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
	        private TextField textField;
	 
	        public TreeCellFactory() { }
	 
	        @Override
	        public void startEdit() {
	            super.startEdit();
	 
	            if (textField == null) {
	                createTextField();
	            }
	            setText(null);
	            textField.selectAll();
	        }
	 
	        @Override
	        public void cancelEdit() {
	            super.cancelEdit();
	            setText((String) getString());
	            setGraphic(getTreeItem().getGraphic());
	        }
	 
	        @Override
	        public void updateItem(PathTreeNode item, boolean empty) {
	            super.updateItem(item, empty);
	 
	            if (empty) {
	                setText(null);
	                setGraphic(null);
	            } else {
	                if (isEditing()) {
	                    if (textField != null) {
	                        textField.setText(getString());
	                    }
	                    setText(null);
	                    setGraphic(textField);
	                } else {
	                    setText(getString());
	                    setGraphic(getTreeItem().getGraphic());
	                }
	            }
	        }
	 
	        private void createTextField() {
	            textField = new TextField(getString());
	            textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
	 
	                @Override
	                public void handle(KeyEvent t) {
	                    if (t.getCode() == KeyCode.ENTER) {
	                        PathTreeNode update = getItem();
	                        update.setLocalPath(Paths.get(textField.getText()));
	                    	commitEdit(update);
	                    } else if (t.getCode() == KeyCode.ESCAPE) {
	                        cancelEdit();
	                    }
	                }
	            });
	        }
	 
	        private String getString() {
	            return getItem() == null ? "" : getItem().getLocalPath().toString();
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