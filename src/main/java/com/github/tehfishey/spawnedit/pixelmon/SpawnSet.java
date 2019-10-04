package com.github.tehfishey.spawnedit.pixelmon;

public class SpawnSet {
	String id;
	SpawnInfoPokemon spawnInfos[];
	
	public String getSetId() { return id; }
	public void setSetId(String setId) { this.id = setId; }
	
	public SpawnInfoPokemon[] getSpawnInfos() { return spawnInfos; }
	public void setSpawnInfos(SpawnInfoPokemon[] spawnInfos) { this.spawnInfos = spawnInfos; }
}
