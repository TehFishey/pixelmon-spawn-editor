package com.github.tehfishey.spawnedit.pixelmon;

public class RarityMultiplier {
	float multiplier;
	Condition condition;
	Condition antiCondition;
	
	public float getMultiplier() { return multiplier; }
	public void setMultiplier(float multiplier) { this.multiplier = multiplier; }
	
	public Condition getCondition() { return condition; }
	public void setCondition(Condition condition) { this.condition = condition; }
	
	public Condition getAntiCondition() { return antiCondition; }
	public void setAntiCondition(Condition antiCondition) { this.antiCondition = antiCondition; }
}
