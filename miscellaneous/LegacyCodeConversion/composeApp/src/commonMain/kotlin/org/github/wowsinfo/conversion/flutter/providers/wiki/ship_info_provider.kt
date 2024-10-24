
class ShipInfoProvider(private val ship: Ship) : ViewModel() {
    private val logger = LoggerFactory.getLogger(ShipInfoProvider::class.java)

    private val shipModules = ShipModules(ship)
    private val shipUpgrades = ShipUpgrades(ship)

    init {
        shipModules.unpackModules()
        shipUpgrades.unpackUpgrades()
    }
}

    private val _shipModules = ShipModules()
    private val _logger = Logger.getLogger(ShipModuleViewModel::class.java.name)

    val selection: ShipModuleSelection
        get() = _shipModules.selection

    fun updateSelection(selection: ShipModuleSelection) {
        _shipModules.updateSelection(selection)
        _logger.fine("Updated ship module selection")
        notifyListeners()
    }

    private fun notifyListeners() {
        // Notify listeners about the change
    }
}

    return value?.let { String.format("%.2f%%", it * 100) } ?: "-"
}

    return value?.let { "${it * 100}%" } ?: "-"
}

    return value?.let { "$it $suffix" } ?: "-"
}

    return String.format("%.2f", this)
}

fun calc(value: Double?, factor: Double): String {
    if (value == null) return "-"
    val calculated = value * factor
    return "${value.toDecimalString()} - ${calculated.toDecimalString()}"
}

    if (value == null) return "-"
    val calculated = value + offset
    return "${value.toDecimalString()} - ${calculated.toDecimalString()}"
}

fun Double.toDecimalString(): String {
    return String.format("%.2f", this)
}

    get() {
        val flags = GameRepository.instance.usefulFlagList
        val flagModifiers = flags
            .map { it.modifiers }
            .filterNotNull()
            .reduceOrNull { a, b -> a.merge(b) }

        val upgrades = _shipUpgrades.modernizations
        val upgradeModifiers = upgrades
            .map { it.modifiers }
            .reduceOrNull { a, b -> a.merge(b) }

        val commanderSkills = GameRepository.instance.commanderSkills[_ship.type]
        val skills = commanderSkills
            ?.flatMap { it }
            ?.map { GameRepository.instance.skillOf(it.name)?.modifiers }
            ?.filterNotNull()
            ?.reduceOrNull { a, b -> a.merge(b) }

        val consumables = _ship.consumables.flatten()
        val consumableModifiers = consumables
            .map { GameRepository.instance.abilityOf(it.name)?.abilityList?.reduceOrNull { a, b -> a.merge(b) } }
            .filterNotNull()
            .reduceOrNull { a, b -> a.merge(b) }

        if (flagModifiers == null || skills == null || consumableModifiers == null) {
            error("All modifiers should be valid")
        }

        return listOfNotNull(flagModifiers, upgradeModifiers, skills, consumableModifiers)
            .reduceOrNull { a, b -> a.merge(b) }
    }

    get() = allModifiers.toString()

val title: String
    get() = "$shipIcon ${_ship.id}"

val tier: String
    get() = GameInfo.tiers[_ship.tier - 1]

val shipName: String?
    get() = Localisation.instance.stringOf(_ship.name)

    get() {
        val name = shipName
        return name?.let { "$tier $it" } ?: "-"
    }

    get() = _ship.index

val description: String
    get() = Localisation.instance.stringOf(_ship.description) ?: "-"

val type: String
    get() = Localisation.instance.stringOf(_ship.typeId) ?: "-"

val region: String
    get() = Localisation.instance.stringOf(_ship.regionId) ?: "-"

val costCR: String?
    get() = if (_ship.costCr > 0) _format(_ship.costCr) else null

val costGold: String?
    get() = if (_ship.costGold > 0) _format(_ship.costGold) else null

val shipAdditional: ShipAdditional?
    get() = GameRepository.instance.shipAdditionalOf(_ship.id.toString())

val canChangeModules: Boolean
    get() = _shipModules.canChangeModules

val moduleList: ShipModuleMap
    get() = _shipModules.moduleList

val hullInfo: HullInfo?
    get() = _shipModules.hullInfo?.data

val renderHull: Boolean
    get() = hullInfo != null

val health: String
    get() = _format(hullInfo?.health)

val torpedoProtection: String
    get() = _rawPercent(hullInfo?.protection)

