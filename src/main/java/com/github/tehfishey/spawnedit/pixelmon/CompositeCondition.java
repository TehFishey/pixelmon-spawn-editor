package com.github.tehfishey.spawnedit.pixelmon;

public class CompositeCondition {
	Condition conditions[];
	Condition antiConditions[];
	
	public Condition[] getConditions() { return conditions; }
	public void setConditions(Condition[] conditions) { this.conditions = conditions; }
	
	public Condition[] getAntiConditions() { return antiConditions; }
	public void setAntiConditions(Condition[] antiConditions) { this.antiConditions = antiConditions; }
}
