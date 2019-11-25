package com.github.tehfishey.spawnedit.pixelmon;

	// Approximate representation of Pixelmon's CompositeCondition class.
	// Used for modeling spawn data.

public class CompositeCondition {
	Condition conditions[];
	Condition anticonditions[];
	
	public Condition[] getConditions() { return conditions; }
	public void setConditions(Condition[] conditions) { this.conditions = conditions; }
	
	public Condition[] getAntiConditions() { return anticonditions; }
	public void setAntiConditions(Condition[] antiConditions) { this.anticonditions = antiConditions; }
}
