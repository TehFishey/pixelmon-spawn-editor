package com.github.tehfishey.spawnedit.pixelmon;

import java.util.ArrayList;

	// 'Super-object' that's meant to represent all spawn data contained in a single .json file.
	// Used for modeling spawn data.

public class SpawnSet {
	String id;
	ArrayList<SpawnInfoPokemon> spawnInfos;
	
	public String getSetId() { return id; }
	public void setSetId(String setId) { this.id = setId; }
	
	public ArrayList<SpawnInfoPokemon> getSpawnInfos() { return spawnInfos; }
	public void setSpawnInfos(ArrayList<SpawnInfoPokemon> spawnInfos) { this.spawnInfos = spawnInfos; }
}
