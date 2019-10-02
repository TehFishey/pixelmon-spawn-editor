package com.github.tehfishey.spawnedit.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import com.github.tehfishey.spawnedit.model.Model;
import com.github.tehfishey.spawnedit.model.SpawnEntry;
import com.github.tehfishey.spawnedit.pixelmon.Constants;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class MainTableController implements Initializable {
	
	private Model model;
	private HashMap<String, TableColumn<HashMap<String, Object>, String>> columnMap;
	private ObservableList<HashMap<String, Object>> dataList;
	
	@FXML private TableView<HashMap<String, Object>> tableView;
	
	@FXML private TableColumn<HashMap<String, Object>, String> spawnSetId;
    @FXML private TableColumn<HashMap<String, Object>, String> spawnSetIndex;
    @FXML private TableColumn<HashMap<String, Object>, String> pokemonSpecSpecies;
    @FXML private TableColumn<HashMap<String, Object>, String> pokemonSpecLevel;
    @FXML private TableColumn<HashMap<String, Object>, String> pokemonSpecGender;
    @FXML private TableColumn<HashMap<String, Object>, String> pokemonSpecStatus;
    @FXML private TableColumn<HashMap<String, Object>, String> pokemonSpecGrowthSize;
    @FXML private TableColumn<HashMap<String, Object>, String> pokemonSpecNature;
    @FXML private TableColumn<HashMap<String, Object>, String> pokemonSpecFormId;
    @FXML private TableColumn<HashMap<String, Object>, String> pokemonSpecPokeRusStage;
    @FXML private TableColumn<HashMap<String, Object>, String> pokemonSpecRandom;
    @FXML private TableColumn<HashMap<String, Object>, String> pokemonSpecCured;
    @FXML private TableColumn<HashMap<String, Object>, String> pokemonSpecShiny;
    @FXML private TableColumn<HashMap<String, Object>, String> pokemonSpecEgg;
    @FXML private TableColumn<HashMap<String, Object>, String> pokemonSpecUntradeable;
    @FXML private TableColumn<HashMap<String, Object>, String> pokemonSpecUnbreedable;
    @FXML private TableColumn<HashMap<String, Object>, String> spawnType;
    @FXML private TableColumn<HashMap<String, Object>, String> intervalType;
    @FXML private TableColumn<HashMap<String, Object>, String> requiredSpace;
    @FXML private TableColumn<HashMap<String, Object>, String> conditionTime;
    @FXML private TableColumn<HashMap<String, Object>, String> conditionWeather;
    @FXML private TableColumn<HashMap<String, Object>, String> conditionBiome;
    @FXML private TableColumn<HashMap<String, Object>, String> conditionTemperature;
    @FXML private TableColumn<HashMap<String, Object>, String> conditionWorld;
    @FXML private TableColumn<HashMap<String, Object>, String> conditionDimension;
    @FXML private TableColumn<HashMap<String, Object>, String> conditionRequiredBlock;
    @FXML private TableColumn<HashMap<String, Object>, String> conditionNearbyBlock;
    @FXML private TableColumn<HashMap<String, Object>, String> conditionVariant;
    @FXML private TableColumn<HashMap<String, Object>, String> conditionMinX;
    @FXML private TableColumn<HashMap<String, Object>, String> conditionMaxX;
    @FXML private TableColumn<HashMap<String, Object>, String> conditionMinZ;
    @FXML private TableColumn<HashMap<String, Object>, String> conditionMaxZ;
    @FXML private TableColumn<HashMap<String, Object>, String> conditionMinY;
    @FXML private TableColumn<HashMap<String, Object>, String> conditionMaxY;
    @FXML private TableColumn<HashMap<String, Object>, String> conditionMinLight;
    @FXML private TableColumn<HashMap<String, Object>, String> conditionMaxLight;
    @FXML private TableColumn<HashMap<String, Object>, String> conditionRequiresSky;
    @FXML private TableColumn<HashMap<String, Object>, String> conditionMoonPhase;
    @FXML private TableColumn<HashMap<String, Object>, String> conditionTag;
    @FXML private TableColumn<HashMap<String, Object>, String> antiConditionTime;
    @FXML private TableColumn<HashMap<String, Object>, String> antiConditionWeather;
    @FXML private TableColumn<HashMap<String, Object>, String> antiConditionBiome;
    @FXML private TableColumn<HashMap<String, Object>, String> antiConditionTemperature;
    @FXML private TableColumn<HashMap<String, Object>, String> antiConditionWorld;
    @FXML private TableColumn<HashMap<String, Object>, String> antiConditionDimension;
    @FXML private TableColumn<HashMap<String, Object>, String> antiConditionRequiredBlock;
    @FXML private TableColumn<HashMap<String, Object>, String> antiConditionNearbyBlock;
    @FXML private TableColumn<HashMap<String, Object>, String> antiConditionVariant;
    @FXML private TableColumn<HashMap<String, Object>, String> antiConditionMinX;
    @FXML private TableColumn<HashMap<String, Object>, String> antiConditionMaxX;
    @FXML private TableColumn<HashMap<String, Object>, String> antiConditionMinZ;
    @FXML private TableColumn<HashMap<String, Object>, String> antiConditionMaxZ;
    @FXML private TableColumn<HashMap<String, Object>, String> antiConditionMinY;
    @FXML private TableColumn<HashMap<String, Object>, String> antiConditionMaxY;
    @FXML private TableColumn<HashMap<String, Object>, String> antiConditionMinLight;
    @FXML private TableColumn<HashMap<String, Object>, String> antiConditionMaxLight;
    @FXML private TableColumn<HashMap<String, Object>, String> antiConditionRequiresSky;
    @FXML private TableColumn<HashMap<String, Object>, String> antiConditionMoonPhase;
    @FXML private TableColumn<HashMap<String, Object>, String> antiConditionTag;
    @FXML private TableColumn<HashMap<String, Object>, String> rarity;
    @FXML private TableColumn<HashMap<String, Object>, String> percentage;
    @FXML private TableColumn<HashMap<String, Object>, String> minLevel;
    @FXML private TableColumn<HashMap<String, Object>, String> maxLevel;
    @FXML private TableColumn<HashMap<String, Object>, String> specificShinyRate;
    @FXML private TableColumn<HashMap<String, Object>, String> specificBossRate;
    @FXML private TableColumn<HashMap<String, Object>, String> specificPokeRusRate;    
    
    public MainTableController(Model model) {
        this.model = model;
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		dataList = getDataEntries();
		columnMap = buildTableColumns();
		
		tableView.setItems(dataList);
		
		spawnSetId.setCellValueFactory(new MapValueFactory("spawnSetId"));
		spawnSetIndex.setCellValueFactory(new MapValueFactory("spawnSetIndex"));
		
		for (Entry<String, TableColumn<HashMap<String, Object>, String>> entry : columnMap.entrySet()) {
			TableColumn<HashMap<String, Object>, String> column = entry.getValue();
			String id = entry.getKey();
		
			column.setCellValueFactory(new MapValueFactory(id));
			column.setCellFactory(getStringCellFactory());
		}
		
		/*spawnSetId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HashMap<String, Object>, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<HashMap<String, Object>, String> p) {
                // for second column we use value
                return new SimpleStringProperty((String) p.getValue().get("spawnSetId"));
            }
        });
		spawnSetIndex.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HashMap<String, Object>, Integer>, ObservableValue<Integer>>() {

            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<HashMap<String, Object>, Integer> p) {
                return new SimpleIntegerProperty((Integer) p.getValue().get("spawnSetIndex")).asObject();
            }
        });*/
		
		
		
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	/*private class CustomCellFactory implements Callback<TableColumn.CellDataFeatures<HashMap<String, Object>, Object>, ObservableValue<Object>> {
		
		@Override
		public ObservableValue<Object> call(CellDataFeatures<HashMap<String, Object>, Object> data) {
			Object value = data.getValue();
	        return (value instanceof ObservableValue)
	                ? (ObservableValue) value
	                : new ReadOnlyObjectWrapper<>(value);
		}
	}*/
	
	private Callback<TableColumn<HashMap<String, Object>, String>, TableCell<HashMap<String, Object>, String>> getStringCellFactory() {
		return new Callback<TableColumn<HashMap<String, Object>, String>, TableCell<HashMap<String, Object>, String>>() {
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

	private ObservableList<HashMap<String, Object>> getDataEntries() {
		ArrayList<SpawnEntry> spawnEntries = model.getSpawnEntries();
		ArrayList<HashMap<String, Object>> allTableEntries = new ArrayList<HashMap<String, Object>>();
		
		for (SpawnEntry entry : spawnEntries) {
			ArrayList<HashMap<String, Object>> tableEntries = entry.getTableEntries();
			for (HashMap<String, Object> tableEntry : tableEntries) allTableEntries.add(tableEntry);
		}
		
		return FXCollections.observableArrayList(allTableEntries);	
	}
	
	private HashMap<String, TableColumn<HashMap<String, Object>, String>> buildTableColumns() {
		HashMap<String, TableColumn<HashMap<String, Object>, String>> newMap = new HashMap<String, TableColumn<HashMap<String, Object>, String>>();
	
		newMap.put("spawnSetId", spawnSetId);
		newMap.put("spawnSetIndex", spawnSetIndex);
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