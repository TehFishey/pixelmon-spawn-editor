package com.github.tehfishey.spawnedit.model;

import java.util.ArrayList;

public class Model {

	private final ArrayList<SpawnEntry> spawnEntries;
	
	public Model() {
		spawnEntries = new ArrayList<SpawnEntry>();
		spawnEntries.add(new SpawnEntry("setID", 12));
		spawnEntries.add(new SpawnEntry("setID2", 18));
	}
	
	public ArrayList<SpawnEntry> getSpawnEntries() {
		return spawnEntries;
	}
}
