package com.github.tehfishey.spawnedit.model.exceptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BatchIOException extends Exception {
	private static final long serialVersionUID = 1L;
	final HashMap<String, IOException> exceptionFileNameMap;
	
	public BatchIOException(String message, HashMap<String, IOException> exceptionFileNameMap) {
		super(message);
		this.exceptionFileNameMap = exceptionFileNameMap;
	}
	
	public BatchIOException(HashMap<String, IOException> exceptionFileNameMap) {
		this.exceptionFileNameMap = exceptionFileNameMap;
	}
	
	public ArrayList<String> getExceptionPaths() {
		ArrayList<String> output = new ArrayList<String>();
		for (String fileName : exceptionFileNameMap.keySet()) output.add(fileName);
		return output;
	}
}
