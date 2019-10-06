package com.github.tehfishey.spawnedit.pixelmon;

public class CompositeCondition {
	Condition conditions[];
	Condition anticonditions[];
	
	public Condition[] getConditions() { return conditions; }
	public void setConditions(Condition[] conditions) { this.conditions = conditions; }
	
	public Condition[] getAntiConditions() { return anticonditions; }
	public void setAntiConditions(Condition[] antiConditions) { this.anticonditions = antiConditions; }
}
