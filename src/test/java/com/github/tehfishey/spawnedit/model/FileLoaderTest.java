package com.github.tehfishey.spawnedit.model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;

import com.github.tehfishey.spawnedit.model.FileLoader;
import com.github.tehfishey.spawnedit.pixelmon.PokemonSpawnSet;

public class FileLoaderTest {

	@Test
	public void testSetId() {
		FileLoader fileLoader = new FileLoader();
		File file = new File("src/test/resources/json/Abra.set.json");
		
		PokemonSpawnSet test = fileLoader.parse(file);
		
		assertEquals(5, test.getSpawnInfos()[0].getMinLevel());
	}
	
	@Test
	public void testSpawnInfoLevel() {
		FileLoader fileLoader = new FileLoader();
		File file = new File("src/test/resources/json/Abra.set.json");
		
		PokemonSpawnSet test = fileLoader.parse(file);
		
		assertEquals("Abra",test.getSetId());
		assertEquals(5, test.getSpawnInfos()[0].getMinLevel());
	}

}
