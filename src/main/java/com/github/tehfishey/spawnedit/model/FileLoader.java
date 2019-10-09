package com.github.tehfishey.spawnedit.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.github.tehfishey.spawnedit.pixelmon.SpawnSet;

	// Component of the FileManager class. Responsible for parsing and validating 
	// loaded .json data files using Gson. Returns SpawnSet objects, which represent
	// whole data file contents.

public class FileLoader {

	private Gson parser;
	
	public FileLoader() {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapterFactory(new ValidatorAdapterFactory());
		
		parser = builder.create();
	}
	
	public SpawnSet parse(Path file) throws IOException, JsonParseException {
		try (BufferedReader reader = Files.newBufferedReader(file)) {
			try {
				SpawnSet spawnSet = parser.fromJson(reader, SpawnSet.class);
				return spawnSet;
			} catch (JsonParseException e) {
				System.out.println(e.getMessage() + "\nJsonParseException @ Filepath: " + file.toString());
				throw e;
			}
		} catch (IOException e) {
			System.out.println(e.getMessage() + "\nIOException @ Filepath: " + file.toString());
            throw e;
        }
	}	
	
	private class ValidatorAdapterFactory implements TypeAdapterFactory {

		// Gson does not currently support error handling or catching of unknown properties for input validation purposes.
		// Suggested workaround taken from comments @ https://github.com/google/gson/issues/188. Credit to Patrik Dufrense (ikus060)
		
	    @Override
	    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
	        // If the type adapter is a reflective type adapter, we want to modify the implementation using reflection. The
	        // trick is to replace the Map object used to lookup the property name. Instead of returning null if the
	        // property is not found, we throw a Json exception to terminate the deserialization.
	        TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);

	        // Check if the type adapter is a reflective, cause this solution only work for reflection.
	        if (delegate instanceof ReflectiveTypeAdapterFactory.Adapter) {

	            try {
	                // Get reference to the existing boundFields.
	                Field f = delegate.getClass().getDeclaredField("boundFields");
	                f.setAccessible(true);
	                Map boundFields = (Map) f.get(delegate);

	                // Then replace it with our implementation throwing exception if the value is null.
	                boundFields = new LinkedHashMap(boundFields) {

	                    @Override
	                    public Object get(Object key) {

	                        Object value = super.get(key);
	                        if (value == null) {
	                            throw new JsonParseException("invalid property name: " + key);
	                        }
	                        return value;

	                    }

	                };
	                // Finally, push our custom map back using reflection.
	                f.set(delegate, boundFields);

	            } catch (Exception e) {
	                // Should never happen if the implementation doesn't change.
	                throw new IllegalStateException(e);
	            }

	        }
	        return delegate;
	    }

	}
	
}
