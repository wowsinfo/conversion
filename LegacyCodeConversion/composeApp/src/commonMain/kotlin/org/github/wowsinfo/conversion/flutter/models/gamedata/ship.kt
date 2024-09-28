

const val premiumGroups = listOf(
    "special"
)

const val specialGroups = listOf(
    "ultimate",
    "specialUnsellable",
    "upgradeableUltimate",
    "upgradeableExclusive",
    "unavailable",
    "disabled",
    "preserved",
    "clan",
    "earlyAccess",
    "demoWithoutStats",
    "demoWithStats"
)

const val specialShipGroups = listOf(
    "preserved",
    "disabled",
    "clan",
    "unavailable",
    "coopOnly"
)

@Serializable
data class Ship(
    val name: String,
    val description: String,
    val year: Int,
    val paperShip: Boolean,
    val id: String,
    val index: Int,
    val tier: Int,
    val region: String,
    val type: String,
    val regionId: String,
    val typeId: String,
    val group: String,
    val consumables: List<Consumable>,
    val costXp: Int,
    val costGold: Int,
    val costCr: Int,
    val nextShips: List<Ship>? = null,
    val modules: List<Module>,
    val components: List<Component>,
    val added: Boolean? = null
)

    val name: String,
    val description: String,
    val year: String,
    val paperShip: Boolean,
    val id: Int,
    val index: String,
    val tier: Int,
    val region: String,
    val type: String,
    val regionId: String,
    val typeId: String,
    val group: String,
    val consumables: List<List<Consumable>>,
    val costXp: Int,
    val costGold: Int,
    val costCr: Int,
    val nextShips: List<Int>?,
    val modules: Map<String, List<ShipModuleInfo>>,
    val components: Map<String, Any>,
    val added: Int?
) {
    val tierString: String
        get() = GameInfo.tiers[tier - 1]

    val isSpecial: Boolean
        get() = _specialGroups.contains(group)

    val isPremium: Boolean
        get() = _premiumGroups.contains(group)

    val isSpecialGroup: Boolean
        get() = _specialShipGroups.contains(group)

    override fun toString(): String {
        return "Ship{name='$name', description='$description', year='$year', paperShip=$paperShip, id=$id, index='$index', tier=$tier, region='$region', type='$type', group='$group', consumables=$consumables, costXp=$costXp, costGold=$costGold, costCr=$costCr, nextShips=$nextShips, modules=$modules, components=$components}"
    }
}

    return when {
        tier == other.tier -> {
            when {
                type == other.type -> {
                    when {
                        region == other.region -> id - other.id
                        else -> region.compareTo(other.region)
                    }
                }
                else -> other.type.compareTo(type)
            }
        }
        else -> tier - other.tier
    }
}


@Serializable
data class Ship(
    val name: String,
    val description: String,
    val year: Int,
    val paperShip: Boolean,
    val id: Int,
    val index: Int,
    val tier: Int,
    val region: String,
    val type: String,
    val regionId: Int,
    val typeId: Int,
    val group: String,
    val consumables: List<List<Consumable>>,
    val costXp: Int,
    val costGold: Int,
    val costCr: Int,
    val nextShips: List<Int>?,
    val modules: Map<String, List<ShipModuleInfo>>,
    val components: Map<String, Any>,
    val added: String
) {
    companion object {
        fun fromJson(json: JsonObject): Ship {
            return Ship(
                name = json["name"]!!.jsonPrimitive.content,
                description = json["description"]!!.jsonPrimitive.content,
                year = json["year"]!!.jsonPrimitive.int,
                paperShip = json["paperShip"]!!.jsonPrimitive.boolean,
                id = json["id"]!!.jsonPrimitive.int,
                index = json["index"]!!.jsonPrimitive.int,
                tier = json["tier"]!!.jsonPrimitive.int,
                region = json["region"]!!.jsonPrimitive.content,
                type = json["type"]!!.jsonPrimitive.content,
                regionId = json["regionID"]!!.jsonPrimitive.int,
                typeId = json["typeID"]!!.jsonPrimitive.int,
                group = json["group"]!!.jsonPrimitive.content,
                consumables = json["consumables"]!!.jsonArray.map { 
                    it.jsonArray.map { consumableJson -> Consumable.fromJson(consumableJson.jsonObject) } 
                },
                costXp = json["costXP"]!!.jsonPrimitive.int,
                costGold = json["costGold"]!!.jsonPrimitive.int,
                costCr = json["costCR"]!!.jsonPrimitive.int,
                nextShips = json["nextShips"]?.jsonArray?.map { it.jsonPrimitive.int },
                modules = json["modules"]!!.jsonObject.mapValues { 
                    it.value.jsonArray.map { moduleJson -> ShipModuleInfo.fromJson(moduleJson.jsonObject) } 
                },
                components = json["components"]!!.jsonObject.mapValues { it.value },
                added = json["added"]!!.jsonPrimitive.content
            )
        }
    }
}

