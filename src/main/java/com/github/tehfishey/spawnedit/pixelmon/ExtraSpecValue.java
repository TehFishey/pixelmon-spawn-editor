package com.github.tehfishey.spawnedit.pixelmon;

	// Class which mirrors Pixelmon's json serialization for extra spec values/keys.
	// Used for modeling spawn data.

public class ExtraSpecValue {
	String key;
	String value;
	
	public String getKey() { return key; }
	public void setKey(String key) { this.key = key; }
	
	public String getValue() { return value; }
	public void setValue(String value) { this.value = value; }
}
