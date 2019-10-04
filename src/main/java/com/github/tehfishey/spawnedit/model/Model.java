package com.github.tehfishey.spawnedit.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Model {
	private final FileManager fileManager;
	private final ArrayList<SpawnEntry> spawnEntries;
	private final PropertyChangeSupport modelUpdateSupport;
	
	public Model() {
		fileManager = new FileManager(this);
		spawnEntries = new ArrayList<SpawnEntry>();
		modelUpdateSupport = new PropertyChangeSupport(this);
	}
	
	public FileManager getFileManager() {
		return fileManager;
	}
	
	public ArrayList<SpawnEntry> getSpawnEntries() { return spawnEntries; }
	
	public void addSpawnEntries(ArrayList<SpawnEntry> spawnEntries) { 
		this.spawnEntries.addAll(spawnEntries); 
		notifyModelAddition(this.spawnEntries);
	}
	
	public void removeSpawnEntries(ArrayList<SpawnEntry> spawnEntries) { 
		this.spawnEntries.removeAll(spawnEntries); 
		notifyModelSubtraction(this.spawnEntries);
	}
	
	
	public void registerListener(PropertyChangeListener listener) {
		modelUpdateSupport.addPropertyChangeListener(listener);
		
	}
	
	private void notifyModelAddition(ArrayList<SpawnEntry> spawnEntries) {
		modelUpdateSupport.firePropertyChange("spawnEntriesAdded", null, spawnEntries);
	}
	
	private void notifyModelSubtraction(ArrayList<SpawnEntry> spawnEntries) {
		modelUpdateSupport.firePropertyChange("spawnEntriesRemoved", null, spawnEntries);
	}
}
