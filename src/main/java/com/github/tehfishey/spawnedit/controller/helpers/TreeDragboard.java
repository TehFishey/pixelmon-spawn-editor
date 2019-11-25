package com.github.tehfishey.spawnedit.controller.helpers;

import com.github.tehfishey.spawnedit.model.objects.PathTreeNode;

import javafx.scene.control.TreeItem;

	// Custom/dummy 'Dragboard' object which helps facilitate drag & drop events in the main TreeView.
	
public class TreeDragboard {
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
