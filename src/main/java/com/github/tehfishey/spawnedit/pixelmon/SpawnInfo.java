package com.github.tehfishey.spawnedit.pixelmon;

public class SpawnInfo {
	String typeID;
	String interval;
	String tags[];
	Condition condition;
	Condition antiCondition;
	CompositeCondition compositeCondition;
	RarityMultiplier rarityMultipliers[];
	int requiredSpace;
	String stringLocationTypes[];
	float rarity;
	float percentage;
	
	public String getTypeID() { return typeID; }
	public void setTypeID(String typeID) { this.typeID = typeID; }
	
	public String getInterval() { return interval; }
	public void setInterval(String interval) { this.interval = interval; }
	
	public String[] getTags() { return tags; }
	public void setTags(String[] tags) { this.tags = tags; }
	
	public Condition getCondition() { return condition; }
	public void setCondition(Condition condition) { this.condition = condition; }
	
	public Condition getAntiCondition() { return antiCondition; }
	public void setAntiCondition(Condition antiCondition) { this.antiCondition = antiCondition; }
	
	public CompositeCondition getCompositeCondition() { return compositeCondition; }
	public void setCompositeCondition(CompositeCondition compositeCondition) { this.compositeCondition = compositeCondition; }
	
	public RarityMultiplier[] getRarityMultipliers() { return rarityMultipliers; }
	public void setRarityMultipliers(RarityMultiplier[] rarityMultipliers) { this.rarityMultipliers = rarityMultipliers; }
	
	public int getRequiredSpace() { return requiredSpace; }
	public void setRequiredSpace(int requiredSpace) { this.requiredSpace = requiredSpace; }
	
	public String[] getStringLocationTypes() { return stringLocationTypes; }
	public void setStringLocationTypes(String[] stringLocationTypes) { this.stringLocationTypes = stringLocationTypes; }
	
	public float getRarity() { return rarity; }
	public void setRarity(float rarity) { this.rarity = rarity; }
	
	public float getPercentage() { return percentage; }
	public void setPercentage(float percentage) { this.percentage = percentage; }
}