@Serializable
data class ShipModuleInfo(
    val cost: Int,
    val index: Int,
    val name: String,
    val components: List<String>
) {
    companion object {
        fun fromJson(json: JsonObject): ShipModuleInfo {
            return ShipModuleInfo(
                cost = json["cost"]!!.jsonPrimitive.int,
                index = json["index"]!!.jsonPrimitive.int,
                name = json["name"]!!.jsonPrimitive.content,
                components = json["components"]!!.jsonArray.map { it.jsonPrimitive.content }
            )
        }
    }
}

@Serializable
data class Consumable(
    val id: Int,
    val name: String
) {
    companion object {
        fun fromJson(json: JsonObject): Consumable {
            return Consumable(
                id = json["id"]!!.jsonPrimitive.int,
                name = json["name"]!!.jsonPrimitive.content
            )
        }
    }
}

    val cost: Cost,
    val index: Int,
    val name: String,
    val components: Map<String, List<String>>
) : Comparable<ShipModuleInfo> {
    companion object {
        fun fromJson(json: Map<String, Any>): ShipModuleInfo {
            val cost = Cost.fromJson(json["cost"] as Map<String, Any>)
            val index = json["index"] as Int
            val name = json["name"] as String
            val components = (json["components"] as Map<String, List<String>>).mapValues { it.value.toList() }
            return ShipModuleInfo(cost, index, name, components)
        }
    }

    override fun compareTo(other: ShipModuleInfo): Int {
        return index - other.index
    }
}

    val cost: Int,
    val index: Int,
    val components: List<String>
) {
    override fun toString(): String {
        return "ShipModule{cost: $cost, index: $index, components: $components}"
    }
}


@Serializable
data class ShipComponent()

@Serializable
data class GunInfo(
    val range: Int,
    val sigma: Double,
    val guns: Int,
    val far: Int? = null,
    val bubbles: Int? = null,
    val burst: Int? = null
)


@Serializable
data class GunInfo(
    val range: Double,
    val sigma: Double,
    val guns: List<WeaponInfo>,
    val far: List<AuraInfo>? = null,
    val bubbles: AirBubbles? = null,
    val burst: Burst? = null
) {
    companion object {
        fun fromJson(json: String): GunInfo {
            return Json.decodeFromString(json)
        }
    }
}

@Serializable
data class Burst(
    val burstReloadTime: Double,
    val fullReloadTime: Double,
    val modifiers: List<String>? = null,
    val shotIntensity: Double,
    val shotsCount: Int
)

@Serializable
data class WeaponInfo(
    // Define properties here
)

@Serializable
data class AuraInfo(
    // Define properties here
)

@Serializable
data class AirBubbles(
    // Define properties here
)


@Serializable
data class Burst(
    val burstReloadTime: Double,
    val fullReloadTime: Double,
    val modifiers: Modifiers? = null,
    val shotIntensity: Double,
    val shotsCount: Int
) {
    companion object {
        fun fromJson(json: JsonObject): Burst {
            return Burst(
                burstReloadTime = json["burstReloadTime"]!!.jsonPrimitive.double,
                fullReloadTime = json["fullReloadTime"]!!.jsonPrimitive.double,
                modifiers = if (json["modifiers"] is JsonObject && json["modifiers"]!!.jsonObject.isNotEmpty()) {
                    Modifiers.fromJson(json["modifiers"]!!.jsonObject)
                } else {
                    null
                },
                shotIntensity = json["shotIntensity"]!!.jsonPrimitive.double,
                shotsCount = json["shotsCount"]!!.jsonPrimitive.int
            )
        }
    }
}

@Serializable
data class TorpedoInfo(
    val singleShot: Boolean,
    val launchers: Int
)

    val singleShot: Boolean,
    val launchers: List<WeaponInfo>
) {
    companion object {
        fun fromJson(json: Map<String, Any>): TorpedoInfo {
            val singleShot = json["singleShot"] as Boolean
            val launchers = (json["launchers"] as List<Map<String, Any>>).map { WeaponInfo.fromJson(it) }
            return TorpedoInfo(singleShot, launchers)
        }
    }
}

