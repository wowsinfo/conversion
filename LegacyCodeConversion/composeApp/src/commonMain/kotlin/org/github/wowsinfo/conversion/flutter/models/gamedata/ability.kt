
@Serializable
data class Ability(
    val nation: String,
    val id: String,
    val name: String,
    val icon: String,
    val description: String,
    val filter: String,
    val type: String,
    val abilities: List<String>,
    val abilityList: List<String>,
    val alter: String? = null
)

    val nation: String,
    val id: Int,
    val name: String,
    val icon: String,
    val description: String,
    val filter: String,
    val type: String,
    val abilities: Map<String, Modifiers>,
    val abilityList: List<Modifiers>,
    val alter: Map<String, AbilityAlter>? = null
) {
    fun descriptionOf(index: Int): String {
        val ability = abilityList[index]
        return ability.toString()
    }
}

    val nation: String?,
    val id: String?,
    val name: String?,
    val icon: String?,
    val description: String?,
    val filter: String?,
    val type: String?,
    val abilities: Map<String, Modifiers>,
    val abilityList: List<Modifiers>,
    val alter: Map<String, AbilityAlter>?
) {
    companion object {
        fun fromJson(json: Map<String, Any?>): Ability {
            return Ability(
                nation = json["nation"] as? String,
                id = json["id"] as? String,
                name = json["name"] as? String,
                icon = json["icon"] as? String,
                description = json["description"] as? String,
                filter = json["filter"] as? String,
                type = json["type"] as? String,
                abilities = (json["abilities"] as? Map<String, Any?>)?.mapValues { Modifiers.fromJson(it.value as Map<String, Any?>) } ?: emptyMap(),
                abilityList = (json["abilities"] as? Map<String, Any?>)?.values?.map { Modifiers.fromJson(it as Map<String, Any?>) } ?: emptyList(),
                alter = (json["alter"] as? Map<String, Any?>)?.mapValues { AbilityAlter.fromJson(it.value as Map<String, Any?>) }
            )
        }
    }
}

data class AbilityAlter(
    val name: String,
    val description: String
) {
    companion object {
        fun fromJson(json: Map<String, Any?>): AbilityAlter {
            return AbilityAlter(
                name = json["name"] as String,
                description = json["description"] as String
            )
        }
    }
}


@Serializable
data class AbilityAlter(
    val name: String,
    val description: String
) {
    companion object {
        fun fromJson(json: Map<String, Any>): AbilityAlter {
            return AbilityAlter(
                name = json["name"] as String,
                description = json["description"] as String
            )
        }
    }
}

