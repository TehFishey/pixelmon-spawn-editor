package com.github.tehfishey.spawnedit.pixelmon;

import java.util.ArrayList;

public class SpawnSet {
	String id;
	ArrayList<SpawnInfoPokemon> spawnInfos;
	
	public String getSetId() { return id; }
	public void setSetId(String setId) { this.id = setId; }
	
	public ArrayList<SpawnInfoPokemon> getSpawnInfos() { return spawnInfos; }
	public void setSpawnInfos(ArrayList<SpawnInfoPokemon> spawnInfos) { this.spawnInfos = spawnInfos; }
}