val mainGunInfo: GunInfo?
    get() = _shipModules.gunInfo?.data

val renderMainGun: Boolean
    get() = mainGunInfo != null

val gun: WeaponInfo?
    get() = mainGunInfo?.guns?.firstOrNull()

val gunReloadTime: String
    get() = _format(gun?.reload, suffix = Localisation.instance.second)

val gunRange: String
    get() {
        val suo = _shipModules.fireControlInfo
        val range = mainGunInfo?.range
        if (range == null) return "-"
        if (suo == null) {
            return _format(range / 1000, suffix = Localisation.instance.kilometer)
        }
        val improvedRange = range * suo.data.maxDistCoef
        return _format(improvedRange / 1000, suffix = Localisation.instance.kilometer)
    }

    get() {
        val guns = mainGunInfo?.guns ?: return "-"
        val config = mutableListOf<String>()

        for (gun in guns) {
            config.add("${gun.count} x ${gun.each}")
        }

        return if (config.isEmpty()) "-" else config.joinToString(" ")
    }

    get() = format(gun?.rotation, suffix = Localisation.instance.second)

val gunName: String
    get() = Localisation.instance.stringOf(shipModules.gunInfo?.module?.name) ?: "-"

val gunSigma: String
    get() = format(mainGunInfo?.sigma, suffix = "σ")

val hasBurstFire: Boolean
    get() = mainGunInfo?.burst != null

val burst: Burst?
    get() = mainGunInfo?.burst

val burstFireHolder: BurstFireHolder?
    get() = extractBurstFire(burst)

private fun extractBurstFire(burst: Burst?): BurstFireHolder? {
    if (burst == null) return null
    return BurstFireHolder(
        reload = format(burst.fullReloadTime, suffix = Localisation.instance.second),
        interval = format(burst.burstReloadTime, suffix = Localisation.instance.second),
        shots = format(burst.shotsCount),
        modifiers = burst.modifiers?.toString()
    )
}

val burstReloadTime: String? get() = format(burst?.burstReloadTime, suffix = Localisation.instance.second)
val burstFullReloadTime: String? get() = format(burst?.fullReloadTime, suffix = Localisation.instance.second)
val burstModifier: String? get() = burst?.modifiers?.toString()

val shells: List<ShellHolder> get() = extractShells(mainGunInfo)

private fun extractShells(gunInfo: GunInfo?): List<ShellHolder> {
    if (gunInfo == null) return emptyList()
    val shells = mutableListOf<ShellHolder>()
    val gun = gunInfo.guns.first()
    for (ammo in gun.ammo) {
        val ammoInfo = GameRepository.instance.projectileOf(ammo) ?: continue

        logger.fine("Found ammo: ${ammoInfo.name}")
        val type = ammoInfo.ammoType ?: continue

        val shell = ShellHolder(name = type)
        shell.burnChance = percent(ammoInfo.burnChance)
        shell.damage = format(ammoInfo.damage)
        shell.velocity = format(ammoInfo.speed, suffix = Localisation.instance.meterPerSecond)
        shell.weight = format(ammoInfo.weight, suffix = Localisation.instance.kilogram)

        when (ammoInfo.ammoType) {
            "HE" -> shell.penetration = format(ammoInfo.penHe, suffix = Localisation.instance.millimeter)
            "AP" -> shell.overmatch = format(ammoInfo.overmatch, suffix = Localisation.instance.millimeter)
            "CS" -> shell.penetration = format(ammoInfo.penSAP, suffix = Localisation.instance.millimeter)
            else -> {
                logger.severe("Unknown ammo type: ${ammoInfo.type}")
                continue
            }
        }
        shells.add(shell)
    }
    return shells
}

val renderSecondaryGun: Boolean get() = secondaryInfo != null
val secondaryGuns: List<SecondaryGunHolder> get() = extractSecondaryGuns(secondaryInfo)