@kotlinx.serialization.Serializable
data class AbilityInfo(
    val numConsumables: Int? = null,
    val preparationTime: Int? = null,
    val reloadTime: Int? = null,
    val workTime: Int? = null,
    val regenerationHPSpeed: Int? = null,
    val regenerationHPSpeedUnits: String? = null,
    val areaDamageMultiplier: Double? = null,
    val bubbleDamageMultiplier: Double? = null,
    val climbAngle: Double? = null,
    val distanceToKill: Double? = null,
    val dogFightTime: Int? = null,
    val fightersName: String? = null,
    val fightersNum: Int? = null,
    val flyAwayTime: Int? = null,
    val radius: Double? = null,
    val timeDelayAttack: Int? = null,
    val timeFromHeaven: Int? = null,
    val timeToTryingCatch: Int? = null,
    val timeWaitDelayAttack: Int? = null,
    val artilleryDistCoeff: Double? = null,
    val activationDelay: Int? = null,
    val height: Double? = null,
    val lifeTime: Int? = null,
    val spawnBackwardShift: Double? = null,
    val speedLimit: Double? = null,
    val startDelayTime: Int? = null,
    val descIDs: List<Int>? = null,
    val iconIDs: List<Int>? = null,
    val titleIDs: List<Int>? = null,
    val backwardEngineForsag: Double? = null,
    val backwardEngineForsagMaxSpeed: Double? = null,
    val boostCoeff: Double? = null,
    val forwardEngineForsag: Double? = null,
    val forwardEngineForsagMaxSpeed: Double? = null,
    val distShip: Double? = null,
    val distTorpedo: Double? = null,
    val targetBuoyancyCoefficients: List<Double>? = null,
    val torpedoReloadTime: Int? = null,
    val affectedClasses: List<String>? = null,
    val regenerationRate: Double? = null,
    val ammo: Int? = null,
    val acousticWaveRadius: Double? = null,
    val updateFrequency: Int? = null,
    val zoneLifetime: Int? = null,
    val buoyancyRudderResetTimeCoeff: Double? = null,
    val buoyancyRudderTimeCoeff: Double? = null,
    val maxBuoyancySpeedCoeff: Double? = null,
    val underwaterMaxRudderAngleCoeff: Double? = null,
    val canUseOnEmpty: Boolean? = null,
    val logic: String? = null,
    val criticalChance: Double? = null,
    val source: String? = null,
    val target: String? = null,
    val zoneRadius: Double? = null,
    val allyAuraBuff: Double? = null,
    val selfAuraBuff: Double? = null,
    val startDistance: Double? = null,
    val waveDistance: Double? = null,
    val waveParams: List<Double>? = null,
    val absoluteBuff: Double? = null,
    val condition: String? = null,
    val conditionalBuff: Double? = null,
    val targetBuff: Double? = null,
    val buoyancyState: String? = null,
    val effectOnEndLongivity: Double? = null,
    val reloadBoostCoeff: Double? = null,
    val weaponTypes: List<String>? = null
)

    val numConsumables: Int? = null,
    val preparationTime: Double? = null,
    val reloadTime: Double? = null,
    val workTime: Double? = null,
    val regenerationHPSpeed: Double? = null,
    val regenerationHPSpeedUnits: Double? = null,
    val areaDamageMultiplier: Double? = null,
    val bubbleDamageMultiplier: Double? = null,
    val climbAngle: Int? = null,
    val distanceToKill: Double? = null,
    val dogFightTime: Double? = null,
    val fightersName: String? = null,
    val fightersNum: Int? = null,
    val flyAwayTime: Double? = null,
    val radius: Double? = null,
    val timeDelayAttack: Double? = null,
    val timeFromHeaven: Double? = null,
    val timeToTryingCatch: Double? = null,
    val timeWaitDelayAttack: Double? = null,
    val artilleryDistCoeff: Double? = null,
    val activationDelay: Double? = null,
    val height: Double? = null,
    val lifeTime: Double? = null,
    val spawnBackwardShift: Double? = null,
    val speedLimit: Double? = null,
    val startDelayTime: Double? = null,
    val descIDs: String? = null,
    val iconIDs: String? = null,
    val titleIDs: String? = null,
    val backwardEngineForsag: Double? = null,
    val backwardEngineForsagMaxSpeed: Double? = null,
    val boostCoeff: Double? = null,
    val forwardEngineForsag: Double? = null,
    val forwardEngineForsagMaxSpeed: Double? = null,
    val distShip: Double? = null,
    val distTorpedo: Double? = null,
    val targetBuoyancyCoefficients: Map<String, Double>? = null,
    val torpedoReloadTime: Double? = null,
    val affectedClasses: List<String>? = null,
    val regenerationRate: Double? = null,
    val ammo: String? = null,
    val acousticWaveRadius: Double? = null,
    val updateFrequency: Double? = null,
    val zoneLifetime: Double? = null,
    val buoyancyRudderResetTimeCoeff: Double? = null,
    val buoyancyRudderTimeCoeff: Double? = null,
    val maxBuoyancySpeedCoeff: Double? = null,
    val underwaterMaxRudderAngleCoeff: Double? = null,
    val canUseOnEmpty: Boolean? = null,
    val logic: String? = null,
    val criticalChance: Double? = null,
    val source: List<String>? = null,
    val target: List<String>? = null,
    val zoneRadius: Double? = null,
    val allyAuraBuff: String? = null,
    val selfAuraBuff: String? = null,
    val startDistance: Double? = null,
    val waveDistance: Int? = null,
    val waveParams: String? = null,
    val absoluteBuff: String? = null,
    val condition: String? = null,
    val conditionalBuff: String? = null,
    val targetBuff: String? = null,
    val buoyancyState: Int? = null,
    val effectOnEndLongivity: Double? = null,
    val reloadBoostCoeff: Double? = null,
    val weaponTypes: List<String>? = null
) {
    override fun toString(): String {
        return ""
    }
}

    val description = Localisation.instance.stringOf(
        key.uppercase(),
        prefix = "IDS_PARAMS_MODIFIER_"
    )
    if (description == " ") return ""

    val valueString = value.toDecimalString()

    return if (key.contains("time")) {
        "$valueString s"
    } else {
        valueString
    }
}

