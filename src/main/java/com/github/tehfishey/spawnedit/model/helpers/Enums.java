package com.github.tehfishey.spawnedit.model.helpers;

	// Enums for each data field contained in SpawnEntries, which are used by the Controller when generating
	// TableEntries. This class should be moved to the Controller, but first I need to update the SpawnEntry class 
	// to work without tracking its own TableEntries (Table entries should be an element of the controller, not the model).

public class Enums {
	private Enums() {}
	
	public static enum ColumnId {
		spawnSetId("spawnSetId", ColumnType.String),
		spawnSetIndex("spawnSetIndex", ColumnType.Integer),
		pokemonSpecSpecies("pokemonSpecSpecies", ColumnType.String),
		pokemonSpecLevel("pokemonSpecLevel", ColumnType.Integer),
		pokemonSpecGender("pokemonSpecGender", ColumnType.Enum),
		pokemonSpecStatus("pokemonSpecStatus", ColumnType.Enum),
		pokemonSpecGrowthSize("pokemonSpecGrowthSize", ColumnType.Enum),
		pokemonSpecNature("pokemonSpecNature", ColumnType.Enum),
		pokemonSpecFormId("pokemonSpecFormId", ColumnType.Integer),
		pokemonSpecPokeRusStage("pokemonSpecPokeRusStage", ColumnType.Enum),
		pokemonSpecRandom("pokemonSpecRandom", ColumnType.Boolean),
		pokemonSpecCured("pokemonSpecCured", ColumnType.Boolean),
		pokemonSpecShiny("pokemonSpecShiny", ColumnType.Boolean),
		pokemonSpecEgg("pokemonSpecEgg", ColumnType.Boolean),
		pokemonSpecUntradeable("pokemonSpecUntradeable", ColumnType.Boolean),
		pokemonSpecUnbreedable("pokemonSpecUnbreedable", ColumnType.Boolean),
		pokemonSpecIVStats("pokemonSpecIVStats", ColumnType.Class),
		pokemonSpecEVStats("pokemonSpecEVStats", ColumnType.Class),
		pokemonSpecs("pokemonSpecs", ColumnType.Class),
		spawnType("spawnType", ColumnType.Enum),
		intervalType("intervalType", ColumnType.Enum),
		requiredSpace("requiredSpace", ColumnType.Float),
		conditionTime("conditionTime", ColumnType.Enum),
		conditionWeather("conditionWeather", ColumnType.Enum),
		conditionBiome("conditionBiome", ColumnType.Enum),
		conditionTemperature("conditionTemperature", ColumnType.Enum),
		conditionWorld("conditionWorld", ColumnType.Integer),
		conditionDimension("conditionDimension", ColumnType.Integer),
		conditionRequiredBlock("conditionRequiredBlock", ColumnType.String),
		conditionNearbyBlock("conditionNearbyBlock", ColumnType.String),
		conditionVariant("conditionVariant", ColumnType.String),
		conditionMinX("conditionMinX", ColumnType.Integer),
		conditionMaxX("conditionMaxX", ColumnType.Integer),
		conditionMinZ("conditionMinZ", ColumnType.Integer),
		conditionMaxZ("conditionMaxZ", ColumnType.Integer),
		conditionMinY("conditionMinY", ColumnType.Integer),
		conditionMaxY("conditionMaxY", ColumnType.Integer),
		conditionMinLight("conditionMinLight", ColumnType.Integer),
		conditionMaxLight("conditionMaxLight", ColumnType.Integer),
		conditionRequiresSky("conditionRequiresSky", ColumnType.Boolean),
		conditionMoonPhase("conditionMoonPhase", ColumnType.Integer),
		conditionTag("conditionTag", ColumnType.String),
		antiConditionTime("antiConditionTime", ColumnType.Enum),
		antiConditionWeather("antiConditionWeather", ColumnType.Enum),
		antiConditionBiome("antiConditionBiome", ColumnType.Enum),
		antiConditionTemperature("antiConditionTemperature", ColumnType.Enum),
		antiConditionWorld("antiConditionWorld", ColumnType.Integer),
		antiConditionDimension("antiConditionDimension", ColumnType.Integer),
		antiConditionRequiredBlock("antiConditionRequiredBlock", ColumnType.String),
		antiConditionNearbyBlock("antiConditionNearbyBlock", ColumnType.String),
		antiConditionVariant("antiConditionVariant", ColumnType.String),
		antiConditionMinX("antiConditionMinX", ColumnType.Integer),
		antiConditionMaxX("antiConditionMaxX", ColumnType.Integer),
		antiConditionMinZ("antiConditionMinZ", ColumnType.Integer),
		antiConditionMaxZ("antiConditionMaxZ", ColumnType.Integer),
		antiConditionMinY("antiConditionMinY", ColumnType.Integer),
		antiConditionMaxY("antiConditionMaxY", ColumnType.Integer),
		antiConditionMinLight("antiConditionMinLight", ColumnType.Integer),
		antiConditionMaxLight("antiConditionMaxLight", ColumnType.Integer),
		antiConditionRequiresSky("antiConditionRequiresSky", ColumnType.Boolean),
		antiConditionMoonPhase("antiConditionMoonPhase", ColumnType.Integer),
		antiConditionTag("antiConditionTag", ColumnType.String),
		compositeConditionConditions("compositeConditionConditions", ColumnType.Class),
		compositeConditionAntiConditions("compositeConditionAntiConditions", ColumnType.Class),
		rarity("rarity", ColumnType.Float),
		rarityMultipliers("rarityMultipliers", ColumnType.Class),
		percentage("percentage", ColumnType.Float),
		minLevel("minLevel", ColumnType.Integer),
		maxLevel("maxLevel", ColumnType.Integer),
		specificShinyRate("specificShinyRate", ColumnType.Float),
		specificBossRate("specificBossRate", ColumnType.Float),
		specificPokeRusRate("specificPokeRusRate", ColumnType.Float),
		heldItems("heldItems", ColumnType.Class);
		
		private String fxId;
		private ColumnType type;
		
		ColumnId(String fxId, ColumnType type) {
			this.fxId = fxId;
			this.type = type;
		}
		
		public String getFxId() { return fxId; }
		public ColumnType getType() { return type; }
	}
	
	public static enum ColumnType {
		String,
		Integer,
		Float,
		Boolean,
		Enum,
		Class
	}	
}
