package com.github.tehfishey.spawnedit.model.exceptions;

	// Custom exception to be raised when the FileManager attempts to load a
	// .json file which is already represented in the Model.

public class DuplicateIDException extends Exception {
	private static final long serialVersionUID = 1L;
	final String fileId;
	
	public DuplicateIDException(String message, String fileId) {
		super(message);
		this.fileId = fileId;
	}
	
	public DuplicateIDException(String fileId) {
		this.fileId = fileId;
	}
	
	public String getDuplicateString() {
		return this.fileId;
	}
}
