package com.github.tehfishey.spawnedit.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.nio.file.Path;
import java.util.ArrayList;

import com.github.tehfishey.spawnedit.model.objects.PathTreeNode;
import com.github.tehfishey.spawnedit.model.objects.PathTreeNode.NodeType;
import com.github.tehfishey.spawnedit.model.objects.SpawnEntry;

public class Model {
	private final FileManager fileManager;
	private final PropertyChangeSupport updateListenerSupport;
	private final ArrayList<SpawnEntry> spawnEntryCache;
	private final PathTreeNode filePathTree;	
	
	public Model() {
		fileManager = new FileManager(this);
		updateListenerSupport = new PropertyChangeSupport(this);
		spawnEntryCache = new ArrayList<SpawnEntry>();
		filePathTree = PathTreeNode.newPathTree();
	}
	
	public FileManager getFileManager() { return fileManager; }
	public ArrayList<SpawnEntry> getSpawnEntries() { return spawnEntryCache; }
	public PathTreeNode getFilePathTree() { return filePathTree; }

	public void addSpawnPath(String key, Path path) {
		this.filePathTree.put(path, key);
	}
	public void removeSpawnPath(PathTreeNode node) {
		ArrayList<SpawnEntry> removalList = new ArrayList<SpawnEntry>();
		
		for (PathTreeNode subNode : node) {
			if (subNode.getNodeType() == NodeType.File) {
				for (SpawnEntry entry : spawnEntryCache) {
					if (entry.getSpawnSetId() == subNode.getFileId())
						removalList.add(entry);
				}
			}
		}

		PathTreeNode.remove(node);
		this.removeSpawnEntries(removalList);
		notifyListeners("fileTreeUpdated", null, null);
	}
	
	public void addSpawnEntries(ArrayList<SpawnEntry> spawnEntries) { 
		this.spawnEntryCache.addAll(spawnEntries); 
		notifyListeners("spawnEntriesAdded", null, spawnEntries);
	}
	
	public void removeSpawnEntries(ArrayList<SpawnEntry> spawnEntries) { 
		this.spawnEntryCache.removeAll(spawnEntries); 
		notifyListeners("spawnEntriesRemoved", null, spawnEntries);
	}
	
	public void notifyListeners(String propertyName, Object value1, Object value2) {
		updateListenerSupport.firePropertyChange(propertyName, value1, value2);
	}
	
	public void registerListener(PropertyChangeListener listener) {
		updateListenerSupport.addPropertyChangeListener(listener);
		
	}
}
