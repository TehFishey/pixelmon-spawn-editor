package com.github.tehfishey.spawnedit.model;

import java.util.ArrayList;
import java.util.HashMap;

import com.github.tehfishey.spawnedit.pixelmon.SpawnInfoPokemon;

public class SpawnEntry extends SpawnInfoPokemon {

	String spawnSetId;
	int spawnSetIndex;
	ArrayList<HashMap<String, Object>> tableEntries;
	
	public SpawnEntry(String setId, int setIndex) {
		spawnSetId = setId;
		spawnSetIndex = setIndex;
		
		tableEntries = generateTableEntries();
	}
	
	public ArrayList<HashMap<String, Object>> getTableEntries() {
		return tableEntries;
	}
	
	public String getSpawnSetId() {
		return spawnSetId;
	}
	
	public int getSpawnSetIndex() {
		return spawnSetIndex;
	}
	
	private ArrayList<HashMap<String, Object>> generateTableEntries() {
		HashMap<String, Object[]> cellValueArrays = new HashMap<String, Object[]>();
		ArrayList<HashMap<String, Object>> arrayEntryValues = new ArrayList<HashMap<String, Object>>();
		ArrayList<HashMap<String, Object>> tableEntries = new ArrayList<HashMap<String, Object>>();
		
		cellValueArrays.computeIfAbsent("spawnType", val -> this.getStringLocationTypes());
		
		if (this.getCondition() != null) {
			cellValueArrays.computeIfAbsent("conditionTime", val -> this.getCondition().getTimes());
			cellValueArrays.computeIfAbsent("conditionWeather", val -> this.getCondition().getWeathers());
			cellValueArrays.computeIfAbsent("conditionBiome", val -> this.getCondition().getStringBiomes());
			cellValueArrays.computeIfAbsent("conditionTemperature", val -> this.getCondition().getTemperatures());
			cellValueArrays.computeIfAbsent("conditionWorld", val -> this.getCondition().getWorlds());
			cellValueArrays.computeIfAbsent("conditionDimension", val -> this.getCondition().getWorlds());
			cellValueArrays.computeIfAbsent("conditionRequiredBlock", val -> this.getCondition().getBaseBlocks());
			cellValueArrays.computeIfAbsent("conditionNearbyBlock", val -> this.getCondition().getNeededNearbyBlocks());
			cellValueArrays.computeIfAbsent("conditionVariant", val -> this.getCondition().getVariant());
		}
		
		if (this.getAntiCondition() != null) {
			cellValueArrays.computeIfAbsent("antiConditionTime", val -> this.getAntiCondition().getTimes());
			cellValueArrays.computeIfAbsent("antiConditionWeather", val -> this.getAntiCondition().getWeathers());
			cellValueArrays.computeIfAbsent("antiConditionBiome", val -> this.getAntiCondition().getStringBiomes());
			cellValueArrays.computeIfAbsent("antiConditionTemperature", val -> this.getAntiCondition().getTemperatures());
			cellValueArrays.computeIfAbsent("antiConditionWorld", val -> this.getAntiCondition().getWorlds());
			cellValueArrays.computeIfAbsent("antiConditionDimension", val -> this.getAntiCondition().getWorlds());
			cellValueArrays.computeIfAbsent("antiConditionRequiredBlock", val -> this.getAntiCondition().getBaseBlocks());
			cellValueArrays.computeIfAbsent("antiConditionNearbyBlock", val -> this.getAntiCondition().getNeededNearbyBlocks());
			cellValueArrays.computeIfAbsent("antiConditionVariant", val -> this.getAntiCondition().getVariant());
		}
		
		cartesianProductLoop(cellValueArrays, arrayEntryValues, new HashMap<String, Object>());

		HashMap<String, Object> tableEntryPrototype = new HashMap<String, Object>();
		
		tableEntryPrototype.computeIfAbsent("spawnSetId", val -> getSpawnSetId());
		tableEntryPrototype.computeIfAbsent("spawnSetIndex", val -> getSpawnSetIndex());
		tableEntryPrototype.computeIfAbsent("intervalType", val -> getInterval());
		tableEntryPrototype.computeIfAbsent("requiredSpace", val -> getRequiredSpace());
		tableEntryPrototype.computeIfAbsent("rarity", val -> getRarity());
		tableEntryPrototype.computeIfAbsent("percentage", val -> getPercentage());
		tableEntryPrototype.computeIfAbsent("minLevel", val -> getMinLevel());
		tableEntryPrototype.computeIfAbsent("maxLevel", val -> getMaxLevel());
		tableEntryPrototype.computeIfAbsent("specificShinyRate", val -> getSpawnSpecificShinyRate());
		tableEntryPrototype.computeIfAbsent("specificBossRate", val ->  getSpawnSpecificBossRate());
		tableEntryPrototype.computeIfAbsent("specificPokeRusRate", val -> getSpawnSpecificPokerusRate());  
		
		if (this.getSpec() != null) {
			tableEntryPrototype.computeIfAbsent("pokemonSpecSpecies", val -> getSpec().getTypeName());
			tableEntryPrototype.computeIfAbsent("pokemonSpecLevel", val -> getSpec().getLevel());
			tableEntryPrototype.computeIfAbsent("pokemonSpecGender", val -> getSpec().getGender());
			tableEntryPrototype.computeIfAbsent("pokemonSpecStatus", val -> getSpec().getStatus());
			tableEntryPrototype.computeIfAbsent("pokemonSpecGrowthSize", val -> getSpec().getGrowth());
			//tableEntryPrototype.put("pokemonSpecNature", getSpec().getNature());
			tableEntryPrototype.computeIfAbsent("pokemonSpecFormId", val -> getSpec().getForm());
			tableEntryPrototype.computeIfAbsent("pokemonSpecPokeRusStage", val -> getSpec().getPokerus());
			tableEntryPrototype.computeIfAbsent("pokemonSpecRandom", val -> getSpec().isRandom());
			tableEntryPrototype.computeIfAbsent("pokemonSpecCured", val -> getSpec().isCured());
			tableEntryPrototype.computeIfAbsent("pokemonSpecShiny", val -> getSpec().isShiny());
			tableEntryPrototype.computeIfAbsent("pokemonSpecEgg", val -> getSpec().isEgg());
			tableEntryPrototype.computeIfAbsent("pokemonSpecUntradeable", val -> getSpec().isUntradeable());
			tableEntryPrototype.computeIfAbsent("pokemonSpecUnbreedable", val -> getSpec().isUnbreedable());
		} 
		
		if (this.getCondition() != null) {
			tableEntryPrototype.computeIfAbsent("conditionMinX", val -> getCondition().getMinX());
			tableEntryPrototype.computeIfAbsent("conditionMaxX", val -> getCondition().getMaxX());
			tableEntryPrototype.computeIfAbsent("conditionMinZ", val -> getCondition().getMinZ());
			tableEntryPrototype.computeIfAbsent("conditionMaxZ", val ->  getCondition().getMaxZ());
			tableEntryPrototype.computeIfAbsent("conditionMinY", val -> getCondition().getMinY());
			tableEntryPrototype.computeIfAbsent("conditionMaxY", val ->  getCondition().getMaxY());
			tableEntryPrototype.computeIfAbsent("conditionMinLight", val -> getCondition().getMinLightLevel());
			tableEntryPrototype.computeIfAbsent("conditionMaxLight", val -> getCondition().getMaxLightLevel());
			tableEntryPrototype.computeIfAbsent("conditionRequiresSky", val -> getCondition().isSeesSky());
			tableEntryPrototype.computeIfAbsent("conditionMoonPhase", val -> getCondition().getMoonPhase());
			tableEntryPrototype.computeIfAbsent("conditionTag", val -> getCondition().getTag());
		}
		
		if (this.getAntiCondition() != null) {
			tableEntryPrototype.computeIfAbsent("antiConditionMinX", val -> getAntiCondition().getMinX());
			tableEntryPrototype.computeIfAbsent("antiConditionMaxX", val -> getAntiCondition().getMaxX());
			tableEntryPrototype.computeIfAbsent("antiConditionMinZ", val -> getAntiCondition().getMinZ());
			tableEntryPrototype.computeIfAbsent("antiConditionMaxZ", val -> getAntiCondition().getMaxZ());
			tableEntryPrototype.computeIfAbsent("antiConditionMinY", val -> getAntiCondition().getMinY());
			tableEntryPrototype.computeIfAbsent("antiConditionMaxY", val -> getAntiCondition().getMaxY());
			tableEntryPrototype.computeIfAbsent("antiConditionMinLight", val -> getAntiCondition().getMinLightLevel());
			tableEntryPrototype.computeIfAbsent("antiConditionMaxLight", val -> getAntiCondition().getMaxLightLevel());
			tableEntryPrototype.computeIfAbsent("antiConditionRequiresSky", val -> getAntiCondition().isSeesSky());
			tableEntryPrototype.computeIfAbsent("antiConditionMoonPhase", val -> getAntiCondition().getMoonPhase());
			tableEntryPrototype.computeIfAbsent("antiConditionTag", val -> getAntiCondition().getTag());
		}
		  
		
		for (HashMap<String, Object> valueSet : arrayEntryValues) {
			HashMap<String, Object> newEntry = new HashMap<String, Object>(tableEntryPrototype);
			newEntry.putAll(valueSet);
			tableEntries.add(newEntry);
		}
		
		return tableEntries;
	}
	
	private void cartesianProductLoop(HashMap<String, Object[]> arraysInput, ArrayList<HashMap<String, Object>> output, HashMap<String, Object> subOutput) {
		if (arraysInput.size() == 0) {
			output.add(subOutput);
		}
		else {
			String key = arraysInput.keySet().iterator().next();
			Object[] currentArray = arraysInput.get(key);
			
			for (int i = 0; i < currentArray.length; i++)
			{
				HashMap<String, Object> newSubOutput = new HashMap<String, Object>(subOutput);
				HashMap<String, Object[]> newSubInput = new HashMap<String, Object[]>(arraysInput);
				newSubInput.remove(key);
				newSubOutput.put(key, currentArray[i]);
				cartesianProductLoop(newSubInput,output,newSubOutput);
			}
		}
	}
}
