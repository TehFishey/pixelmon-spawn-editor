package com.github.tehfishey.spawnedit.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.github.tehfishey.spawnedit.pixelmon.SpawnSet;
import com.github.tehfishey.spawnedit.pixelmon.SpawnInfoPokemon;

public class FileManager {
	private final Model parent;
	private final HashMap<String, String> directoryMap;
	private final FileLoader fileLoader;
	private final FileSaver fileSaver;
	
	public FileManager(Model parent) {
		this.parent = parent;
		this.directoryMap = new HashMap<String, String>();
		this.fileLoader = new FileLoader();
		this.fileSaver = new FileSaver();
	}
	
	public void loadFile(File file) {
		SpawnSet data = fileLoader.parse(file);
		directoryMap.put(data.getSetId(), file.getAbsolutePath());
		
		ArrayList<SpawnEntry> newEntries = processSpawnSet(data);
		parent.addSpawnEntries(newEntries);
	}
	
	public ArrayList<SpawnEntry> processSpawnSet(SpawnSet data) {
		ArrayList<SpawnEntry> newEntries = new ArrayList<SpawnEntry>();
		String SpawnSetId = data.getSetId();
		
		for (int i = 0; i < data.getSpawnInfos().length; i++) {
			SpawnInfoPokemon spawnInfo = data.getSpawnInfos()[i];
			newEntries.add(new SpawnEntry(SpawnSetId, i, spawnInfo));
		}
		
		return newEntries;
	}
}