fun Number.toDecimalString(): String {
    return this.toString() // Implement your decimal formatting logic here
}

data class AbilityInfo(
    val numConsumables: Int?,
    val preparationTime: Int?,
    val reloadTime: Int?,
    val workTime: Int?,
    val regenerationHPSpeed: Double?,
    val regenerationHPSpeedUnits: String?,
    val areaDamageMultiplier: Double?,
    val bubbleDamageMultiplier: Double?,
    val climbAngle: Double?,
    val distanceToKill: Double?,
    val dogFightTime: Double?,
    val fightersName: String?,
    val fightersNum: Int?,
    val flyAwayTime: Double?,
    val radius: Double?,
    val timeDelayAttack: Double?,
    val timeFromHeaven: Double?,
    val timeToTryingCatch: Double?,
    val timeWaitDelayAttack: Double?,
    val artilleryDistCoeff: Double?,
    val activationDelay: Double?,
    val height: Double?,
    val lifeTime: Double?,
    val spawnBackwardShift: Double?,
    val speedLimit: Double?,
    val startDelayTime: Double?,
    val descIDs: List<String>?,
    val iconIDs: List<String>?,
    val titleIDs: List<String>?,
    val backwardEngineForsag: Double?,
    val backwardEngineForsagMaxSpeed: Double?,
    val boostCoeff: Double?,
    val forwardEngineForsag: Double?,
    val forwardEngineForsagMaxSpeed: Double?,
    val distShip: Double?,
    val distTorpedo: Double?,
    val targetBuoyancyCoefficients: Map<String, Double>?,
    val torpedoReloadTime: Double?,
    val affectedClasses: List<String>?,
    val regenerationRate: Double?,
    val ammo: Int?,
    val acousticWaveRadius: Double?,
    val updateFrequency: Double?,
    val zoneLifetime: Double?,
    val buoyancyRudderResetTimeCoeff: Double?,
    val buoyancyRudderTimeCoeff: Double?,
    val maxBuoyancySpeedCoeff: Double?,
    val underwaterMaxRudderAngleCoeff: Double?,
    val canUseOnEmpty: Boolean?,
    val logic: String?,
    val criticalChance: Double?,
    val source: List<String>?,
    val target: List<String>?,
    val zoneRadius: Double?,
    val allyAuraBuff: Double?,
    val selfAuraBuff: Double?,
    val startDistance: Double?,
    val waveDistance: Double?,
    val waveParams: String?,
    val absoluteBuff: Double?,
    val condition: String?,
    val conditionalBuff: Double?,
    val targetBuff: Double?,
    val buoyancyState: String?,
    val effectOnEndLongivity: Double?,
    val reloadBoostCoeff: Double?,
    val weaponTypes: List<String>?
)

