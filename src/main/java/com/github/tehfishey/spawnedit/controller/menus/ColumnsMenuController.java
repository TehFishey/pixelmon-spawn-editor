package com.github.tehfishey.spawnedit.controller.menus;

import java.util.HashMap;
import java.util.Map.Entry;

import com.github.tehfishey.spawnedit.controller.ControllerStateManager;
import com.github.tehfishey.spawnedit.model.helpers.Enums.ColumnId;

import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class ColumnsMenuController {
	
	private final ControllerStateManager manager;
	private HashMap<ColumnId, CheckBox> checkBoxMap;
	
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
    	
    	for (Entry<ColumnId, CheckBox> checkBoxEntry : checkBoxMap.entrySet()) {
    		ColumnId id = checkBoxEntry.getKey();
    		CheckBox checkBox = checkBoxEntry.getValue();
    		BooleanProperty boundBoolean = manager.getVisibleColumns().get(id);
    		
    		checkBox.setSelected(boundBoolean.get());
    		boundBoolean.bind(checkBox.selectedProperty());
    	}
    }
    
    private HashMap<ColumnId, CheckBox> mapCheckBoxes() {
    	HashMap<ColumnId, CheckBox> newMap = new HashMap<ColumnId, CheckBox>();
    	
    	newMap.put(ColumnId.spawnSetId, columnMenuSpawnInfo);
		newMap.put(ColumnId.spawnSetIndex, columnMenuSpawnIndex);
		newMap.put(ColumnId.pokemonSpecSpecies, columnMenuSpecSpecies);
		newMap.put(ColumnId.pokemonSpecLevel, columnMenuSpecLevel);
		newMap.put(ColumnId.pokemonSpecGender, columnMenuSpecGender);
		newMap.put(ColumnId.pokemonSpecStatus, columnMenuSpecStatus);
		newMap.put(ColumnId.pokemonSpecGrowthSize, columnMenuSpecGrowthSize);
		newMap.put(ColumnId.pokemonSpecNature, columnMenuSpecNature);
		newMap.put(ColumnId.pokemonSpecFormId, columnMenuSpecFormId);
		newMap.put(ColumnId.pokemonSpecPokeRusStage, columnMenuSpecPokeRusStage);
		newMap.put(ColumnId.pokemonSpecRandom, columnMenuSpecRandom);
		newMap.put(ColumnId.pokemonSpecCured, columnMenuSpecCured);
		newMap.put(ColumnId.pokemonSpecShiny, columnMenuSpecShiny);
		newMap.put(ColumnId.pokemonSpecEgg, columnMenuSpecEgg);
		newMap.put(ColumnId.pokemonSpecUntradeable, columnMenuSpecUntradeable);
		newMap.put(ColumnId.pokemonSpecUnbreedable, columnMenuSpecUnbreedable);
		newMap.put(ColumnId.pokemonSpecIVStats, columnMenuSpecIVStats);
		newMap.put(ColumnId.pokemonSpecEVStats, columnMenuSpecEVStats);
		newMap.put(ColumnId.pokemonSpecs, columnMenuPokemonSpecs);
		newMap.put(ColumnId.spawnType, columnMenuSpawnType);
		newMap.put(ColumnId.intervalType, columnMenuIntervalType);
		newMap.put(ColumnId.requiredSpace, columnMenuRequiredSpace);
		newMap.put(ColumnId.conditionTime, columnMenuConditionTime);
		newMap.put(ColumnId.conditionWeather, columnMenuConditionWeather);
		newMap.put(ColumnId.conditionBiome, columnMenuConditionBiome);
		newMap.put(ColumnId.conditionTemperature, columnMenuConditionTemperature);
		newMap.put(ColumnId.conditionWorld, columnMenuConditionWorld);
		newMap.put(ColumnId.conditionDimension, columnMenuConditionDimension);
		newMap.put(ColumnId.conditionRequiredBlock, columnMenuConditionRequiredBlock);
		newMap.put(ColumnId.conditionNearbyBlock, columnMenuConditionNearbyBlock);
		newMap.put(ColumnId.conditionVariant, columnMenuConditionVariant);
		newMap.put(ColumnId.conditionMinX, columnMenuConditionMinX);
		newMap.put(ColumnId.conditionMaxX, columnMenuConditionMaxX);
		newMap.put(ColumnId.conditionMinZ, columnMenuConditionMinZ);
		newMap.put(ColumnId.conditionMaxZ, columnMenuConditionMaxZ);
		newMap.put(ColumnId.conditionMinY, columnMenuConditionMinY);
		newMap.put(ColumnId.conditionMaxY, columnMenuConditionMaxY);
		newMap.put(ColumnId.conditionMinLight, columnMenuConditionMinLight);
		newMap.put(ColumnId.conditionMaxLight, columnMenuConditionMaxLight);
		newMap.put(ColumnId.conditionRequiresSky, columnMenuConditionRequiresSky);
		newMap.put(ColumnId.conditionMoonPhase, columnMenuConditionMoonPhase);
		newMap.put(ColumnId.conditionTag, columnMenuConditionTag);
		newMap.put(ColumnId.antiConditionTime, columnMenuAntiConditionTime);
		newMap.put(ColumnId.antiConditionWeather, columnMenuAntiConditionWeather);
		newMap.put(ColumnId.antiConditionBiome, columnMenuAntiConditionBiome);
		newMap.put(ColumnId.antiConditionTemperature, columnMenuAntiConditionTemperature);
		newMap.put(ColumnId.antiConditionWorld, columnMenuAntiConditionWorld);
		newMap.put(ColumnId.antiConditionDimension, columnMenuAntiConditionDimension);
		newMap.put(ColumnId.antiConditionRequiredBlock, columnMenuAntiConditionRequiredBlock);
		newMap.put(ColumnId.antiConditionNearbyBlock, columnMenuAntiConditionNearbyBlock);
		newMap.put(ColumnId.antiConditionVariant, columnMenuAntiConditionVariant);
		newMap.put(ColumnId.antiConditionMinX, columnMenuAntiConditionMinX);
		newMap.put(ColumnId.antiConditionMaxX, columnMenuAntiConditionMaxX);
		newMap.put(ColumnId.antiConditionMinZ, columnMenuAntiConditionMinZ);
		newMap.put(ColumnId.antiConditionMaxZ, columnMenuAntiConditionMaxZ);
		newMap.put(ColumnId.antiConditionMinY, columnMenuAntiConditionMinY);
		newMap.put(ColumnId.antiConditionMaxY, columnMenuAntiConditionMaxY);
		newMap.put(ColumnId.antiConditionMinLight, columnMenuAntiConditionMinLight);
		newMap.put(ColumnId.antiConditionMaxLight, columnMenuAntiConditionMaxLight);
		newMap.put(ColumnId.antiConditionRequiresSky, columnMenuAntiConditionRequiresSky);
		newMap.put(ColumnId.antiConditionMoonPhase, columnMenuAntiConditionMoonPhase);
		newMap.put(ColumnId.antiConditionTag, columnMenuAntiConditionTag);
		newMap.put(ColumnId.compositeConditionConditions, columnMenuCompositeConditionConditions);
		newMap.put(ColumnId.compositeConditionAntiConditions, columnMenuCompositeConditionAntiConditions);
		newMap.put(ColumnId.rarity, columnMenuRarity);
		newMap.put(ColumnId.rarityMultipliers, columnMenuRarityMultipliers);
		newMap.put(ColumnId.percentage, columnMenuPercentage);
		newMap.put(ColumnId.minLevel, columnMenuMinLevel);
		newMap.put(ColumnId.maxLevel, columnMenuMaxLevel);
		newMap.put(ColumnId.specificShinyRate, columnMenuSpecificShinyRate);
		newMap.put(ColumnId.specificBossRate, columnMenuSpecificBossRate);
		newMap.put(ColumnId.specificPokeRusRate, columnMenuSpecificPokeRusRate);
		newMap.put(ColumnId.heldItems, columnMenuHeldItems);
		
		return newMap;
    }
}
