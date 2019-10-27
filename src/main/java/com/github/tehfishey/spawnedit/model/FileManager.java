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
import com.github.tehfishey.spawnedit.model.exceptions.BatchDuplicateIDException;
import com.github.tehfishey.spawnedit.model.exceptions.BatchIOException;
import com.github.tehfishey.spawnedit.model.exceptions.BatchJsonException;
import com.github.tehfishey.spawnedit.model.exceptions.DuplicateIDException;
import com.github.tehfishey.spawnedit.model.objects.PathTreeNode;
import com.github.tehfishey.spawnedit.model.objects.SpawnEntry;
import com.github.tehfishey.spawnedit.pixelmon.SpawnInfoPokemon;

	

public class FileManager {
	private final Model parent;
	private final FileLoader fileLoader;
	private final FileSaver fileSaver;
	
	
	public FileManager(Model parent) {
		this.parent = parent;
		this.fileLoader = new FileLoader();
		this.fileSaver = new FileSaver();		
	}

	public void saveFile(Path root, PathTreeNode file) throws BatchIOException {
		ArrayList<SpawnSet> output = processSpawnEntries(parent.getSpawnEntries());
		HashMap<Path,IOException> ioExceptions = new HashMap<Path,IOException>();
		HashMap<String, Path> savePaths = file.toHashMap();
		
		for (SpawnSet set : output) {
			if (savePaths.containsKey(set.getSetId())) {
				Path pathRecord = savePaths.get(set.getSetId());
				Path newPath = root.resolve(pathRecord);
				try {
					fileSaver.saveSpawnSetToPath(set, newPath);
					savePaths.replace(set.getSetId(), newPath);
				} catch (IOException e) {
					ioExceptions.put(newPath,e);
					continue;
				}
			}
		}
		if (!ioExceptions.isEmpty()) throw new BatchIOException("IOExceptions occured during saving...", ioExceptions);
	}
	
	public void saveAll(Path root) throws BatchIOException {
		ArrayList<SpawnSet> output = processSpawnEntries(parent.getSpawnEntries());
		HashMap<Path,IOException> ioExceptions = new HashMap<Path,IOException>();
		HashMap<String, Path> savePaths = parent.getFilePathTree().toHashMap();
		
		for (SpawnSet set : output) {
			Path pathRecord = savePaths.get(set.getSetId());
			Path newPath = root.resolve(pathRecord);
			try {
				fileSaver.saveSpawnSetToPath(set, newPath);
				savePaths.replace(set.getSetId(), newPath);
			} catch (IOException e) {
        		ioExceptions.put(newPath,e);
        		continue;
			}
		}
		if (!ioExceptions.isEmpty()) throw new BatchIOException("IOExceptions occured during saving...", ioExceptions);
	}
	
	
	
	public void loadFile(Path file) throws BatchIOException, BatchJsonException, BatchDuplicateIDException {
		Path root = file.getParent();
		try {
			SpawnSet data = fileLoader.parse(file);
			if (parent.getFilePathTree().containsId(data.getSetId())) {
				DuplicateIDException e = new DuplicateIDException("Model already contains a file with id " + data.getSetId(), data.getSetId());
				HashMap<Path, DuplicateIDException> exceptedPathMap = new HashMap<Path, DuplicateIDException>();
				exceptedPathMap.put(file,  e);
				throw new BatchDuplicateIDException(exceptedPathMap);
			}
			else {
				ArrayList<SpawnEntry> newEntries = processSpawnSet(data);
				parent.addSpawnPath(data.getSetId(), root.relativize(file));
				parent.addSpawnEntries(newEntries);
				parent.notifyListeners("fileTreeUpdated", null, null);
			}
		} catch (IOException e) {
			HashMap<Path, IOException> exceptedPathMap = new HashMap<Path, IOException>();
			exceptedPathMap.put(file, e);
			throw new BatchIOException(exceptedPathMap);
		} catch (JsonParseException e) {
			HashMap<Path, JsonParseException> exceptedPathMap = new HashMap<Path, JsonParseException>();
			exceptedPathMap.put(file, e);
			throw new BatchJsonException(exceptedPathMap);
		}
	}
	
	public void loadDirectory(Path root) throws BatchIOException, BatchJsonException, BatchDuplicateIDException {
		ArrayList<SpawnEntry> newEntries = new ArrayList<SpawnEntry>();
		HashMap<Path,IOException> ioExceptions = new HashMap<Path,IOException>();
		HashMap<Path,JsonParseException> jsonExceptions = new HashMap<Path,JsonParseException>();
		HashMap<Path,DuplicateIDException> idExceptions = new HashMap<Path,DuplicateIDException>();
		
		try (Stream<Path> paths = Files.walk(root)) {
			paths.filter(file -> file.toString().endsWith(".json"))
				.forEach(file -> {
					try {
						SpawnSet data = fileLoader.parse(file);
						if (parent.getFilePathTree().containsId(data.getSetId())) {
							DuplicateIDException e = new DuplicateIDException(data.getSetId());
							idExceptions.put(file.getFileName(), e);
						}
						else {
							parent.addSpawnPath(data.getSetId(), root.relativize(file));
							newEntries.addAll(processSpawnSet(data));
						}
					} catch (IOException e) {
			        	ioExceptions.put(file.getFileName(),e);
			    	} catch (JsonParseException e) {
			       		jsonExceptions.put(file.getFileName(),e);
			    	}
				});
		} catch (IOException e) {
			e.printStackTrace();
		}

		parent.addSpawnEntries(newEntries);
		parent.notifyListeners("fileTreeUpdated", null, null);
		if (!ioExceptions.isEmpty()) throw new BatchIOException("IOExceptions occured during loading...", ioExceptions);
		if (!jsonExceptions.isEmpty()) throw new BatchJsonException("JsonParseExceptions occured during loading...", jsonExceptions);
		if (!idExceptions.isEmpty()) throw new BatchDuplicateIDException("DuplicateIDExceptions occured during loading...", idExceptions);
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
