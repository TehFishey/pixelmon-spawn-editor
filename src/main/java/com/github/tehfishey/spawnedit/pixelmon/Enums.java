package com.github.tehfishey.spawnedit.pixelmon;

public class Enums {
	
	private Enums() {}
	
	public static enum Time {
		DAWN("DAWN"),
		MORNING("MORNING"),
		DAY("DAY"),
		MIDDAY("MIDDAY"),
		AFTERNOON("AFTERNOON"),
		DUSK("DUSK"),
		NIGHT("NIGHT"),
		MIDNIGHT("MIDNIGHT");
		
		private String time;
		
		Time(String time) {
			this.time = time;
		}
		
		public String getTime() {
			return time;
		}
	}
	
	public static enum Weather {
		CLEAR("CLEAR"),
		RAIN("RAIN"),
		STORM("STORM");
		
		private String weather;
				
		Weather(String weather) {
			this.weather = weather;
		}
				
		public String getWeather() {
			return weather;
		}		
	}
	
	public static enum Temperature {
		OCEAN("OCEAN"),
		COLD("COLD"),
		MEDIUM("MEDIUM"),
		WARM("WARM");
		
		private String temperature;
		
		Temperature(String temperature) {
			this.temperature = temperature;
		}
				
		public String getTemperature() {
			return temperature;
		}	
	}

	public static enum Gender {
		MALE("male"),
		FEMALE("female");
		
		private String gender;
		
		Gender(String gender) {
			this.gender = gender;
		}
				
		public String getGender() {
			return gender;
		}	
	}
	
	public static enum Growth {
		PYGMY("Pygmy"),
		RUNT("Runt"),
		SMALL("Small"),
		ORDINARY("Orginary"),
		HUGE("Huge"),
		GIANT("Giant"),
		ENORMOUS("Enormous"),
		GINOURMOUS("Ginormous"),
		MICROSCOPIC("Microscopic");
		
		private String growth;
		
		Growth(String growth) {
			this.growth = growth;
		}
		
		public String getGrowth() {
			return growth;
		}
	}
	
	public static enum Boss {
		NOTBOSS("NotBoss"),
		UNCOMMON("Uncommon"),
		RARE("Rare"),
		LEGENDARY("Legendary"),
		ULTIMATE("Ultimate"),
		EQUAL("Equal");
		
		private String bossType;
		
		Boss(String bossType) {
			this.bossType = bossType;
		}
		
		public String getBossType() {
			return bossType;
		}
	}
	
	public static enum PokeRusType {
		UNINFECTED("UNINFECTED"),
		A("A"),
		B("B"),
		C("C"),
		D("D");
		
		private String stage;
		
		PokeRusType(String stage) {
			this.stage = stage;
		}
		
		public String getPokeRusType() {
			return stage;
		}
	}
	
	public static enum Status {
		SLEEP("sleep"),
		BURNE("burn"),
		PARALYSIS("paralysis"),
		FROZEN("frozen"),
		POISON("poison"),
		BADLY_POISON("poisonbadly");
		
		private String status;
		
		Status(String status) {
			this.status = status;
		}
				
		public String getStatus() {
			return status;
		}	
	}
	
	public static enum Nature {
		ADAMANT("adamant"),
		BASHFUL("bashful"),
		BOLD("bold"),
		BRAVE("brave"),
		CALM("calm"),
		CAREFUL("careful"),
		DOCILE("docile"),
		GENTLE("gentle"),
		HARDY("hardy"),
		HASTY("hasty"),
		IMPISH("impish"),
		JOLLY("jolly"),
		LAX("lax"),
		LONELY("lonely"),
		MILD("mild"),
		MODEST("modest"),
		NAIVE("naive"),
		NAUGHTY("naughty"),
		QUIET("quiet"),
		QUIRKY("quirky"),
		RASH("rash"),
		RELAXED("relaxed"),
		SASSY("sassy"),
		SERIOUS("serious"),
		TIMID("timid");
		
		private String nature;
		
		Nature(String nature) {
			this.nature = nature;
		}
				
		public String getNature() {
			return nature;
		}	
	}
	
