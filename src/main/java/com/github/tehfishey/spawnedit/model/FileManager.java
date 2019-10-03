package com.github.tehfishey.spawnedit.model;

import java.io.File;
import java.util.ArrayList;

import com.github.tehfishey.spawnedit.pixelmon.PokemonSpawnSet;
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
	
	public void loadFile(File file) {
		PokemonSpawnSet data = fileLoader.parse(file);
			// needs to take JSON and return formatted SpawnSet containing all information
		//directoryMap.put(info.getSetId(), file.getAbsolutePath());
			//at some point we need to map the SpawnSetId to the file path so that changes can be saved properly
		ArrayList<SpawnEntry> newEntries = processSpawnSet(data);
			//then we need to take SpawnInfo and convert it into an ArrayList of SpawnEntries
		parent.addSpawnEntries(newEntries);
			//finally we need to add the ArrayList of SpawnEntries to the model's existing array...
	}
	
	public ArrayList<SpawnEntry> processSpawnSet(PokemonSpawnSet data) {
		ArrayList<SpawnEntry> newEntries = new ArrayList<SpawnEntry>();
		String SpawnSetId = data.getSetId();
		
		for (int i = 0; i < data.getSpawnInfos().length; i++) {
			SpawnInfoPokemon spawnInfo = data.getSpawnInfos()[i];
			newEntries.add(new SpawnEntry(SpawnSetId, i, spawnInfo));
		}
		
		return newEntries;
	}
}
