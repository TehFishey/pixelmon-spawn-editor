package com.github.tehfishey.spawnedit.model.exceptions;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.JsonParseException;

public class BatchJsonException extends Exception {
	private static final long serialVersionUID = 1L;
	final HashMap<String, JsonParseException> exceptionFileNameMap;
	
	public BatchJsonException(String message, HashMap<String, JsonParseException> exceptionFileNameMap) {
		super(message);
		this.exceptionFileNameMap = exceptionFileNameMap;
	}
	
	public BatchJsonException(HashMap<String, JsonParseException> exceptionFileNameMap) {
		this.exceptionFileNameMap = exceptionFileNameMap;
		System.out.println("BatchJsonException created...");
	}
	
	public ArrayList<String> getErrorPaths() {
		ArrayList<String> output = new ArrayList<String>();
		for (String fileName : exceptionFileNameMap.keySet()) output.add(fileName);
		return output;
	}
}
