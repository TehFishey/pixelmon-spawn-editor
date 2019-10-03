package com.github.tehfishey.spawnedit.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Model {
	private final FileManager fileManager;
	private final ArrayList<SpawnEntry> spawnEntries;
	private final PropertyChangeSupport listenerSupport;
	
	public Model() {
		fileManager = new FileManager(this);
		spawnEntries = new ArrayList<SpawnEntry>();
		listenerSupport = new PropertyChangeSupport(this);
	}
	
	public FileManager getFileManager() {
		return fileManager;
	}
	
	public ArrayList<SpawnEntry> getSpawnEntries() { return spawnEntries; }
	
	public void addSpawnEntries(ArrayList<SpawnEntry> spawnEntries) { 
		this.spawnEntries.addAll(spawnEntries); 
		notifyListenerChanges(this.spawnEntries);
	}
	
	public void removeSpawnEntries(ArrayList<SpawnEntry> spawnEntries) { 
		this.spawnEntries.removeAll(spawnEntries); 
		notifyListenerChanges(this.spawnEntries);
	}
	
	
	public void registerListener(PropertyChangeListener listener) {
		listenerSupport.addPropertyChangeListener(listener);
		
	}
	
	private void notifyListenerChanges(ArrayList<SpawnEntry> spawnEntries) {
		listenerSupport.firePropertyChange("spawnEntries", null, spawnEntries);
	}
	
	
}
