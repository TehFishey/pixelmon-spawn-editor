package com.github.tehfishey.spawnedit.model;

import java.util.ArrayList;
import java.util.HashMap;

import com.github.tehfishey.spawnedit.pixelmon.SpawnInfoPokemon;

public class SpawnEntry {

	String spawnSetId;
	int spawnSetIndex;
	SpawnInfoPokemon spawnInfo;
	ArrayList<HashMap<String, Object>> tableEntries;
	
	public SpawnEntry(String spawnSetId, int spawnSetIndex, SpawnInfoPokemon spawnInfo) {
		this.spawnSetId = spawnSetId;
		this.spawnSetIndex = spawnSetIndex;
		this.spawnInfo = spawnInfo;
		
		tableEntries = generateTableEntries();
	}
	
	public ArrayList<HashMap<String, Object>> getTableEntries() {
		return tableEntries;
	}
	
	public String getSpawnSetId() { return spawnSetId; }
	public int getSpawnSetIndex() { return spawnSetIndex; }
	
	private ArrayList<HashMap<String, Object>> generateTableEntries() {
		HashMap<String, Object[]> cellValueArrays = new HashMap<String, Object[]>();
		ArrayList<HashMap<String, Object>> arrayEntryValues = new ArrayList<HashMap<String, Object>>();
		ArrayList<HashMap<String, Object>> tableEntries = new ArrayList<HashMap<String, Object>>();
		
		cellValueArrays.computeIfAbsent("spawnType", val -> spawnInfo.getStringLocationTypes());
		
		if (spawnInfo.getCondition() != null) {
			cellValueArrays.computeIfAbsent("conditionTime", val -> spawnInfo.getCondition().getTimes());
			cellValueArrays.computeIfAbsent("conditionWeather", val -> spawnInfo.getCondition().getWeathers());
			cellValueArrays.computeIfAbsent("conditionBiome", val -> spawnInfo.getCondition().getStringBiomes());
			cellValueArrays.computeIfAbsent("conditionTemperature", val -> spawnInfo.getCondition().getTemperatures());
			cellValueArrays.computeIfAbsent("conditionWorld", val -> spawnInfo.getCondition().getWorlds());
			cellValueArrays.computeIfAbsent("conditionDimension", val -> spawnInfo.getCondition().getWorlds());
			cellValueArrays.computeIfAbsent("conditionRequiredBlock", val -> spawnInfo.getCondition().getBaseBlocks());
			cellValueArrays.computeIfAbsent("conditionNearbyBlock", val -> spawnInfo.getCondition().getNeededNearbyBlocks());
			cellValueArrays.computeIfAbsent("conditionVariant", val -> spawnInfo.getCondition().getVariant());
		}
		
		if (spawnInfo.getAntiCondition() != null) {
			cellValueArrays.computeIfAbsent("antiConditionTime", val -> spawnInfo.getAntiCondition().getTimes());
			cellValueArrays.computeIfAbsent("antiConditionWeather", val -> spawnInfo.getAntiCondition().getWeathers());
			cellValueArrays.computeIfAbsent("antiConditionBiome", val -> spawnInfo.getAntiCondition().getStringBiomes());
			cellValueArrays.computeIfAbsent("antiConditionTemperature", val -> spawnInfo.getAntiCondition().getTemperatures());
			cellValueArrays.computeIfAbsent("antiConditionWorld", val -> spawnInfo.getAntiCondition().getWorlds());
			cellValueArrays.computeIfAbsent("antiConditionDimension", val -> spawnInfo.getAntiCondition().getWorlds());
			cellValueArrays.computeIfAbsent("antiConditionRequiredBlock", val -> spawnInfo.getAntiCondition().getBaseBlocks());
			cellValueArrays.computeIfAbsent("antiConditionNearbyBlock", val -> spawnInfo.getAntiCondition().getNeededNearbyBlocks());
			cellValueArrays.computeIfAbsent("antiConditionVariant", val -> spawnInfo.getAntiCondition().getVariant());
		}
		
		cartesianProductLoop(cellValueArrays, arrayEntryValues, new HashMap<String, Object>());

		HashMap<String, Object> tableEntryPrototype = new HashMap<String, Object>();
		
		tableEntryPrototype.computeIfAbsent("spawnSetId", val -> getSpawnSetId());
		tableEntryPrototype.computeIfAbsent("spawnSetIndex", val -> getSpawnSetIndex());
		tableEntryPrototype.computeIfAbsent("intervalType", val -> spawnInfo.getInterval());
		tableEntryPrototype.computeIfAbsent("requiredSpace", val -> spawnInfo.getRequiredSpace());
		tableEntryPrototype.computeIfAbsent("rarity", val -> spawnInfo.getRarity());
		tableEntryPrototype.computeIfAbsent("percentage", val -> spawnInfo.getPercentage());
		tableEntryPrototype.computeIfAbsent("minLevel", val -> spawnInfo.getMinLevel());
		tableEntryPrototype.computeIfAbsent("maxLevel", val -> spawnInfo.getMaxLevel());
		tableEntryPrototype.computeIfAbsent("specificShinyRate", val -> spawnInfo.getSpawnSpecificShinyRate());
		tableEntryPrototype.computeIfAbsent("specificBossRate", val ->  spawnInfo.getSpawnSpecificBossRate());
		tableEntryPrototype.computeIfAbsent("specificPokeRusRate", val -> spawnInfo.getSpawnSpecificPokerusRate());  
		
		if (spawnInfo.getSpec() != null) {
			tableEntryPrototype.computeIfAbsent("pokemonSpecSpecies", val -> spawnInfo.getSpec().getTypeName());
			tableEntryPrototype.computeIfAbsent("pokemonSpecLevel", val -> spawnInfo.getSpec().getLevel());
			tableEntryPrototype.computeIfAbsent("pokemonSpecGender", val -> spawnInfo.getSpec().getGender());
			tableEntryPrototype.computeIfAbsent("pokemonSpecStatus", val -> spawnInfo.getSpec().getStatus());
			tableEntryPrototype.computeIfAbsent("pokemonSpecGrowthSize", val -> spawnInfo.getSpec().getGrowth());
			//tableEntryPrototype.put("pokemonSpecNature", getSpec().getNature());
			tableEntryPrototype.computeIfAbsent("pokemonSpecFormId", val -> spawnInfo.getSpec().getForm());
			tableEntryPrototype.computeIfAbsent("pokemonSpecPokeRusStage", val -> spawnInfo.getSpec().getPokerus());
			tableEntryPrototype.computeIfAbsent("pokemonSpecRandom", val -> spawnInfo.getSpec().isRandom());
			tableEntryPrototype.computeIfAbsent("pokemonSpecCured", val -> spawnInfo.getSpec().isCured());
			tableEntryPrototype.computeIfAbsent("pokemonSpecShiny", val -> spawnInfo.getSpec().isShiny());
			tableEntryPrototype.computeIfAbsent("pokemonSpecEgg", val -> spawnInfo.getSpec().isEgg());
			tableEntryPrototype.computeIfAbsent("pokemonSpecUntradeable", val -> spawnInfo.getSpec().isUntradeable());
			tableEntryPrototype.computeIfAbsent("pokemonSpecUnbreedable", val -> spawnInfo.getSpec().isUnbreedable());
		} 
		
		if (spawnInfo.getCondition() != null) {
			tableEntryPrototype.computeIfAbsent("conditionMinX", val -> spawnInfo.getCondition().getMinX());
			tableEntryPrototype.computeIfAbsent("conditionMaxX", val -> spawnInfo.getCondition().getMaxX());
			tableEntryPrototype.computeIfAbsent("conditionMinZ", val -> spawnInfo.getCondition().getMinZ());
			tableEntryPrototype.computeIfAbsent("conditionMaxZ", val ->  spawnInfo.getCondition().getMaxZ());
			tableEntryPrototype.computeIfAbsent("conditionMinY", val -> spawnInfo.getCondition().getMinY());
			tableEntryPrototype.computeIfAbsent("conditionMaxY", val ->  spawnInfo.getCondition().getMaxY());
			tableEntryPrototype.computeIfAbsent("conditionMinLight", val -> spawnInfo.getCondition().getMinLightLevel());
			tableEntryPrototype.computeIfAbsent("conditionMaxLight", val -> spawnInfo.getCondition().getMaxLightLevel());
			tableEntryPrototype.computeIfAbsent("conditionRequiresSky", val -> spawnInfo.getCondition().isSeesSky());
			tableEntryPrototype.computeIfAbsent("conditionMoonPhase", val -> spawnInfo.getCondition().getMoonPhase());
			tableEntryPrototype.computeIfAbsent("conditionTag", val -> spawnInfo.getCondition().getTag());
		}
		
		if (spawnInfo.getAntiCondition() != null) {
			tableEntryPrototype.computeIfAbsent("antiConditionMinX", val -> spawnInfo.getAntiCondition().getMinX());
			tableEntryPrototype.computeIfAbsent("antiConditionMaxX", val -> spawnInfo.getAntiCondition().getMaxX());
			tableEntryPrototype.computeIfAbsent("antiConditionMinZ", val -> spawnInfo.getAntiCondition().getMinZ());
			tableEntryPrototype.computeIfAbsent("antiConditionMaxZ", val -> spawnInfo.getAntiCondition().getMaxZ());
			tableEntryPrototype.computeIfAbsent("antiConditionMinY", val -> spawnInfo.getAntiCondition().getMinY());
			tableEntryPrototype.computeIfAbsent("antiConditionMaxY", val -> spawnInfo.getAntiCondition().getMaxY());
			tableEntryPrototype.computeIfAbsent("antiConditionMinLight", val -> spawnInfo.getAntiCondition().getMinLightLevel());
			tableEntryPrototype.computeIfAbsent("antiConditionMaxLight", val -> spawnInfo.getAntiCondition().getMaxLightLevel());
			tableEntryPrototype.computeIfAbsent("antiConditionRequiresSky", val -> spawnInfo.getAntiCondition().isSeesSky());
			tableEntryPrototype.computeIfAbsent("antiConditionMoonPhase", val -> spawnInfo.getAntiCondition().getMoonPhase());
			tableEntryPrototype.computeIfAbsent("antiConditionTag", val -> spawnInfo.getAntiCondition().getTag());
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
