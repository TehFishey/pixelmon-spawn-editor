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
}
