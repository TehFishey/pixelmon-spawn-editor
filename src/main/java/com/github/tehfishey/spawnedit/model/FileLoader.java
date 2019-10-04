package com.github.tehfishey.spawnedit.model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.google.gson.Gson;

import com.github.tehfishey.spawnedit.pixelmon.SpawnSet;

	// Component of the FileManager class. Responsible for parsing and validating 
	// loaded .json data files using Gson. Returns SpawnSet objects, which represent
	// whole data file contents.

public class FileLoader {

	private Gson parser;
	
	public FileLoader() {
		parser = new Gson();
	}
	
	public SpawnSet parse(File file) {
		try (Reader reader = new FileReader(file)) {
			SpawnSet spawnSet = parser.fromJson(reader, SpawnSet.class);
			return spawnSet;
		} catch (IOException e) {
            e.printStackTrace();
            return new SpawnSet();
        }
	}
}
