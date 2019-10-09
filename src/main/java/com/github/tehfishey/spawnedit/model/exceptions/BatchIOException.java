package com.github.tehfishey.spawnedit.model.exceptions;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class BatchIOException extends Exception {
	private static final long serialVersionUID = 1L;
	final HashMap<Path, IOException> exceptedFilePathsMap;
	
	public BatchIOException(String message, HashMap<Path, IOException> exceptedFilePathsMap) {
		super(message);
		this.exceptedFilePathsMap = exceptedFilePathsMap;
	}
	
	public BatchIOException(HashMap<Path, IOException> exceptedFilePathsMap) {
		this.exceptedFilePathsMap = exceptedFilePathsMap;
	}
	
	public ArrayList<Path> getExceptedPaths() {
		ArrayList<Path> output = new ArrayList<Path>();
		for (Path filePath : exceptedFilePathsMap.keySet()) output.add(filePath);
		return output;
	}
}
