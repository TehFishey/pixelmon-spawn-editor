package com.github.tehfishey.spawnedit.model.exceptions;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.JsonParseException;

	// Batch exception for passing to the FileManager method caller when multiple
	// JsonParseExceptions are raised during a single file/directory loading operation.

public class BatchJsonException extends Exception {
	private static final long serialVersionUID = 1L;
	final HashMap<Path, JsonParseException> exceptedFilePathsMap;
	
	public BatchJsonException(String message, HashMap<Path, JsonParseException> exceptedFilePathsMap) {
		super(message);
		this.exceptedFilePathsMap = exceptedFilePathsMap;
	}
	
	public BatchJsonException(HashMap<Path, JsonParseException> exceptedFilePathsMap) {
		this.exceptedFilePathsMap = exceptedFilePathsMap;
		System.out.println("BatchJsonException created...");
	}
	
	public ArrayList<Path> getExceptedPaths() {
		ArrayList<Path> output = new ArrayList<Path>();
		for (Path filePath : exceptedFilePathsMap.keySet()) output.add(filePath);
		return output;
	}
}
