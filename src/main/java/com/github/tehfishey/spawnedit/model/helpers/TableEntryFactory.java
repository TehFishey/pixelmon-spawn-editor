package com.github.tehfishey.spawnedit.model.helpers;

import java.util.ArrayList;
import java.util.HashMap;

import com.github.tehfishey.spawnedit.model.SpawnEntry;
import com.github.tehfishey.spawnedit.model.helpers.Enums.COLUMN_ID;
import com.github.tehfishey.spawnedit.pixelmon.SpawnInfoPokemon;

	// Utility class for generating data for TableView display from SpawnEntry domain objects. Each SpawnEntry
	// can potentially have multiple table-row entries - one for each permutation of its array-properties.
	// TableEntryFactory takes the Cartesian product of these properties and creates new entry data for each result.

public class TableEntryFactory {

	public static ArrayList<HashMap<COLUMN_ID, Object>> buildEntries(SpawnEntry spawnEntry) {
		ArrayList<HashMap<COLUMN_ID, Object>> tableEntries = new ArrayList<HashMap<COLUMN_ID, Object>>();
		
		HashMap<COLUMN_ID, Object> tableEntryPrototype = processIndividualValues(spawnEntry);
		ArrayList<HashMap<COLUMN_ID, Object>> arrayValuePermutations = processArrayValues(spawnEntry);
		
		for (HashMap<COLUMN_ID, Object> arrayValueSet : arrayValuePermutations) {
			HashMap<COLUMN_ID, Object> newEntry = new HashMap<COLUMN_ID, Object>(tableEntryPrototype);
			newEntry.putAll(arrayValueSet);
			tableEntries.add(newEntry);
		}
		
		return tableEntries;
	}
	
