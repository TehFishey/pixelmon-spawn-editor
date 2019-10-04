package com.github.tehfishey.spawnedit.pixelmon;

public class ItemStack {
	String itemID;
	int quantity;
	int meta;
	String nbt;
	float percentChance;
	
	public String getItemID() { return itemID; }
	public void setItemID(String itemID) { this.itemID = itemID; }
	
	public int getQuantity() { return quantity; }
	public void setQuantity(int quantity) { this.quantity = quantity; }
	
	public int getMeta() { return meta; }
	public void setMeta(int meta) { this.meta = meta; }
	
	public String getNbt() { return nbt; }
	public void setNbt(String nbt) { this.nbt = nbt; }
	
	public float getPercentChance() { return percentChance; }
	public void setPercentChance(float percentChance) { this.percentChance = percentChance; }
}
