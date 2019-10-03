package com.github.tehfishey.spawnedit.model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.google.gson.Gson;

import com.github.tehfishey.spawnedit.pixelmon.PokemonSpawnSet;

public class FileLoader {

	private Gson parser;
	
	public FileLoader() {
		parser = new Gson();
	}
	
	public PokemonSpawnSet parse(File file) {
		try (Reader reader = new FileReader(file)) {
			PokemonSpawnSet spawnSet = parser.fromJson(reader, PokemonSpawnSet.class);
			System.out.println("FileLoader.parse");
			return spawnSet;
		} catch (IOException e) {
            e.printStackTrace();
            return new PokemonSpawnSet();
        }
	}
}
