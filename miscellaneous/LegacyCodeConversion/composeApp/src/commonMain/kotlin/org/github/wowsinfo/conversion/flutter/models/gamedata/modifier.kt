
const val blackList = listOf(
    "affectedClasses"
)

const val coeffList = listOf(
    "AAAuraDamage",
    "AABubbleDamage",
    "AAMaxHP",
    "GMIdealRadius",
    "GMMaxDist",
    "GMMaxHP",
    "GMRotationSpeed",
    "GMShotDelay",
    "GSIdealRadius",
    "GSMaxDist",
    "GSMaxHP",
    "GSShotDelay",
    "GTMaxHP",
    "GTRotationSpeed",
    "GTShotDelay",
    "afterBattleRepair",
    "torpedoBomberHealth",
    "skipBomberHealth",
    "planeHealth",
    "fighterHealth",
    "diveBomberHealth",
    "planeSpeed",
    "planeSpawnTime",
    "planeRegenerationRate",
    "shootShift",
    "gmShotDelay",
    "planeEmptyReturnSpeed",
    "collisionDamageApply",
    "collisionDamageNerf"
)

const val negativeCoeff = listOf(
    "SGRepairTime"
)

const val coeffListZero = listOf(
    "buoyancyRudderResetTimeCoeff",
    "damagedEngineCoeff",
    "lastChanceReloadCoefficient",
    "reloadBoostCoeff",
    "burnChanceFactorBig",
    "burnChanceFactorSmall",
    "rocketBurnChanceBonus",
    "regenerationRate",
    "boostCoeff",
    "artilleryBurnChanceBonus"
)

const val numberPercent = listOf(
    "uwCoeffBonus"
)

const val rawPercent = listOf(
    "regenerationHPSpeed"
)

const val additionalList = listOf(
    "AAExtraBubbles",
    "AAInnerExtraBubbles",
    "additionalConsumables",
    "torpedoBomberAimingTime",
    "fighterAimingTime",
    "skipBomberAimingTime",
    "dcNumPacksBonus"
)

const val distList = listOf(
    "radius"
)

const val rawDistList = listOf(
    "acousticWaveRadius",
    "visionXRayTorpedoDist"
)

const val timeList = listOf(
    "workTime",
    "torpedoReloadTime",
    "reloadTime",
    "preparationTime",
    "lifeTime"
)

