package com.github.tehfishey.spawnedit.model.helpers;

import java.util.ArrayList;
import java.util.HashMap;

import com.github.tehfishey.spawnedit.model.helpers.Enums.ColumnId;
import com.github.tehfishey.spawnedit.model.objects.SpawnEntry;
import com.github.tehfishey.spawnedit.pixelmon.SpawnInfoPokemon;

	// Utility class that generates data for TableView display from SpawnEntry domain objects. Each SpawnEntry
	// can potentially have multiple table entries - one for each permutation of its array-form properties.
	// TableEntryFactory takes the Cartesian product of such arrays and creates new HashMap of entry data
	// for each result.

public class TableEntryFactory {

	public static ArrayList<HashMap<ColumnId, Object>> buildEntries(SpawnEntry spawnEntry) {
		ArrayList<HashMap<ColumnId, Object>> tableEntries = new ArrayList<HashMap<ColumnId, Object>>();
		
		HashMap<ColumnId, Object> tableEntryPrototype = processIndividualValues(spawnEntry);
		ArrayList<HashMap<ColumnId, Object>> arrayValuePermutations = processArrayValues(spawnEntry);
		
		for (HashMap<ColumnId, Object> arrayValueSet : arrayValuePermutations) {
			HashMap<ColumnId, Object> newEntry = new HashMap<ColumnId, Object>(tableEntryPrototype);
			newEntry.putAll(arrayValueSet);
			tableEntries.add(newEntry);
		}
		
		return tableEntries;
	}
	