	public static enum Ability {
		ADAPTABILITY("Adaptability"),
		AERILATE("Aerilate"),
		AFTERMATH("Aftermath"),
		AIR_LOCK("AirLock"),
		ANALYTIC("Analytic"),
		ANGER_POINT("AngerPoint"),
		ANTICIPATION("Anticipation"),
		ARENA_TRAP("ArenaTrap"),
		AROMA_VEIL("AromaVeil"),
		AURA_BREAK("AuraBreak"),
		BAD_DREAMS("BadDreams"),
		BATTERY("Battery"),
		BATTLE_ARMOR("BattleArmor"),
		BATTLE_BOND("BattleBond"),
		BERSERK("Berserk"),
		BIG_PECKS("BigPecks"),
		BLAZE("Blaze"),
		BULLETPROOF("Bulletproof"),
		CHEEK_POUCH("CheekPouch"),
		CHLOROPHYLL("Chlorophyll"),
		CLEAR_BODY("ClearBody"),
		CLOUD_NINE("CloudNine"),
		COLOR_CHANGE("ColorChange"),
		COMATOSE("Comatose"),
		COMPETITIVE("Competitive"),
		COMPOUND_EYES("CompoundEyes"),
		CONTRARY("Contrary"),
		CORROSION("Corrosion"),
		CURSED_BODY("CursedBody"),
		CUTE_CHARM("CuteCharm"),
		DAMP("Damp"),
		DANCER("Dancer"),
		DARK_AURA("DarkAura"),
		DAZZLING("Dazzling"),
		DEFEATIST("Defeatist"),
		DEFIANT("Defiant"),
		DELTA_STREAM("DeltaStream"),
		DISGUISE("Disguise"),
		DOWNLOAD("Download"),
		DRIZZLE("Drizzle"),
		DROUGHT("Drought"),
		DRY_SKIN("DrySkin"),
		EARLY_BIRD("EarlyBird"),
		EFFECT_SPORE("EffectSpore"),
		ELECTRIC_SURGE("ElectricSurge"),
		EMERGENCY_EXIT("EmergencyExit"),
		FAIRY_AURA("FairyAura"),
		FILTER("Filter"),
		FLAME_BODY("FlameBody"),
		FLARE_BOOST("FlareBoost"),
		FLASH_FIRE("FlashFire"),
		FLOWER_GIFT("FlowerGift"),
		FLOWER_VEIL("FlowerVeil"),
		FLUFFY("Fluffy"),
		FORECAST("Forecast"),
		FOREWARN("Forewarn"),
		FRIEND_GUARD("FriendGuard"),
		FRISK("Frisk"),
		FUR_COAT("FurCoat"),
		FULL_METAL_BODY("FullMetalBody"),
		GALE_WINGS("GaleWings"),
		GALVANIZE("Galvanize"),
		GLUTTONY("Gluttony"),
		GOOEY("Gooey"),
		GRASS_PELT("GrassPelt"),
		GRASSY_SURGE("GrassySurge"),
		GUTS("Guts"),
		HARVEST("Harvest"),
		HEALER("Healer"),
		HEATPROOF("Heatproof"),
		HEAVY_METAL("HeavyMetal"),
		HONEY_GATHER("HoneyGather"),
		HUGE_POWER("HugePower"),
		HUSTLE("Hustle"),
		HYDRATION("Hydration"),
		HYPER_CUTTER("HyperCutter"),
		ICE_BODY("IceBody"),
		ILLUMINATE("Illuminate"),
		ILLUSION("Illusion"),
		IMMUNITY("Immunity"),
		IMPOSTER("Imposter"),
		INNARDS_OUT("InnardsOut"),
		INFILTRATOR("Infiltrator"),
		INNER_FOCUS("InnerFocus"),
		INSOMNIA("Insomnia"),
		INTIMIDATE("Intimidate"),
		IRON_BARBS("IronBarbs"),
		IRON_FIST("IronFist"),
		JUSTIFIED("Justified"),
		KEEN_EYE("KeenEye"),
		KLUTZ("Klutz"),
		LEAF_GUARD("LeafGuard"),
		LEVITATE("Levitate"),
		LIGHT_METAL("LightMetal"),
		LIGHTNING_ROD("LightningRod"),
		LIMBER("Limber"),
		LIQUID_OOZE("LiquidOoze"),
		LIQUID_VOICE("LiquidVoice"),
		LONG_REACH("LongReach"),
		MAGIC_BOUNCE("MagicBounce"),
		MAGIC_GUARD("MagicGuard"),
		MAGICIAN("Magician"),
		MAGMA_ARMOR("MagmaArmor"),
		MAGNET_PULL("MagnetPull"),
		MARVEL_SCALE("MarvelScale"),
		MEGA_LAUNCHER("MegaLauncher"),
		MERCILESS("Merciless"),
		MINUS("Minus"),
		MISTY_SURGE("MistySurge"),
		MOLD_BREAKER("MoldBreaker"),
		MOODY("Moody"),
		MOTOR_DRIVE("MotorDrive"),
		MOXIE("Moxie"),
		MULTISCALE("Multiscale"),
		MULTITYPE("Multitype"),
		MUMMY("Mummy"),
		NATURAL_CURE("NaturalCure"),
		NEUROFORCE("Neuroforce"),
		NO_GUARD("NoGuard"),
		NORMALIZE("Normalize"),
		OBLIVIOUS("Oblivious"),
		OVERCOAT("Overcoat"),
		OVERGROW("Overgrow"),
		OWN_TEMPO("OwnTempo"),
		PARENTAL_BOND("ParentalBond"),
		PICKPOCKET("Pickpocket"),
		PICKUP("Pickup"),
		PIXILATE("Pixilate"),
		PLUS("Plus"),
		POISON_HEAL("PoisonHeal"),
		POISON_POINT("PoisonPoint"),
		POISON_TOUCH("PoisonTouch"),
		POWER_CONSTRUCT("PowerConstruct"),
		POWER_OF_ALCHEMY("PowerOfAlchemy"),
		PRANKSTER("Prankster"),
		PRESSURE("Pressure"),
		PRISM_ARMOR("PrismArmor"),
		PROTEAN("Protean"),
		PSYCHIC_SURGE("PsychicSurge"),
		PURE_POWER("PurePower"),
		QUEENLY_MAJESTY("QueenlyMajesty"),
		QUICK_FEET("QuickFeet"),
		RAIN_DISH("RainDish"),
		RATTLED("Rattled"),
		RECEIVER("Receiver"),
		RECKLESS("Reckless"),
		REFRIGERATE("Refrigerate"),
		REGENERATOR("Regenerator"),
		RIVALRY("Rivalry"),
		RKS_SYSTEM("RksSystem"),
		ROCK_HEAD("RockHead"),
		ROUGH_SKIN("RoughSkin"),
		RUN_AWAY("RunAway"),
		SAND_FORCE("SandForce"),
		SAND_RUSH("SandRush"),
		SAND_STREAM("SandStream"),
		SAND_VEIL("SandVeil"),
		SAP_SIPPER("SapSipper"),
		SCHOOLING("Schooling"),
		SCRAPPY("Scrappy"),
		SERENE_GRACE("SereneGrace"),
		SHADOW_TAG("ShadowTag"),
		SHADOW_SHIELD("ShadowShield"),
		SHIELDS_DOWN("ShieldsDown"),
		SHED_SKIN("ShedSkin"),
		SHEER_FORCE("SheerForce"),
		SHELL_ARMOR("ShellArmor"),
		SHIELD_DUST("ShieldDust"),
		SIMPLE("Simple"),
		SKILL_LINK("SkillLink"),
		SLOW_START("SlowStart"),
		SLUSH_RUSH("SlushRush"),
		SNIPER("Sniper"),
		SNOW_CLOAK("SnowCloak"),
		SNOW_WARNING("SnowWarning"),
		SOULHEART("Soul-Heart"),
		SOLAR_POWER("SolarPower"),
		SOLID_ROCK("SolidRock"),
		SOUNDPROOF("Soundproof"),
		SPEED_BOOST("SpeedBoost"),
		STALL("Stall"),
		STAKEOUT("Stakeout"),
		STANCE_CHANGE("StanceChange"),
		STATIC("Static"),
		STAMINA("Stamina"),
		STEADFAST("Steadfast"),
		STENCH("Stench"),
		STEELWORKER("Steelworker"),
		STICKY_HOLD("StickyHold"),
		STORM_DRAIN("StormDrain"),
		STRONG_JAW("StrongJaw"),
		STURDY("Sturdy"),
		SUCTION_CUPS("SuctionCups"),
		SUPER_LUCK("SuperLuck"),
		SURGE_SURFER("SurgeSurfer"),
		SWARM("Swarm"),
		SWEET_VEIL("SweetVeil"),
		SWIFT_SWIM("SwiftSwim"),
		SYMBIOSIS("Symbiosis"),
		SYNCHRONIZE("Synchronize"),
		TANGLED_FEET("TangledFeet"),
		TANGLING_HAIR("TanglingHair"),
		TECHNICIAN("Technician"),
		TELEPATHY("Telepathy"),
		TERAVOLT("Teravolt"),
		THICK_FAT("ThickFat"),
		TINTED_LENS("TintedLens"),
		TORRENT("Torrent"),
		TOUGH_CLAWS("ToughClaws"),
		TOXIC_BOOST("ToxicBoost"),
		TRACE("Trace"),
		TRIAGE("Triage"),
		TRUANT("Truant"),
		TURBOBLAZE("Turboblaze"),
		UNAWARE("Unaware"),
		UNBURDEN("Unburden"),
		UNNERVE("Unnerve"),
		VICTORY_STAR("VictoryStar"),
		VITAL_SPIRIT("VitalSpirit"),
		VOLT_ABSORB("VoltAbsorb"),
		WATER_ABSORB("WaterAbsorb"),
		WATER_BUBBLE("WaterBubble"),
		WATER_COMPACTION("WaterCompaction"),
		WATER_VEIL("WaterVeil"),
		WEAK_ARMOR("WeakArmor"),
		WHITE_SMOKE("WhiteSmoke"),
		WONDER_GUARD("WonderGuard"),
		WONDER_SKIN("WonderSkin"),
		WIMP_OUT("WimpOut"),
		ZEN_MODE("ZenMode");
		
		private String ability;
		
		Ability(String ability) {
			this.ability = ability;
		}
				
		public String getAbility() {
			return ability;
		}	
	}
}
