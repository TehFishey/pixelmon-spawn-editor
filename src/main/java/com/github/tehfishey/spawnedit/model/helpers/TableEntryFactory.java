package com.github.tehfishey.spawnedit.model.helpers;

import java.util.ArrayList;
import java.util.HashMap;

import com.github.tehfishey.spawnedit.model.SpawnEntry;
import com.github.tehfishey.spawnedit.pixelmon.SpawnInfoPokemon;

	// Utility class for generating data for TableView display from SpawnEntry domain objects. Each SpawnEntry
	// can potentially have multiple table-row entries - one for each permutation of its array-properties.
	// TableEntryFactory takes the Cartesian product of these properties and creates new entry data for each result.

public class TableEntryFactory {

	public static ArrayList<HashMap<String, Object>> buildEntries(SpawnEntry spawnEntry) {
		ArrayList<HashMap<String, Object>> tableEntries = new ArrayList<HashMap<String, Object>>();
		
		HashMap<String, Object> tableEntryPrototype = processIndividualValues(spawnEntry);
		ArrayList<HashMap<String, Object>> arrayValuePermutations = processArrayValues(spawnEntry);
		
		for (HashMap<String, Object> arrayValueSet : arrayValuePermutations) {
			HashMap<String, Object> newEntry = new HashMap<String, Object>(tableEntryPrototype);
			newEntry.putAll(arrayValueSet);
			tableEntries.add(newEntry);
		}
		
		return tableEntries;
	}
	
	public static HashMap<String, Object> processIndividualValues(SpawnEntry spawnEntry) {
		HashMap<String, Object> tableEntryPrototype = new HashMap<String, Object>();
		SpawnInfoPokemon spawnInfo = spawnEntry.getSpawnInfo();
		
		tableEntryPrototype.computeIfAbsent("spawnSetId", val -> spawnEntry.getSpawnSetId());
		tableEntryPrototype.computeIfAbsent("spawnSetIndex", val -> spawnEntry.getSpawnSetIndex());
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
		
		return tableEntryPrototype;
	}
	
	public static ArrayList<HashMap<String, Object>> processArrayValues(SpawnEntry spawnEntry) {
		SpawnInfoPokemon spawnInfo = spawnEntry.getSpawnInfo();
		
		HashMap<String, Object[]> cellValueArrays = new HashMap<String, Object[]>();
		ArrayList<HashMap<String, Object>> arrayEntryValues = new ArrayList<HashMap<String, Object>>();
		
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
		
		return arrayEntryValues;
	}
	
	private static void cartesianProductLoop(HashMap<String, Object[]> arraysInput, ArrayList<HashMap<String, Object>> output, HashMap<String, Object> subOutput) {
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
