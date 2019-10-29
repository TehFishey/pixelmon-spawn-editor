package com.github.tehfishey.spawnedit.model.objects;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

public class PathTreeNode implements Iterable<PathTreeNode> {
	private final NodeType nodeType;
	private String fileId;
	private Path localPath;
	private Path absolutePath;
	private PathTreeNode parent;
	private ArrayList<PathTreeNode> children;
	
	private static enum NodeType {
		Root,
		Directory,
		File
	}
	
	public static PathTreeNode newPathTree() {
		PathTreeNode tree = new PathTreeNode(NodeType.Root, Paths.get(""), null);
		return tree;
	}
	
	public String getFileId() { return this.fileId; }
	public PathTreeNode getParent() { return parent; }
	public Path getLocalPath() { return localPath; }
	public Path getAbsolutePath() { return absolutePath; }
	public ArrayList<PathTreeNode> getChildren() { return children; }
	public boolean isRoot() {return (nodeType == NodeType.Root); }
	public boolean isDirectory() {return (nodeType == NodeType.Directory); }
	public boolean isFile() {return (nodeType == NodeType.File); }
	
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
		if ((this.isFile()) && (this.fileId == fileId)) return this;
		for (PathTreeNode child : children) {
			if ((child.isFile()) && (child.getFileId() == fileId)) {
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
		if (!this.isRoot() && !newParent.isFile()) {
			parent.getChildren().remove(this);
			this.parent = newParent;
			parent.getChildren().add(this);
			updateAbsolutePaths();
		}
	}
	
	public boolean contains(PathTreeNode node) {
		if (this.equals(node)) return true;
		
		if (!this.isFile()) {
			for (PathTreeNode subNode : this)
				if (subNode.equals(node)) return true;
		}
		
		return false;
		
	}
	
	public void remove() {
		this.getParent().getChildren().remove(this);
	}
	
	public HashMap<String, Path> toHashMap() {
		HashMap<String, Path> output = new HashMap<String, Path>();
		if (this.isFile()) {
			output.put(this.fileId, this.getAbsolutePath());
			return output;
		}
		else {
			for (PathTreeNode child : children) {
				if (child.isFile()) 
					output.put(child.fileId, child.getAbsolutePath());
				else {
					output.putAll(child.toHashMap());
				}
			}
			
			return output;
		}
	}
	
	@Override
	public Iterator<PathTreeNode> iterator() {
		final ArrayList<PathTreeNode> iterList = new ArrayList<PathTreeNode>();
		iterList.add(this);

        return new Iterator<PathTreeNode>() {
            @Override
            public boolean hasNext() {
                return !iterList.isEmpty();
            }

            @Override
            public PathTreeNode next() {
            	PathTreeNode node = iterList.get(0);
            	iterList.remove(0);
            	iterList.addAll(0, node.getChildren());
                return node;
            }
        };

	}
	
	private PathTreeNode(NodeType type, Path localPath, PathTreeNode parent) {
		this.nodeType = type;
		this.localPath = localPath;
		this.parent = parent;
		this.children = new ArrayList<PathTreeNode>();
		updateAbsolutePaths();
	}
	
	private void updateAbsolutePaths() {
		ArrayList<Path> pathList = new ArrayList<Path>();
		Path absolutePath = Paths.get("");
		
		pathList.add(localPath);
		if (!this.isRoot())
			parent.walkPathUpwards(pathList);
		
		for (int i = pathList.size() - 1; i >= 0; i--)
			absolutePath = absolutePath.resolve(pathList.get(i));
		
		this.absolutePath = absolutePath;
		
		if (!this.isFile()) {
			for (PathTreeNode child : children) {
				child.updateAbsolutePaths();
			}
		}
	}
	
	private void walkPathUpwards(ArrayList<Path> pathList) {
		pathList.add(localPath);
		if (!this.isRoot())
			parent.walkPathUpwards(pathList);
	}

	
}
