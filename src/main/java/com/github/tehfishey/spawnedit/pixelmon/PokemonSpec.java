package com.github.tehfishey.spawnedit.pixelmon;

import com.github.tehfishey.spawnedit.pixelmon.helpers.Enums.ABILITY;
import com.github.tehfishey.spawnedit.pixelmon.helpers.Enums.GENDER;
import com.github.tehfishey.spawnedit.pixelmon.helpers.Enums.NATURE;
import com.github.tehfishey.spawnedit.pixelmon.helpers.Enums.STATUS;

public class PokemonSpec {
	
	String name;
	int level;
	GENDER gender;
	boolean shiny;
	float growth;
	NATURE nature;
	ABILITY ability;
	//BOSS boss;
	//BALL ball;
	int form;
	char pokerus;
	boolean cured;
	boolean egg;
	boolean random;
	boolean untradeable;
	boolean unbreedable;
	STATUS status;
	int iv[];
	int ev[];
	
	public String getTypeName() { return name; }
	public void setTypeName(String typeName) { this.name = typeName; }
	
	public int getLevel() { return level; }
	public void setLevel(int level) { this.level = level; }
	
	public GENDER getGender() { return gender; }
	public void setGender(GENDER gender) { this.gender = gender; }
	
	public boolean isShiny() { return shiny; }
	public void setShiny(boolean shiny) { this.shiny = shiny; }
	
	public float getGrowth() { return growth; }
	public void setGrowth(float growth) { this.growth = growth; }
	
	public NATURE getNature() { return nature; }
	public void setNature(NATURE nature) { this.nature = nature; }
	
	public int getForm() { return form; }
	public void setForm(int form) { this.form = form; }
	
	public char getPokerus() { return pokerus; }
	public void setPokerus(char pokerus) { this.pokerus = pokerus; }
	
	public boolean isCured() { return cured; }
	public void setCured(boolean cured) { this.cured = cured; }
	
	public boolean isEgg() { return egg; }
	public void setEgg(boolean egg) { this.egg = egg; }
	
	public boolean isRandom() { return random; }
	public void setRandom(boolean random) { this.random = random; }
	
	public boolean isUntradeable() { return untradeable; }
	public void setUntradeable(boolean untradeable) { this.untradeable = untradeable; }
	
	public boolean isUnbreedable() { return unbreedable; }
	public void setUnbreedable(boolean unbreedable) { this.unbreedable = unbreedable; }
	
	public STATUS getStatus() { return status; }
	public void setStatus(STATUS status) { this.status = status; }
	
	public int[] getIv() { return iv; }
	public void setIv(int[] iv) { this.iv = iv; }
	
	public int[] getEv() { return ev; }
	public void setEv(int[] ev) { this.ev = ev; }
}
