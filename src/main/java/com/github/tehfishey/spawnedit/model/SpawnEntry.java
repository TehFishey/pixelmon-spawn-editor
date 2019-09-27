package com.github.tehfishey.spawnedit.model;

import java.util.ArrayList;
import java.util.Arrays;

import com.github.tehfishey.spawnedit.pixelmon.SpawnInfoPokemon;

public class SpawnEntry extends SpawnInfoPokemon {

	String spawnSetId;
	int spawnSetIndex;
	ArrayList<TableEntry> tableEntries;
	
	public SpawnEntry(String setId, int setIndex) {
		spawnSetId = setId;
		spawnSetIndex = setIndex;
		
		tableEntries = generateTableEntries();
	}
	
	private ArrayList<TableEntry> generateTableEntries() {
		ArrayList<TableEntry> entries = new ArrayList<TableEntry>();
		
		return entries;
	}
	
	private ArrayList<Object> generateCartesianProduct(ArrayList<Object>...arrayLists ) {
		ArrayList<ArrayList<Object>> arrays = new ArrayList<ArrayList<Object>>(Arrays.asList(arrayLists));
		
		// A miracle occurs...
		
		return new ArrayList<Object>();
	}
	
}
