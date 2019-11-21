package com.github.tehfishey.spawnedit.pixelmon;

import com.github.tehfishey.spawnedit.pixelmon.Enums.Ability;
import com.github.tehfishey.spawnedit.pixelmon.Enums.Boss;
import com.github.tehfishey.spawnedit.pixelmon.Enums.Gender;
import com.github.tehfishey.spawnedit.pixelmon.Enums.Growth;
import com.github.tehfishey.spawnedit.pixelmon.Enums.Nature;
import com.github.tehfishey.spawnedit.pixelmon.Enums.PokeRusType;

	// Approximate representation of Pixelmon's PokemonSpec class.
	// Some elements currently nonexistant/not tracked until I work out logic for how they will be handled in the utility.
	// Used for modeling spawn data.


public class PokemonSpec {
	
	String name;
	Integer level;
	Gender gender;
	Growth growth;
	Nature nature;
	Ability ability;
	Boss boss;
	//BALL ball;
	Integer form;
	PokeRusType pokerusType;
	Boolean pokerusSpread;
	Boolean shiny;
	Boolean cured;
	Boolean egg;
	ExtraSpecValue[] extraSpecs;
	//Boolean random;
	//Boolean untradeable;
	//Boolean unbreedable;
	//Status status;
	//Integer iv[];
	//Integer ev[];
	
	public String getTypeName() { return name; }
	public void setTypeName(String typeName) { this.name = typeName; }
	
	public Integer getLevel() { return level; }
	public void setLevel(int level) { this.level = level; }
	
	public Gender getGender() { return gender; }
	public void setGender(Gender gender) { this.gender = gender; }
	
	public Boolean isShiny() { return shiny; }
	public void setShiny(boolean shiny) { this.shiny = shiny; }
	
	public Growth getGrowth() { return growth; }
	public void setGrowth(Growth growth) { this.growth = growth; }
	
	public Nature getNature() { return nature; }
	public void setNature(Nature nature) { this.nature = nature; }
	
	public Integer getForm() { return form; }
	public void setForm(int form) { this.form = form; }
	
	public PokeRusType getPokerus() { return pokerusType; }
	public void setPokerus(PokeRusType pokerus) { this.pokerusType = pokerus; }
	
	public Boolean isCured() { return cured; }
	public void setCured(boolean cured) { this.cured = cured; }
	
	public Boolean isEgg() { return egg; }
	public void setEgg(boolean egg) { this.egg = egg; }
	
	//public Boolean isRandom() { return random; }
	//public void setRandom(boolean random) { this.random = random; }
	
	//public Boolean isUntradeable() { return untradeable; }
	//public void setUntradeable(boolean untradeable) { this.untradeable = untradeable; }
	
	//public Boolean isUnbreedable() { return unbreedable; }
	//public void setUnbreedable(boolean unbreedable) { this.unbreedable = unbreedable; }
	
	//public Status getStatus() { return status; }
	//public void setStatus(Status status) { this.status = status; }
	
	//public Integer[] getIv() { return iv; }
	//public void setIv(Integer[] iv) { this.iv = iv; }
	
	//public Integer[] getEv() { return ev; }
	//public void setEv(Integer[] ev) { this.ev = ev; }
}
