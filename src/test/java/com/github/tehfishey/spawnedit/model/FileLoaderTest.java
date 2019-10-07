package com.github.tehfishey.spawnedit.model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import com.github.tehfishey.spawnedit.model.FileLoader;
import com.github.tehfishey.spawnedit.pixelmon.SpawnSet;
import com.google.gson.JsonParseException;

public class FileLoaderTest {

	@Test
	public void testSetId() {
		FileLoader fileLoader = new FileLoader();
		File file = new File("src/test/resources/json/Abra.set.json");
		
		try {
			SpawnSet test = fileLoader.parse(file);
			assertEquals((Integer) 5, test.getSpawnInfos().get(0).getMinLevel());
		} 
		catch (IOException e) { e.printStackTrace(); } 
		catch (JsonParseException e) { e.printStackTrace(); }
	}
	
	@Test
	public void testSpawnInfoLevel() {
		FileLoader fileLoader = new FileLoader();
		File file = new File("src/test/resources/json/Abra.set.json");
		
		try {
			SpawnSet test = fileLoader.parse(file);
			assertEquals("Abra",test.getSetId());
			assertEquals((Integer) 5, test.getSpawnInfos().get(0).getMinLevel());
		} 
		catch (IOException e) { e.printStackTrace(); } 
		catch (JsonParseException e) { e.printStackTrace(); }
	}

}