@Serializable
data class Modifiers(
    val raw: Double,
    val aaAuraDamage: Double? = null,
    val aaAuraReceiveDamageCoeff: Double? = null,
    val aaBubbleDamage: Double? = null,
    val aaExtraBubbles: Int? = null,
    val aaInnerExtraBubbles: Int? = null,
    val aaMaxHp: Double? = null,
    val consumableReloadTime: Double? = null,
    val consumablesWorkTime: Double? = null,
    val gmapDamageCoeff: Double? = null,
    val gmBigGunVisibilityCoeff: Double? = null,
    val gmCritProb: Double? = null,
    val gmhecsDamageCoeff: Double? = null,
    val gmHeavyCruiserCaliberDamageCoeff: Double? = null,
    val gmIdealRadius: Double? = null,
    val gmMaxDist: Double? = null,
    val gmMaxHp: Double? = null,
    val gmRepairTime: Double? = null,
    val gmRotationSpeed: Double? = null,
    val gmShotDelay: Double? = null,
    val gsIdealRadius: Double? = null,
    val gsMaxDist: Double? = null,
    val gsMaxHp: Double? = null,
    val gsShotDelay: Double? = null,
    val gtCritProb: Double? = null,
    val gtMaxHp: Double? = null,
    val gtRepairTime: Double? = null,
    val gtRotationSpeed: Double? = null,
    val gtShotDelay: Double? = null,
    val pmDetonationProb: Double? = null,
    val sgCritProb: Double? = null,
    val sgCritRudderTime: Double? = null,
    val sgRepairTime: Double? = null,
    val sgRudderTime: Double? = null,
    val absoluteBuff: Double? = null,
    val acousticWaveRadius: Double? = null,
    val activationDelay: Double? = null,
    val additionalConsumables: Int? = null,
    val affectedClasses: List<String>? = null,
    val afterBattleRepair: Double? = null,
    val airDefenseDispReloadCoeff: Double? = null,
    val airDefenseDispWorkTimeCoeff: Double? = null,
    val allyAuraBuff: Double? = null,
    val ammo: Int? = null,
    val areaDamageMultiplier: Double? = null,
    val artilleryAlertEnabled: Boolean? = null,
    val artilleryAlertMinDistance: Double? = null,
    val artilleryBoostersReloadCoeff: Double? = null,
    val artilleryBurnChanceBonus: Double? = null,
    val artilleryDistCoeff: Double? = null,
    val asMaxHealthCoeff: Double? = null,
    val asReloadTimeCoeff: Double? = null,
    val backwardEngineForsag: Double? = null,
    val backwardEngineForsagMaxSpeed: Double? = null,
    val batteryCapacityCoeff: Double? = null,
    val batteryRegenCoeff: Double? = null,
    val bombAlphaDamageMultiplier: Double? = null,
    val bombApAlphaDamageMultiplier: Double? = null,
    val bombBurnChanceBonus: Double? = null,
    val boostCoeff: Double? = null,
    val bubbleDamageMultiplier: Double? = null,
    val buoyancyRudderResetTimeCoeff: Double? = null,
    val buoyancyRudderTimeCoeff: Double? = null,
    val buoyancyState: Double? = null,
    val burnChanceFactorBig: Double? = null,
    val burnChanceFactorHighLevel: Double? = null,
    val burnChanceFactorLowLevel: Double? = null,
    val burnChanceFactorSmall: Double? = null,
    val burnProb: Double? = null,
    val burnTime: Double? = null,
    val callFightersAdditionalConsumables: Int? = null,
    val callFightersAirOnly: Boolean? = null,
    val callFightersAppearDelay: Double? = null,
    val callFightersRadiusCoeff: Double? = null,
    val callFightersTimeDelayAttack: Double? = null,
    val callFightersWorkTimeCoeff: Double? = null,
    val canUseOnEmpty: Boolean? = null,
    val climbAngle: Double? = null,
    val collisionDamageApply: Double? = null,
    val collisionDamageNerf: Double? = null,
    val condition: Double? = null,
    val conditionalBuff: Double? = null,
    val consumableType: String? = null,
    val crashCrewAdditionalConsumables: Int? = null,
    val crashCrewReloadCoeff: Double? = null,
    val crashCrewWorkTimeCoeff: Double? = null,
    val creditsFactor: Double? = null,
    val crewExpFactor: Double? = null,
    val critProbCoefficient: Double? = null,
    val criticalChance: Double? = null,
    val damagedEngineCoeff: Double? = null,
    val dcAlphaDamageMultiplier: Double? = null,
    val dcNumPacksBonus: Int? = null,
    val dcSplashRadiusMultiplier: Double? = null,
    val descIDs: List<String>? = null,
    val distShip: Double? = null,
    val distTorpedo: Double? = null,
    val distanceToKill: Double? = null,
    val diveBomberAccuracyIncRateCoeff: Double? = null,
    val diveBomberHealth: Double? = null,
    val diveBomberMaxSpeedMultiplier: Double? = null,
    val diveBomberMinSpeedMultiplier: Double? = null,
    val diveBomberSpeedMultiplier: Double? = null,
    val dogFightTime: Double? = null,
    val effectOnEndLongivity: Double? = null,
    val engineBackwardForsageMaxSpeed: Double? = null,
    val engineBackwardForsagePower: Double? = null,
    val engineBackwardUpTime: Double? = null,
    val engineCritProb: Double? = null,
    val engineForwardForsageMaxSpeed: Double? = null,
    val engineForwardForsagePower: Double? = null,
    val engineForwardUpTime: Double? = null,
    val engineRepairTime: Double? = null,
    val expFactor: Double? = null,
    val extraFighterCount: Int? = null,
    val fighterAccuracyIncRateCoeff: Double? = null,
    val fighterAimingTime: Double? = null,
    val fighterHealth: Double? = null,
    val fighterReloadCoeff: Double? = null,
    val fightersName: String? = null,
    val fightersNum: Int? = null,
    val fireResistanceEnabled: Boolean? = null,
    val firstSectorTimeCoeff: Double? = null,
    val floodChanceFactor: Double? = null,
    val floodChanceFactorPlane: Double? = null,
    val floodProb: Double? = null,
    val floodTime: Double? = null,
    val flyAwayTime: Double? = null,
    val forwardEngineForsag: Double? = null,
    val forwardEngineForsagMaxSpeed: Double? = null,
    val freeExpFactor: Double? = null,
    val healForsageReloadCoeff: Double? = null,
    val healthPerLevel: Double? = null,
    val height: Double? = null,
    val hlCritTimeCoeff: Double? = null,
    val hydrophoneUpdateFrequencyCoeff: Double? = null,
    val hydrophoneWaveSpeedCoeff: Double? = null,
    val iconIDs: List<String>? = null,
    val ignorePtzBonus: Boolean? = null,
    val lastChanceReloadCoefficient: Double? = null,
    val lifeTime: Double? = null,
    val logic: String? = null,
    val maxBuoyancySpeedCoeff: Double? = null,
    val nearEnemyIntuitionEnabled: Boolean? = null,
    val nearRlsEnabled: Boolean? = null,
    val numConsumables: Int? = null,
    val penetrationCoeffHe: Double? = null,
    val pingerCritProb: Double? = null,
    val pingerRepairTime: Double? = null,
    val pingerWaveSpeedCoeff: Double? = null,
    val planeBubbleArmorCoeff: Double? = null,
    val planeConsumablesWorkTime: Double? = null,
    val planeEmptyReturnSpeed: Double? = null,
    val planeEscapeHeightCoeff: Double? = null,
    val planeExtraHangarSize: Int? = null,
    val planeForsageTimeCoeff: Double? = null,
    val planeHealth: Double? = null,
    val planeHealthPerLevel: Double? = null,
    val planeMaxSpeedMultiplier: Double? = null,
    val planeRegenerationRate: Double? = null,
    val planeSpawnTime: Double? = null,
    val planeSpeed: Double? = null,
    val planeTorpedoArmingTimeCoeff: Double? = null,
    val planeTorpedoSpeedMultiplier: Double? = null,
    val planeVisibilityFactor: Double? = null,
    val preparationTime: Double? = null,
    val prioritySectorCooldownMultiplier: Double? = null,
    val prioritySectorStrengthBonus: Double? = null,
    val priorityTargetEnabled: Boolean? = null,
    val radius: Double? = null,
    val regenCrewAdditionalConsumables: Int? = null,
    val regenCrewReloadCoeff: Double? = null,
    val regenCrewWorkTimeCoeff: Double? = null,
    val regenerateHealthAdditionalConsumables: Int? = null,
    val regenerateHealthWorkTimeCoeff: Double? = null,
    val regenerationHpSpeed: Double? = null,
    val regenerationHpSpeedUnits: String? = null,
    val regenerationRate: Double? = null,
    val reloadBoostCoeff: Double? = null,
    val reloadTime: Double? = null,
    val restoreForsage: Double? = null,
    val rlsWorkTimeCoeff: Double? = null,
    val rocketApAlphaDamageMultiplier: Double? = null,
    val rocketBurnChanceBonus: Double? = null,
    val scoutAdditionalConsumables: Int? = null,
    val scoutReloadCoeff: Double? = null,
    val scoutWorkTimeCoeff: Double? = null,
    val secondSectorTimeCoeff: Double? = null,
    val selfAuraBuff: Double? = null,
    val shootShift: Double? = null,
    val skipBomberAccuracyIncRateCoeff: Double? = null,
    val skipBomberAimingTime: Double? = null,
    val skipBomberHealth: Double? = null,
    val skipBomberSpeedMultiplier: Double? = null,
    val smokeGeneratorLifeTime: Double? = null,
    val smokeGeneratorWorkTimeCoeff: Double? = null,
    val softCriticalEnabled: Boolean? = null,
    val sonarWorkTimeCoeff: Double? = null,
    val source: String? = null,
    val spawnBackwardShift: Double? = null,
    val speedBoostersWorkTimeCoeff: Double? = null,
    val speedCoef: Double? = null,
    val speedLimit: Double? = null,
    val startDelayTime: Double? = null,
    val startDistance: Double? = null,
    val switchAmmoReloadCoef: Double? = null,
    val target: String? = null,
    val targetBuff: Double? = null,
    val timeDelayAttack: Double? = null,
    val timeFromHeaven: Double? = null,
    val timeToTryingCatch: Double? = null,
    val timeWaitDelayAttack: Double? = null,
    val titleIDs: List<String>? = null,
    val torpedoBomberAccuracyIncRateCoeff: Double? = null,
    val torpedoBomberAimingTime: Double? = null,
    val torpedoBomberHealth: Double? = null,
    val torpedoDamageCoeff: Double? = null,
    val torpedoDetectionCoefficient: Double? = null,
    val torpedoDetectionCoefficientByPlane: Double? = null,
    val torpedoFullPingDamageCoeff: Double? = null,
    val torpedoReloadTime: Double? = null,
    val torpedoReloaderReloadCoeff: Double? = null,
    val torpedoSpeedMultiplier: Double? = null,
    val torpedoVisibilityFactor: Double? = null,
    val underwaterMaxRudderAngleCoeff: Double? = null,
    val updateFrequency: Double? = null,
    val uwCoeffBonus: Double? = null,
    val visibilityDistCoeff: Double? = null,
    val visibilityFactor: Double? = null,
    val visibilityForSubmarineCoeff: Double? = null,
    val visionXRayTorpedoDist: Double? = null,
    val waveDistance: Double? = null,
    val waveParams: String? = null,
    val weaponTypes: List<String>? = null,
    val workTime: Double? = null,
    val zoneLifetime: Double? = null,
    val zoneRadius: Double? = null
)

    
    val isNotEmpty: Boolean
        get() = raw.isNotEmpty()

    val consumableCount: String?
        get() {
            val count = numConsumables
            return when {
                count == null -> null
                count == -1 -> "∞"
                else -> count.toString()
            }
        }

    private val numConsumables: Int? = raw["numConsumables"] as? Int
}

    private val _additionalList = listOf<String>() // Define your additional list here

    fun merge(other: Modifiers?): Modifiers {
        requireNotNull(other) { "Modifiers.merge(null)" }

        val output = raw.toMutableMap()
        for ((key, value) in other.raw) {
            if (output[key] != null) {
                if (value is Number) {
                    if (key in _additionalList) {
                        output[key] = (output[key] as Number).toDouble() + value.toDouble()
                    } else {
                        output[key] = (output[key] as Number).toDouble() * value.toDouble()
                    }
                }
            } else {
                output[key] = value
            }
        }
        return Modifiers(output)
    }
}

