package com.github.tehfishey.spawnedit.model.objects;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class PathTreeNode {
	private final NodeType nodeType;
	private String fileId;
	private Path localPath;
	private Path absolutePath;
	private PathTreeNode parent;
	private ArrayList<PathTreeNode> children;
	
	public static enum NodeType {
		Root,
		Directory,
		File
	}
	
	public static PathTreeNode newPathTree() {
		PathTreeNode tree = new PathTreeNode(NodeType.Root, Paths.get(""), null);
		return tree;
	}
	
	public NodeType getNodeType() { return this.nodeType; }
	public String getFileId() { return this.fileId; }
	public PathTreeNode getParent() { return parent; }
	public Path getLocalPath() { return localPath; }
	public Path getAbsolutePath() { return absolutePath; }
	public ArrayList<PathTreeNode> getChildren() { return children; }
	
	public void setFileId(String fileId) { this.fileId = fileId; }
	public void setLocalPath(Path localPath) {
		this.localPath = localPath;
		updateAbsolutePaths();
	}
	
	public PathTreeNode newChildDirectory(Path localPath) {
		PathTreeNode child = new PathTreeNode(NodeType.Directory, localPath, this);
		this.children.add(child);
		return child;
	}
	
	public PathTreeNode newChildFile(Path localPath, String fileId) {
		PathTreeNode child = new PathTreeNode(NodeType.File, localPath, this);
		child.setFileId(fileId);
		this.children.add(child);
		return child;
	}
	
	public void put(Path filePath, String fileId) {
		if (nodeType != NodeType.File) {
			Iterator<Path> it = filePath.iterator();
			PathTreeNode position = this;
			
			while (it.hasNext()) {
				Path nextPath = it.next();
				boolean pathExists = false;
				
				for (PathTreeNode child : position.getChildren()) {
					if (child.getLocalPath().equals(nextPath)) {
						position = child;
						pathExists = true;
					}
				}
				
				if (!pathExists) {
					if (it.hasNext()) position = position.newChildDirectory(nextPath); 
					else position = position.newChildFile(nextPath, fileId);
				}
			}
		}
	}
	
	public PathTreeNode get(String fileId) {
		if ((this.nodeType == NodeType.File) && (this.fileId == fileId)) return this;
		for (PathTreeNode child : children) {
			if ((child.getNodeType() == NodeType.File) && (child.getFileId() == fileId)) {
				return child;
			}
			else {
				PathTreeNode iterOutput;
				iterOutput = child.get(fileId);
				if (!(iterOutput == null))
					return iterOutput;
			}
		}
		return null;
	}
	
	public void migrate(PathTreeNode newParent) {
		if ((nodeType != NodeType.Root) && (newParent.getNodeType() != NodeType.File)) {
			parent.getChildren().remove(this);
			this.parent = newParent;
			parent.getChildren().add(this);
			updateAbsolutePaths();
		}
	}
	
	public HashMap<String, Path> toHashMap() {
		HashMap<String, Path> output = new HashMap<String, Path>();
		if (this.nodeType == NodeType.File) {
			output.put(this.fileId, this.getAbsolutePath());
			return output;
		}
		else {
			for (PathTreeNode child : children) {
				if (child.getNodeType() == NodeType.File) 
					output.put(child.fileId, child.getAbsolutePath());
				else {
					output.putAll(child.toHashMap());
				}
			}
			
			return output;
		}
	}
	
	private PathTreeNode(NodeType type, Path localPath, PathTreeNode parent) {
		this.nodeType = type;
		this.localPath = localPath;
		this.parent = parent;
		this.children = new ArrayList<PathTreeNode>();
		updateAbsolutePaths();
	}
	
	private void updateAbsolutePaths() {
		if (nodeType != NodeType.File) {
			for (PathTreeNode child : children) {
				child.updateAbsolutePaths();
			}
		}
		
		ArrayList<Path> pathList = new ArrayList<Path>();
		Path absolutePath = Paths.get("");
		
		pathList.add(localPath);
		if (nodeType != NodeType.Root)
			parent.walkPathUpwards(pathList);
		
		for (int i = pathList.size() - 1; i >= 0; i--)
			absolutePath = absolutePath.resolve(pathList.get(i));
		
		this.absolutePath = absolutePath;
	}
	
	private void walkPathUpwards(ArrayList<Path> pathList) {
		pathList.add(localPath);
		if (nodeType != NodeType.Root)
			parent.walkPathUpwards(pathList);
	}
}
