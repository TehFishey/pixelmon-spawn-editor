package com.github.tehfishey.spawnedit.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

import com.github.tehfishey.spawnedit.pixelmon.SpawnSet;
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
	private final HashMap<String, Path> pathMap;
	private final HashMap<String, Path> rootMap;
	private final FileLoader fileLoader;
	private final FileSaver fileSaver;
	
	public FileManager(Model parent) {
		this.parent = parent;
		this.pathMap = new HashMap<String, Path>();
		this.rootMap = new HashMap<String, Path>();
		this.fileLoader = new FileLoader();
		this.fileSaver = new FileSaver();
	}
	
	public void saveAllToMap() throws BatchIOException {
		ArrayList<SpawnSet> output = processSpawnEntries(parent.getSpawnEntries());
		HashMap<Path,IOException> ioExceptions = new HashMap<Path,IOException>();
		
		for (SpawnSet set : output) {
			Path path = pathMap.get(set.getSetId());
			try {
				fileSaver.saveSpawnSetToPath(set, path);
			} catch (IOException e) {
        		ioExceptions.put(path,e);
        		continue;
			}
		}
		if (!ioExceptions.isEmpty()) throw new BatchIOException("IOExceptions occured during saving...", ioExceptions);
	}
	
	public void saveAllToDirectory(Path root) throws BatchIOException {
		ArrayList<SpawnSet> output = processSpawnEntries(parent.getSpawnEntries());
		HashMap<Path,IOException> ioExceptions = new HashMap<Path,IOException>();
		
		for (SpawnSet set : output) {
			Path pathRecord = pathMap.get(set.getSetId());
			Path rootRecord = rootMap.get(set.getSetId());
			Path newPath = root.resolve(rootRecord.relativize(pathRecord));
			try {
				fileSaver.saveSpawnSetToPath(set, newPath);
			} catch (IOException e) {
        		ioExceptions.put(newPath,e);
        		continue;
			}
		}
		if (!ioExceptions.isEmpty()) throw new BatchIOException("IOExceptions occured during saving...", ioExceptions);
	}
	
	public void loadFile(Path file) throws BatchIOException, BatchJsonException {
		try {
			SpawnSet data = fileLoader.parse(file);
			pathMap.put(data.getSetId(), file);
			rootMap.put(data.getSetId(), file.getParent());
			ArrayList<SpawnEntry> newEntries = processSpawnSet(data);
			parent.addSpawnEntries(newEntries);
		} catch (IOException e) {
			HashMap<Path, IOException> pathMap = new HashMap<Path, IOException>();
			pathMap.put(file, e);
			throw new BatchIOException(pathMap);
		} catch (JsonParseException e) {
			HashMap<Path, JsonParseException> pathMap = new HashMap<Path, JsonParseException>();
			pathMap.put(file, e);
			throw new BatchJsonException(pathMap);
		}
	}
	
	public void loadDirectory(Path root) throws BatchIOException, BatchJsonException {
		ArrayList<SpawnEntry> newEntries = new ArrayList<SpawnEntry>();
		HashMap<Path,IOException> ioExceptions = new HashMap<Path,IOException>();
		HashMap<Path,JsonParseException> jsonExceptions = new HashMap<Path,JsonParseException>();
		
		try (Stream<Path> paths = Files.walk(root)) {
			paths.filter(f -> f.toString().endsWith(".json"))
				.forEach(f -> {
					try {
						SpawnSet data = fileLoader.parse(f);
						pathMap.put(data.getSetId(), f);
						rootMap.put(data.getSetId(), root);
						newEntries.addAll(processSpawnSet(data));
					} catch (IOException e) {
			        	ioExceptions.put(f.getFileName(),e);
			    	} catch (JsonParseException e) {
			       		jsonExceptions.put(f.getFileName(),e);
			    	}
				});
		} catch (IOException e) {
			e.printStackTrace();
		}

		parent.addSpawnEntries(newEntries);
		if (!ioExceptions.isEmpty()) throw new BatchIOException("IOExceptions occured during loading...", ioExceptions);
		if (!jsonExceptions.isEmpty()) throw new BatchJsonException("JsonParseExceptions occured during loading...", jsonExceptions);
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
	
	private ArrayList<SpawnSet> processSpawnEntries(ArrayList<SpawnEntry> data) {
		ArrayList<SpawnSet> newSets = new ArrayList<SpawnSet>();
		ArrayList<String> setIds = new ArrayList<String>();
		
		for (SpawnEntry entry : data) {
			String setId = entry.getSpawnSetId();
			if (!setIds.contains(setId)) setIds.add(setId);
		}
		
		for (String setId : setIds) {
			SpawnSet newSet = new SpawnSet();
			newSet.setSetId(setId);
			newSet.setSpawnInfos(new ArrayList<SpawnInfoPokemon>());
			
			for (SpawnEntry entry : data) {
				if (entry.getSpawnSetId().equals(setId)) {
					newSet.getSpawnInfos().add(entry.getSpawnSetIndex(), entry.getSpawnInfo());
				}
			}
			newSets.add(newSet);
		}
		
		return newSets;
	}
}
