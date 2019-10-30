package com.github.tehfishey.spawnedit.controller;

import java.util.ArrayDeque;

import com.github.tehfishey.spawnedit.controller.commands.Command;

public class CommandManager {
	private LimitedStack<Command> undoStack;
	private LimitedStack<Command> redoStack;
	
	public CommandManager() {
		this.undoStack = new LimitedStack<Command>(15);
		this.redoStack = new LimitedStack<Command>(15);
	}
	
	public void execute(Command cmd) {
		cmd.execute();
		redoStack.clear();
		if (cmd.canUndo()) undoStack.addLast(cmd);
	}
	
	public void executeUndo() {
		if (undoStack.isEmpty()) return;
		Command cmd = undoStack.removeLast();
		
		cmd.undo();
		redoStack.addLast(cmd);
	}
	
	public void executeRedo() {
		if (redoStack.isEmpty()) return;
		Command cmd = redoStack.removeLast();
		
		cmd.redo();
		undoStack.addLast(cmd);
	}
	
	private class LimitedStack<E> extends ArrayDeque<E> {
		private final int maxSize;
		
		public LimitedStack(int size) {
			maxSize = size;
		}

		@Override
		public void addLast(E e) {
			if (this.size() >= maxSize) this.removeFirst();
			super.addLast(e);
		}
		
		@Override
		public boolean offerLast(E e) {
			if (this.size() >= maxSize) this.removeFirst();
			return super.offerLast(e);
		}
		
		
	}
}
