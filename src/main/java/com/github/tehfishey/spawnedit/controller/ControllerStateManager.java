package com.github.tehfishey.spawnedit.controller;

import java.util.HashMap;

import com.github.tehfishey.spawnedit.model.helpers.Enums.COLUMN_ID;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class ControllerStateManager {

	private final HashMap<COLUMN_ID, BooleanProperty> visibleColumns;
	
	public ControllerStateManager() {
		this.visibleColumns = new HashMap<COLUMN_ID, BooleanProperty>();
		createVisibleTableDefaults(visibleColumns);
	}
	
	public HashMap<COLUMN_ID, BooleanProperty> getVisibleColumns() { return visibleColumns; }
	
	private void createVisibleTableDefaults(HashMap<COLUMN_ID, BooleanProperty> visibleColumns) {
		
		visibleColumns.put(COLUMN_ID.spawnSetId, new SimpleBooleanProperty(true));
		visibleColumns.put(COLUMN_ID.spawnSetIndex, new SimpleBooleanProperty(true));
		visibleColumns.put(COLUMN_ID.pokemonSpecSpecies, new SimpleBooleanProperty(true));
		visibleColumns.put(COLUMN_ID.pokemonSpecLevel, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.pokemonSpecGender, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.pokemonSpecStatus, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.pokemonSpecGrowthSize, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.pokemonSpecNature, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.pokemonSpecFormId, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.pokemonSpecPokeRusStage, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.pokemonSpecRandom, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.pokemonSpecCured, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.pokemonSpecShiny, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.pokemonSpecEgg, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.pokemonSpecUntradeable, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.pokemonSpecUnbreedable, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.pokemonSpecIVStats, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.pokemonSpecEVStats, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.pokemonSpecs, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.spawnType, new SimpleBooleanProperty(true));
		visibleColumns.put(COLUMN_ID.intervalType, new SimpleBooleanProperty(true));
		visibleColumns.put(COLUMN_ID.requiredSpace, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.conditionTime, new SimpleBooleanProperty(true));
		visibleColumns.put(COLUMN_ID.conditionWeather, new SimpleBooleanProperty(true));
		visibleColumns.put(COLUMN_ID.conditionBiome, new SimpleBooleanProperty(true));
		visibleColumns.put(COLUMN_ID.conditionTemperature, new SimpleBooleanProperty(true));
		visibleColumns.put(COLUMN_ID.conditionWorld, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.conditionDimension, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.conditionRequiredBlock, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.conditionNearbyBlock, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.conditionVariant, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.conditionMinX, new SimpleBooleanProperty(true));
		visibleColumns.put(COLUMN_ID.conditionMaxX, new SimpleBooleanProperty(true));
		visibleColumns.put(COLUMN_ID.conditionMinZ, new SimpleBooleanProperty(true));
		visibleColumns.put(COLUMN_ID.conditionMaxZ, new SimpleBooleanProperty(true));
		visibleColumns.put(COLUMN_ID.conditionMinY, new SimpleBooleanProperty(true));
		visibleColumns.put(COLUMN_ID.conditionMaxY, new SimpleBooleanProperty(true));
		visibleColumns.put(COLUMN_ID.conditionMinLight, new SimpleBooleanProperty(true));
		visibleColumns.put(COLUMN_ID.conditionMaxLight, new SimpleBooleanProperty(true));
		visibleColumns.put(COLUMN_ID.conditionRequiresSky, new SimpleBooleanProperty(true));
		visibleColumns.put(COLUMN_ID.conditionMoonPhase, new SimpleBooleanProperty(true));
		visibleColumns.put(COLUMN_ID.conditionTag, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.antiConditionTime, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.antiConditionWeather, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.antiConditionBiome, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.antiConditionTemperature, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.antiConditionWorld, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.antiConditionDimension, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.antiConditionRequiredBlock, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.antiConditionNearbyBlock, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.antiConditionVariant, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.antiConditionMinX, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.antiConditionMaxX, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.antiConditionMinZ, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.antiConditionMaxZ, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.antiConditionMinY, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.antiConditionMaxY, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.antiConditionMinLight, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.antiConditionMaxLight, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.antiConditionRequiresSky, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.antiConditionMoonPhase, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.antiConditionTag, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.compositeConditionConditions, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.compositeConditionAntiConditions, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.rarity, new SimpleBooleanProperty(true));
		visibleColumns.put(COLUMN_ID.rarityMultipliers, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.percentage, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.minLevel, new SimpleBooleanProperty(true));
		visibleColumns.put(COLUMN_ID.maxLevel, new SimpleBooleanProperty(true));
		visibleColumns.put(COLUMN_ID.specificShinyRate, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.specificBossRate, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.specificPokeRusRate, new SimpleBooleanProperty(false));
		visibleColumns.put(COLUMN_ID.heldItems, new SimpleBooleanProperty(false));
	}
	
}
