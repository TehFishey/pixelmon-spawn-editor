package com.github.tehfishey.spawnedit.pixelmon;

	// Approximate representation of Pixelmon's SpawnInfo class.
	// Used for modeling spawn data.

public class SpawnInfo {
	String typeID;
	String interval;
	String tags[];
	Condition condition;
	Condition anticondition;
	CompositeCondition compositeCondition;
	RarityMultiplier rarityMultipliers[];
	Integer requiredSpace;
	String stringLocationTypes[];
	Float rarity;
	Float percentage;
	
	public String getTypeID() { return typeID; }
	public void setTypeID(String typeID) { this.typeID = typeID; }
	
	public String getInterval() { return interval; }
	public void setInterval(String interval) { this.interval = interval; }
	
	public String[] getTags() { return tags; }
	public void setTags(String[] tags) { this.tags = tags; }
	
	public Condition getCondition() { return condition; }
	public void setCondition(Condition condition) { this.condition = condition; }
	
	public Condition getAntiCondition() { return anticondition; }
	public void setAntiCondition(Condition antiCondition) { this.anticondition = antiCondition; }
	
	public CompositeCondition getCompositeCondition() { return compositeCondition; }
	public void setCompositeCondition(CompositeCondition compositeCondition) { this.compositeCondition = compositeCondition; }
	
	public RarityMultiplier[] getRarityMultipliers() { return rarityMultipliers; }
	public void setRarityMultipliers(RarityMultiplier[] rarityMultipliers) { this.rarityMultipliers = rarityMultipliers; }
	
	public Integer getRequiredSpace() { return requiredSpace; }
	public void setRequiredSpace(int requiredSpace) { this.requiredSpace = requiredSpace; }
	
	public String[] getStringLocationTypes() { return stringLocationTypes; }
	public void setStringLocationTypes(String[] stringLocationTypes) { this.stringLocationTypes = stringLocationTypes; }
	
	public Float getRarity() { return rarity; }
	public void setRarity(float rarity) { this.rarity = rarity; }
	
	public Float getPercentage() { return percentage; }
	public void setPercentage(float percentage) { this.percentage = percentage; }
}
