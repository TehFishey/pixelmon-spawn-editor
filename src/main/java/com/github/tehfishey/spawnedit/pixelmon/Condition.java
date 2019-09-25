package com.github.tehfishey.spawnedit.pixelmon;

import com.github.tehfishey.spawnedit.pixelmon.Constants.TEMPERATURE;
import com.github.tehfishey.spawnedit.pixelmon.Constants.TIME;
import com.github.tehfishey.spawnedit.pixelmon.Constants.WEATHER;

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
	TEMPERATURE temperature[];
	String baseBlocks[];
	String variant[];
	String neededNearbyBlocks[];
}