data class DepthChargeInfo(
    val reload: Int,
    val ammo: Int,
    val bombs: Int,
    val groups: Int
) {
    companion object {
        fun fromJson(json: Map<String, Any>): DepthChargeInfo {
            val reload = json["reload"] as Int
            val ammo = json["ammo"] as Int
            val bombs = json["bombs"] as Int
            val groups = json["groups"] as Int
            return DepthChargeInfo(reload, ammo, bombs, groups)
        }
    }
}


@Serializable
data class DepthChargeInfo(
    val reload: Double,
    val ammo: String,
    val bombs: Int,
    val groups: Int
) {
    companion object {
        fun fromJson(json: Map<String, Any>): DepthChargeInfo {
            return DepthChargeInfo(
                reload = json["reload"] as Double,
                ammo = json["ammo"] as String,
                bombs = json["bombs"] as Int,
                groups = json["groups"] as Int
            )
        }
    }
}

@Serializable
data class AirSupportInfo(
    val name: String,
    val chargesNum: Int,
    val plane: String,
    val reload: Double,
    val range: Double
)

    val name: String,
    val chargesNum: Number,
    val plane: String,
    val reload: Number,
    val range: Double
) {
    companion object {
        fun fromJson(json: Map<String, Any>): AirSupportInfo {
            return AirSupportInfo(
                name = json["name"] as String,
                chargesNum = json["chargesNum"] as Number,
                plane = json["plane"] as String,
                reload = json["reload"] as Number,
                range = json["range"] as Double
            )
        }
    }
}

data class FireControlInfo(
    val maxDistCoef: Double,
    val sigmaCountCoef: Double
)


@Serializable
data class FireControlInfo(
    val maxDistCoef: Double,
    val sigmaCountCoef: Double
) {
    companion object {
        fun fromJson(json: String): FireControlInfo {
            return Json.decodeFromString(json)
        }
    }
}

@Serializable
data class SpecialsInfo(
    val rageMode: Boolean
)


@Serializable
data class SpecialsInfo(
    val rageMode: RageMode
) {
    companion object {
        fun fromJson(json: Map<String, Any>): SpecialsInfo {
            val rageModeJson = json["rageMode"] as Map<String, Any>
            return SpecialsInfo(
                rageMode = RageMode.fromJson(rageModeJson)
            )
        }
    }
}

@Serializable
data class RageMode(
    val boostDuration: Int,
    val decrementCount: Int,
    val decrementDelay: Int,
    val decrementPeriod: Int,
    val gunsForSalvo: Int,
    val modifiers: List<String>? = null,
    val radius: Int,
    val rageModeName: String,
    val requiredHits: Int
) {
    companion object {
        fun fromJson(json: Map<String, Any>): RageMode {
            return RageMode(
                boostDuration = json["boostDuration"] as Int,
                decrementCount = json["decrementCount"] as Int,
                decrementDelay = json["decrementDelay"] as Int,
                decrementPeriod = json["decrementPeriod"] as Int,
                gunsForSalvo = json["gunsForSalvo"] as Int,
                modifiers = json["modifiers"] as? List<String>,
                radius = json["radius"] as Int,
                rageModeName = json["rageModeName"] as String,
                requiredHits = json["requiredHits"] as Int
            )
        }
    }
}

    val boostDuration: Double,
    val decrementCount: Int,
    val decrementDelay: Double,
    val decrementPeriod: Double,
    val gunsForSalvo: Int,
    val modifiers: Modifiers?,
    val radius: Double,
    val rageModeName: String,
    val requiredHits: Int
) {
    companion object {
        fun fromJson(json: Map<String, Any>): RageMode {
            return RageMode(
                boostDuration = json["boostDuration"] as Double,
                decrementCount = json["decrementCount"] as Int,
                decrementDelay = json["decrementDelay"] as Double,
                decrementPeriod = json["decrementPeriod"] as Double,
                gunsForSalvo = json["gunsForSalvo"] as Int,
                modifiers = json["modifiers"]?.let { Modifiers.fromJson(it as Map<String, Any>) },
                radius = json["radius"] as Double,
                rageModeName = json["rageModeName"] as String,
                requiredHits = json["requiredHits"] as Int
            )
        }
    }
}

data class EngineInfo(
    val speedCoef: Double
)

    companion object {
        fun fromJson(json: Map<String, Any>?): EngineInfo {
            return if (json == null) {
                EngineInfo(speedCoef = 1.0)
            } else {
                EngineInfo(speedCoef = json["speedCoef"] as Double)
            }
        }
    }
}


