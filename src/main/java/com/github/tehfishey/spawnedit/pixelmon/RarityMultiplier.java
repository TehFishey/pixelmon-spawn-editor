package com.github.tehfishey.spawnedit.pixelmon;

	// Approximate representation of Pixelmon's RarityMultiplier class.
	// Used for modeling spawn data.

public class RarityMultiplier {
	Float multiplier;
	Condition condition;
	Condition antiCondition;
	
	public Float getMultiplier() { return multiplier; }
	public void setMultiplier(float multiplier) { this.multiplier = multiplier; }
	
	public Condition getCondition() { return condition; }
	public void setCondition(Condition condition) { this.condition = condition; }
	
	public Condition getAntiCondition() { return antiCondition; }
	public void setAntiCondition(Condition antiCondition) { this.antiCondition = antiCondition; }
}
