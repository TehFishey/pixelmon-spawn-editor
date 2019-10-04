package com.github.tehfishey.spawnedit.model.helpers;

public class Enums {
	private Enums() {}
	
	public static enum COLUMN_ID {
		spawnSetId("spawnSetId"),
		spawnSetIndex("spawnSetIndex"),
		pokemonSpecSpecies("pokemonSpecSpecies"),
		pokemonSpecLevel("pokemonSpecLevel"),
		pokemonSpecGender("pokemonSpecGender"),
		pokemonSpecStatus("pokemonSpecStatus"),
		pokemonSpecGrowthSize("pokemonSpecGrowthSize"),
		pokemonSpecNature("pokemonSpecNature"),
		pokemonSpecFormId("pokemonSpecFormId"),
		pokemonSpecPokeRusStage("pokemonSpecPokeRusStage"),
		pokemonSpecRandom("pokemonSpecRandom"),
		pokemonSpecCured("pokemonSpecCured"),
		pokemonSpecShiny("pokemonSpecShiny"),
		pokemonSpecEgg("pokemonSpecEgg"),
		pokemonSpecUntradeable("pokemonSpecUntradeable"),
		pokemonSpecUnbreedable("pokemonSpecUnbreedable"),
		pokemonSpecIVStats("pokemonSpecIVStats"),
		pokemonSpecEVStats("pokemonSpecEVStats"),
		pokemonSpecs("pokemonSpecs"),
		spawnType("spawnType"),
		intervalType("intervalType"),
		requiredSpace("requiredSpace"),
		conditionTime("conditionTime"),
		conditionWeather("conditionWeather"),
		conditionBiome("conditionBiome"),
		conditionTemperature("conditionTemperature"),
		conditionWorld("conditionWorld"),
		conditionDimension("conditionDimension"),
		conditionRequiredBlock("conditionRequiredBlock"),
		conditionNearbyBlock("conditionNearbyBlock"),
		conditionVariant("conditionVariant"),
		conditionMinX("conditionMinX"),
		conditionMaxX("conditionMaxX"),
		conditionMinZ("conditionMinZ"),
		conditionMaxZ("conditionMaxZ"),
		conditionMinY("conditionMinY"),
		conditionMaxY("conditionMaxY"),
		conditionMinLight("conditionMinLight"),
		conditionMaxLight("conditionMaxLight"),
		conditionRequiresSky("conditionRequiresSky"),
		conditionMoonPhase("conditionMoonPhase"),
		conditionTag("conditionTag"),
		antiConditionTime("antiConditionTime"),
		antiConditionWeather("antiConditionWeather"),
		antiConditionBiome("antiConditionBiome"),
		antiConditionTemperature("antiConditionTemperature"),
		antiConditionWorld("antiConditionWorld"),
		antiConditionDimension("antiConditionDimension"),
		antiConditionRequiredBlock("antiConditionRequiredBlock"),
		antiConditionNearbyBlock("antiConditionNearbyBlock"),
		antiConditionVariant("antiConditionVariant"),
		antiConditionMinX("antiConditionMinX"),
		antiConditionMaxX("antiConditionMaxX"),
		antiConditionMinZ("antiConditionMinZ"),
		antiConditionMaxZ("antiConditionMaxZ"),
		antiConditionMinY("antiConditionMinY"),
		antiConditionMaxY("antiConditionMaxY"),
		antiConditionMinLight("antiConditionMinLight"),
		antiConditionMaxLight("antiConditionMaxLight"),
		antiConditionRequiresSky("antiConditionRequiresSky"),
		antiConditionMoonPhase("antiConditionMoonPhase"),
		antiConditionTag("antiConditionTag"),
		compositeConditionConditions("compositeConditionConditions"),
		compositeConditionAntiConditions("compositeConditionAntiConditions"),
		rarity("rarity"),
		rarityMultipliers("rarityMultipliers"),
		percentage("percentage"),
		minLevel("minLevel"),
		maxLevel("maxLevel"),
		specificShinyRate("specificShinyRate"),
		specificBossRate("specificBossRate"),
		specificPokeRusRate("specificPokeRusRate"),
		heldItems("heldItems");
		
		private String fxId;
		//private Type type;
		
		COLUMN_ID(String fxId) {
			this.fxId = fxId;
		}
		
		public String getFxId() { return fxId; }
	}
}