@Serializable
data class PingerInfo(
    val reload: Boolean,
    val range: Int,
    val lifeTime1: Int,
    val lifeTime2: Int,
    val speed: Int
)

    val reload: Double,
    val range: Number,
    val lifeTime1: Number,
    val lifeTime2: Number,
    val speed: Number
) {
    val rangeInKm: Double
        get() = range.toDouble() / 1000

    companion object {
        fun fromJson(json: Map<String, Any>): PingerInfo {
            return PingerInfo(
                reload = json["reload"] as Double,
                range = json["range"] as Number,
                lifeTime1 = json["lifeTime1"] as Number,
                lifeTime2 = json["lifeTime2"] as Number,
                speed = json["speed"] as Number
            )
        }
    }
}

data class HullInfo(
    val health: Number,
    val protection: Number? = null,
    val visibility: Number,
    val mobility: Number,
    val submarineBattery: Number? = null
)


@Serializable
data class HullInfo(
    val health: Number,
    val protection: Double?,
    val visibility: VisibilityInfo,
    val mobility: MobilityInfo,
    val submarineBattery: SubmarineBatteryInfo?
) {
    companion object {
        fun fromJson(json: JsonObject): HullInfo {
            return HullInfo(
                health = json["health"]?.jsonPrimitive?.content?.toDouble() ?: 0,
                protection = json["protection"]?.jsonPrimitive?.content?.toDouble(),
                visibility = VisibilityInfo.fromJson(json["visibility"]?.jsonObject ?: JsonObject(emptyMap())),
                mobility = MobilityInfo.fromJson(json["mobility"]?.jsonObject ?: JsonObject(emptyMap())),
                submarineBattery = json["submarineBattery"]?.jsonObject?.let { SubmarineBatteryInfo.fromJson(it) }
            )
        }
    }
}

@Serializable
data class MobilityInfo(
    val speed: Double,
    val turningRadius: Double,
    val rudderTime: Double
) {
    companion object {
        fun fromJson(json: JsonObject): MobilityInfo {
            return MobilityInfo(
                speed = json["speed"]?.jsonPrimitive?.content?.toDouble() ?: 0.0,
                turningRadius = json["turningRadius"]?.jsonPrimitive?.content?.toDouble() ?: 0.0,
                rudderTime = json["rudderTime"]?.jsonPrimitive?.content?.toDouble() ?: 0.0
            )
        }
    }
}

@Serializable
data class VisibilityInfo(
    val range: Double,
    val angle: Double
) {
    companion object {
        fun fromJson(json: JsonObject): VisibilityInfo {
            return VisibilityInfo(
                range = json["range"]?.jsonPrimitive?.content?.toDouble() ?: 0.0,
                angle = json["angle"]?.jsonPrimitive?.content?.toDouble() ?: 0.0
            )
        }
    }
}

@Serializable
data class SubmarineBatteryInfo(
    val capacity: Double,
    val rechargeRate: Double
) {
    companion object {
        fun fromJson(json: JsonObject): SubmarineBatteryInfo {
            return SubmarineBatteryInfo(
                capacity = json["capacity"]?.jsonPrimitive?.content?.toDouble() ?: 0.0,
                rechargeRate = json["rechargeRate"]?.jsonPrimitive?.content?.toDouble() ?: 0.0
            )
        }
    }
}


@Serializable
data class MobilityInfo(
    val speed: Double,
    val turningRadius: Double,
    val rudderTime: Double
) {
    companion object {
        fun fromJson(json: Map<String, Any>): MobilityInfo {
            return MobilityInfo(
                speed = json["speed"] as Double,
                turningRadius = json["turningRadius"] as Double,
                rudderTime = json["rudderTime"] as Double
            )
        }
    }
}

@Serializable
data class VisibilityInfo(
    val sea: Double,
    val plane: Double,
    val seaInSmoke: Double,
    val planeInSmoke: Double,
    val submarine: Double,
    val seaFireCoeff: Double,
    val planeFireCoeff: Double
)


@Serializable
data class VisibilityInfo(
    val sea: Double,
    val plane: Double,
    val seaInSmoke: Double,
    val planeInSmoke: Double,
    val submarine: Double,
    val seaFireCoeff: Double,
    val planeFireCoeff: Double
) {
    companion object {
        fun fromJson(json: String): VisibilityInfo {
            return Json.decodeFromString(json)
        }
    }
}

