package com.github.tehfishey.spawnedit.model.exceptions;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

	// Batch exception for passing to the FileManager method caller when multiple
	// DuplicateIDExceptions are raised during a single file/directory loading operation.

public class BatchDuplicateIDException extends Exception {
	private static final long serialVersionUID = 1L;
	final HashMap<Path, DuplicateIDException> exceptedIDsMap;
	
	public BatchDuplicateIDException(String message, HashMap<Path, DuplicateIDException> exceptedIDsMap) {
		super(message);
		this.exceptedIDsMap = exceptedIDsMap;
	}
	
	public BatchDuplicateIDException(HashMap<Path, DuplicateIDException> exceptedIDsMap) {
		this.exceptedIDsMap = exceptedIDsMap;
	}
	
	public ArrayList<Path> getExceptedPaths() {
		ArrayList<Path> output = new ArrayList<Path>();
		for (Path path : exceptedIDsMap.keySet()) output.add(path);
		return output;
	}
}