val secondaryRange: String
    get() {
        val range = secondaryInfo?.range
        return if (range == null) "-" else format(range / 1000, suffix = Localisation.instance.kilometer)
    }

    if (gunInfo == null) return emptyList()
    val guns = mutableListOf<SecondaryGunHolder>()
    for (gun in gunInfo.guns) {
        val ammo = gun.ammo.firstOrNull() ?: continue
        val ammoInfo = GameRepository.instance.projectileOf(ammo) ?: continue

        val config = "${gun.count} x ${gun.each}"
        val holder = SecondaryGunHolder(
            name = "$config ${Localisation.instance.stringOf(ammo, prefix = "IDS_")}"
        )

        holder.burnChance = percent(ammoInfo.burnChance)
        holder.damage = format(ammoInfo.damage)
        holder.velocity = format(ammoInfo.speed, suffix = Localisation.instance.meterPerSecond)
        holder.reloadTime = format(gun.reload, suffix = Localisation.instance.second)
        holder.config = config

        when (ammoInfo.ammoType) {
            "HE" -> holder.penetration = format(ammoInfo.penHe, suffix = Localisation.instance.millimeter)
            "CS" -> holder.penetration = format(ammoInfo.penSAP, suffix = Localisation.instance.millimeter)
        }
        guns.add(holder)
    }
    return guns
}

val renderPinger: Boolean get() = pingerInfo != null
val pingerRange: String get() = format(pingerInfo?.rangeInKm, suffix = Localisation.instance.kilometer)
val pingerReloadTime: String get() = format(pingerInfo?.reload, suffix = Localisation.instance.second)
val pingerSpeed: String get() = format(pingerInfo?.speed, suffix = Localisation.instance.meterPerSecond)
val pingerDuration: String get() = "${format(pingerInfo?.lifeTime1, suffix = Localisation.instance.second)} | ${format(pingerInfo?.lifeTime2, suffix = Localisation.instance.second)}"

val submarineBatteryInfo: SubmarineBatteryInfo? get() = hullInfo?.submarineBattery
val renderSubmarineBattery: Boolean get() = submarineBatteryInfo != null
val submarineBatteryCapacity: String get() = format(submarineBatteryInfo?.capacity, suffix = Localisation.instance.unit)
val submarineBatteryUseRate: String get() = format(1.0, suffix = Localisation.instance.unit)
val submarineBatteryRegen: String get() = format(submarineBatteryInfo?.regen, suffix = Localisation.instance.unitPerSecond)

val torpedoInfo: TorpedoInfo? get() = shipModules.torpedoInfo?.data
val renderTorpedo: Boolean get() = torpedoInfo != null
val torpedo: WeaponInfo? get() = torpedoInfo?.launchers?.firstOrNull()
val torpedoReloadTime: String get() = format(torpedo?.reload, suffix = Localisation.instance.second)
val torpedoRotationTime: String get() = format(torpedo?.rotation, suffix = Localisation.instance.second)
val torpedoConfiguration: String {
    val launchers = torpedoInfo?.launchers
    if (launchers == null) return "-"

    val config = launchers.joinToString(" ") { "${it.count} x ${it.each}" }
    return if (config.isEmpty()) "-" else config
}

    return extractTorpedoes(torpedoInfo)
}

private fun extractTorpedoes(torpedoInfo: TorpedoInfo?): List<TorpedoHolder> {
    if (torpedo == null) return emptyList()
    val torpedoes = mutableListOf<TorpedoHolder>()
    for (ammo in torpedo.ammo) {
        val ammoInfo = GameRepository.instance.projectileOf(ammo) ?: continue

        val torpName = Localisation.instance.stringOf(ammo, prefix = "IDS_")
        val holder = TorpedoHolder(name = torpName ?: "-")
        
        val alphaDamage = ammoInfo.alphaDamage ?: 0
        val damage = ammoInfo.damage ?: 0
        val actualDamage = ((alphaDamage / 3) + damage).toInt()
        if (actualDamage > 0) {
            holder.damage = format(actualDamage)
        }

        val range = ammoInfo.range ?: 0
        val actualRange = range / (100.0 / 3.0)
        if (actualRange > 0) {
            holder.range = format(actualRange, suffix = Localisation.instance.kilometer)
        }
        
        ammoInfo.visibility?.let {
            holder.visibility = format(it, suffix = Localisation.instance.kilometer)
        }
        
        ammoInfo.speed?.let {
            holder.speed = format(it, suffix = Localisation.instance.knot)
        }

        ammoInfo.floodChance?.let {
            holder.floodChance = rawPercent(it)
        }

        val reaction = (ammoInfo.visibility ?: 0) / (ammoInfo.speed ?: 1) / 2.6854 * 1000
        if (reaction > 0) {
            holder.reactionTime = format(reaction, suffix = Localisation.instance.second)
        }
        torpedoes.add(holder)
    }
    return torpedoes
}

    get() = Localisation.instance.stringOf("IDS_${_torpedoInfo?.launchers?.get(0)?.ammo?.get(0)}") ?: ""