	public static HashMap<ColumnId, Object> processIndividualValues(SpawnEntry spawnEntry) {
		HashMap<ColumnId, Object> tableEntryPrototype = new HashMap<ColumnId, Object>();
		SpawnInfoPokemon spawnInfo = spawnEntry.getSpawnInfo();
		
		tableEntryPrototype.computeIfAbsent(ColumnId.spawnSetId, val -> spawnEntry.getSpawnSetId());
		tableEntryPrototype.computeIfAbsent(ColumnId.spawnSetIndex, val -> spawnEntry.getSpawnSetIndex());
		tableEntryPrototype.computeIfAbsent(ColumnId.intervalType, val -> spawnInfo.getInterval());
		tableEntryPrototype.computeIfAbsent(ColumnId.pokemonSpecs, val -> spawnInfo.getSpecs());
		tableEntryPrototype.computeIfAbsent(ColumnId.requiredSpace, val -> spawnInfo.getRequiredSpace());
		tableEntryPrototype.computeIfAbsent(ColumnId.rarity, val -> spawnInfo.getRarity());
		tableEntryPrototype.computeIfAbsent(ColumnId.rarityMultipliers, val -> spawnInfo.getRarityMultipliers());
		tableEntryPrototype.computeIfAbsent(ColumnId.percentage, val -> spawnInfo.getPercentage());
		tableEntryPrototype.computeIfAbsent(ColumnId.minLevel, val -> spawnInfo.getMinLevel());
		tableEntryPrototype.computeIfAbsent(ColumnId.maxLevel, val -> spawnInfo.getMaxLevel());
		tableEntryPrototype.computeIfAbsent(ColumnId.specificShinyRate, val -> spawnInfo.getSpawnSpecificShinyRate());
		tableEntryPrototype.computeIfAbsent(ColumnId.specificBossRate, val ->  spawnInfo.getSpawnSpecificBossRate());
		tableEntryPrototype.computeIfAbsent(ColumnId.specificPokeRusRate, val -> spawnInfo.getSpawnSpecificPokerusRate());
		tableEntryPrototype.computeIfAbsent(ColumnId.heldItems, val -> spawnInfo.getHeldItems());
		
		if (spawnInfo.getSpec() != null) {
			tableEntryPrototype.computeIfAbsent(ColumnId.pokemonSpecSpecies, val -> spawnInfo.getSpec().getTypeName());
			tableEntryPrototype.computeIfAbsent(ColumnId.pokemonSpecLevel, val -> spawnInfo.getSpec().getLevel());
			tableEntryPrototype.computeIfAbsent(ColumnId.pokemonSpecGender, val -> spawnInfo.getSpec().getGender());
			//tableEntryPrototype.computeIfAbsent(ColumnId.pokemonSpecStatus, val -> spawnInfo.getSpec().getStatus());
			//tableEntryPrototype.computeIfAbsent(ColumnId.pokemonSpecGrowthSize, val -> spawnInfo.getSpec().getGrowth());
			tableEntryPrototype.computeIfAbsent(ColumnId.pokemonSpecNature, val -> spawnInfo.getSpec().getNature());
			tableEntryPrototype.computeIfAbsent(ColumnId.pokemonSpecFormId, val -> spawnInfo.getSpec().getForm());
			//tableEntryPrototype.computeIfAbsent(ColumnId.pokemonSpecPokeRusStage, val -> spawnInfo.getSpec().getPokerus());
			//tableEntryPrototype.computeIfAbsent(ColumnId.pokemonSpecRandom, val -> spawnInfo.getSpec().isRandom());
			tableEntryPrototype.computeIfAbsent(ColumnId.pokemonSpecCured, val -> spawnInfo.getSpec().isCured());
			tableEntryPrototype.computeIfAbsent(ColumnId.pokemonSpecShiny, val -> spawnInfo.getSpec().isShiny());
			tableEntryPrototype.computeIfAbsent(ColumnId.pokemonSpecEgg, val -> spawnInfo.getSpec().isEgg());
			//tableEntryPrototype.computeIfAbsent(ColumnId.pokemonSpecUntradeable, val -> spawnInfo.getSpec().isUntradeable());
			//tableEntryPrototype.computeIfAbsent(ColumnId.pokemonSpecUnbreedable, val -> spawnInfo.getSpec().isUnbreedable());
			//tableEntryPrototype.computeIfAbsent(ColumnId.pokemonSpecIVStats, val -> spawnInfo.getSpec().isUnbreedable());
			//tableEntryPrototype.computeIfAbsent(ColumnId.pokemonSpecEVStats, val -> spawnInfo.getSpec().isUnbreedable());
		} 
		
		if (spawnInfo.getCondition() != null) {
			tableEntryPrototype.computeIfAbsent(ColumnId.conditionMinX, val -> spawnInfo.getCondition().getMinX());
			tableEntryPrototype.computeIfAbsent(ColumnId.conditionMaxX, val -> spawnInfo.getCondition().getMaxX());
			tableEntryPrototype.computeIfAbsent(ColumnId.conditionMinZ, val -> spawnInfo.getCondition().getMinZ());
			tableEntryPrototype.computeIfAbsent(ColumnId.conditionMaxZ, val ->  spawnInfo.getCondition().getMaxZ());
			tableEntryPrototype.computeIfAbsent(ColumnId.conditionMinY, val -> spawnInfo.getCondition().getMinY());
			tableEntryPrototype.computeIfAbsent(ColumnId.conditionMaxY, val ->  spawnInfo.getCondition().getMaxY());
			tableEntryPrototype.computeIfAbsent(ColumnId.conditionMinLight, val -> spawnInfo.getCondition().getMinLightLevel());
			tableEntryPrototype.computeIfAbsent(ColumnId.conditionMaxLight, val -> spawnInfo.getCondition().getMaxLightLevel());
			tableEntryPrototype.computeIfAbsent(ColumnId.conditionRequiresSky, val -> spawnInfo.getCondition().isSeesSky());
			tableEntryPrototype.computeIfAbsent(ColumnId.conditionMoonPhase, val -> spawnInfo.getCondition().getMoonPhase());
			tableEntryPrototype.computeIfAbsent(ColumnId.conditionTag, val -> spawnInfo.getCondition().getTag());
			tableEntryPrototype.computeIfAbsent(ColumnId.conditionTemperature, val -> spawnInfo.getCondition().getTemperature());
		}
		
		if (spawnInfo.getAntiCondition() != null) {
			tableEntryPrototype.computeIfAbsent(ColumnId.antiConditionMinX, val -> spawnInfo.getAntiCondition().getMinX());
			tableEntryPrototype.computeIfAbsent(ColumnId.antiConditionMaxX, val -> spawnInfo.getAntiCondition().getMaxX());
			tableEntryPrototype.computeIfAbsent(ColumnId.antiConditionMinZ, val -> spawnInfo.getAntiCondition().getMinZ());
			tableEntryPrototype.computeIfAbsent(ColumnId.antiConditionMaxZ, val -> spawnInfo.getAntiCondition().getMaxZ());
			tableEntryPrototype.computeIfAbsent(ColumnId.antiConditionMinY, val -> spawnInfo.getAntiCondition().getMinY());
			tableEntryPrototype.computeIfAbsent(ColumnId.antiConditionMaxY, val -> spawnInfo.getAntiCondition().getMaxY());
			tableEntryPrototype.computeIfAbsent(ColumnId.antiConditionMinLight, val -> spawnInfo.getAntiCondition().getMinLightLevel());
			tableEntryPrototype.computeIfAbsent(ColumnId.antiConditionMaxLight, val -> spawnInfo.getAntiCondition().getMaxLightLevel());
			tableEntryPrototype.computeIfAbsent(ColumnId.antiConditionRequiresSky, val -> spawnInfo.getAntiCondition().isSeesSky());
			tableEntryPrototype.computeIfAbsent(ColumnId.antiConditionMoonPhase, val -> spawnInfo.getAntiCondition().getMoonPhase());
			tableEntryPrototype.computeIfAbsent(ColumnId.antiConditionTag, val -> spawnInfo.getAntiCondition().getTag());
			tableEntryPrototype.computeIfAbsent(ColumnId.antiConditionTemperature, val -> spawnInfo.getAntiCondition().getTemperature());
		}
		if (spawnInfo.getCompositeCondition() != null) {
			tableEntryPrototype.computeIfAbsent(ColumnId.compositeConditionConditions, val -> spawnInfo.getCompositeCondition().getConditions());
			tableEntryPrototype.computeIfAbsent(ColumnId.compositeConditionAntiConditions, val -> spawnInfo.getCompositeCondition().getAntiConditions());
		}
		
		return tableEntryPrototype;
	}
	
