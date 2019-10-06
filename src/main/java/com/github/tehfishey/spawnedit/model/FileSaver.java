package com.github.tehfishey.spawnedit.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.github.tehfishey.spawnedit.pixelmon.SpawnSet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FileSaver {

	private Gson printer;
	
	public FileSaver() {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		
		printer = new GsonBuilder().create();
	}
	
	public void saveMultipleFiles(ArrayList<SpawnSet> spawnSets, HashMap<String, String> pathMap) {
		for (Entry<String, String> pathEntry : pathMap.entrySet()) {
			String fileId = pathEntry.getKey();
			
			for (SpawnSet file : spawnSets) {
				if (file.getSetId() == fileId) {
					try {
						FileWriter writer = new FileWriter(pathEntry.getValue());
						System.out.println("Saving SpawnSet (" + file.toString() + ") to file path: " + pathEntry.getValue());
						writer.write(printer.toJson(file));
						writer.close();
						  
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
