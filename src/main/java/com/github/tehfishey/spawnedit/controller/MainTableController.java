package com.github.tehfishey.spawnedit.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import com.github.tehfishey.spawnedit.model.TableEntry;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainTableController implements Initializable {
	private HashMap<String, TableColumn<TableEntry, ?>> columnMap;
	
	@FXML private TableView<TableEntry> tableView;
	
    @FXML private TableColumn<TableEntry, SimpleStringProperty> entryId;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> spawnInfoId;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> pokemonSpecSpecies;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> pokemonSpecLevel;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> pokemonSpecGender;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> pokemonSpecStatus;
    @FXML private TableColumn<TableEntry, SimpleFloatProperty> pokemonSpecGrowthSize;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> pokemonSpecNature;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> pokemonSpecFormId;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> pokemonSpecPokeRusStage;
    @FXML private TableColumn<TableEntry, SimpleBooleanProperty> pokemonSpecRandom;
    @FXML private TableColumn<TableEntry, SimpleBooleanProperty> pokemonSpecCured;
    @FXML private TableColumn<TableEntry, SimpleBooleanProperty> pokemonSpecShiny;
    @FXML private TableColumn<TableEntry, SimpleBooleanProperty> pokemonSpecEgg;
    @FXML private TableColumn<TableEntry, SimpleBooleanProperty> pokemonSpecUntradeable;
    @FXML private TableColumn<TableEntry, SimpleBooleanProperty> pokemonSpecUnbreedable;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> pokemonSpecIVHP;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> pokemonSpecIVATK;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> pokemonSpecIVDEF;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> pokemonSpecIVSATK;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> pokemonSpecIVSDEF;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> pokemonSpecIVSPD;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> pokemonSpecEVHP;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> pokemonSpecEVATK;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> pokemonSpecEVDEF;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> pokemonSpecEVSATK;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> pokemonSpecEVSDEF;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> pokemonSpecEVSPD;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> spawnType;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> intervalType;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> requiredSpace;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> conditionTime;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> conditionWeather;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> conditionBiome;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> conditionTemperature;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> conditionWorld;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> conditionDimension;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> conditionRequiredBlock;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> conditionNearbyBlock;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> conditionVariant;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> conditionMinX;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> conditionMaxX;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> conditionMinZ;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> conditionMaxZ;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> conditionMinY;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> conditionMaxY;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> conditionMinLight;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> conditionMaxLight;
    @FXML private TableColumn<TableEntry, SimpleBooleanProperty> conditionRequiresSky;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> conditionMoonPhase;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> conditionTag;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> antiConditionTime;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> antiConditionWeather;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> antiConditionBiome;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> antiConditionTemperature;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> antiConditionWorld;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> antiConditionDimension;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> antiConditionRequiredBlock;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> antiConditionNearbyBlock;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> antiConditionVariant;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> antiConditionMinX;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> antiConditionMaxX;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> antiConditionMinZ;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> antiConditionMaxZ;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> antiConditionMinY;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> antiConditionMaxY;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> antiConditionMinLight;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> antiConditionMaxLight;
    @FXML private TableColumn<TableEntry, SimpleBooleanProperty> antiConditionRequiresSky;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> antiConditionMoonPhase;
    @FXML private TableColumn<TableEntry, SimpleStringProperty> antiConditionTag;
    @FXML private TableColumn<TableEntry, SimpleFloatProperty> rarity;
    @FXML private TableColumn<TableEntry, SimpleFloatProperty> percentage;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> minLevel;
    @FXML private TableColumn<TableEntry, SimpleIntegerProperty> maxLevel;
    @FXML private TableColumn<TableEntry, SimpleFloatProperty> specificShinyRate;
    @FXML private TableColumn<TableEntry, SimpleFloatProperty> specificBossRate;
    @FXML private TableColumn<TableEntry, SimpleFloatProperty> specificPokeRusRate;    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		columnMap = buildTableColumns();
		
		for (Entry<String, TableColumn<TableEntry, ?>> entry : columnMap.entrySet()) {
			TableColumn<TableEntry, ?> column = entry.getValue();
			String id = entry.getKey();
			
			column.setCellValueFactory(new PropertyValueFactory<TableEntry, Object>(id));
		}
		
		tableView.getItems().setAll(parseEntryList());
	}

	private TableEntry[] parseEntryList(){
        return new TableEntry[1];   
    }
	
	private HashMap<String, TableColumn<TableEntry, ?>> buildTableColumns() {
		HashMap<String, TableColumn<TableEntry, ?>> newMap = new HashMap<String, TableColumn<TableEntry, ?>>();
		
		newMap.put("entryId", entryId);
		newMap.put("spawnInfoId", spawnInfoId);
		newMap.put("pokemonSpecSpecies", pokemonSpecSpecies);
		newMap.put("pokemonSpecLevel", pokemonSpecLevel);
		newMap.put("pokemonSpecGender", pokemonSpecGender);
		newMap.put("pokemonSpecStatus", pokemonSpecStatus);
		newMap.put("pokemonSpecGrowthSize", pokemonSpecGrowthSize);
		newMap.put("pokemonSpecNature", pokemonSpecNature);
		newMap.put("pokemonSpecFormId", pokemonSpecFormId);
		newMap.put("pokemonSpecPokeRusStage", pokemonSpecPokeRusStage);
		newMap.put("pokemonSpecRandom", pokemonSpecRandom);
		newMap.put("pokemonSpecCured", pokemonSpecCured);
		newMap.put("pokemonSpecShiny", pokemonSpecShiny);
		newMap.put("pokemonSpecEgg", pokemonSpecEgg);
		newMap.put("pokemonSpecUntradeable", pokemonSpecUntradeable);
		newMap.put("pokemonSpecUnbreedable", pokemonSpecUnbreedable);
		newMap.put("pokemonSpecIVHP", pokemonSpecIVHP);
		newMap.put("pokemonSpecIVATK", pokemonSpecIVATK);
		newMap.put("pokemonSpecIVDEF", pokemonSpecIVDEF);
		newMap.put("pokemonSpecIVSATK", pokemonSpecIVSATK);
		newMap.put("pokemonSpecIVSDEF", pokemonSpecIVSDEF);
		newMap.put("pokemonSpecIVSPD", pokemonSpecIVSPD);
		newMap.put("pokemonSpecEVHP", pokemonSpecEVHP);
		newMap.put("pokemonSpecEVATK", pokemonSpecEVATK);
		newMap.put("pokemonSpecEVDEF", pokemonSpecEVDEF);
		newMap.put("pokemonSpecEVSATK", pokemonSpecEVSATK);
		newMap.put("pokemonSpecEVSDEF", pokemonSpecEVSDEF);
		newMap.put("pokemonSpecEVSPD", pokemonSpecEVSPD);
		newMap.put("spawnType", spawnType);
		newMap.put("intervalType", intervalType);
		newMap.put("requiredSpace", requiredSpace);
		newMap.put("conditionTime", conditionTime);
		newMap.put("conditionWeather", conditionWeather);
		newMap.put("conditionBiome", conditionBiome);
		newMap.put("conditionTemperature", conditionTemperature);
		newMap.put("conditionWorld", conditionWorld);
		newMap.put("conditionDimension", conditionDimension);
		newMap.put("conditionRequiredBlock", conditionRequiredBlock);
		newMap.put("conditionNearbyBlock", conditionNearbyBlock);
		newMap.put("conditionVariant", conditionVariant);
		newMap.put("conditionMinX", conditionMinX);
		newMap.put("conditionMaxX", conditionMaxX);
		newMap.put("conditionMinZ", conditionMinZ);
		newMap.put("conditionMaxZ", conditionMaxZ);
		newMap.put("conditionMinY", conditionMinY);
		newMap.put("conditionMaxY", conditionMaxY);
		newMap.put("conditionMinLight", conditionMinLight);
		newMap.put("conditionMaxLight", conditionMaxLight);
		newMap.put("conditionRequiresSky", conditionRequiresSky);
		newMap.put("conditionMoonPhase", conditionMoonPhase);
		newMap.put("conditionTag", conditionTag);
		newMap.put("antiConditionTime", antiConditionTime);
		newMap.put("antiConditionWeather", antiConditionWeather);
		newMap.put("antiConditionBiome", antiConditionBiome);
		newMap.put("antiConditionTemperature", antiConditionTemperature);
		newMap.put("antiConditionWorld", antiConditionWorld);
		newMap.put("antiConditionDimension", antiConditionDimension);
		newMap.put("antiConditionRequiredBlock", antiConditionRequiredBlock);
		newMap.put("antiConditionNearbyBlock", antiConditionNearbyBlock);
		newMap.put("antiConditionVariant", antiConditionVariant);
		newMap.put("antiConditionMinX", antiConditionMinX);
		newMap.put("antiConditionMaxX", antiConditionMaxX);
		newMap.put("antiConditionMinZ", antiConditionMinZ);
		newMap.put("antiConditionMaxZ", antiConditionMaxZ);
		newMap.put("antiConditionMinY", antiConditionMinY);
		newMap.put("antiConditionMaxY", antiConditionMaxY);
		newMap.put("antiConditionMinLight", antiConditionMinLight);
		newMap.put("antiConditionMaxLight", antiConditionMaxLight);
		newMap.put("antiConditionRequiresSky", antiConditionRequiresSky);
		newMap.put("antiConditionMoonPhase", antiConditionMoonPhase);
		newMap.put("antiConditionTag", antiConditionTag);
		newMap.put("rarity", rarity);
		newMap.put("percentage", percentage);
		newMap.put("minLevel", minLevel);
		newMap.put("maxLevel", maxLevel);
		newMap.put("specificShinyRate", specificShinyRate);
		newMap.put("specificBossRate", specificBossRate);
		newMap.put("specificPokeRusRate", specificPokeRusRate);
		
		return newMap;
	}
	

}