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
import com.github.tehfishey.spawnedit.model.helpers.Enums.ColumnId;

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
	private HashMap<ColumnId, TableColumn<HashMap<ColumnId, Object>, String>> columnMap;
	private final ObservableList<HashMap<ColumnId, Object>> dataList;
	
	@FXML private TableView<HashMap<ColumnId, Object>> tableView;
	
	@FXML private TableColumn<HashMap<ColumnId, Object>, String> spawnSetId;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> spawnSetIndex;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> pokemonSpecSpecies;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> pokemonSpecLevel;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> pokemonSpecGender;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> pokemonSpecStatus;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> pokemonSpecGrowthSize;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> pokemonSpecNature;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> pokemonSpecFormId;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> pokemonSpecPokeRusStage;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> pokemonSpecRandom;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> pokemonSpecCured;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> pokemonSpecShiny;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> pokemonSpecEgg;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> pokemonSpecUntradeable;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> pokemonSpecUnbreedable;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> pokemonSpecIVStats;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> pokemonSpecEVStats;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> pokemonSpecs;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> spawnType;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> intervalType;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> requiredSpace;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionTime;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionWeather;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionBiome;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionTemperature;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionWorld;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionDimension;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionRequiredBlock;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionNearbyBlock;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionVariant;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionMinX;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionMaxX;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionMinZ;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionMaxZ;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionMinY;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionMaxY;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionMinLight;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionMaxLight;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionRequiresSky;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionMoonPhase;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionTag;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionTime;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionWeather;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionBiome;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionTemperature;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionWorld;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionDimension;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionRequiredBlock;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionNearbyBlock;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionVariant;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionMinX;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionMaxX;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionMinZ;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionMaxZ;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionMinY;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionMaxY;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionMinLight;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionMaxLight;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionRequiresSky;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionMoonPhase;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionTag;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> compositeConditionConditions;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> compositeConditionAntiConditions;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> rarity;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> rarityMultipliers;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> percentage;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> minLevel;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> maxLevel;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> specificShinyRate;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> specificBossRate;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> specificPokeRusRate;    
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> heldItems; 
    
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
		
		for (Entry<ColumnId, TableColumn<HashMap<ColumnId, Object>, String>> entry : columnMap.entrySet()) {
			TableColumn<HashMap<ColumnId, Object>, String> column = entry.getValue();
			ColumnId id = entry.getKey();
			
			column.visibleProperty().bind(manager.getVisibleColumns().get(id));
			column.setCellValueFactory(new MapValueFactory(id));
			column.setCellFactory(getStringCellFactory());
			
		}	
	}

	private Callback<TableColumn<HashMap<ColumnId, Object>, String>, TableCell<HashMap<ColumnId, Object>, String>> getStringCellFactory() {
		return new Callback<TableColumn<HashMap<ColumnId, Object>, String>, TableCell<HashMap<ColumnId, Object>, String>>() {
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

	private void importModelData(ObservableList<HashMap<ColumnId, Object>> dataList) {
		ArrayList<SpawnEntry> spawnEntries = model.getSpawnEntries();
		ArrayList<HashMap<ColumnId, Object>> allTableEntries = new ArrayList<HashMap<ColumnId, Object>>();
		
		for (SpawnEntry entry : spawnEntries) {
			ArrayList<HashMap<ColumnId, Object>> tableEntries = entry.getTableEntries();
			for (HashMap<ColumnId, Object> tableEntry : tableEntries) allTableEntries.add(tableEntry);
		}
		
		dataList.clear();
		dataList.addAll(allTableEntries);
	}
	
	private void addModelData(ObservableList<HashMap<ColumnId, Object>> dataList, ArrayList<SpawnEntry> newSpawnEntries) {
		ArrayList<HashMap<ColumnId, Object>> allTableEntries = new ArrayList<HashMap<ColumnId, Object>>();
		
		for (SpawnEntry entry : newSpawnEntries) {
			ArrayList<HashMap<ColumnId, Object>> tableEntries = entry.getTableEntries();
			for (HashMap<ColumnId, Object> tableEntry : tableEntries) allTableEntries.add(tableEntry);
		}
		
		dataList.removeAll(allTableEntries);
		dataList.addAll(allTableEntries);
	}
	
	private void removeModelData(ObservableList<HashMap<ColumnId, Object>> dataList, ArrayList<SpawnEntry> oldSpawnEntries) {
		ArrayList<HashMap<ColumnId, Object>> allTableEntries = new ArrayList<HashMap<ColumnId, Object>>();
		
		for (SpawnEntry entry : oldSpawnEntries) {
			ArrayList<HashMap<ColumnId, Object>> tableEntries = entry.getTableEntries();
			for (HashMap<ColumnId, Object> tableEntry : tableEntries) allTableEntries.add(tableEntry);
		}
		
		dataList.removeAll(allTableEntries);
	}
	
	private HashMap<ColumnId, TableColumn<HashMap<ColumnId, Object>, String>> mapColumns() {
		HashMap<ColumnId, TableColumn<HashMap<ColumnId, Object>, String>> newMap = new HashMap<ColumnId, TableColumn<HashMap<ColumnId, Object>, String>>();
	
		newMap.put(ColumnId.spawnSetId, spawnSetId);
		newMap.put(ColumnId.spawnSetIndex, spawnSetIndex);
		newMap.put(ColumnId.pokemonSpecSpecies, pokemonSpecSpecies);
		newMap.put(ColumnId.pokemonSpecLevel, pokemonSpecLevel);
		newMap.put(ColumnId.pokemonSpecGender, pokemonSpecGender);
		newMap.put(ColumnId.pokemonSpecStatus, pokemonSpecStatus);
		newMap.put(ColumnId.pokemonSpecGrowthSize, pokemonSpecGrowthSize);
		newMap.put(ColumnId.pokemonSpecNature, pokemonSpecNature);
		newMap.put(ColumnId.pokemonSpecFormId, pokemonSpecFormId);
		newMap.put(ColumnId.pokemonSpecPokeRusStage, pokemonSpecPokeRusStage);
		newMap.put(ColumnId.pokemonSpecRandom, pokemonSpecRandom);
		newMap.put(ColumnId.pokemonSpecCured, pokemonSpecCured);
		newMap.put(ColumnId.pokemonSpecShiny, pokemonSpecShiny);
		newMap.put(ColumnId.pokemonSpecEgg, pokemonSpecEgg);
		newMap.put(ColumnId.pokemonSpecUntradeable, pokemonSpecUntradeable);
		newMap.put(ColumnId.pokemonSpecUnbreedable, pokemonSpecUnbreedable);
		newMap.put(ColumnId.pokemonSpecIVStats, pokemonSpecIVStats);
		newMap.put(ColumnId.pokemonSpecEVStats, pokemonSpecEVStats);
		newMap.put(ColumnId.pokemonSpecs, pokemonSpecs);
		newMap.put(ColumnId.spawnType, spawnType);
		newMap.put(ColumnId.intervalType, intervalType);
		newMap.put(ColumnId.requiredSpace, requiredSpace);
		newMap.put(ColumnId.conditionTime, conditionTime);
		newMap.put(ColumnId.conditionWeather, conditionWeather);
		newMap.put(ColumnId.conditionBiome, conditionBiome);
		newMap.put(ColumnId.conditionTemperature, conditionTemperature);
		newMap.put(ColumnId.conditionWorld, conditionWorld);
		newMap.put(ColumnId.conditionDimension, conditionDimension);
		newMap.put(ColumnId.conditionRequiredBlock, conditionRequiredBlock);
		newMap.put(ColumnId.conditionNearbyBlock, conditionNearbyBlock);
		newMap.put(ColumnId.conditionVariant, conditionVariant);
		newMap.put(ColumnId.conditionMinX, conditionMinX);
		newMap.put(ColumnId.conditionMaxX, conditionMaxX);
		newMap.put(ColumnId.conditionMinZ, conditionMinZ);
		newMap.put(ColumnId.conditionMaxZ, conditionMaxZ);
		newMap.put(ColumnId.conditionMinY, conditionMinY);
		newMap.put(ColumnId.conditionMaxY, conditionMaxY);
		newMap.put(ColumnId.conditionMinLight, conditionMinLight);
		newMap.put(ColumnId.conditionMaxLight, conditionMaxLight);
		newMap.put(ColumnId.conditionRequiresSky, conditionRequiresSky);
		newMap.put(ColumnId.conditionMoonPhase, conditionMoonPhase);
		newMap.put(ColumnId.conditionTag, conditionTag);
		newMap.put(ColumnId.antiConditionTime, antiConditionTime);
		newMap.put(ColumnId.antiConditionWeather, antiConditionWeather);
		newMap.put(ColumnId.antiConditionBiome, antiConditionBiome);
		newMap.put(ColumnId.antiConditionTemperature, antiConditionTemperature);
		newMap.put(ColumnId.antiConditionWorld, antiConditionWorld);
		newMap.put(ColumnId.antiConditionDimension, antiConditionDimension);
		newMap.put(ColumnId.antiConditionRequiredBlock, antiConditionRequiredBlock);
		newMap.put(ColumnId.antiConditionNearbyBlock, antiConditionNearbyBlock);
		newMap.put(ColumnId.antiConditionVariant, antiConditionVariant);
		newMap.put(ColumnId.antiConditionMinX, antiConditionMinX);
		newMap.put(ColumnId.antiConditionMaxX, antiConditionMaxX);
		newMap.put(ColumnId.antiConditionMinZ, antiConditionMinZ);
		newMap.put(ColumnId.antiConditionMaxZ, antiConditionMaxZ);
		newMap.put(ColumnId.antiConditionMinY, antiConditionMinY);
		newMap.put(ColumnId.antiConditionMaxY, antiConditionMaxY);
		newMap.put(ColumnId.antiConditionMinLight, antiConditionMinLight);
		newMap.put(ColumnId.antiConditionMaxLight, antiConditionMaxLight);
		newMap.put(ColumnId.antiConditionRequiresSky, antiConditionRequiresSky);
		newMap.put(ColumnId.antiConditionMoonPhase, antiConditionMoonPhase);
		newMap.put(ColumnId.antiConditionTag, antiConditionTag);
		newMap.put(ColumnId.compositeConditionConditions, compositeConditionConditions);
		newMap.put(ColumnId.compositeConditionAntiConditions, compositeConditionAntiConditions);
		newMap.put(ColumnId.rarity, rarity);
		newMap.put(ColumnId.rarityMultipliers, rarityMultipliers);
		newMap.put(ColumnId.percentage, percentage);
		newMap.put(ColumnId.minLevel, minLevel);
		newMap.put(ColumnId.maxLevel, maxLevel);
		newMap.put(ColumnId.specificShinyRate, specificShinyRate);
		newMap.put(ColumnId.specificBossRate, specificBossRate);
		newMap.put(ColumnId.specificPokeRusRate, specificPokeRusRate);
		newMap.put(ColumnId.heldItems, heldItems);

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