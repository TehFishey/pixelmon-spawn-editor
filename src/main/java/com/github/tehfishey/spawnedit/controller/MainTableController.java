package com.github.tehfishey.spawnedit.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import com.github.tehfishey.spawnedit.model.Model;
import com.github.tehfishey.spawnedit.model.helpers.Enums.ColumnId;
import com.github.tehfishey.spawnedit.model.objects.SpawnEntry;
import com.github.tehfishey.spawnedit.pixelmon.Enums.Gender;
import com.github.tehfishey.spawnedit.pixelmon.Enums.Growth;
import com.github.tehfishey.spawnedit.pixelmon.Enums.Nature;
import com.github.tehfishey.spawnedit.pixelmon.Enums.PokeRusType;
import com.github.tehfishey.spawnedit.pixelmon.Enums.Temperature;
import com.github.tehfishey.spawnedit.pixelmon.Enums.Time;
import com.github.tehfishey.spawnedit.pixelmon.Enums.Weather;

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

	// Controller for elements in the MainTable.fxml file.
	// Generates, contains, and displays all cell value data associated with the model's SpawnEntries/TableEntry HashMaps.
	// (Because of the large number of columns/data fields, TableColumns draw info from HashMaps instead of a purpose-built
	// data class - having 70+ getters/setters would be insane, right? I might change this later?)

	// Really, this class is just a mess. Cell values/properties are stored as Strings rather than their respective object types.
	// It's impossible to edit cell values, and it's non-trivial to implement methods for doing so that would actually update the 
	// model. I have no idea what I'm doing in JavaFx and am very, very sad :(

public class MainTableController implements Initializable {
	
	private final ControllerManager manager;
	private final Model model;
	private final PropertyChangeListener modelListener;
	private HashMap<ColumnId, TableColumn<?,?>> columnMap;
	
	private HashMap<ColumnId, TableColumn<HashMap<ColumnId, Object>, String>> stringColumns;
	private HashMap<ColumnId, TableColumn<HashMap<ColumnId, Object>, Integer>> integerColumns;
	private HashMap<ColumnId, TableColumn<HashMap<ColumnId, Object>, Boolean>> booleanColumns;
	private HashMap<ColumnId, TableColumn<HashMap<ColumnId, Object>, Float>> floatColumns;
	private HashMap<ColumnId, TableColumn<HashMap<ColumnId, Object>, Object>> enumColumns;
	private HashMap<ColumnId, TableColumn<HashMap<ColumnId, Object>, Object>> classColumns;
	private final ObservableList<HashMap<ColumnId, Object>> dataList;
	
	@FXML private TableView<HashMap<ColumnId, Object>> tableView;
	
