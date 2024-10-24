
interface ShipFilterInterface {
    val name: String
    val tier: Int
    val region: String
    val type: String
}

@Serializable
data class ShipFilter(
    val name: String,
    val tiers: List<Int>,
    val regions: List<String>,
    val types: List<String>
) : ShipFilterInterface {
    @Transient
    override val tier: Int
        get() = tiers.firstOrNull() ?: 0

    @Transient
    override val region: String
        get() = regions.firstOrNull() ?: ""

    @Transient
    override val type: String
        get() = types.firstOrNull() ?: ""
}


@Serializable
data class ShipFilter(
    val name: String,
    val tiers: List<Int>,
    val regions: List<String>,
    val types: List<String>
) {
    private val logger = LoggerFactory.getLogger(ShipFilter::class.java)

    val isEmpty: Boolean
        get() = name.trim().isEmpty() && tiers.isEmpty() && regions.isEmpty() && types.isEmpty()
}

    if (name.trim().isNotEmpty()) {
        val shipName = Localisation.instance.stringOf(ship.name)
        if (shipName == null) {
            throw Exception("Failed to get ship name: ${ship.name}")
        }

        if (!shipName.lowercase().contains(name.lowercase())) {
            return false
        }
    }

    if (tiers.isNotEmpty() && !tiers.contains(ship.tier)) {
        return false
    }
    if (regions.isNotEmpty() && !regions.contains(ship.region)) {
        return false
    }
    if (types.isNotEmpty() && !types.contains(ship.type)) {
        return false
    }

    return true
}

    val name: String,
    val tiers: List<String>,
    val regions: List<String>,
    val types: List<String>
) {
    override fun toString(): String {
        return "ShipFilter{shipName: $name, tierList: $tiers, regionList: $regions, typeList: $types}"
    }
}

    val name: String,
    val tiers: List<String>,
    val regions: List<String>,
    val types: List<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MyDataClass) return false

        if (name != other.name) return false
        if (tiers != other.tiers) return false
        if (regions != other.regions) return false
        if (types != other.types) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + tiers.hashCode()
        result = 31 * result + regions.hashCode()
        result = 31 * result + types.hashCode()
        return result
    }
}