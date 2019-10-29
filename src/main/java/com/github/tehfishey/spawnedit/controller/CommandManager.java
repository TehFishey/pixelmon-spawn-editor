package com.github.tehfishey.spawnedit.controller;

import com.github.tehfishey.spawnedit.controller.commands.Command;
import com.google.common.collect.EvictingQueue;

public class CommandManager {
	private EvictingQueue<Command> undoStack;
	private EvictingQueue<Command> redoStack;
	
	public CommandManager() {
		this.undoStack = EvictingQueue.create(15);
		this.redoStack = EvictingQueue.create(15);
	}
	
	public void execute(Command cmd) {
		cmd.execute();
		redoStack.clear();
		if (cmd.canUndo()) undoStack.add(cmd);
	}
	
	public void executeUndo() {
		if (undoStack.isEmpty()) return;
		Command cmd = undoStack.remove();
		
		cmd.undo();
		redoStack.add(cmd);
	}
	
	public void executeRedo() {
		if (redoStack.isEmpty()) return;
		Command cmd = redoStack.remove();
		
		cmd.undo();
		redoStack.remove(cmd);
		undoStack.add(cmd);
	}
}
