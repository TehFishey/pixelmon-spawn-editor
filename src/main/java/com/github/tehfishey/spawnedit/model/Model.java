package com.github.tehfishey.spawnedit.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.nio.file.Path;
import java.util.ArrayList;

import com.github.tehfishey.spawnedit.model.objects.PathTreeNode;
import com.github.tehfishey.spawnedit.model.objects.SpawnEntry;

	// Primary data model class; its purpose is to track and store all data objects which are 
	// currently being manipulated by the program. Contains a single FileManager component which handles
	// reading/writing operations. Maintains an array of SpawnEntry objects (which each represent a single Pixelmon
	// SpawnInfo), and virtual file path in the form of a single path tree (which tracks what data belongs where). 
	// Also registers/maintains listeners, which are notified when data is updated.

public class Model {
	private final FileManager fileManager;
	private final PropertyChangeSupport updateListenerSupport;
	private final ArrayList<SpawnEntry> spawnEntryCache;
	private final PathTreeNode filePathTree;	
	
	public Model() {
		spawnEntryCache = new ArrayList<SpawnEntry>();
		filePathTree = PathTreeNode.newPathTree();
		fileManager = new FileManager(this);
		updateListenerSupport = new PropertyChangeSupport(this);
	}
	
	public FileManager getFileManager() { return fileManager; }
	public ArrayList<SpawnEntry> getSpawnEntries() { return spawnEntryCache; }
	public PathTreeNode getFilePathTree() { return filePathTree; }

	public void addSpawnPath(String key, Path path) {
		this.filePathTree.put(path, key);
	}
	public ArrayList<SpawnEntry> removeSpawnPath(PathTreeNode node) {
		ArrayList<SpawnEntry> removalList = new ArrayList<SpawnEntry>();
		
		for (PathTreeNode subNode : node) {
			if (subNode.isFile()) {
				for (SpawnEntry entry : spawnEntryCache) {
					if (entry.getSpawnSetId() == subNode.getFileId())
						removalList.add(entry);
				}
			}
		}

		node.remove();
		this.removeSpawnEntries(removalList);
		notifyListeners("fileTreeUpdated", null, null);
		return removalList;
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
