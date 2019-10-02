package com.github.tehfishey.spawnedit.pixelmon;

public class SpawnSet {
	String setId;
	SpawnInfo spawnInfos[];
	
	public String getSetId() {
		return setId;
	}
	public void setSetId(String setId) {
		this.setId = setId;
	}
	public SpawnInfo[] getSpawnInfos() {
		return spawnInfos;
	}
	public void setSpawnInfos(SpawnInfo[] spawnInfos) {
		this.spawnInfos = spawnInfos;
	}
}
