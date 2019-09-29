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
	
	public void cartesianProductLoop(ArrayList<ArrayList<Object>> arrays, ArrayList<ArrayList<Object>> output, ArrayList<Object> subOutput, int position, ArrayList<Integer> indicies) {
		if (position == arrays.size())
			output.add(subOutput);
		else {
			ArrayList<Object> currentArray = arrays.get(position);
			
			for (int i = 0; i < currentArray.size(); i++)
			{
				ArrayList<Object> newSubOutput = (ArrayList<Object>) subOutput.clone();
				newSubOutput.add(currentArray.get(i));
				cartesianProductLoop(arrays,output,newSubOutput,position+1, indicies);
			}
		}
	}
	
}