private val specialsInfo: SpecialsInfo?
    get() = _shipModules.specialsInfo?.data

val renderSpecials: Boolean
    get() = specialsInfo != null

private val rageMode: RageMode?
    get() = specialsInfo?.rageMode

val specialName: String
    get() = Localisation.instance.stringOf(rageMode?.rageModeName?.uppercase(), "IDS_DOCK_RAGE_MODE_TITLE_") ?: ""

val specialDescription: String
    get() = Localisation.instance.stringOf(rageMode?.rageModeName?.uppercase(), "IDS_DOCK_RAGE_MODE_DESCRIPTION_") ?: ""

val specialModifier: String
    get() = rageMode?.modifiers?.toString() ?: ""

val specialDuration: String
    get() = _format(rageMode?.boostDuration, Localisation.instance.second)

val specialHitsRequired: String
    get() = _format(specialsInfo?.rageMode?.requiredHits)

private val airDefense: AirDefense?
    get() = _shipModules.airDefenseInfo?.data

val renderAirDefense: Boolean
    get() = airDefenses.isNotEmpty()

val airDefenses: List<AirDefenseHolder>
    get() = _extractAirDefenses(airDefense, _mainGunInfo, _secondaryInfo)

val airBubbles: List<AirBubbleHolder>
    get() = _extractAirBubbles(_mainGunInfo, _secondaryInfo)

private fun _extractAirBubbles(mainGunInfo: GunInfo?, secondaryInfo: GunInfo?): List<AirBubbleHolder> {
    val airBubbles = mutableListOf<AirBubbleHolder>()
    val mainBubble = mainGunInfo?.bubbles
    val secondaryBubble = secondaryInfo?.bubbles

    for (bubble in listOf(mainBubble, secondaryBubble)) {
        bubble?.let {
            val holder = AirBubbleHolder(
                damage = _format(it.damage),
                explosions = "${it.inner} + ${it.outer}",
                hitChance = _percent(it.hitChance),
                range = "${_format(it.minRange)}- ${_format(it.maxRange, Localisation.instance.kilometer)}"
            )
            airBubbles.add(holder)
        }
    }

    return airBubbles
}

