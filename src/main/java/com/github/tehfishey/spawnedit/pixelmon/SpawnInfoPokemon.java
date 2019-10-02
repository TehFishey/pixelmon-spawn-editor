package com.github.tehfishey.spawnedit.pixelmon;

public class SpawnInfoPokemon extends SpawnInfo {
	
	String typeID = "pokemon";
	PokemonSpec spec;
	PokemonSpec[] specs;
	int minLevel;
	int maxLevel;
	float spawnSpecificShinyRate;
	float spawnSpecificBossRate;
	float spawnSpecificPokerusRate;
	ItemStack[] heldItems;
	
	public PokemonSpec getSpec() { return spec; }
	public void setSpec(PokemonSpec spec) { this.spec = spec; }
	
	public PokemonSpec[] getSpecs() { return specs; }
	public void setSpecs(PokemonSpec[] specs) { this.specs = specs; }
	
	public int getMinLevel() { return minLevel; }
	public void setMinLevel(int minLevel) { this.minLevel = minLevel; }
	
	public int getMaxLevel() { return maxLevel; }
	public void setMaxLevel(int maxLevel) { this.maxLevel = maxLevel; }
	
	public float getSpawnSpecificShinyRate() { return spawnSpecificShinyRate; }
	public void setSpawnSpecificShinyRate(float spawnSpecificShinyRate) { this.spawnSpecificShinyRate = spawnSpecificShinyRate; }
	
	public float getSpawnSpecificBossRate() { return spawnSpecificBossRate; }
	public void setSpawnSpecificBossRate(float spawnSpecificBossRate) { this.spawnSpecificBossRate = spawnSpecificBossRate; }
	
	public float getSpawnSpecificPokerusRate() { return spawnSpecificPokerusRate; }
	public void setSpawnSpecificPokerusRate(float spawnSpecificPokerusRate) { this.spawnSpecificPokerusRate = spawnSpecificPokerusRate; }
	
	public ItemStack[] getHeldItems() { return heldItems; }
	public void setHeldItems(ItemStack[] heldItems) { this.heldItems = heldItems; }

	
}
