package com.github.tehfishey.spawnedit.model;

public class Enums {
	private Enums() {}
	
	public static enum ColumnId {
		spawnSetId("spawnSetId", Type.String),
		spawnSetIndex("spawnSetIndex", Type.Integer),
		pokemonSpecSpecies("pokemonSpecSpecies", Type.String),
		pokemonSpecLevel("pokemonSpecLevel", Type.Integer),
		pokemonSpecGender("pokemonSpecGender", Type.Enum),
		pokemonSpecStatus("pokemonSpecStatus", Type.Enum),
		pokemonSpecGrowthSize("pokemonSpecGrowthSize", Type.Enum),
		pokemonSpecNature("pokemonSpecNature", Type.Enum),
		pokemonSpecFormId("pokemonSpecFormId", Type.Integer),
		pokemonSpecPokeRusStage("pokemonSpecPokeRusStage", Type.Enum),
		pokemonSpecRandom("pokemonSpecRandom", Type.Boolean),
		pokemonSpecCured("pokemonSpecCured", Type.Boolean),
		pokemonSpecShiny("pokemonSpecShiny", Type.Boolean),
		pokemonSpecEgg("pokemonSpecEgg", Type.Boolean),
		pokemonSpecUntradeable("pokemonSpecUntradeable", Type.Boolean),
		pokemonSpecUnbreedable("pokemonSpecUnbreedable", Type.Boolean),
		pokemonSpecIVStats("pokemonSpecIVStats", Type.Class),
		pokemonSpecEVStats("pokemonSpecEVStats", Type.Class),
		pokemonSpecs("pokemonSpecs", Type.Class),
		spawnType("spawnType", Type.Enum),
		intervalType("intervalType", Type.Enum),
		requiredSpace("requiredSpace", Type.Float),
		conditionTime("conditionTime", Type.Enum),
		conditionWeather("conditionWeather", Type.Enum),
		conditionBiome("conditionBiome", Type.Enum),
		conditionTemperature("conditionTemperature", Type.Enum),
		conditionWorld("conditionWorld", Type.Integer),
		conditionDimension("conditionDimension", Type.Integer),
		conditionRequiredBlock("conditionRequiredBlock", Type.String),
		conditionNearbyBlock("conditionNearbyBlock", Type.String),
		conditionVariant("conditionVariant", Type.String),
		conditionMinX("conditionMinX", Type.Integer),
		conditionMaxX("conditionMaxX", Type.Integer),
		conditionMinZ("conditionMinZ", Type.Integer),
		conditionMaxZ("conditionMaxZ", Type.Integer),
		conditionMinY("conditionMinY", Type.Integer),
		conditionMaxY("conditionMaxY", Type.Integer),
		conditionMinLight("conditionMinLight", Type.Integer),
		conditionMaxLight("conditionMaxLight", Type.Integer),
		conditionRequiresSky("conditionRequiresSky", Type.Boolean),
		conditionMoonPhase("conditionMoonPhase", Type.Integer),
		conditionTag("conditionTag", Type.String),
		antiConditionTime("antiConditionTime", Type.Enum),
		antiConditionWeather("antiConditionWeather", Type.Enum),
		antiConditionBiome("antiConditionBiome", Type.Enum),
		antiConditionTemperature("antiConditionTemperature", Type.Enum),
		antiConditionWorld("antiConditionWorld", Type.Integer),
		antiConditionDimension("antiConditionDimension", Type.Integer),
		antiConditionRequiredBlock("antiConditionRequiredBlock", Type.String),
		antiConditionNearbyBlock("antiConditionNearbyBlock", Type.String),
		antiConditionVariant("antiConditionVariant", Type.String),
		antiConditionMinX("antiConditionMinX", Type.Integer),
		antiConditionMaxX("antiConditionMaxX", Type.Integer),
		antiConditionMinZ("antiConditionMinZ", Type.Integer),
		antiConditionMaxZ("antiConditionMaxZ", Type.Integer),
		antiConditionMinY("antiConditionMinY", Type.Integer),
		antiConditionMaxY("antiConditionMaxY", Type.Integer),
		antiConditionMinLight("antiConditionMinLight", Type.Integer),
		antiConditionMaxLight("antiConditionMaxLight", Type.Integer),
		antiConditionRequiresSky("antiConditionRequiresSky", Type.Boolean),
		antiConditionMoonPhase("antiConditionMoonPhase", Type.Integer),
		antiConditionTag("antiConditionTag", Type.String),
		compositeConditionConditions("compositeConditionConditions", Type.Class),
		compositeConditionAntiConditions("compositeConditionAntiConditions", Type.Class),
		rarity("rarity", Type.Float),
		rarityMultipliers("rarityMultipliers", Type.Class),
		percentage("percentage", Type.Float),
		minLevel("minLevel", Type.Integer),
		maxLevel("maxLevel", Type.Integer),
		specificShinyRate("specificShinyRate", Type.Float),
		specificBossRate("specificBossRate", Type.Float),
		specificPokeRusRate("specificPokeRusRate", Type.Float),
		heldItems("heldItems", Type.Class);
		
		private String fxId;
		private Type type;
		
		ColumnId(String fxId, Type type) {
			this.fxId = fxId;
			this.type = type;
		}
		
		public String getFxId() { return fxId; }
		public Type getType() { return type; }
	}
	
	public static enum Type {
		String,
		Integer,
		Float,
		Boolean,
		Enum,
		Class
	}
	
}