fun extractAirDefenses(
    airDefense: AirDefense?,
    mainGunInfo: GunInfo?,
    secondaryInfo: GunInfo?
): List<AirDefenseHolder> {
    val airDefenses = mutableListOf<AirDefenseHolder>()
    val mid = airDefense?.medium
    val near = airDefense?.near
    val far = airDefense?.far
    val mainFar = mainGunInfo?.far
    val secondaryFar = secondaryInfo?.far

    for (aa in listOf(near, mid, far, mainFar, secondaryFar)) {
        if (aa == null) continue
        for (aaInfo in aa) {
            val aaGuns = mutableMapOf<String, MutableList<AuraGuns>>()
            for (gun in aaInfo.guns) {
                val name = Localisation.instance.stringOf(gun.name) ?: continue
                if (!aaGuns.containsKey(name)) {
                    aaGuns[name] = mutableListOf(gun)
                } else {
                    aaGuns[name]?.add(gun)
                }
            }

            for ((name, gunList) in aaGuns) {
                if (gunList.isEmpty()) {
                    assert(false) { "No guns found for $name" }
                    continue
                }

                var count = 0
                val each = gunList.first().each
                for (gunInfo in gunList) {
                    count += gunInfo.count
                }

                val holder = AirDefenseHolder(
                    name = "$count x $each $name",
                    damage = format(aaInfo.dps),
                    hitChance = percent(aaInfo.hitChance),
                    range = format(aaInfo.maxRange, suffix = Localisation.instance.kilometer)
                )
                airDefenses.add(holder)
            }
        }
    }

    return airDefenses
}


    private val mobility: MobilityInfo? get() = hullInfo?.mobility
    val renderMobility: Boolean get() = mobility != null
    val rudderTime: String get() = format(mobility?.rudderTime, suffix = Localisation.instance.second)
    val maxSpeed: String get() = format(mobility?.speed, suffix = Localisation.instance.knot)
    val turningRadius: String get() = format(mobility?.turningRadius, suffix = Localisation.instance.meter)

    private val visibility: VisibilityInfo? get() = hullInfo?.visibility
    val renderVisibility: Boolean get() = visibility != null
    val seaVisibility: String get() = format(visibility?.sea, suffix = Localisation.instance.kilometer)
    val planeVisibility: String get() = format(visibility?.plane, suffix = Localisation.instance.kilometer)

    private val airSupport: AirSupportInfo? get() = shipModules.airSupportInfo?.data
    val renderAirSupport: Boolean get() = airSupport != null
    val airSupportName: String get() = Localisation.instance.stringOf(airSupport?.name) ?: ""
    val airSupportCharges: String get() = format(airSupport?.chargesNum)
    val airSupportReload: String get() = format(airSupport?.reload, suffix = Localisation.instance.second)
    val airSupportRange: String get() = format(airSupport?.range, suffix = Localisation.instance.kilometer)
    private val airSupportPlane: Aircraft? get() = GameRepository.instance.aircraftOf(airSupport?.plane ?: "")
    val airSupportPlaneHealth: String get() = format(airSupportPlane?.health)
    val airSupportBombs: String get() = format(airSupportPlane?.aircraft?.attackCount)
    val airSupportTotalPlanes: String get() = format(airSupportPlane?.totalPlanes)
    private val aircraftBomb: Projectile? get() = GameRepository.instance.projectileOf(airSupportPlane?.aircraft?.bombName ?: "")
    val airSupportBombDamage: String get() = format(aircraftBomb?.damage)
    val airSupportBombBurnChance: String get() = percent(aircraftBomb?.burnChance)
    val airSupportBombFloodChance: String get() = rawPercent(aircraftBomb?.floodChance)
    val airSupportBombPenetration: String get() = format(aircraftBomb?.penHe ?: aircraftBomb?.penSAP, suffix = Localisation.instance.millimeter)

    private val depthCharge: DepthChargeInfo? get() = shipModules.depthChargeInfo?.data
    val renderDepthCharge: Boolean get() = depthCharge != null
    val depthChargeReload: String get() = format(depthCharge?.reload, suffix = Localisation.instance.second)
    val depthChargeConfig: String get() = "${format(depthCharge?.groups)} x ${format(depthCharge?.bombs)}"
    private val depthChargeAmmo: Projectile? get() = GameRepository.instance.projectileOf(depthCharge?.ammo ?: "")
    val depthChargeDamage: String get() = format(depthChargeAmmo?.damage)
    val depthChargeBurnChance: String get() = percent(depthChargeAmmo?.burnChance)
    val depthChargeFloodChance: String get() = rawPercent(depthChargeAmmo?.floodChance)

    val hasUpgrades: Boolean get() = shipUpgrades.modernizations.isNotEmpty()
    val upgrades: List<List<Modernization>> get() = shipUpgrades.modernizationsBySlot

    val hasNextShip: Boolean get() = ship.nextShips?.isNotEmpty() ?: false
    val nextShips: List<Ship?> get() = ship.nextShips?.map { GameRepository.instance.shipOf(it.toString()) } ?: emptyList()

    val hasConsumables: Boolean get() = ship.consumables.isNotEmpty()
    val consumables: List<List<Consumable>> get() = ship.consumables
}

data class ShellHolder(val name: String)

    val name: String,
    var burnChance: String? = null,
    var weight: String? = null,
    var velocity: String? = null,
    var damage: String? = null,
    var penetration: String? = null,
    var overmatch: String? = null
)

data class BurstFireHolder(
    val reload: Int,
    val interval: Int,
    val shots: Int,
    val modifiers: List<String>? = null
)

    val reload: String,
    val interval: String,
    val shots: String,
    val modifiers: String?
)

data class SecondaryGunHolder(
    val name: String
)

    val name: String,
    var reloadTime: String? = null,
    var burnChance: String? = null,
    var damage: String? = null,
    var penetration: String? = null,
    var velocity: String? = null,
    var config: String? = null
)

data class TorpedoHolder(
    val name: String
)

    val name: String,
    var damage: String? = null,
    var range: String? = null,
    var visibility: String? = null,
    var speed: String? = null,
    var reactionTime: String? = null,
    var floodChance: String? = null
)

data class AirDefenseHolder(
    val name: String,
    val damage: String,
    val range: String,
    val hitChance: String
)

    val name: String,
    val damage: String,
    val range: String,
    val hitChance: String
)

data class AirBubbleHolder(
    val damage: String,
    val range: String,
    val hitChance: String,
    val explosions: Int
)

    val damage: String,
    val range: String,
    val hitChance: String,
    val explosions: String
)