@Serializable
data class SubmarineBatteryInfo(
    val capacity: Double,
    val regen: Double
)

    val capacity: Int,
    val regen: Double
) {
    companion object {
        fun fromJson(json: Map<String, Any>): SubmarineBatteryInfo {
            return SubmarineBatteryInfo(
                capacity = json["capacity"] as Int,
                regen = json["regen"] as Double
            )
        }
    }
}

data class WeaponInfo(
    val reload: Int,
    val rotation: Int,
    val each: Int,
    val ammo: Int,
    val vertSector: Int,
    val count: Int
)


@Serializable
data class WeaponInfo(
    val reload: Number,
    val rotation: Double,
    val each: Int,
    val ammo: List<String>,
    val vertSector: Double,
    val count: Int
) {
    companion object {
        fun fromJson(json: Map<String, Any>): WeaponInfo {
            return WeaponInfo(
                reload = json["reload"] as Number,
                rotation = json["rotation"] as Double,
                each = json["each"] as Int,
                ammo = (json["ammo"] as List<*>).map { it as String },
                vertSector = json["vertSector"] as Double,
                count = json["count"] as Int
            )
        }
    }
}

@Serializable
data class Cost(
    val costCr: Int,
    val costXp: Int
)

    val costCr: Int,
    val costXp: Int
) {
    companion object {
        fun fromJson(json: Map<String, Any>): Cost {
            return Cost(
                costCr = json["costCR"] as Int,
                costXp = json["costXP"] as Int
            )
        }
    }

    override fun toString(): String {
        return "Cost{costCr: $costCr, costXp: $costXp}"
    }
}


@Serializable
data class AirDefense(
    val medium: Int? = null,
    val near: Int? = null,
    val far: Int? = null
)


@Serializable
data class AuraInfo(val someProperty: String) // Define properties as needed

@Serializable
data class AirDefense(
    val medium: List<AuraInfo>? = null,
    val near: List<AuraInfo>? = null,
    val far: List<AuraInfo>? = null
) {
    companion object {
        fun fromJson(json: JsonObject): AirDefense {
            return AirDefense(
                medium = json["medium"]?.jsonArray?.map { AuraInfo.fromJson(it.jsonObject) },
                near = json["near"]?.jsonArray?.map { AuraInfo.fromJson(it.jsonObject) },
                far = json["far"]?.jsonArray?.map { AuraInfo.fromJson(it.jsonObject) }
            )
        }
    }
}

@Serializable
data class AirBubbles(
    val inner: Int,
    val outer: Int,
    val rof: Int,
    val minRange: Int,
    val maxRange: Int,
    val spawnTime: Int,
    val hitChance: Double,
    val damage: Int
)

    val inner: Int,
    val outer: Int,
    val rof: Number,
    val minRange: Double,
    val maxRange: Double,
    val spawnTime: Double,
    val hitChance: Double,
    val damage: Number
) {
    companion object {
        fun fromJson(json: Map<String, Any>): AirBubbles {
            return AirBubbles(
                inner = json["inner"] as Int,
                outer = json["outer"] as Int,
                rof = json["rof"] as Number,
                minRange = json["minRange"] as Double,
                maxRange = json["maxRange"] as Double,
                spawnTime = json["spawnTime"] as Double,
                hitChance = json["hitChance"] as Double,
                damage = json["damage"] as Number
            )
        }
    }
}

data class AuraInfo(
    val minRange: Double,
    val maxRange: Double,
    val hitChance: Double,
    val damage: Double,
    val rof: Double,
    val dps: Double,
    val guns: List<String>
)


@Serializable
data class AuraInfo(
    val minRange: Double,
    val maxRange: Double,
    val hitChance: Double,
    val damage: Number,
    val rof: Double,
    val dps: Number,
    val guns: List<AuraGuns>
) {
    companion object {
        fun fromJson(json: String): AuraInfo {
            return Json.decodeFromString(json)
        }
    }
}

@Serializable
data class AuraGuns(
    val ammo: Int,
    val name: String,
    val each: Double,
    val count: Int,
    val reload: Double
)

    val ammo: String,
    val name: String,
    val each: Int,
    val count: Int,
    val reload: Double
) {
    companion object {
        fun fromJson(json: Map<String, Any>): AuraGuns {
            return AuraGuns(
                ammo = json["ammo"] as String,
                name = json["name"] as String,
                each = json["each"] as Int,
                count = json["count"] as Int,
                reload = json["reload"] as Double
            )
        }
    }
}