package com.github.tehfishey.spawnedit.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import com.github.tehfishey.spawnedit.model.Model;
import com.github.tehfishey.spawnedit.model.SpawnEntry;
import com.github.tehfishey.spawnedit.model.helpers.Enums.COLUMN_ID;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class MainTableController implements Initializable {
	
	private final ControllerStateManager manager;
	private final Model model;
	private final PropertyChangeListener modelListener;
	private HashMap<COLUMN_ID, TableColumn<HashMap<COLUMN_ID, Object>, String>> columnMap;
	private final ObservableList<HashMap<COLUMN_ID, Object>> dataList;
	
	@FXML private TableView<HashMap<COLUMN_ID, Object>> tableView;
	
	@FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> spawnSetId;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> spawnSetIndex;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> pokemonSpecSpecies;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> pokemonSpecLevel;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> pokemonSpecGender;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> pokemonSpecStatus;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> pokemonSpecGrowthSize;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> pokemonSpecNature;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> pokemonSpecFormId;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> pokemonSpecPokeRusStage;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> pokemonSpecRandom;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> pokemonSpecCured;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> pokemonSpecShiny;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> pokemonSpecEgg;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> pokemonSpecUntradeable;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> pokemonSpecUnbreedable;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> pokemonSpecIVStats;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> pokemonSpecEVStats;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> pokemonSpecs;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> spawnType;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> intervalType;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> requiredSpace;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> conditionTime;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> conditionWeather;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> conditionBiome;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> conditionTemperature;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> conditionWorld;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> conditionDimension;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> conditionRequiredBlock;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> conditionNearbyBlock;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> conditionVariant;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> conditionMinX;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> conditionMaxX;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> conditionMinZ;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> conditionMaxZ;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> conditionMinY;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> conditionMaxY;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> conditionMinLight;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> conditionMaxLight;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> conditionRequiresSky;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> conditionMoonPhase;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> conditionTag;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> antiConditionTime;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> antiConditionWeather;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> antiConditionBiome;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> antiConditionTemperature;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> antiConditionWorld;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> antiConditionDimension;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> antiConditionRequiredBlock;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> antiConditionNearbyBlock;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> antiConditionVariant;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> antiConditionMinX;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> antiConditionMaxX;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> antiConditionMinZ;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> antiConditionMaxZ;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> antiConditionMinY;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> antiConditionMaxY;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> antiConditionMinLight;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> antiConditionMaxLight;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> antiConditionRequiresSky;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> antiConditionMoonPhase;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> antiConditionTag;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> compositeConditionConditions;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> compositeConditionAntiConditions;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> rarity;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> rarityMultipliers;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> percentage;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> minLevel;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> maxLevel;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> specificShinyRate;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> specificBossRate;
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> specificPokeRusRate;    
    @FXML private TableColumn<HashMap<COLUMN_ID, Object>, String> heldItems; 
    
    public MainTableController(ControllerStateManager manager, Model model) {
        this.manager = manager;
    	this.model = model;
        this.modelListener = new ModelUpdateListener(this);
        this.dataList = FXCollections.observableArrayList();
        model.registerListener(modelListener);
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		importModelData(dataList);
		columnMap = mapColumns();
		
		tableView.setItems(dataList);
		
		spawnSetId.setCellValueFactory(new MapValueFactory("spawnSetId"));
		spawnSetIndex.setCellValueFactory(new MapValueFactory("spawnSetIndex"));
		
		for (Entry<COLUMN_ID, TableColumn<HashMap<COLUMN_ID, Object>, String>> entry : columnMap.entrySet()) {
			TableColumn<HashMap<COLUMN_ID, Object>, String> column = entry.getValue();
			COLUMN_ID id = entry.getKey();
			
			column.visibleProperty().bind(manager.getVisibleColumns().get(id));
			column.setCellValueFactory(new MapValueFactory(id));
			column.setCellFactory(getStringCellFactory());
			
		}	
	}

	private Callback<TableColumn<HashMap<COLUMN_ID, Object>, String>, TableCell<HashMap<COLUMN_ID, Object>, String>> getStringCellFactory() {
		return new Callback<TableColumn<HashMap<COLUMN_ID, Object>, String>, TableCell<HashMap<COLUMN_ID, Object>, String>>() {
            @SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
            public TableCell call(TableColumn p) {
                return new TextFieldTableCell(new StringConverter() {
                    @Override
                    public String toString(Object t) {
                    	if (t != null) return t.toString();
                    	return("");
                    }
                    @Override
                    public Object fromString(String string) {
                        return string;
                    }                                    
                });
            }
		};
	}

	private void importModelData(ObservableList<HashMap<COLUMN_ID, Object>> dataList) {
		ArrayList<SpawnEntry> spawnEntries = model.getSpawnEntries();
		ArrayList<HashMap<COLUMN_ID, Object>> allTableEntries = new ArrayList<HashMap<COLUMN_ID, Object>>();
		
		for (SpawnEntry entry : spawnEntries) {
			ArrayList<HashMap<COLUMN_ID, Object>> tableEntries = entry.getTableEntries();
			for (HashMap<COLUMN_ID, Object> tableEntry : tableEntries) allTableEntries.add(tableEntry);
		}
		
		dataList.clear();
		dataList.addAll(allTableEntries);
	}
	
	private void addModelData(ObservableList<HashMap<COLUMN_ID, Object>> dataList, ArrayList<SpawnEntry> newSpawnEntries) {
		ArrayList<HashMap<COLUMN_ID, Object>> allTableEntries = new ArrayList<HashMap<COLUMN_ID, Object>>();
		
		for (SpawnEntry entry : newSpawnEntries) {
			ArrayList<HashMap<COLUMN_ID, Object>> tableEntries = entry.getTableEntries();
			for (HashMap<COLUMN_ID, Object> tableEntry : tableEntries) allTableEntries.add(tableEntry);
		}
		
		dataList.removeAll(allTableEntries);
		dataList.addAll(allTableEntries);
	}
	
	private void removeModelData(ObservableList<HashMap<COLUMN_ID, Object>> dataList, ArrayList<SpawnEntry> oldSpawnEntries) {
		ArrayList<HashMap<COLUMN_ID, Object>> allTableEntries = new ArrayList<HashMap<COLUMN_ID, Object>>();
		
		for (SpawnEntry entry : oldSpawnEntries) {
			ArrayList<HashMap<COLUMN_ID, Object>> tableEntries = entry.getTableEntries();
			for (HashMap<COLUMN_ID, Object> tableEntry : tableEntries) allTableEntries.add(tableEntry);
		}
		
		dataList.removeAll(allTableEntries);
	}
	
	private HashMap<COLUMN_ID, TableColumn<HashMap<COLUMN_ID, Object>, String>> mapColumns() {
		HashMap<COLUMN_ID, TableColumn<HashMap<COLUMN_ID, Object>, String>> newMap = new HashMap<COLUMN_ID, TableColumn<HashMap<COLUMN_ID, Object>, String>>();
	
		newMap.put(COLUMN_ID.spawnSetId, spawnSetId);
		newMap.put(COLUMN_ID.spawnSetIndex, spawnSetIndex);
		newMap.put(COLUMN_ID.pokemonSpecSpecies, pokemonSpecSpecies);
		newMap.put(COLUMN_ID.pokemonSpecLevel, pokemonSpecLevel);
		newMap.put(COLUMN_ID.pokemonSpecGender, pokemonSpecGender);
		newMap.put(COLUMN_ID.pokemonSpecStatus, pokemonSpecStatus);
		newMap.put(COLUMN_ID.pokemonSpecGrowthSize, pokemonSpecGrowthSize);
		newMap.put(COLUMN_ID.pokemonSpecNature, pokemonSpecNature);
		newMap.put(COLUMN_ID.pokemonSpecFormId, pokemonSpecFormId);
		newMap.put(COLUMN_ID.pokemonSpecPokeRusStage, pokemonSpecPokeRusStage);
		newMap.put(COLUMN_ID.pokemonSpecRandom, pokemonSpecRandom);
		newMap.put(COLUMN_ID.pokemonSpecCured, pokemonSpecCured);
		newMap.put(COLUMN_ID.pokemonSpecShiny, pokemonSpecShiny);
		newMap.put(COLUMN_ID.pokemonSpecEgg, pokemonSpecEgg);
		newMap.put(COLUMN_ID.pokemonSpecUntradeable, pokemonSpecUntradeable);
		newMap.put(COLUMN_ID.pokemonSpecUnbreedable, pokemonSpecUnbreedable);
		newMap.put(COLUMN_ID.pokemonSpecIVStats, pokemonSpecIVStats);
		newMap.put(COLUMN_ID.pokemonSpecEVStats, pokemonSpecEVStats);
		newMap.put(COLUMN_ID.pokemonSpecs, pokemonSpecs);
		newMap.put(COLUMN_ID.spawnType, spawnType);
		newMap.put(COLUMN_ID.intervalType, intervalType);
		newMap.put(COLUMN_ID.requiredSpace, requiredSpace);
		newMap.put(COLUMN_ID.conditionTime, conditionTime);
		newMap.put(COLUMN_ID.conditionWeather, conditionWeather);
		newMap.put(COLUMN_ID.conditionBiome, conditionBiome);
		newMap.put(COLUMN_ID.conditionTemperature, conditionTemperature);
		newMap.put(COLUMN_ID.conditionWorld, conditionWorld);
		newMap.put(COLUMN_ID.conditionDimension, conditionDimension);
		newMap.put(COLUMN_ID.conditionRequiredBlock, conditionRequiredBlock);
		newMap.put(COLUMN_ID.conditionNearbyBlock, conditionNearbyBlock);
		newMap.put(COLUMN_ID.conditionVariant, conditionVariant);
		newMap.put(COLUMN_ID.conditionMinX, conditionMinX);
		newMap.put(COLUMN_ID.conditionMaxX, conditionMaxX);
		newMap.put(COLUMN_ID.conditionMinZ, conditionMinZ);
		newMap.put(COLUMN_ID.conditionMaxZ, conditionMaxZ);
		newMap.put(COLUMN_ID.conditionMinY, conditionMinY);
		newMap.put(COLUMN_ID.conditionMaxY, conditionMaxY);
		newMap.put(COLUMN_ID.conditionMinLight, conditionMinLight);
		newMap.put(COLUMN_ID.conditionMaxLight, conditionMaxLight);
		newMap.put(COLUMN_ID.conditionRequiresSky, conditionRequiresSky);
		newMap.put(COLUMN_ID.conditionMoonPhase, conditionMoonPhase);
		newMap.put(COLUMN_ID.conditionTag, conditionTag);
		newMap.put(COLUMN_ID.antiConditionTime, antiConditionTime);
		newMap.put(COLUMN_ID.antiConditionWeather, antiConditionWeather);
		newMap.put(COLUMN_ID.antiConditionBiome, antiConditionBiome);
		newMap.put(COLUMN_ID.antiConditionTemperature, antiConditionTemperature);
		newMap.put(COLUMN_ID.antiConditionWorld, antiConditionWorld);
		newMap.put(COLUMN_ID.antiConditionDimension, antiConditionDimension);
		newMap.put(COLUMN_ID.antiConditionRequiredBlock, antiConditionRequiredBlock);
		newMap.put(COLUMN_ID.antiConditionNearbyBlock, antiConditionNearbyBlock);
		newMap.put(COLUMN_ID.antiConditionVariant, antiConditionVariant);
		newMap.put(COLUMN_ID.antiConditionMinX, antiConditionMinX);
		newMap.put(COLUMN_ID.antiConditionMaxX, antiConditionMaxX);
		newMap.put(COLUMN_ID.antiConditionMinZ, antiConditionMinZ);
		newMap.put(COLUMN_ID.antiConditionMaxZ, antiConditionMaxZ);
		newMap.put(COLUMN_ID.antiConditionMinY, antiConditionMinY);
		newMap.put(COLUMN_ID.antiConditionMaxY, antiConditionMaxY);
		newMap.put(COLUMN_ID.antiConditionMinLight, antiConditionMinLight);
		newMap.put(COLUMN_ID.antiConditionMaxLight, antiConditionMaxLight);
		newMap.put(COLUMN_ID.antiConditionRequiresSky, antiConditionRequiresSky);
		newMap.put(COLUMN_ID.antiConditionMoonPhase, antiConditionMoonPhase);
		newMap.put(COLUMN_ID.antiConditionTag, antiConditionTag);
		newMap.put(COLUMN_ID.compositeConditionConditions, compositeConditionConditions);
		newMap.put(COLUMN_ID.compositeConditionAntiConditions, compositeConditionAntiConditions);
		newMap.put(COLUMN_ID.rarity, rarity);
		newMap.put(COLUMN_ID.rarityMultipliers, rarityMultipliers);
		newMap.put(COLUMN_ID.percentage, percentage);
		newMap.put(COLUMN_ID.minLevel, minLevel);
		newMap.put(COLUMN_ID.maxLevel, maxLevel);
		newMap.put(COLUMN_ID.specificShinyRate, specificShinyRate);
		newMap.put(COLUMN_ID.specificBossRate, specificBossRate);
		newMap.put(COLUMN_ID.specificPokeRusRate, specificPokeRusRate);
		newMap.put(COLUMN_ID.heldItems, heldItems);

		return newMap;
	}
	
	private class ModelUpdateListener implements PropertyChangeListener {

		private final MainTableController parent;
		
		ModelUpdateListener(MainTableController parent) {
			this.parent = parent;
		}
		
		@Override
		@SuppressWarnings("unchecked")
		public void propertyChange(PropertyChangeEvent evt) {
			String propertyName = evt.getPropertyName();
			
			switch (propertyName) {
				case "spawnEntriesAdded" :
					parent.addModelData(dataList, (ArrayList<SpawnEntry>) evt.getNewValue());
					break;
				case "spawnEntriesRemoved" :
					parent.removeModelData(dataList, (ArrayList<SpawnEntry>) evt.getNewValue());
					break;
			}
		}
		
	}
	
}