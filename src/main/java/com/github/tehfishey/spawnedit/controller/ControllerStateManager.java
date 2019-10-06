package com.github.tehfishey.spawnedit.controller;

import java.util.HashMap;

import com.github.tehfishey.spawnedit.model.Enums.ColumnId;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class ControllerStateManager {

	private final HashMap<ColumnId, BooleanProperty> visibleColumns;
	
	public ControllerStateManager() {
		this.visibleColumns = new HashMap<ColumnId, BooleanProperty>();
		createVisibleTableDefaults(visibleColumns);
	}
	
	public HashMap<ColumnId, BooleanProperty> getVisibleColumns() { return visibleColumns; }
	
	private void createVisibleTableDefaults(HashMap<ColumnId, BooleanProperty> visibleColumns) {
		
		visibleColumns.put(ColumnId.spawnSetId, new SimpleBooleanProperty(true));
		visibleColumns.put(ColumnId.spawnSetIndex, new SimpleBooleanProperty(true));
		visibleColumns.put(ColumnId.pokemonSpecSpecies, new SimpleBooleanProperty(true));
		visibleColumns.put(ColumnId.pokemonSpecLevel, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.pokemonSpecGender, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.pokemonSpecStatus, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.pokemonSpecGrowthSize, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.pokemonSpecNature, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.pokemonSpecFormId, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.pokemonSpecPokeRusStage, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.pokemonSpecRandom, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.pokemonSpecCured, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.pokemonSpecShiny, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.pokemonSpecEgg, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.pokemonSpecUntradeable, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.pokemonSpecUnbreedable, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.pokemonSpecIVStats, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.pokemonSpecEVStats, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.pokemonSpecs, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.spawnType, new SimpleBooleanProperty(true));
		visibleColumns.put(ColumnId.intervalType, new SimpleBooleanProperty(true));
		visibleColumns.put(ColumnId.requiredSpace, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.conditionTime, new SimpleBooleanProperty(true));
		visibleColumns.put(ColumnId.conditionWeather, new SimpleBooleanProperty(true));
		visibleColumns.put(ColumnId.conditionBiome, new SimpleBooleanProperty(true));
		visibleColumns.put(ColumnId.conditionTemperature, new SimpleBooleanProperty(true));
		visibleColumns.put(ColumnId.conditionWorld, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.conditionDimension, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.conditionRequiredBlock, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.conditionNearbyBlock, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.conditionVariant, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.conditionMinX, new SimpleBooleanProperty(true));
		visibleColumns.put(ColumnId.conditionMaxX, new SimpleBooleanProperty(true));
		visibleColumns.put(ColumnId.conditionMinZ, new SimpleBooleanProperty(true));
		visibleColumns.put(ColumnId.conditionMaxZ, new SimpleBooleanProperty(true));
		visibleColumns.put(ColumnId.conditionMinY, new SimpleBooleanProperty(true));
		visibleColumns.put(ColumnId.conditionMaxY, new SimpleBooleanProperty(true));
		visibleColumns.put(ColumnId.conditionMinLight, new SimpleBooleanProperty(true));
		visibleColumns.put(ColumnId.conditionMaxLight, new SimpleBooleanProperty(true));
		visibleColumns.put(ColumnId.conditionRequiresSky, new SimpleBooleanProperty(true));
		visibleColumns.put(ColumnId.conditionMoonPhase, new SimpleBooleanProperty(true));
		visibleColumns.put(ColumnId.conditionTag, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.antiConditionTime, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.antiConditionWeather, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.antiConditionBiome, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.antiConditionTemperature, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.antiConditionWorld, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.antiConditionDimension, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.antiConditionRequiredBlock, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.antiConditionNearbyBlock, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.antiConditionVariant, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.antiConditionMinX, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.antiConditionMaxX, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.antiConditionMinZ, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.antiConditionMaxZ, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.antiConditionMinY, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.antiConditionMaxY, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.antiConditionMinLight, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.antiConditionMaxLight, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.antiConditionRequiresSky, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.antiConditionMoonPhase, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.antiConditionTag, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.compositeConditionConditions, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.compositeConditionAntiConditions, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.rarity, new SimpleBooleanProperty(true));
		visibleColumns.put(ColumnId.rarityMultipliers, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.percentage, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.minLevel, new SimpleBooleanProperty(true));
		visibleColumns.put(ColumnId.maxLevel, new SimpleBooleanProperty(true));
		visibleColumns.put(ColumnId.specificShinyRate, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.specificBossRate, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.specificPokeRusRate, new SimpleBooleanProperty(false));
		visibleColumns.put(ColumnId.heldItems, new SimpleBooleanProperty(false));
	}
	
}