override fun toString(): String {
    val logger = Logger("Modifiers")

    var description = ""
    for ((keyOriginal, value) in raw.entries) {
        if (keyOriginal in _blackList) continue

        logger.fine("keyOriginal: $keyOriginal")
        val key = keyOriginal.lowercase()

        if (value == null) continue
        logger.fine("$keyOriginal: $value")

        val valueMap: List<ModifierShipTypeHolder>

        if (value is Map<*, *>) {
            val types = ModifierShipType.fromJson(value as Map<String, Any>)
            if (types.isEmpty()) continue
            valueMap = types.generateList(key.uppercase())
        } else {
            valueMap = listOf(
                ModifierShipTypeHolder(
                    key = key.uppercase(),
                    value = value,
                    type = null
                )
            )
        }

        val stringSet = valueMap
            .map { e ->
                val valueKey = e.fullKey
                Localisation.instance.stringOf(
                    valueKey,
                    prefix = "IDS_PARAMS_MODIFIER_"
                )
            }
            .toSet()
            .toList()
            .filterNot { it.isNullOrEmpty() }

        val valueSet = valueMap.map { it.value }.toSet().toList()
        valueSet.removeIf { it == 0.0 }

        for (item in valueMap) {
            val valueKey = item.fullKey
            val langString: String?

            val langKeys = Localisation.instance.findKeyWith(
                valueKey,
                prefix = "IDS_PARAMS_MODIFIER_"
            )

            if (langKeys.isEmpty()) continue
            langString = when (langKeys.size) {
                1 -> Localisation.instance.get(langKeys.first())
                2 -> {
                    val longerKey = if (langKeys.first().length > langKeys.last().length) langKeys.first() else langKeys.last()
                    Localisation.instance.get(longerKey)
                }
                else -> Localisation.instance.stringOf(
                    valueKey,
                    prefix = "IDS_PARAMS_MODIFIER_"
                )
            }

            if (langString == null) continue

            val value = item.value
            val shipType = item.type
            val valueString: String

            if (value == 0.0) continue

            valueString = when {
                _timeList.contains(keyOriginal) -> {
                    val time = value.toDouble()
                    "$langString: ${time.toDecimalString()} ${Localisation.instance.second}\n"
                }
                _numberPercent.contains(keyOriginal) -> {
                    val percent = value.toDouble() / 100
                    "$langString: +${percent.toPercentString()}\n"
                }
                _additionalList.contains(keyOriginal) || key.contains("additional") || key.contains("extra") -> {
                    val extra = value.toDouble()
                    val sign = if (extra >= 0) "+" else "-"
                    val tempString = "$langString: $sign${extra.absoluteValue.toDecimalString()}"
                    if (key.contains("time")) {
                        "$tempString ${Localisation.instance.second}\n"
                    } else {
                        "$tempString\n"
                    }
                }
                _coeffListZero.contains(keyOriginal) -> {
                    val coeff = value.toDouble()
                    "$langString: +${coeff.toPercentString()}\n"
                }
                _coeffList.contains(keyOriginal) || key.contains("coef") || key.contains("factor") || key.contains("multiplier") || key.contains("time") || key.contains("prob") -> {
                    val coeff = value.toDouble()
                    val positive = coeff > 1.0
                    val percent = (coeff - 1).absoluteValue.toPercentString()
                    "$langString: ${if (positive) "+" else "-"}$percent\n"
                }
                _rawDistList.contains(keyOriginal) -> {
                    val dist = value.toDouble()
                    "$langString: ${(dist / 1000).toDecimalString()} ${Localisation.instance.kilometer}\n"
                }
                key.contains("dist") || _distList.contains(keyOriginal) -> {
                    val dist = value.toDouble()
                    "$langString: ${(dist / 33.35).toDecimalString()} ${Localisation.instance.kilometer}\n"
                }
                _rawPercent.contains(keyOriginal) -> {
                    val percent = value.toDouble()
                    "$langString: ${percent.toPercentString()}\n"
                }
                else -> {
                    logger.warning("Unknown modifier: $keyOriginal")
                    if (value == -1.0) {
                        "$langString: ∞\n"
                    } else {
                        "$langString: $value\n"
                    }
                }
            }

            val sameForAll = (stringSet.size == 1 && valueSet.size == 1)
            if (shipType == null || (sameForAll && !description.contains(valueString.trim()))) {
                description += valueString
            } else if (sameForAll) {
                continue
            } else {
                val shipTypeString = Localisation.instance.stringOf(shipType, prefix = "IDS_")
                description += "${valueString.trim()} ($shipTypeString)\n"
            }
        }
    }

    return description
}

    if (value == 1.0) return "+1"
    var adjustedValue = value
    if (value < 0.35) {
        adjustedValue = value + 1
    }
    val positive = adjustedValue > 1
    val offset = ((adjustedValue - 1).absoluteValue * 100).toString()
    return "${if (positive) "+" else "-"}$offset%"
}

    val aaAuraDamage: ModifierShipType?,
    val aaAuraReceiveDamageCoeff: Double?,
    val aaBubbleDamage: ModifierShipType?,
    val aaExtraBubbles: Number?,
    val aaInnerExtraBubbles: Number?,
    val aaMaxHp: Number?,
    val consumableReloadTime: ModifierShipType?,
    val consumablesWorkTime: Double?,
    val gmapDamageCoeff: Double?,
    val gmBigGunVisibilityCoeff: Double?,
    val gmCritProb: Double?,
    val gmhecsDamageCoeff: Double?,
    val gmHeavyCruiserCaliberDamageCoeff: Double?,
    val gmIdealRadius: Double?,
    val gmMaxDist: Double?,
    val gmMaxHp: Double?,
    val gmRepairTime: Double?,
    val gmRotationSpeed: ModifierShipType?,
    val gmShotDelay: Double?,
    val gsIdealRadius: Double?,
    val gsMaxDist: Double?,
    val gsMaxHp: Number?,
    val gsShotDelay: Double?,
    val gtCritProb: Double?,
    val gtMaxHp: Double?,
    val gtRepairTime: Double?,
    val gtRotationSpeed: Double?,
    val gtShotDelay: Double?,
    val pmDetonationProb: Double?,
    val sgCritProb: Double?,
    val sgCritRudderTime: Number?,
    val sgRepairTime: Double?,
    val sgRudderTime: Double?,
    val absoluteBuff: String?,
    val acousticWaveRadius: Number?,
    val activationDelay: Number?,
    val additionalConsumables: Number?,
    val affectedClasses: List<String>?,
    val afterBattleRepair: Double?,
    val airDefenseDispReloadCoeff: Double?,
    val airDefenseDispWorkTimeCoeff: Double?,
    val allyAuraBuff: String?,
    val ammo: String?,
    val areaDamageMultiplier: Number?,
    val artilleryAlertEnabled: Boolean?,
    val artilleryAlertMinDistance: Number?,
    val artilleryBoostersReloadCoeff: Double?,
    val artilleryBurnChanceBonus: ModifierShipType?,
    val artilleryDistCoeff: Double?,
    val asMaxHealthCoeff: Double?,
    val asReloadTimeCoeff: Double?,
    val backwardEngineForsag: Number?,
    val backwardEngineForsagMaxSpeed: Number?,
    val batteryCapacityCoeff: Double?,
    val batteryRegenCoeff: Double?,
    val bombAlphaDamageMultiplier: Double?,
    val bombApAlphaDamageMultiplier: Double?,
    val bombBurnChanceBonus: Double?,
    val boostCoeff: Number?,
    val bubbleDamageMultiplier: Number?,
    val buoyancyRudderResetTimeCoeff: Double?,
    val buoyancyRudderTimeCoeff: Double?,
    val buoyancyState: Number?,
    val burnChanceFactorBig: Double?,
    val burnChanceFactorHighLevel: Double?,
    val burnChanceFactorLowLevel: Double?,
    val burnChanceFactorSmall: Double?,
    val burnProb: Double?,
    val burnTime: Double?,
    val callFightersAdditionalConsumables: Number?,
    val callFightersAirOnly: Boolean?,
    val callFightersAppearDelay: Double?,
    val callFightersRadiusCoeff: Double?,
    val callFightersTimeDelayAttack: Double?,
    val callFightersWorkTimeCoeff: Double?,
    val canUseOnEmpty: Boolean?,
    val climbAngle: Number?,
    val collisionDamageApply: Double?,
    val collisionDamageNerf: Double?,
    val condition: String?,
    val conditionalBuff: String?,
    val consumableType: String?,
    val crashCrewAdditionalConsumables: Number?,
    val crashCrewReloadCoeff: Double?,
    val crashCrewWorkTimeCoeff: Double?,
    val creditsFactor: Double?,
    val crewExpFactor: Double?,
    val critProbCoefficient: Double?,
    val criticalChance: Number?,
    val damagedEngineCoeff: Double?,
    val dcAlphaDamageMultiplier: ModifierShipType?,
    val dcNumPacksBonus: Number?,
    val dcSplashRadiusMultiplier: Double?,
    val descIDs: String?,
    val distShip: Number?,
    val distTorpedo: Number?,
    val distanceToKill: Number?,
    val diveBomberAccuracyIncRateCoeff: Double?,
    val diveBomberHealth: Double?,
    val diveBomberMaxSpeedMultiplier: Double?,
    val diveBomberMinSpeedMultiplier: Double?,
    val diveBomberSpeedMultiplier: Double?,
    val dogFightTime: Number?,
    val effectOnEndLongivity: Number?,
    val engineBackwardForsageMaxSpeed: Number?,
    val engineBackwardForsagePower: Double?,
    val engineBackwardUpTime: Double?,
    val engineCritProb: Double?,
    val engineForwardForsageMaxSpeed: Double?,
    val engineForwardForsagePower: Double?,
    val engineForwardUpTime: Double?,
    val engineRepairTime: Double?,
    val expFactor: Double?,
    val extraFighterCount: Number?,
    val fighterAccuracyIncRateCoeff: Double?,
    val fighterAimingTime: Number?,
    val fighterHealth: Double?,
    val fighterReloadCoeff: Double?,
    val fightersName: String?,
    val fightersNum: Number?,
    val fireResistanceEnabled: Boolean?,
    val firstSectorTimeCoeff: Double?,
    val floodChanceFactor: Double?,
    val floodChanceFactorPlane: Double?,
    val floodProb: Double?,
    val floodTime: Double?,
    val flyAwayTime: Number?,
    val forwardEngineForsag: Number?,
    val forwardEngineForsagMaxSpeed: Number?,
    val freeExpFactor: Number?,
    val healForsageReloadCoeff: Double?,
    val healthPerLevel: ModifierShipType?,
    val height: Number?,
    val hlCritTimeCoeff: Double?,
    val hydrophoneUpdateFrequencyCoeff: Double?,
    val hydrophoneWaveSpeedCoeff: Double?,
    val iconIDs: String?,
    val ignorePtzBonus: Number?,
    val lastChanceReloadCoefficient: Double?,
    val lifeTime: Number?,
    val logic: String?,
    val maxBuoyancySpeedCoeff: Double?,
    val nearEnemyIntuitionEnabled: Boolean?,
    val nearRlsEnabled: Boolean?,
    val numConsumables: Number?,
    val penetrationCoeffHe: Double?,
    val pingerCritProb: Double?,
    val pingerRepairTime: Double?,
    val pingerWaveSpeedCoeff: Double?,
    val planeBubbleArmorCoeff: Double?,
    val planeConsumablesWorkTime: Double?,
    val planeEmptyReturnSpeed: Double?,
    val planeEscapeHeightCoeff: Double?,
    val planeExtraHangarSize: Number?,
    val planeForsageTimeCoeff: Double?,
    val planeHealth: Double?,
    val planeHealthPerLevel: Number?,
    val planeMaxSpeedMultiplier: Double?,
    val planeRegenerationRate: Double?,
    val planeSpawnTime: Double?,
    val planeSpeed: Double?,
    val planeTorpedoArmingTimeCoeff: Double?,
    val planeTorpedoSpeedMultiplier: Double?,
    val planeVisibilityFactor: Double?,
    val preparationTime: Number?,
    val prioritySectorCooldownMultiplier: Double?,
    val prioritySectorStrengthBonus: Number?,
    val priorityTargetEnabled: Boolean?,
    val radius: Number?,
    val regenCrewAdditionalConsumables: Number?,
    val regenCrewReloadCoeff: Double?,
    val regenCrewWorkTimeCoeff: Double?,
    val regenerateHealthAdditionalConsumables: Number?,
    val regenerateHealthWorkTimeCoeff: Double?,
    val regenerationHpSpeed: Number?,
    val regenerationHpSpeedUnits: Number?,
    val regenerationRate: Double?,
    val reloadBoostCoeff: Double?,
    val reloadTime: Number?,
    val restoreForsage: Boolean?,
    val rlsWorkTimeCoeff: Double?,
    val rocketApAlphaDamageMultiplier: Double?,
    val rocketBurnChanceBonus: Double?,
    val scoutAdditionalConsumables: Number?,
    val scoutReloadCoeff: Double?,
    val scoutWorkTimeCoeff: Double?,
    val secondSectorTimeCoeff: Double?,
    val selfAuraBuff: String?,
    val shootShift: Double?,
    val skipBomberAccuracyIncRateCoeff: Double?,
    val skipBomberAimingTime: Number?,
    val skipBomberHealth: Double?,
    val skipBomberSpeedMultiplier: Double?,
    val smokeGeneratorLifeTime: Double?,
    val smokeGeneratorWorkTimeCoeff: Double?,
    val softCriticalEnabled: Boolean?,
    val sonarWorkTimeCoeff: Double?,
    val source: List<String>?,
    val spawnBackwardShift: Double?,
    val speedBoostersWorkTimeCoeff: Double?,
    val speedCoef: Double?,
    val speedLimit: Double?,
    val startDelayTime: Number?,
    val startDistance: Number?,
    val switchAmmoReloadCoef: Double?,
    val target: List<String>?,
    val targetBuff: String?,
    val timeDelayAttack: Number?,
    val timeFromHeaven: Number?,
    val timeToTryingCatch: Number?,
    val timeWaitDelayAttack: Number?,
    val titleIDs: String?,
    val torpedoBomberAccuracyIncRateCoeff: Double?,
    val torpedoBomberAimingTime: Number?,
    val torpedoBomberHealth: Double?,
    val torpedoDamageCoeff: Double?,
    val torpedoDetectionCoefficient: Double?,
    val torpedoDetectionCoefficientByPlane: Number?,
    val torpedoFullPingDamageCoeff: Double?,
    val torpedoReloadTime: Number?,
    val torpedoReloaderReloadCoeff: Double?,
    val torpedoSpeedMultiplier: Double?,
    val torpedoVisibilityFactor: Double?,
    val underwaterMaxRudderAngleCoeff: Number?,
    val updateFrequency: Number?,
    val uwCoeffBonus: Number?,
    val visibilityDistCoeff: ModifierShipType?,
    val visibilityFactor: Double?,
    val visibilityForSubmarineCoeff: Double?,
    val visionXRayTorpedoDist: Number?,
    val waveDistance: Number?,
    val waveParams: String?,
    val weaponTypes: List<String>?,
    val workTime: Number?,
    val zoneLifetime: Double?,
    val zoneRadius: Double?
)