	@FXML private TableColumn<HashMap<ColumnId, Object>, String> spawnSetId;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> spawnSetIndex;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> pokemonSpecSpecies;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> pokemonSpecLevel;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Gender> pokemonSpecGender;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> pokemonSpecStatus;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Growth> pokemonSpecGrowthSize;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Nature> pokemonSpecNature;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> pokemonSpecFormId;
    @FXML private TableColumn<HashMap<ColumnId, Object>, PokeRusType> pokemonSpecPokeRusStage;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Boolean> pokemonSpecRandom;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Boolean> pokemonSpecCured;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Boolean> pokemonSpecShiny;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Boolean> pokemonSpecEgg;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Boolean> pokemonSpecUntradeable;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Boolean> pokemonSpecUnbreedable;
    @FXML private TableColumn<HashMap<ColumnId, Object>, ?> pokemonSpecIVStats;
    @FXML private TableColumn<HashMap<ColumnId, Object>, ?> pokemonSpecEVStats;
    @FXML private TableColumn<HashMap<ColumnId, Object>, ?> pokemonSpecs;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> spawnType;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> intervalType;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> requiredSpace;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Time> conditionTime;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Weather> conditionWeather;
    @FXML private TableColumn<HashMap<ColumnId, Object>, ?> conditionBiome;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Temperature> conditionTemperature;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> conditionWorld;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> conditionDimension;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionRequiredBlock;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionNearbyBlock;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionVariant;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> conditionMinX;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> conditionMaxX;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> conditionMinZ;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> conditionMaxZ;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> conditionMinY;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> conditionMaxY;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> conditionMinLight;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> conditionMaxLight;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Boolean> conditionRequiresSky;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> conditionMoonPhase;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> conditionTag;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Time> antiConditionTime;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Weather> antiConditionWeather;
    @FXML private TableColumn<HashMap<ColumnId, Object>, ?> antiConditionBiome;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Temperature> antiConditionTemperature;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> antiConditionWorld;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> antiConditionDimension;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionRequiredBlock;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionNearbyBlock;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionVariant;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> antiConditionMinX;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> antiConditionMaxX;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> antiConditionMinZ;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> antiConditionMaxZ;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> antiConditionMinY;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> antiConditionMaxY;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> antiConditionMinLight;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> antiConditionMaxLight;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Boolean> antiConditionRequiresSky;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> antiConditionMoonPhase;
    @FXML private TableColumn<HashMap<ColumnId, Object>, String> antiConditionTag;
    @FXML private TableColumn<HashMap<ColumnId, Object>, ?> compositeConditionConditions;
    @FXML private TableColumn<HashMap<ColumnId, Object>, ?> compositeConditionAntiConditions;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Float> rarity;
    @FXML private TableColumn<HashMap<ColumnId, Object>, ?> rarityMultipliers;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Float> percentage;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> minLevel;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Integer> maxLevel;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Float> specificShinyRate;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Float> specificBossRate;
    @FXML private TableColumn<HashMap<ColumnId, Object>, Float> specificPokeRusRate;    
    @FXML private TableColumn<HashMap<ColumnId, Object>, ?> heldItems; 
    
    public MainTableController(ControllerManager manager, Model model) {
        this.manager = manager;
    	this.model = model;
        this.dataList = FXCollections.observableArrayList();
        this.modelListener = new PropertyChangeListener() {
        	@Override
        	@SuppressWarnings("unchecked")
        	public void propertyChange(PropertyChangeEvent evt) {
        		switch (evt.getPropertyName()) {
        		case "spawnEntriesAdded" :
        			addModelData(dataList, (ArrayList<SpawnEntry>) evt.getNewValue());
        			break;
        		case "spawnEntriesRemoved" :
        			removeModelData(dataList, (ArrayList<SpawnEntry>) evt.getNewValue());
        			break;
        		}
        	}
        };
        model.registerListener(modelListener);
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		importModelData(dataList);
		columnMap = mapColumns();
		
		tableView.setItems(dataList);
		
		spawnSetId.setCellValueFactory(new MapValueFactory("spawnSetId"));
		spawnSetIndex.setCellValueFactory(new MapValueFactory("spawnSetIndex"));
		
		/*
		for (Entry<ColumnId, TableColumn<HashMap<ColumnId, Object>, String>> entry : columnMap.entrySet()) {
			TableColumn<HashMap<ColumnId, Object>, String> column = entry.getValue();
			ColumnId id = entry.getKey();
			
			column.visibleProperty().bind(manager.getVisibleColumns().get(id));
			column.setCellValueFactory(new MapValueFactory(id));
			column.setCellFactory(getStringCellFactory());
			
		}
		*/	
	}

	private Callback<TableColumn<HashMap<ColumnId, Object>, Object>, TableCell<HashMap<ColumnId, Object>, Object>> getStringCellFactory() {
		return new Callback<TableColumn<HashMap<ColumnId, Object>, Object>, TableCell<HashMap<ColumnId, Object>, Object>>() {
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
	

	private HashMap<ColumnId, TableColumn<?, ?>> mapColumns() {
		HashMap<ColumnId, TableColumn<?, ?>> newMap = new HashMap<ColumnId, TableColumn<?, ?>>();
	
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

}