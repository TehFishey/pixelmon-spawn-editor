package com.github.tehfishey.spawnedit.controller;

import java.util.HashMap;
import java.util.Map.Entry;

import com.github.tehfishey.spawnedit.model.helpers.Enums.COLUMN_ID;

import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class ColumnsMenuController {
	
	private final ControllerStateManager manager;
	private HashMap<COLUMN_ID, CheckBox> checkBoxMap;
	
	@FXML private CheckBox columnMenuSpawnInfo;
	@FXML private CheckBox columnMenuSpawnIndex;
	@FXML private CheckBox columnMenuSpecSpecies;
	@FXML private CheckBox columnMenuSpecLevel;
	@FXML private CheckBox columnMenuSpecGender;
	@FXML private CheckBox columnMenuSpecStatus;
	@FXML private CheckBox columnMenuSpecGrowthSize;
	@FXML private CheckBox columnMenuSpecNature;
	@FXML private CheckBox columnMenuSpecFormId;
	@FXML private CheckBox columnMenuSpecPokeRusStage;
	@FXML private CheckBox columnMenuSpecRandom;
	@FXML private CheckBox columnMenuSpecCured;
	@FXML private CheckBox columnMenuSpecShiny;
	@FXML private CheckBox columnMenuSpecEgg;
	@FXML private CheckBox columnMenuSpecUntradeable;
	@FXML private CheckBox columnMenuSpecUnbreedable;
	@FXML private CheckBox columnMenuSpecIVStats;
	@FXML private CheckBox columnMenuSpecEVStats;
	@FXML private CheckBox columnMenuPokemonSpecs;
	@FXML private CheckBox columnMenuSpawnType;
	@FXML private CheckBox columnMenuIntervalType;
	@FXML private CheckBox columnMenuRequiredSpace;
	@FXML private CheckBox columnMenuConditionTime;
	@FXML private CheckBox columnMenuConditionWeather;
	@FXML private CheckBox columnMenuConditionBiome;
	@FXML private CheckBox columnMenuConditionTemperature;
	@FXML private CheckBox columnMenuConditionWorld;
	@FXML private CheckBox columnMenuConditionDimension;
	@FXML private CheckBox columnMenuConditionRequiredBlock;
	@FXML private CheckBox columnMenuConditionNearbyBlock;
	@FXML private CheckBox columnMenuConditionVariant;
	@FXML private CheckBox columnMenuConditionMinX;
	@FXML private CheckBox columnMenuConditionMaxX;
	@FXML private CheckBox columnMenuConditionMinZ;
	@FXML private CheckBox columnMenuConditionMaxZ;
	@FXML private CheckBox columnMenuConditionMinY;
	@FXML private CheckBox columnMenuConditionMaxY;
	@FXML private CheckBox columnMenuConditionMinLight;
	@FXML private CheckBox columnMenuConditionMaxLight;
	@FXML private CheckBox columnMenuConditionRequiresSky;
	@FXML private CheckBox columnMenuConditionMoonPhase;
	@FXML private CheckBox columnMenuConditionTag;
	@FXML private CheckBox columnMenuAntiConditionTime;
	@FXML private CheckBox columnMenuAntiConditionWeather;
	@FXML private CheckBox columnMenuAntiConditionBiome;
	@FXML private CheckBox columnMenuAntiConditionTemperature;
	@FXML private CheckBox columnMenuAntiConditionWorld;
	@FXML private CheckBox columnMenuAntiConditionDimension;
	@FXML private CheckBox columnMenuAntiConditionRequiredBlock;
	@FXML private CheckBox columnMenuAntiConditionNearbyBlock;
	@FXML private CheckBox columnMenuAntiConditionVariant;
	@FXML private CheckBox columnMenuAntiConditionMinX;
	@FXML private CheckBox columnMenuAntiConditionMaxX;
	@FXML private CheckBox columnMenuAntiConditionMinZ;
	@FXML private CheckBox columnMenuAntiConditionMaxZ;
	@FXML private CheckBox columnMenuAntiConditionMinY;
	@FXML private CheckBox columnMenuAntiConditionMaxY;
	@FXML private CheckBox columnMenuAntiConditionMinLight;
	@FXML private CheckBox columnMenuAntiConditionMaxLight;
	@FXML private CheckBox columnMenuAntiConditionRequiresSky;
	@FXML private CheckBox columnMenuAntiConditionMoonPhase;
	@FXML private CheckBox columnMenuAntiConditionTag;
	@FXML private CheckBox columnMenuCompositeConditionConditions;
	@FXML private CheckBox columnMenuCompositeConditionAntiConditions;
	@FXML private CheckBox columnMenuRarity;
	@FXML private CheckBox columnMenuRarityMultipliers;
	@FXML private CheckBox columnMenuPercentage;
	@FXML private CheckBox columnMenuMinLevel;
	@FXML private CheckBox columnMenuMaxLevel;
	@FXML private CheckBox columnMenuSpecificShinyRate;
	@FXML private CheckBox columnMenuSpecificBossRate;
	@FXML private CheckBox columnMenuSpecificPokeRusRate;
	@FXML private CheckBox columnMenuHeldItems;
	
	
    public ColumnsMenuController(ControllerStateManager manager) {
    	this.manager = manager;
    }
    
    public void initialize() {
    	checkBoxMap = mapCheckBoxes();
    	
    	for (Entry<COLUMN_ID, CheckBox> checkBoxEntry : checkBoxMap.entrySet()) {
    		COLUMN_ID id = checkBoxEntry.getKey();
    		CheckBox checkBox = checkBoxEntry.getValue();
    		BooleanProperty boundBoolean = manager.getVisibleColumns().get(id);
    		
    		checkBox.setSelected(boundBoolean.get());
    		boundBoolean.bind(checkBox.selectedProperty());
    	}
    }
    
    private HashMap<COLUMN_ID, CheckBox> mapCheckBoxes() {
    	HashMap<COLUMN_ID, CheckBox> newMap = new HashMap<COLUMN_ID, CheckBox>();
    	
    	newMap.put(COLUMN_ID.spawnSetId, columnMenuSpawnInfo);
		newMap.put(COLUMN_ID.spawnSetIndex, columnMenuSpawnIndex);
		newMap.put(COLUMN_ID.pokemonSpecSpecies, columnMenuSpecSpecies);
		newMap.put(COLUMN_ID.pokemonSpecLevel, columnMenuSpecLevel);
		newMap.put(COLUMN_ID.pokemonSpecGender, columnMenuSpecGender);
		newMap.put(COLUMN_ID.pokemonSpecStatus, columnMenuSpecStatus);
		newMap.put(COLUMN_ID.pokemonSpecGrowthSize, columnMenuSpecGrowthSize);
		newMap.put(COLUMN_ID.pokemonSpecNature, columnMenuSpecNature);
		newMap.put(COLUMN_ID.pokemonSpecFormId, columnMenuSpecFormId);
		newMap.put(COLUMN_ID.pokemonSpecPokeRusStage, columnMenuSpecPokeRusStage);
		newMap.put(COLUMN_ID.pokemonSpecRandom, columnMenuSpecRandom);
		newMap.put(COLUMN_ID.pokemonSpecCured, columnMenuSpecCured);
		newMap.put(COLUMN_ID.pokemonSpecShiny, columnMenuSpecShiny);
		newMap.put(COLUMN_ID.pokemonSpecEgg, columnMenuSpecEgg);
		newMap.put(COLUMN_ID.pokemonSpecUntradeable, columnMenuSpecUntradeable);
		newMap.put(COLUMN_ID.pokemonSpecUnbreedable, columnMenuSpecUnbreedable);
		newMap.put(COLUMN_ID.pokemonSpecIVStats, columnMenuSpecIVStats);
		newMap.put(COLUMN_ID.pokemonSpecEVStats, columnMenuSpecEVStats);
		newMap.put(COLUMN_ID.pokemonSpecs, columnMenuPokemonSpecs);
		newMap.put(COLUMN_ID.spawnType, columnMenuSpawnType);
		newMap.put(COLUMN_ID.intervalType, columnMenuIntervalType);
		newMap.put(COLUMN_ID.requiredSpace, columnMenuRequiredSpace);
		newMap.put(COLUMN_ID.conditionTime, columnMenuConditionTime);
		newMap.put(COLUMN_ID.conditionWeather, columnMenuConditionWeather);
		newMap.put(COLUMN_ID.conditionBiome, columnMenuConditionBiome);
		newMap.put(COLUMN_ID.conditionTemperature, columnMenuConditionTemperature);
		newMap.put(COLUMN_ID.conditionWorld, columnMenuConditionWorld);
		newMap.put(COLUMN_ID.conditionDimension, columnMenuConditionDimension);
		newMap.put(COLUMN_ID.conditionRequiredBlock, columnMenuConditionRequiredBlock);
		newMap.put(COLUMN_ID.conditionNearbyBlock, columnMenuConditionNearbyBlock);
		newMap.put(COLUMN_ID.conditionVariant, columnMenuConditionVariant);
		newMap.put(COLUMN_ID.conditionMinX, columnMenuConditionMinX);
		newMap.put(COLUMN_ID.conditionMaxX, columnMenuConditionMaxX);
		newMap.put(COLUMN_ID.conditionMinZ, columnMenuConditionMinZ);
		newMap.put(COLUMN_ID.conditionMaxZ, columnMenuConditionMaxZ);
		newMap.put(COLUMN_ID.conditionMinY, columnMenuConditionMinY);
		newMap.put(COLUMN_ID.conditionMaxY, columnMenuConditionMaxY);
		newMap.put(COLUMN_ID.conditionMinLight, columnMenuConditionMinLight);
		newMap.put(COLUMN_ID.conditionMaxLight, columnMenuConditionMaxLight);
		newMap.put(COLUMN_ID.conditionRequiresSky, columnMenuConditionRequiresSky);
		newMap.put(COLUMN_ID.conditionMoonPhase, columnMenuConditionMoonPhase);
		newMap.put(COLUMN_ID.conditionTag, columnMenuConditionTag);
		newMap.put(COLUMN_ID.antiConditionTime, columnMenuAntiConditionTime);
		newMap.put(COLUMN_ID.antiConditionWeather, columnMenuAntiConditionWeather);
		newMap.put(COLUMN_ID.antiConditionBiome, columnMenuAntiConditionBiome);
		newMap.put(COLUMN_ID.antiConditionTemperature, columnMenuAntiConditionTemperature);
		newMap.put(COLUMN_ID.antiConditionWorld, columnMenuAntiConditionWorld);
		newMap.put(COLUMN_ID.antiConditionDimension, columnMenuAntiConditionDimension);
		newMap.put(COLUMN_ID.antiConditionRequiredBlock, columnMenuAntiConditionRequiredBlock);
		newMap.put(COLUMN_ID.antiConditionNearbyBlock, columnMenuAntiConditionNearbyBlock);
		newMap.put(COLUMN_ID.antiConditionVariant, columnMenuAntiConditionVariant);
		newMap.put(COLUMN_ID.antiConditionMinX, columnMenuAntiConditionMinX);
		newMap.put(COLUMN_ID.antiConditionMaxX, columnMenuAntiConditionMaxX);
		newMap.put(COLUMN_ID.antiConditionMinZ, columnMenuAntiConditionMinZ);
		newMap.put(COLUMN_ID.antiConditionMaxZ, columnMenuAntiConditionMaxZ);
		newMap.put(COLUMN_ID.antiConditionMinY, columnMenuAntiConditionMinY);
		newMap.put(COLUMN_ID.antiConditionMaxY, columnMenuAntiConditionMaxY);
		newMap.put(COLUMN_ID.antiConditionMinLight, columnMenuAntiConditionMinLight);
		newMap.put(COLUMN_ID.antiConditionMaxLight, columnMenuAntiConditionMaxLight);
		newMap.put(COLUMN_ID.antiConditionRequiresSky, columnMenuAntiConditionRequiresSky);
		newMap.put(COLUMN_ID.antiConditionMoonPhase, columnMenuAntiConditionMoonPhase);
		newMap.put(COLUMN_ID.antiConditionTag, columnMenuAntiConditionTag);
		newMap.put(COLUMN_ID.compositeConditionConditions, columnMenuCompositeConditionConditions);
		newMap.put(COLUMN_ID.compositeConditionAntiConditions, columnMenuCompositeConditionAntiConditions);
		newMap.put(COLUMN_ID.rarity, columnMenuRarity);
		newMap.put(COLUMN_ID.rarityMultipliers, columnMenuRarityMultipliers);
		newMap.put(COLUMN_ID.percentage, columnMenuPercentage);
		newMap.put(COLUMN_ID.minLevel, columnMenuMinLevel);
		newMap.put(COLUMN_ID.maxLevel, columnMenuMaxLevel);
		newMap.put(COLUMN_ID.specificShinyRate, columnMenuSpecificShinyRate);
		newMap.put(COLUMN_ID.specificBossRate, columnMenuSpecificBossRate);
		newMap.put(COLUMN_ID.specificPokeRusRate, columnMenuSpecificPokeRusRate);
		newMap.put(COLUMN_ID.heldItems, columnMenuHeldItems);
		
		return newMap;
    }
}
