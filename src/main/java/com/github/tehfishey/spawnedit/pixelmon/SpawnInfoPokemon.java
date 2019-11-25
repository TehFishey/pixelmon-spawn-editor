package com.github.tehfishey.spawnedit.pixelmon;

	// Approximate representation of Pixelmon's Pokemon SpawnInfo sub-class.
	// Used for modeling spawn data.

public class SpawnInfoPokemon extends SpawnInfo {
	
	PokemonSpec spec;
	PokemonSpec[] specs;
	Integer minLevel;
	Integer maxLevel;
	Float spawnSpecificShinyRate;
	Float spawnSpecificBossRate;
	Float spawnSpecificPokerusRate;
	ItemStack[] heldItems;
	
	public PokemonSpec getSpec() { return spec; }
	public void setSpec(PokemonSpec spec) { this.spec = spec; }
	
	public PokemonSpec[] getSpecs() { return specs; }
	public void setSpecs(PokemonSpec[] specs) { this.specs = specs; }
	
	public Integer getMinLevel() { return minLevel; }
	public void setMinLevel(int minLevel) { this.minLevel = minLevel; }
	
	public Integer getMaxLevel() { return maxLevel; }
	public void setMaxLevel(int maxLevel) { this.maxLevel = maxLevel; }
	
	public Float getSpawnSpecificShinyRate() { return spawnSpecificShinyRate; }
	public void setSpawnSpecificShinyRate(float spawnSpecificShinyRate) { this.spawnSpecificShinyRate = spawnSpecificShinyRate; }
	
	public Float getSpawnSpecificBossRate() { return spawnSpecificBossRate; }
	public void setSpawnSpecificBossRate(float spawnSpecificBossRate) { this.spawnSpecificBossRate = spawnSpecificBossRate; }
	
	public Float getSpawnSpecificPokerusRate() { return spawnSpecificPokerusRate; }
	public void setSpawnSpecificPokerusRate(float spawnSpecificPokerusRate) { this.spawnSpecificPokerusRate = spawnSpecificPokerusRate; }
	
	public ItemStack[] getHeldItems() { return heldItems; }
	public void setHeldItems(ItemStack[] heldItems) { this.heldItems = heldItems; }

	
}
