package com.github.tehfishey.spawnedit.pixelmon;

import com.github.tehfishey.spawnedit.pixelmon.Enums.Temperature;
import com.github.tehfishey.spawnedit.pixelmon.Enums.Time;
import com.github.tehfishey.spawnedit.pixelmon.Enums.Weather;

public class Condition {
	
	Time times[];
	Weather weathers[];
	String stringBiomes[];
	Integer dimensions[];
	String worlds[];
	Integer minX;
	Integer maxX;
	Integer minY;
	Integer maxY;
	Integer minZ;
	Integer maxZ;
	Boolean seesSky;
	Integer moonPhase;
	Integer minLightLevel;
	Integer maxLightLevel;
	String tag;
	Temperature temperature;
	String baseBlocks[];
	String variant[];
	String neededNearbyBlocks[];

	public Time[] getTimes() { return times; }
	public void setTimes(Time[] times) { this.times = times; }
	
	public Weather[] getWeathers() { return weathers; }
	public void setWeathers(Weather[] weathers) { this.weathers = weathers; }
	
	public String[] getStringBiomes() { return stringBiomes; }
	public void setStringBiomes(String[] stringBiomes) { this.stringBiomes = stringBiomes; }
	
	public Integer[] getDimensions() { return dimensions; }
	public void setDimensions(Integer[] dimensions) { this.dimensions = dimensions; }
	
	public String[] getWorlds() { return worlds; }
	public void setWorlds(String[] worlds) { this.worlds = worlds; }
	
	public Integer getMinX() { return minX; }
	public void setMinX(int minX) { this.minX = minX; }
	
	public Integer getMaxX() { return maxX; }
	public void setMaxX(int maxX) { this.maxX = maxX; }
	
	public Integer getMinY() { return minY; }
	public void setMinY(int minY) { this.minY = minY; }
	
	public Integer getMaxY() { return maxY; }
	public void setMaxY(int maxY) { this.maxY = maxY; }
	
	public Integer getMinZ() { return minZ; }
	public void setMinZ(int minZ) { this.minZ = minZ; }
	
	public Integer getMaxZ() { return maxZ; }
	public void setMaxZ(int maxZ) { this.maxZ = maxZ; }
	
	public Boolean isSeesSky() { return seesSky; }
	public void setSeesSky(boolean seesSky) { this.seesSky = seesSky; }
	
	public Integer getMoonPhase() { return moonPhase; }
	public void setMoonPhase(int moonPhase) { this.moonPhase = moonPhase; }
	
	public Integer getMinLightLevel() { return minLightLevel; }
	public void setMinLightLevel(int minLightLevel) { this.minLightLevel = minLightLevel; }
	
	public Integer getMaxLightLevel() { return maxLightLevel; }
	public void setMaxLightLevel(int maxLightLevel) { this.maxLightLevel = maxLightLevel; }
	
	public String getTag() { return tag; }
	public void setTag(String tag) { this.tag = tag; }
	
	public Temperature getTemperature() { return temperature; }
	public void setTemperature(Temperature temperature) { this.temperature = temperature; }
	
	public String[] getBaseBlocks() { return baseBlocks; }
	public void setBaseBlocks(String[] baseBlocks) { this.baseBlocks = baseBlocks; }
	
	public String[] getVariant() { return variant; }
	public void setVariant(String[] variant) { this.variant = variant; }
	
	public String[] getNeededNearbyBlocks() { return neededNearbyBlocks; }
	public void setNeededNearbyBlocks(String[] neededNearbyBlocks) { this.neededNearbyBlocks = neededNearbyBlocks; }
}
