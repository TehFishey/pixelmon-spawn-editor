package com.github.tehfishey.spawnedit.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.nio.file.Path;
import java.util.ArrayList;

import com.github.tehfishey.spawnedit.model.objects.PathTreeNode;
import com.github.tehfishey.spawnedit.model.objects.SpawnEntry;

public class Model {
	private final FileManager fileManager;
	private final PropertyChangeSupport updateListenerSupport;
	private final ArrayList<SpawnEntry> spawnEntryCache;
	private final PathTreeNode filePathTree;
	private Path spawnPathRoot;
	
	
	public Model() {
		fileManager = new FileManager(this);
		updateListenerSupport = new PropertyChangeSupport(this);
		spawnEntryCache = new ArrayList<SpawnEntry>();
		filePathTree = PathTreeNode.newPathTree();
	}
	
	public FileManager getFileManager() { return fileManager; }
	public ArrayList<SpawnEntry> getSpawnEntries() { return spawnEntryCache; }
	public PathTreeNode getFilePathTree() { return filePathTree; }
	public Path getSpawnPathRoot() { return spawnPathRoot; }
	public void setSpawnPathRoot(Path spawnPathRoot) { this.spawnPathRoot = spawnPathRoot; }

	public void addSpawnPath(String key, Path path) {
		this.filePathTree.put(path, key);
		updateListenerSupport.firePropertyChange("spawnPathsChanged", null, filePathTree);
	}
	
	public void addSpawnEntries(ArrayList<SpawnEntry> spawnEntries) { 
		this.spawnEntryCache.addAll(spawnEntries); 
		updateListenerSupport.firePropertyChange("spawnEntriesAdded", null, spawnEntries);
	}
	
	public void removeSpawnEntries(ArrayList<SpawnEntry> spawnEntries) { 
		this.spawnEntryCache.removeAll(spawnEntries); 
		updateListenerSupport.firePropertyChange("spawnEntriesRemoved", null, spawnEntries);
	}
	
	
	public void registerListener(PropertyChangeListener listener) {
		updateListenerSupport.addPropertyChangeListener(listener);
		
	}
}
