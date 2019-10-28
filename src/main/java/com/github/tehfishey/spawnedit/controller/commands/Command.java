package com.github.tehfishey.spawnedit.controller.commands;

public abstract class Command {
	protected boolean canUndo;
	
	public boolean canUndo() { return canUndo; }
	
	public Command() {
		canUndo = false;
	}
	
	public abstract void execute();
	public abstract void undo();
	public abstract void redo();
}
