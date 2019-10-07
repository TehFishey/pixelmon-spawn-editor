package com.github.tehfishey.spawnedit.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.github.tehfishey.spawnedit.pixelmon.SpawnSet;
import com.google.common.io.Files;
import com.google.gson.JsonParseException;
import com.github.tehfishey.spawnedit.model.exceptions.BatchIOException;
import com.github.tehfishey.spawnedit.model.exceptions.BatchJsonException;
import com.github.tehfishey.spawnedit.pixelmon.SpawnInfoPokemon;

	// Component of Model class, responsible for handling data io by serializing & deserializing .json files formatted 
	// to Pixelmon's own specifications. When loading, SpawnInfo objects are unwrapped from SpawnSet objects (which each represent
	// a parsed data file) and wrapped into SpawnEnry objects within the model. Saving involves the reverse, with output
	// being written according to a stored path map.

public class FileManager {
	private final Model parent;
	private final HashMap<String, String> pathMap;
	private final FileLoader fileLoader;
	private final FileSaver fileSaver;
	
	public FileManager(Model parent) {
		this.parent = parent;
		this.pathMap = new HashMap<String, String>();
		this.fileLoader = new FileLoader();
		this.fileSaver = new FileSaver();
	}
	
	public void saveAll() {
		ArrayList<SpawnEntry> entries = parent.getSpawnEntries();
		ArrayList<SpawnSet> output = new ArrayList<SpawnSet>();

		for (Entry<String, String> pathMapEntry : pathMap.entrySet()) {
			String setId = pathMapEntry.getKey();
			SpawnSet newSet = new SpawnSet();
			newSet.setSetId(setId);
			newSet.setSpawnInfos(new ArrayList<SpawnInfoPokemon>());
			
			for (SpawnEntry entry : entries) {
				if (entry.getSpawnSetId().equals(setId)) {
					newSet.getSpawnInfos().add(entry.getSpawnSetIndex(), entry.getSpawnInfo());
				}
			}
			
			output.add(newSet);
		}

		fileSaver.saveMultipleFiles(output, pathMap);
	}
	
	public void loadFile(File file) throws BatchIOException, BatchJsonException {
		try {
			SpawnSet data = fileLoader.parse(file);
			pathMap.put(data.getSetId(), file.getAbsolutePath());
			ArrayList<SpawnEntry> newEntries = processSpawnSet(data);
			parent.addSpawnEntries(newEntries);
		} catch (IOException e) {
			HashMap<String, IOException> pathMap = new HashMap<String, IOException>();
			pathMap.put(file.getName(), e);
			throw new BatchIOException(pathMap);
		} catch (JsonParseException e) {
			HashMap<String, JsonParseException> pathMap = new HashMap<String, JsonParseException>();
			pathMap.put(file.getName(), e);
			throw new BatchJsonException(pathMap);
		}
	}
	
	public void loadDirectory(File directory) throws BatchIOException, BatchJsonException {
		ArrayList<SpawnEntry> newEntries = new ArrayList<SpawnEntry>();
		HashMap<String,IOException> ioExceptions = new HashMap<String,IOException>();
		HashMap<String,JsonParseException> jsonExceptions = new HashMap<String,JsonParseException>();
		
		loadDirectoryLoop(directory, newEntries, ioExceptions, jsonExceptions);
		parent.addSpawnEntries(newEntries);
		
		if (!ioExceptions.isEmpty()) throw new BatchIOException("IOExceptions occured during loading...", ioExceptions);
		if (!jsonExceptions.isEmpty()) throw new BatchJsonException("JsonParseExceptions occured during loading...", jsonExceptions);
	}
	
	private void loadDirectoryLoop(File subDirectory, ArrayList<SpawnEntry> newEntries, HashMap<String,IOException> ioExceptions, HashMap<String,JsonParseException> jsonExceptions) {		
		for (final File file : subDirectory.listFiles()) {
	        if (file.isDirectory()) {
	        	loadDirectoryLoop(file, newEntries, ioExceptions, jsonExceptions);
	        } else if (Files.getFileExtension(file.getName()).equals("json")) {
	        	try {
	        		SpawnSet data = fileLoader.parse(file);
		        	pathMap.put(data.getSetId(), file.getAbsolutePath());
		        	newEntries.addAll(processSpawnSet(data));
	        	} catch (IOException e) {
	        		if (ioExceptions.equals(null)) ioExceptions = new HashMap<String,IOException>();
	        		ioExceptions.put(file.getName(),e);
	        		continue;
	    		} catch (JsonParseException e) {
	    			if (jsonExceptions.equals(null)) jsonExceptions = new HashMap<String,JsonParseException>();
	        		jsonExceptions.put(file.getName(),e);
	        		continue;
	    		}
	        }
	    }
	}
	
	private ArrayList<SpawnEntry> processSpawnSet(SpawnSet data) {
		ArrayList<SpawnEntry> newEntries = new ArrayList<SpawnEntry>();
		String SpawnSetId = data.getSetId();
		
		for (int i = 0; i < data.getSpawnInfos().size(); i++) {
			SpawnInfoPokemon spawnInfo = data.getSpawnInfos().get(i);
			newEntries.add(new SpawnEntry(SpawnSetId, i, spawnInfo));
		}
		
		return newEntries;
	}
}
