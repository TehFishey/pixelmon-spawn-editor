package com.github.tehfishey.spawnedit.model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;

import com.github.tehfishey.spawnedit.model.FileLoader;
import com.github.tehfishey.spawnedit.pixelmon.SpawnSet;

public class FileLoaderTest {

	@Test
	public void testSetId() {
		FileLoader fileLoader = new FileLoader();
		File file = new File("src/test/resources/json/Abra.set.json");
		
		SpawnSet test = fileLoader.parse(file);
		
		assertEquals((Integer) 5, test.getSpawnInfos().get(0).getMinLevel());
	}
	
	@Test
	public void testSpawnInfoLevel() {
		FileLoader fileLoader = new FileLoader();
		File file = new File("src/test/resources/json/Abra.set.json");
		
		SpawnSet test = fileLoader.parse(file);
		
		assertEquals("Abra",test.getSetId());
		assertEquals((Integer) 5, test.getSpawnInfos().get(0).getMinLevel());
	}

}
