package com.github.tehfishey.spawnedit.pixelmon;

public class SpawnInfoPokemon extends SpawnInfo {
	
	String typeID = "pokemon";
	String interval;
	String tags[];
	Condition condition;
	Condition antiCondition;
	CompositeCondition compositeCondition;
	int requiredSpace;
	String stringLocationTypes[];
	float rarity;
	RarityMultiplier rarityMultipliers[];
	float percentage;
	PokemonSpec spec;
	PokemonSpec[] specs;
	int minLevel;
	int maxLevel;
	float spawnSpecificShinyRate;
	float spawnSpecificBossRate;
	float spawnSpecificPokerusRate;
	ItemStack[] heldItems;
}
