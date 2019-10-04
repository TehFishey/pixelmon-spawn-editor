package com.github.tehfishey.spawnedit.pixelmon;

import com.github.tehfishey.spawnedit.pixelmon.helpers.Enums.TEMPERATURE;
import com.github.tehfishey.spawnedit.pixelmon.helpers.Enums.TIME;
import com.github.tehfishey.spawnedit.pixelmon.helpers.Enums.WEATHER;

public class Condition {
	
	TIME times[];
	WEATHER weathers[];
	String stringBiomes[];
	int dimensions[];
	String worlds[];
	int minX;
	int maxX;
	int minY;
	int maxY;
	int minZ;
	int maxZ;
	boolean seesSky;
	int moonPhase;
	int minLightLevel;
	int maxLightLevel;
	String tag;
	TEMPERATURE temperatures[];
	String baseBlocks[];
	String variant[];
	String neededNearbyBlocks[];

	public TIME[] getTimes() { return times; }
	public void setTimes(TIME[] times) { this.times = times; }
	
	public WEATHER[] getWeathers() { return weathers; }
	public void setWeathers(WEATHER[] weathers) { this.weathers = weathers; }
	
	public String[] getStringBiomes() { return stringBiomes; }
	public void setStringBiomes(String[] stringBiomes) { this.stringBiomes = stringBiomes; }
	
	public int[] getDimensions() { return dimensions; }
	public void setDimensions(int[] dimensions) { this.dimensions = dimensions; }
	
	public String[] getWorlds() { return worlds; }
	public void setWorlds(String[] worlds) { this.worlds = worlds; }
	
	public int getMinX() { return minX; }
	public void setMinX(int minX) { this.minX = minX; }
	
	public int getMaxX() { return maxX; }
	public void setMaxX(int maxX) { this.maxX = maxX; }
	
	public int getMinY() { return minY; }
	public void setMinY(int minY) { this.minY = minY; }
	
	public int getMaxY() { return maxY; }
	public void setMaxY(int maxY) { this.maxY = maxY; }
	
	public int getMinZ() { return minZ; }
	public void setMinZ(int minZ) { this.minZ = minZ; }
	
	public int getMaxZ() { return maxZ; }
	public void setMaxZ(int maxZ) { this.maxZ = maxZ; }
	
	public boolean isSeesSky() { return seesSky; }
	public void setSeesSky(boolean seesSky) { this.seesSky = seesSky; }
	
	public int getMoonPhase() { return moonPhase; }
	public void setMoonPhase(int moonPhase) { this.moonPhase = moonPhase; }
	
	public int getMinLightLevel() { return minLightLevel; }
	public void setMinLightLevel(int minLightLevel) { this.minLightLevel = minLightLevel; }
	
	public int getMaxLightLevel() { return maxLightLevel; }
	public void setMaxLightLevel(int maxLightLevel) { this.maxLightLevel = maxLightLevel; }
	
	public String getTag() { return tag; }
	public void setTag(String tag) { this.tag = tag; }
	
	public TEMPERATURE[] getTemperatures() { return temperatures; }
	public void setTemperatures(TEMPERATURE[] temperatures) { this.temperatures = temperatures; }
	
	public String[] getBaseBlocks() { return baseBlocks; }
	public void setBaseBlocks(String[] baseBlocks) { this.baseBlocks = baseBlocks; }
	
	public String[] getVariant() { return variant; }
	public void setVariant(String[] variant) { this.variant = variant; }
	
	public String[] getNeededNearbyBlocks() { return neededNearbyBlocks; }
	public void setNeededNearbyBlocks(String[] neededNearbyBlocks) { this.neededNearbyBlocks = neededNearbyBlocks; }
}