data class ModifierShipType(
    // Define properties here
)

fun fromJson(json: Map<String, Any?>): Modifiers {
    return Modifiers(
        aaAuraDamage = json["AAAuraDamage"]?.let { ModifierShipType.fromJson(it as Map<String, Any?>) },
        aaAuraReceiveDamageCoeff = json["AAAuraReceiveDamageCoeff"] as? Double,
        aaBubbleDamage = json["AABubbleDamage"]?.let { ModifierShipType.fromJson(it as Map<String, Any?>) },
        aaExtraBubbles = json["AAExtraBubbles"] as? Number,
        aaInnerExtraBubbles = json["AAInnerExtraBubbles"] as? Number,
        aaMaxHp = json["AAMaxHP"] as? Number,
        consumableReloadTime = json["ConsumableReloadTime"]?.let { ModifierShipType.fromJson(it as Map<String, Any?>) },
        consumablesWorkTime = json["ConsumablesWorkTime"] as? Double,
        gmapDamageCoeff = json["GMAPDamageCoeff"] as? Double,
        gmBigGunVisibilityCoeff = json["GMBigGunVisibilityCoeff"] as? Double,
        gmCritProb = json["GMCritProb"] as? Double,
        gmhecsDamageCoeff = json["GMHECSDamageCoeff"] as? Double,
        gmHeavyCruiserCaliberDamageCoeff = json["GMHeavyCruiserCaliberDamageCoeff"] as? Double,
        gmIdealRadius = json["GMIdealRadius"] as? Double,
        gmMaxDist = json["GMMaxDist"] as? Double,
        gmMaxHp = json["GMMaxHP"] as? Double,
        gmRepairTime = json["GMRepairTime"] as? Double,
        gmRotationSpeed = json["GMRotationSpeed"]?.let { ModifierShipType.fromJson(it as Map<String, Any?>) },
        gmShotDelay = json["GMShotDelay"] as? Double,
        gsIdealRadius = json["GSIdealRadius"] as? Double,
        gsMaxDist = json["GSMaxDist"] as? Double,
        gsMaxHp = json["GSMaxHP"] as? Number,
        gsShotDelay = json["GSShotDelay"] as? Double,
        gtCritProb = json["GTCritProb"] as? Double,
        gtMaxHp = json["GTMaxHP"] as? Double,
        gtRepairTime = json["GTRepairTime"] as? Double,
        gtRotationSpeed = json["GTRotationSpeed"] as? Double,
        gtShotDelay = json["GTShotDelay"] as? Double,
        pmDetonationProb = json["PMDetonationProb"] as? Double,
        sgCritProb = json["SGCritProb"] as? Double,
        sgCritRudderTime = json["SGCritRudderTime"] as? Number,
        sgRepairTime = json["SGRepairTime"] as? Double,
        sgRudderTime = json["SGRudderTime"] as? Double,
        absoluteBuff = json["absoluteBuff"] as? String,
        acousticWaveRadius = json["acousticWaveRadius"] as? Number,
        activationDelay = json["activationDelay"] as? Number,
        additionalConsumables = json["additionalConsumables"] as? Number,
        affectedClasses = json["affectedClasses"] as? List<String>,
        afterBattleRepair = json["afterBattleRepair"] as? Double,
        airDefenseDispReloadCoeff = json["airDefenseDispReloadCoeff"] as? Double,
        airDefenseDispWorkTimeCoeff = json["airDefenseDispWorkTimeCoeff"] as? Double,
        allyAuraBuff = json["allyAuraBuff"] as? String,
        ammo = json["ammo"] as? String,
        areaDamageMultiplier = json["areaDamageMultiplier"] as? Number,
        artilleryAlertEnabled = json["artilleryAlertEnabled"] as? Boolean,
        artilleryAlertMinDistance = json["artilleryAlertMinDistance"] as? Number,
        artilleryBoostersReloadCoeff = json["artilleryBoostersReloadCoeff"] as? Double,
        artilleryBurnChanceBonus = json["artilleryBurnChanceBonus"]?.let { ModifierShipType.fromJson(it as Map<String, Any?>) },
        artilleryDistCoeff = json["artilleryDistCoeff"] as? Double,
        asMaxHealthCoeff = json["asMaxHealthCoeff"] as? Double,
        asReloadTimeCoeff = json["asReloadTimeCoeff"] as? Double,
        backwardEngineForsag = json["backwardEngineForsag"] as? Number,
        backwardEngineForsagMaxSpeed = json["backwardEngineForsagMaxSpeed"] as? Number,
        batteryCapacityCoeff = json["batteryCapacityCoeff"] as? Double,
        batteryRegenCoeff = json["batteryRegenCoeff"] as? Double,
        bombAlphaDamageMultiplier = json["bombAlphaDamageMultiplier"] as? Double,
        bombApAlphaDamageMultiplier = json["bombApAlphaDamageMultiplier"] as? Double,
        bombBurnChanceBonus = json["bombBurnChanceBonus"] as? Double,
        boostCoeff = json["boostCoeff"] as? Number,
        bubbleDamageMultiplier = json["bubbleDamageMultiplier"] as? Number,
        buoyancyRudderResetTimeCoeff = json["buoyancyRudderResetTimeCoeff"] as? Double,
        buoyancyRudderTimeCoeff = json["buoyancyRudderTimeCoeff"] as? Double,
        buoyancyState = json["buoyancyState"] as? Number,
        burnChanceFactorBig = json["burnChanceFactorBig"] as? Double,
        burnChanceFactorHighLevel = json["burnChanceFactorHighLevel"] as? Double,
        burnChanceFactorLowLevel = json["burnChanceFactorLowLevel"] as? Double,
        burnChanceFactorSmall = json["burnChanceFactorSmall"] as? Double,
        burnProb = json["burnProb"] as? Double,
        burnTime = json["burnTime"] as? Double,
        callFightersAdditionalConsumables = json["callFightersAdditionalConsumables"] as? Number,
        callFightersAirOnly = json["callFightersAirOnly"] as? Boolean,
        callFightersAppearDelay = json["callFightersAppearDelay"] as? Double,
        callFightersRadiusCoeff = json["callFightersRadiusCoeff"] as? Double,
        callFightersTimeDelayAttack = json["callFightersTimeDelayAttack"] as? Double,
        callFightersWorkTimeCoeff = json["callFightersWorkTimeCoeff"] as? Double,
        canUseOnEmpty = json["canUseOnEmpty"] as? Boolean,
        climbAngle = json["climbAngle"] as? Number,
        collisionDamageApply = json["collisionDamageApply"] as? Double,
        collisionDamageNerf = json["collisionDamageNerf"] as? Double,
        condition = json["condition"] as? String,
        conditionalBuff = json["conditionalBuff"] as? String,
        consumableType = json["consumableType"] as? String,
        crashCrewAdditionalConsumables = json["crashCrewAdditionalConsumables"] as? Number,
        crashCrewReloadCoeff = json["crashCrewReloadCoeff"] as? Double,
        crashCrewWorkTimeCoeff = json["crashCrewWorkTimeCoeff"] as? Double,
        creditsFactor = json["creditsFactor"] as? Double,
        crewExpFactor = json["crewExpFactor"] as? Double,
        critProbCoefficient = json["critProbCoefficient"] as? Double,
        criticalChance = json["criticalChance"] as? Number,
        damagedEngineCoeff = json["damagedEngineCoeff"] as? Double,
        dcAlphaDamageMultiplier = json["dcAlphaDamageMultiplier"]?.let { ModifierShipType.fromJson(it as Map<String, Any?>) },
        dcNumPacksBonus = json["dcNumPacksBonus"] as? Number,
        dcSplashRadiusMultiplier = json["dcSplashRadiusMultiplier"] as? Double,
        descIDs = json["descIDs"] as? String,
        distShip = json["distShip"] as? Number,
        distTorpedo = json["distTorpedo"] as? Number,
        distanceToKill = json["distanceToKill"] as? Number,
        diveBomberAccuracyIncRateCoeff = json["diveBomberAccuracyIncRateCoeff"] as? Double,
        diveBomberHealth = json["diveBomberHealth"] as? Double,
        diveBomberMaxSpeedMultiplier = json["diveBomberMaxSpeedMultiplier"] as? Double,
        diveBomberMinSpeedMultiplier = json["diveBomberMinSpeedMultiplier"] as? Double,
        diveBomberSpeedMultiplier = json["diveBomberSpeedMultiplier"] as? Double,
        dogFightTime = json["dogFightTime"] as? Number,
        effectOnEndLongivity = json["effectOnEndLongivity"] as? Number,
        engineBackwardForsageMaxSpeed = json["engineBackwardForsageMaxSpeed"] as? Number,
        engineBackwardForsagePower = json["engineBackwardForsagePower"] as? Double,
        engineBackwardUpTime = json["engineBackwardUpTime"] as? Double,
        engineCritProb = json["engineCritProb"] as? Double,
        engineForwardForsageMaxSpeed = json["engineForwardForsageMaxSpeed"] as? Double,
        engineForwardForsagePower = json["engineForwardForsagePower"] as? Double,
        engineForwardUpTime = json["engineForwardUpTime"] as? Double,
        engineRepairTime = json["engineRepairTime"] as? Double,
        expFactor = json["expFactor"] as? Double,
        extraFighterCount = json["extraFighterCount"] as? Number,
        fighterAccuracyIncRateCoeff = json["fighterAccuracyIncRateCoeff"] as? Double,
        fighterAimingTime = json["fighterAimingTime"] as? Number,
        fighterHealth = json["fighterHealth"] as? Double,
        fighterReloadCoeff = json["fighterReloadCoeff"] as? Double,
        fightersName = json["fightersName"] as? String,
        fightersNum = json["fightersNum"] as? Number,
        fireResistanceEnabled = json["fireResistanceEnabled"] as? Boolean,
        firstSectorTimeCoeff = json["firstSectorTimeCoeff"] as? Double,
        floodChanceFactor = json["floodChanceFactor"] as? Double,
        floodChanceFactorPlane = json["floodChanceFactorPlane"] as? Double,
        floodProb = json["floodProb"] as? Double,
        floodTime = json["floodTime"] as? Double,
        flyAwayTime = json["flyAwayTime"] as? Number,
        forwardEngineForsag = json["forwardEngineForsag"] as? Number,
        forwardEngineForsagMaxSpeed = json["forwardEngineForsagMaxSpeed"] as? Number,
        freeExpFactor = json["freeExpFactor"] as? Number,
        healForsageReloadCoeff = json["healForsageReloadCoeff"] as? Double,
        healthPerLevel = json["healthPerLevel"]?.let { ModifierShipType.fromJson(it as Map<String, Any?>) },
        height = json["height"] as? Number,
        hlCritTimeCoeff = json["hlCritTimeCoeff"] as? Double,
        hydrophoneUpdateFrequencyCoeff = json["hydrophoneUpdateFrequencyCoeff"] as? Double,
        hydrophoneWaveSpeedCoeff = json["hydrophoneWaveSpeedCoeff"] as? Double,
        iconIDs = json["iconIDs"] as? String,
        ignorePtzBonus = json["ignorePTZBonus"] as? Number,
        lastChanceReloadCoefficient = json["lastChanceReloadCoefficient"] as? Double,
        lifeTime = json["lifeTime"] as? Number,
        logic = json["logic"] as? String,
        maxBuoyancySpeedCoeff = json["maxBuoyancySpeedCoeff"] as? Double,
        nearEnemyIntuitionEnabled = json["nearEnemyIntuitionEnabled"] as? Boolean,
        nearRlsEnabled = json["nearRLSEnabled"] as? Boolean,
        numConsumables = json["numConsumables"] as? Number,
        penetrationCoeffHe = json["penetrationCoeffHE"] as? Double,
        pingerCritProb = json["pingerCritProb"] as? Double,
        pingerRepairTime = json["pingerRepairTime"] as? Double,
        pingerWaveSpeedCoeff = json["pingerWaveSpeedCoeff"] as? Double,
        planeBubbleArmorCoeff = json["planeBubbleArmorCoeff"] as? Double,
        planeConsumablesWorkTime = json["planeConsumablesWorkTime"] as? Double,
        planeEmptyReturnSpeed = json["planeEmptyReturnSpeed"] as? Double,
        planeEscapeHeightCoeff = json["planeEscapeHeightCoeff"] as? Double,
        planeExtraHangarSize = json["planeExtraHangarSize"] as? Number,
        planeForsageTimeCoeff = json["planeForsageTimeCoeff"] as? Double,
        planeHealth = json["planeHealth"] as? Double,
        planeHealthPerLevel = json["planeHealthPerLevel"] as? Number,
        planeMaxSpeedMultiplier = json["planeMaxSpeedMultiplier"] as? Double,
        planeRegenerationRate = json["planeRegenerationRate"] as? Double,
        planeSpawnTime = json["planeSpawnTime"] as? Double,
        planeSpeed = json["planeSpeed"] as? Double,
        planeTorpedoArmingTimeCoeff = json["planeTorpedoArmingTimeCoeff"] as? Double,
        planeTorpedoSpeedMultiplier = json["planeTorpedoSpeedMultiplier"] as? Double,
        planeVisibilityFactor = json["planeVisibilityFactor"] as? Double,
        preparationTime = json["preparationTime"] as? Number,
        prioritySectorCooldownMultiplier = json["prioritySectorCooldownMultiplier"] as? Double,
        prioritySectorStrengthBonus = json["prioritySectorStrengthBonus"] as? Number,
        priorityTargetEnabled = json["priorityTargetEnabled"] as? Boolean,
        radius = json["radius"] as? Number,
        regenCrewAdditionalConsumables = json["regenCrewAdditionalConsumables"] as? Number,
        regenCrewReloadCoeff = json["regenCrewReloadCoeff"] as? Double,
        regenCrewWorkTimeCoeff = json["regenCrewWorkTimeCoeff"] as? Double,
        regenerateHealthAdditionalConsumables = json["regenerateHealthAdditionalConsumables"] as? Number,
        regenerateHealthWorkTimeCoeff = json["regenerateHealthWorkTimeCoeff"] as? Double,
        regenerationHpSpeed = json["regenerationHPSpeed"] as? Number,
        regenerationHpSpeedUnits = json["regenerationHPSpeedUnits"] as? Number,
        regenerationRate = json["regenerationRate"] as? Double,
        reloadBoostCoeff = json["reloadBoostCoeff"] as? Double,
        reloadTime = json["reloadTime"] as? Number,
        restoreForsage = json["restoreForsage"] as? Boolean,
        rlsWorkTimeCoeff = json["rlsWorkTimeCoeff"] as? Double,
        rocketApAlphaDamageMultiplier = json["rocketApAlphaDamageMultiplier"] as? Double,
        rocketBurnChanceBonus = json["rocketBurnChanceBonus"] as? Double,
        scoutAdditionalConsumables = json["scoutAdditionalConsumables"] as? Number,
        scoutReloadCoeff = json["scoutReloadCoeff"] as? Double,
        scoutWorkTimeCoeff = json["scoutWorkTimeCoeff"] as? Double,
        secondSectorTimeCoeff = json["secondSectorTimeCoeff"] as? Double,
        selfAuraBuff = json["selfAuraBuff"] as? String,
        shootShift = json["shootShift"] as? Double,
        skipBomberAccuracyIncRateCoeff = json["skipBomberAccuracyIncRateCoeff"] as? Double,
        skipBomberAimingTime = json["skipBomberAimingTime"] as? Number,
        skipBomberHealth = json["skipBomberHealth"] as? Double,
        skipBomberSpeedMultiplier = json["skipBomberSpeedMultiplier"] as? Double,
        smokeGeneratorLifeTime = json["smokeGeneratorLifeTime"] as? Double,
        smokeGeneratorWorkTimeCoeff = json["smokeGeneratorWorkTimeCoeff"] as? Double,
        softCriticalEnabled = json["softCriticalEnabled"] as? Boolean,
        sonarWorkTimeCoeff = json["sonarWorkTimeCoeff"] as? Double,
        source = json["source"] as? List<String>,
        spawnBackwardShift = json["spawnBackwardShift"] as? Double,
        speedBoostersWorkTimeCoeff = json["speedBoostersWorkTimeCoeff"] as? Double,
        speedCoef = json["speedCoef"] as? Double,
        speedLimit = json["speedLimit"] as? Double,
        startDelayTime = json["startDelayTime"] as? Number,
        startDistance = json["startDistance"] as? Number,
        switchAmmoReloadCoef = json["switchAmmoReloadCoef"] as? Double,
        target = json["target"] as? List<String>,
        targetBuff = json["targetBuff"] as? String,
        timeDelayAttack = json["timeDelayAttack"] as? Number,
        timeFromHeaven = json["timeFromHeaven"] as? Number,
        timeToTryingCatch = json["timeToTryingCatch"] as? Number,
        timeWaitDelayAttack = json["timeWaitDelayAttack"] as? Number,
        titleIDs = json["titleIDs"] as? String,
        torpedoBomberAccuracyIncRateCoeff = json["torpedoBomberAccuracyIncRateCoeff"] as? Double,
        torpedoBomberAimingTime = json["torpedoBomberAimingTime"] as? Number,
        torpedoBomberHealth = json["torpedoBomberHealth"] as? Double,
        torpedoDamageCoeff = json["torpedoDamageCoeff"] as? Double,
        torpedoDetectionCoefficient = json["torpedoDetectionCoefficient"] as? Double,
        torpedoDetectionCoefficientByPlane = json["torpedoDetectionCoefficientByPlane"] as? Number,
        torpedoFullPingDamageCoeff = json["torpedoFullPingDamageCoeff"] as? Double,
        torpedoReloadTime = json["torpedoReloadTime"] as? Number,
        torpedoReloaderReloadCoeff = json["torpedoReloaderReloadCoeff"] as? Double,
        torpedoSpeedMultiplier = json["torpedoSpeedMultiplier"] as? Double,
        torpedoVisibilityFactor = json["torpedoVisibilityFactor"] as? Double,
        underwaterMaxRudderAngleCoeff = json["underwaterMaxRudderAngleCoeff"] as? Number,
        updateFrequency = json["updateFrequency"] as? Number,
        uwCoeffBonus = json["uwCoeffBonus"] as? Number,
        visibilityDistCoeff = json["visibilityDistCoeff"]?.let { ModifierShipType.fromJson(it as Map<String, Any?>) },
        visibilityFactor = json["visibilityFactor"] as? Double,
        visibilityForSubmarineCoeff = json["visibilityForSubmarineCoeff"] as? Double,
        visionXRayTorpedoDist = json["visionXRayTorpedoDist"] as? Number,
        waveDistance = json["waveDistance"] as? Number,
        waveParams = json["waveParams"] as? String,
        weaponTypes = json["weaponTypes"] as? List<String>,
        workTime = json["workTime"] as? Number,
        zoneLifetime = json["zoneLifetime"] as? Double,
        zoneRadius = json["zoneRadius"] as? Double
    )
}

    val key: String,
    val type: String? = null,
    val value: Any?
) {
    val fullKey: String
        get() = if (type == null) key else "${key}_$type"
}

