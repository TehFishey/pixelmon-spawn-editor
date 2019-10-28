package com.github.tehfishey.spawnedit.controller;

import java.util.Stack;

import com.github.tehfishey.spawnedit.controller.commands.Command;

public class CommandManager {
	private Stack<Command> undoStack;
	private Stack<Command> redoStack;
	
	public CommandManager() {
		this.undoStack = new Stack<Command>();
		this.redoStack = new Stack<Command>();
	}
	
	public void execute(Command cmd) {
		cmd.execute();
		
		if (cmd.canUndo()) undoStack.add(cmd);
		else { 
			undoStack.clear(); 
			redoStack.clear();
		}
	}
	
	public void executeUndo() {
		if (undoStack.isEmpty()) return;
		Command cmd = undoStack.get(undoStack.size()-1);
		
		cmd.undo();
		undoStack.remove(cmd);
		redoStack.add(cmd);
	}
	
	public void executeRedo() {
		if (redoStack.isEmpty()) return;
		Command cmd = redoStack.get(redoStack.size()-1);
		
		cmd.undo();
		redoStack.remove(cmd);
		undoStack.add(cmd);
	}
}
