package com.github.tehfishey.spawnedit;

	// Dummy 'launcher' class for JavaFX application. Because the Java11 runtime strongly requires
	// the JavaFX platform to be available as a module if Main extends javafx.application.Application,
	// and this project isn't modular, our main class needs to be set up in this manner.

public class Main {
	
    public static void main(String[] args) {
    	PixelmonSpawnEditor.main(args);
    }
}
