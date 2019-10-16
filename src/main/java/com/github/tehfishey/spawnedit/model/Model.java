package com.github.tehfishey.spawnedit.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class Model {
	private final FileManager fileManager;
	private final PropertyChangeSupport modelUpdateSupport;
	private final ArrayList<SpawnEntry> spawnEntries;
	private final HashMap<String, Path> spawnPaths;
	private Path spawnPathRoot;
	
	
	public Model() {
		fileManager = new FileManager(this);
		modelUpdateSupport = new PropertyChangeSupport(this);
		spawnEntries = new ArrayList<SpawnEntry>();
		spawnPaths = new HashMap<String, Path>();
	}
	
	public FileManager getFileManager() { return fileManager; }
	public ArrayList<SpawnEntry> getSpawnEntries() { return spawnEntries; }
	public HashMap<String, Path> getSpawnPaths() { return spawnPaths; }
	public Path getSpawnPathRoot() { return spawnPathRoot; }
	public void setSpawnPathRoot(Path spawnPathRoot) { this.spawnPathRoot = spawnPathRoot; }

	public void addSpawnPath(String key, Path path) {
		this.spawnPaths.put(key, path);
		modelUpdateSupport.firePropertyChange("spawnPathsChanged", null, spawnPaths);
	}
	
	public void addSpawnEntries(ArrayList<SpawnEntry> spawnEntries) { 
		this.spawnEntries.addAll(spawnEntries); 
		modelUpdateSupport.firePropertyChange("spawnEntriesAdded", null, spawnEntries);
	}
	
	public void removeSpawnEntries(ArrayList<SpawnEntry> spawnEntries) { 
		this.spawnEntries.removeAll(spawnEntries); 
		modelUpdateSupport.firePropertyChange("spawnEntriesRemoved", null, spawnEntries);
	}
	
	
	public void registerListener(PropertyChangeListener listener) {
		modelUpdateSupport.addPropertyChangeListener(listener);
		
	}
}
