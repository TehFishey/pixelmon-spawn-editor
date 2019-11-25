package com.github.tehfishey.spawnedit.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.github.tehfishey.spawnedit.pixelmon.SpawnSet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

	// Component of the FileManager class. Responsible for writing .json files from SpawnSet
	// objects, which mirror Pixelmon's data structures. Conversion of internal domain objects
	// to SpawnSets is done in the FileManager.

public class FileSaver {

	private Gson printer;
	
	public FileSaver() {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		
		printer = builder.create();
	}
	
	public void saveSpawnSetToPath(SpawnSet spawnSet, Path filePath) throws IOException {
		String content = printer.toJson(spawnSet);
		try {
			Files.write(filePath, content.getBytes());
		} catch (IOException e) {
			try {
				Files.createDirectories(filePath.getParent());
				Files.write(filePath, content.getBytes());
			} catch (IOException ex) {
				System.out.println(ex.getMessage() + "\nIOException @ Filepath: " + filePath.toString());
				throw e;
			}
		}
	}
}