fun AbilityInfo.Companion.fromJson(json: Map<String, Any?>): AbilityInfo {
    return AbilityInfo(
        numConsumables = json["numConsumables"] as? Int,
        preparationTime = json["preparationTime"] as? Int,
        reloadTime = json["reloadTime"] as? Int,
        workTime = json["workTime"] as? Int,
        regenerationHPSpeed = json["regenerationHPSpeed"] as? Double,
        regenerationHPSpeedUnits = json["regenerationHPSpeedUnits"] as? String,
        areaDamageMultiplier = json["areaDamageMultiplier"] as? Double,
        bubbleDamageMultiplier = json["bubbleDamageMultiplier"] as? Double,
        climbAngle = json["climbAngle"] as? Double,
        distanceToKill = json["distanceToKill"] as? Double,
        dogFightTime = json["dogFightTime"] as? Double,
        fightersName = json["fightersName"] as? String,
        fightersNum = json["fightersNum"] as? Int,
        flyAwayTime = json["flyAwayTime"] as? Double,
        radius = json["radius"] as? Double,
        timeDelayAttack = json["timeDelayAttack"] as? Double,
        timeFromHeaven = json["timeFromHeaven"] as? Double,
        timeToTryingCatch = json["timeToTryingCatch"] as? Double,
        timeWaitDelayAttack = json["timeWaitDelayAttack"] as? Double,
        artilleryDistCoeff = json["artilleryDistCoeff"] as? Double,
        activationDelay = json["activationDelay"] as? Double,
        height = json["height"] as? Double,
        lifeTime = json["lifeTime"] as? Double,
        spawnBackwardShift = json["spawnBackwardShift"] as? Double,
        speedLimit = json["speedLimit"] as? Double,
        startDelayTime = json["startDelayTime"] as? Double,
        descIDs = json["descIDs"] as? List<String>,
        iconIDs = json["iconIDs"] as? List<String>,
        titleIDs = json["titleIDs"] as? List<String>,
        backwardEngineForsag = json["backwardEngineForsag"] as? Double,
        backwardEngineForsagMaxSpeed = json["backwardEngineForsagMaxSpeed"] as? Double,
        boostCoeff = json["boostCoeff"] as? Double,
        forwardEngineForsag = json["forwardEngineForsag"] as? Double,
        forwardEngineForsagMaxSpeed = json["forwardEngineForsagMaxSpeed"] as? Double,
        distShip = json["distShip"] as? Double,
        distTorpedo = json["distTorpedo"] as? Double,
        targetBuoyancyCoefficients = (json["targetBuoyancyCoefficients"] as? Map<String, Any?>)?.mapValues { it.value as? Double },
        torpedoReloadTime = json["torpedoReloadTime"] as? Double,
        affectedClasses = json["affectedClasses"] as? List<String>,
        regenerationRate = json["regenerationRate"] as? Double,
        ammo = json["ammo"] as? Int,
        acousticWaveRadius = json["acousticWaveRadius"] as? Double,
        updateFrequency = json["updateFrequency"] as? Double,
        zoneLifetime = json["zoneLifetime"] as? Double,
        buoyancyRudderResetTimeCoeff = json["buoyancyRudderResetTimeCoeff"] as? Double,
        buoyancyRudderTimeCoeff = json["buoyancyRudderTimeCoeff"] as? Double,
        maxBuoyancySpeedCoeff = json["maxBuoyancySpeedCoeff"] as? Double,
        underwaterMaxRudderAngleCoeff = json["underwaterMaxRudderAngleCoeff"] as? Double,
        canUseOnEmpty = json["canUseOnEmpty"] as? Boolean,
        logic = json["logic"] as? String,
        criticalChance = json["criticalChance"] as? Double,
        source = json["source"] as? List<String>,
        target = json["target"] as? List<String>,
        zoneRadius = json["zoneRadius"] as? Double,
        allyAuraBuff = json["allyAuraBuff"] as? Double,
        selfAuraBuff = json["selfAuraBuff"] as? Double,
        startDistance = json["startDistance"] as? Double,
        waveDistance = json["waveDistance"] as? Double,
        waveParams = json["waveParams"] as? String,
        absoluteBuff = json["absoluteBuff"] as? Double,
        condition = json["condition"] as? String,
        conditionalBuff = json["conditionalBuff"] as? Double,
        targetBuff = json["targetBuff"] as? Double,
        buoyancyState = json["buoyancyState"] as? String,
        effectOnEndLongivity = json["effectOnEndLongivity"] as? Double,
        reloadBoostCoeff = json["reloadBoostCoeff"] as? Double,
        weaponTypes = json["weaponTypes"] as? List<String>
    )
}