	public static ArrayList<HashMap<ColumnId, Object>> processArrayValues(SpawnEntry spawnEntry) {
		ArrayList<HashMap<ColumnId, Object>> tableEntrySets = new ArrayList<HashMap<ColumnId, Object>>();
		SpawnInfoPokemon spawnInfo = spawnEntry.getSpawnInfo();
		
		HashMap<ColumnId, Object[]> arrayProperties = new HashMap<ColumnId, Object[]>();
		
		arrayProperties.computeIfAbsent(ColumnId.spawnType, val -> spawnInfo.getStringLocationTypes());
		
		if (spawnInfo.getCondition() != null) {
			arrayProperties.computeIfAbsent(ColumnId.conditionTime, val -> spawnInfo.getCondition().getTimes());
			arrayProperties.computeIfAbsent(ColumnId.conditionWeather, val -> spawnInfo.getCondition().getWeathers());
			arrayProperties.computeIfAbsent(ColumnId.conditionBiome, val -> spawnInfo.getCondition().getStringBiomes());
			arrayProperties.computeIfAbsent(ColumnId.conditionWorld, val -> spawnInfo.getCondition().getWorlds());
			arrayProperties.computeIfAbsent(ColumnId.conditionDimension, val -> spawnInfo.getCondition().getWorlds());
			arrayProperties.computeIfAbsent(ColumnId.conditionRequiredBlock, val -> spawnInfo.getCondition().getBaseBlocks());
			arrayProperties.computeIfAbsent(ColumnId.conditionNearbyBlock, val -> spawnInfo.getCondition().getNeededNearbyBlocks());
			arrayProperties.computeIfAbsent(ColumnId.conditionVariant, val -> spawnInfo.getCondition().getVariant());
		}
		
		if (spawnInfo.getAntiCondition() != null) {
			arrayProperties.computeIfAbsent(ColumnId.antiConditionTime, val -> spawnInfo.getAntiCondition().getTimes());
			arrayProperties.computeIfAbsent(ColumnId.antiConditionWeather, val -> spawnInfo.getAntiCondition().getWeathers());
			arrayProperties.computeIfAbsent(ColumnId.antiConditionBiome, val -> spawnInfo.getAntiCondition().getStringBiomes());
			arrayProperties.computeIfAbsent(ColumnId.antiConditionWorld, val -> spawnInfo.getAntiCondition().getWorlds());
			arrayProperties.computeIfAbsent(ColumnId.antiConditionDimension, val -> spawnInfo.getAntiCondition().getWorlds());
			arrayProperties.computeIfAbsent(ColumnId.antiConditionRequiredBlock, val -> spawnInfo.getAntiCondition().getBaseBlocks());
			arrayProperties.computeIfAbsent(ColumnId.antiConditionNearbyBlock, val -> spawnInfo.getAntiCondition().getNeededNearbyBlocks());
			arrayProperties.computeIfAbsent(ColumnId.antiConditionVariant, val -> spawnInfo.getAntiCondition().getVariant());
		}
		
		cartesianProductLoop(arrayProperties, tableEntrySets, new HashMap<ColumnId, Object>());
		
		return tableEntrySets;
	}
	
	private static void cartesianProductLoop(HashMap<ColumnId, Object[]> arraysInput, ArrayList<HashMap<ColumnId, Object>> output, HashMap<ColumnId, Object> subOutput) {
		if (arraysInput.size() == 0) {
			output.add(subOutput);
		}
		else {
			ColumnId key = arraysInput.keySet().iterator().next();
			Object[] currentArray = arraysInput.get(key);
			
			for (int i = 0; i < currentArray.length; i++)
			{
				HashMap<ColumnId, Object> newSubOutput = new HashMap<ColumnId, Object>(subOutput);
				HashMap<ColumnId, Object[]> newSubInput = new HashMap<ColumnId, Object[]>(arraysInput);
				newSubInput.remove(key);
				newSubOutput.put(key, currentArray[i]);
				cartesianProductLoop(newSubInput,output,newSubOutput);
			}
		}
	}
}