	public static HashMap<COLUMN_ID, Object> processIndividualValues(SpawnEntry spawnEntry) {
		HashMap<COLUMN_ID, Object> tableEntryPrototype = new HashMap<COLUMN_ID, Object>();
		SpawnInfoPokemon spawnInfo = spawnEntry.getSpawnInfo();
		
		tableEntryPrototype.computeIfAbsent(COLUMN_ID.spawnSetId, val -> spawnEntry.getSpawnSetId());
		tableEntryPrototype.computeIfAbsent(COLUMN_ID.spawnSetIndex, val -> spawnEntry.getSpawnSetIndex());
		tableEntryPrototype.computeIfAbsent(COLUMN_ID.intervalType, val -> spawnInfo.getInterval());
		tableEntryPrototype.computeIfAbsent(COLUMN_ID.pokemonSpecs, val -> spawnInfo.getSpecs());
		tableEntryPrototype.computeIfAbsent(COLUMN_ID.requiredSpace, val -> spawnInfo.getRequiredSpace());
		tableEntryPrototype.computeIfAbsent(COLUMN_ID.rarity, val -> spawnInfo.getRarity());
		tableEntryPrototype.computeIfAbsent(COLUMN_ID.rarityMultipliers, val -> spawnInfo.getRarityMultipliers());
		tableEntryPrototype.computeIfAbsent(COLUMN_ID.percentage, val -> spawnInfo.getPercentage());
		tableEntryPrototype.computeIfAbsent(COLUMN_ID.minLevel, val -> spawnInfo.getMinLevel());
		tableEntryPrototype.computeIfAbsent(COLUMN_ID.maxLevel, val -> spawnInfo.getMaxLevel());
		tableEntryPrototype.computeIfAbsent(COLUMN_ID.specificShinyRate, val -> spawnInfo.getSpawnSpecificShinyRate());
		tableEntryPrototype.computeIfAbsent(COLUMN_ID.specificBossRate, val ->  spawnInfo.getSpawnSpecificBossRate());
		tableEntryPrototype.computeIfAbsent(COLUMN_ID.specificPokeRusRate, val -> spawnInfo.getSpawnSpecificPokerusRate());
		tableEntryPrototype.computeIfAbsent(COLUMN_ID.heldItems, val -> spawnInfo.getHeldItems());
		
		if (spawnInfo.getSpec() != null) {
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.pokemonSpecSpecies, val -> spawnInfo.getSpec().getTypeName());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.pokemonSpecLevel, val -> spawnInfo.getSpec().getLevel());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.pokemonSpecGender, val -> spawnInfo.getSpec().getGender());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.pokemonSpecStatus, val -> spawnInfo.getSpec().getStatus());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.pokemonSpecGrowthSize, val -> spawnInfo.getSpec().getGrowth());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.pokemonSpecNature, val -> spawnInfo.getSpec().getNature());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.pokemonSpecFormId, val -> spawnInfo.getSpec().getForm());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.pokemonSpecPokeRusStage, val -> spawnInfo.getSpec().getPokerus());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.pokemonSpecRandom, val -> spawnInfo.getSpec().isRandom());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.pokemonSpecCured, val -> spawnInfo.getSpec().isCured());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.pokemonSpecShiny, val -> spawnInfo.getSpec().isShiny());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.pokemonSpecEgg, val -> spawnInfo.getSpec().isEgg());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.pokemonSpecUntradeable, val -> spawnInfo.getSpec().isUntradeable());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.pokemonSpecUnbreedable, val -> spawnInfo.getSpec().isUnbreedable());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.pokemonSpecIVStats, val -> spawnInfo.getSpec().isUnbreedable());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.pokemonSpecEVStats, val -> spawnInfo.getSpec().isUnbreedable());
		} 
		
		if (spawnInfo.getCondition() != null) {
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.conditionMinX, val -> spawnInfo.getCondition().getMinX());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.conditionMaxX, val -> spawnInfo.getCondition().getMaxX());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.conditionMinZ, val -> spawnInfo.getCondition().getMinZ());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.conditionMaxZ, val ->  spawnInfo.getCondition().getMaxZ());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.conditionMinY, val -> spawnInfo.getCondition().getMinY());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.conditionMaxY, val ->  spawnInfo.getCondition().getMaxY());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.conditionMinLight, val -> spawnInfo.getCondition().getMinLightLevel());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.conditionMaxLight, val -> spawnInfo.getCondition().getMaxLightLevel());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.conditionRequiresSky, val -> spawnInfo.getCondition().isSeesSky());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.conditionMoonPhase, val -> spawnInfo.getCondition().getMoonPhase());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.conditionTag, val -> spawnInfo.getCondition().getTag());
		}
		
		if (spawnInfo.getAntiCondition() != null) {
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.antiConditionMinX, val -> spawnInfo.getAntiCondition().getMinX());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.antiConditionMaxX, val -> spawnInfo.getAntiCondition().getMaxX());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.antiConditionMinZ, val -> spawnInfo.getAntiCondition().getMinZ());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.antiConditionMaxZ, val -> spawnInfo.getAntiCondition().getMaxZ());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.antiConditionMinY, val -> spawnInfo.getAntiCondition().getMinY());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.antiConditionMaxY, val -> spawnInfo.getAntiCondition().getMaxY());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.antiConditionMinLight, val -> spawnInfo.getAntiCondition().getMinLightLevel());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.antiConditionMaxLight, val -> spawnInfo.getAntiCondition().getMaxLightLevel());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.antiConditionRequiresSky, val -> spawnInfo.getAntiCondition().isSeesSky());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.antiConditionMoonPhase, val -> spawnInfo.getAntiCondition().getMoonPhase());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.antiConditionTag, val -> spawnInfo.getAntiCondition().getTag());
		}
		if (spawnInfo.getCompositeCondition() != null) {
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.compositeConditionConditions, val -> spawnInfo.getCompositeCondition().getConditions());
			tableEntryPrototype.computeIfAbsent(COLUMN_ID.compositeConditionAntiConditions, val -> spawnInfo.getCompositeCondition().getAntiConditions());
		}
		
		return tableEntryPrototype;
	}
	
	public static ArrayList<HashMap<COLUMN_ID, Object>> processArrayValues(SpawnEntry spawnEntry) {
		SpawnInfoPokemon spawnInfo = spawnEntry.getSpawnInfo();
		
		HashMap<COLUMN_ID, Object[]> cellValueArrays = new HashMap<COLUMN_ID, Object[]>();
		ArrayList<HashMap<COLUMN_ID, Object>> arrayEntryValues = new ArrayList<HashMap<COLUMN_ID, Object>>();
		
		cellValueArrays.computeIfAbsent(COLUMN_ID.spawnType, val -> spawnInfo.getStringLocationTypes());
		
		if (spawnInfo.getCondition() != null) {
			cellValueArrays.computeIfAbsent(COLUMN_ID.conditionTime, val -> spawnInfo.getCondition().getTimes());
			cellValueArrays.computeIfAbsent(COLUMN_ID.conditionWeather, val -> spawnInfo.getCondition().getWeathers());
			cellValueArrays.computeIfAbsent(COLUMN_ID.conditionBiome, val -> spawnInfo.getCondition().getStringBiomes());
			cellValueArrays.computeIfAbsent(COLUMN_ID.conditionTemperature, val -> spawnInfo.getCondition().getTemperatures());
			cellValueArrays.computeIfAbsent(COLUMN_ID.conditionWorld, val -> spawnInfo.getCondition().getWorlds());
			cellValueArrays.computeIfAbsent(COLUMN_ID.conditionDimension, val -> spawnInfo.getCondition().getWorlds());
			cellValueArrays.computeIfAbsent(COLUMN_ID.conditionRequiredBlock, val -> spawnInfo.getCondition().getBaseBlocks());
			cellValueArrays.computeIfAbsent(COLUMN_ID.conditionNearbyBlock, val -> spawnInfo.getCondition().getNeededNearbyBlocks());
			cellValueArrays.computeIfAbsent(COLUMN_ID.conditionVariant, val -> spawnInfo.getCondition().getVariant());
		}
		
		if (spawnInfo.getAntiCondition() != null) {
			cellValueArrays.computeIfAbsent(COLUMN_ID.antiConditionTime, val -> spawnInfo.getAntiCondition().getTimes());
			cellValueArrays.computeIfAbsent(COLUMN_ID.antiConditionWeather, val -> spawnInfo.getAntiCondition().getWeathers());
			cellValueArrays.computeIfAbsent(COLUMN_ID.antiConditionBiome, val -> spawnInfo.getAntiCondition().getStringBiomes());
			cellValueArrays.computeIfAbsent(COLUMN_ID.antiConditionTemperature, val -> spawnInfo.getAntiCondition().getTemperatures());
			cellValueArrays.computeIfAbsent(COLUMN_ID.antiConditionWorld, val -> spawnInfo.getAntiCondition().getWorlds());
			cellValueArrays.computeIfAbsent(COLUMN_ID.antiConditionDimension, val -> spawnInfo.getAntiCondition().getWorlds());
			cellValueArrays.computeIfAbsent(COLUMN_ID.antiConditionRequiredBlock, val -> spawnInfo.getAntiCondition().getBaseBlocks());
			cellValueArrays.computeIfAbsent(COLUMN_ID.antiConditionNearbyBlock, val -> spawnInfo.getAntiCondition().getNeededNearbyBlocks());
			cellValueArrays.computeIfAbsent(COLUMN_ID.antiConditionVariant, val -> spawnInfo.getAntiCondition().getVariant());
		}
		
		cartesianProductLoop(cellValueArrays, arrayEntryValues, new HashMap<COLUMN_ID, Object>());
		
		return arrayEntryValues;
	}
	
	private static void cartesianProductLoop(HashMap<COLUMN_ID, Object[]> arraysInput, ArrayList<HashMap<COLUMN_ID, Object>> output, HashMap<COLUMN_ID, Object> subOutput) {
		if (arraysInput.size() == 0) {
			output.add(subOutput);
		}
		else {
			COLUMN_ID key = arraysInput.keySet().iterator().next();
			Object[] currentArray = arraysInput.get(key);
			
			for (int i = 0; i < currentArray.length; i++)
			{
				HashMap<COLUMN_ID, Object> newSubOutput = new HashMap<COLUMN_ID, Object>(subOutput);
				HashMap<COLUMN_ID, Object[]> newSubInput = new HashMap<COLUMN_ID, Object[]>(arraysInput);
				newSubInput.remove(key);
				newSubOutput.put(key, currentArray[i]);
				cartesianProductLoop(newSubInput,output,newSubOutput);
			}
		}
	}
}