data class ModifierShipType(
    val airCarrier: Any? = null,
    val auxiliary: Any? = null,
    val battleship: Any? = null,
    val cruiser: Any? = null,
    val destroyer: Any? = null,
    val submarine: Any? = null
)

    val airCarrier: Double? = null,
    val auxiliary: Double? = null,
    val battleship: Double? = null,
    val cruiser: Double? = null,
    val destroyer: Double? = null,
    val submarine: Double? = null
) {
    companion object {
        fun fromJson(json: Map<String, Any>?): ModifierShipType {
            return if (json == null) {
                ModifierShipType()
            } else {
                ModifierShipType(
                    airCarrier = json["AirCarrier"] as? Double,
                    auxiliary = json["Auxiliary"] as? Double,
                    battleship = json["Battleship"] as? Double,
                    cruiser = json["Cruiser"] as? Double,
                    destroyer = json["Destroyer"] as? Double,
                    submarine = json["Submarine"] as? Double
                )
            }
        }
    }
}


fun generateList(key: String): List<ModifierShipTypeHolder> {
    return listOf(
        ModifierShipTypeHolder(
            key = key,
            type = "AIRCARRIER",
            value = validate(airCarrier)
        ),
        ModifierShipTypeHolder(
            key = key,
            type = "AUXILIARY",
            value = validate(auxiliary)
        ),
        ModifierShipTypeHolder(
            key = key,
            type = "BATTLESHIP",
            value = validate(battleship)
        ),
        ModifierShipTypeHolder(
            key = key,
            type = "CRUISER",
            value = validate(cruiser)
        ),
        ModifierShipTypeHolder(
            key = key,
            type = "DESTROYER",
            value = validate(destroyer)
        )
    )
}

fun validate(value: Any): Int {
    // Implement your validation logic here
    return 0 // Placeholder return value
}

val airCarrier = Any() // Replace with actual value
val auxiliary = Any() // Replace with actual value
val battleship = Any() // Replace with actual value
val cruiser = Any() // Replace with actual value
val destroyer = Any() // Replace with actual value

    if (value == null) return 0.0
    if (value == 1.0) return 0.0
    return value
}

    return airCarrier == null &&
        auxiliary == null &&
        battleship == null &&
        cruiser == null &&
        destroyer == null &&
        submarine == null
}

    val airCarrier: Boolean,
    val auxiliary: Boolean,
    val battleship: Boolean,
    val cruiser: Boolean,
    val destroyer: Boolean,
    val submarine: Boolean
) {
    override fun toString(): String {
        return "ModifierShipType{airCarrier=$airCarrier, auxiliary=$auxiliary, battleship=$battleship, cruiser=$cruiser, destroyer=$destroyer, submarine=$submarine}"
    }
}


@Composable
fun MyApp() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    var count by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "You have clicked the button $count times")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            count++
        }) {
            Text("Click Me")
        }
    }
}