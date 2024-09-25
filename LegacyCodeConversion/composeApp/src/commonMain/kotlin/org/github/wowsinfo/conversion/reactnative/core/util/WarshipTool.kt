    return when {
        tier < 1 -> {
            println("getTierLabel: Invalid tier: $tier")
            "O"
        }
        else -> {
            // Add logic for valid tiers here
            "Valid Tier"
        }
    }
}

    // Implementation of tier list
}

fun getLabel(tier: Int): String {
    val label = getTierList()
    return label[tier - 1]
}

fun getColourWithRange(min: Int, curr: Int, max: Int): String {
    return when {
        curr < min -> "#FF0000"
        // Additional color logic can be added here
        else -> "#00FF00" // Example for green
    }
}

    return ((curr - min) / (max - min) * 100).toInt()
}

fun componentToHex(c: Int): String {
    val hex = c.toString(16).take(2)
    return if (hex.length == 1) "0$hex" else hex
}

    return "#" + componentToHex(r) + componentToHex(g) + componentToHex(b)
}

fun componentToHex(c: Int): String {
    val hex = c.toString(16)
    return if (hex.length == 1) "0$hex" else hex
}

    return String.format("#%02X%02X%02X", r, g, b)
}

fun getColour(scale: Int): String {
    val g = (255 * scale) / 100
    val r = (255 * (100 - scale)) / 100
    val colour = rgbToHex(r, g, 0)
    println(colour)
    return colour
}

fun getKeyByValue(map: Map<String, Any>, value: Any): String? {
    return map.entries.find { it.value == value }?.key
}

fun getTierList(): List<String> {
    return listOf("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "★")
}

fun filterShip(data: Map<String, Any>, shipData: List<Any>? = null): Any? {
    val premium = data["premium"] as? Boolean ?: false
    val name = data["name"] as? String ?: ""
    val nation = data["nation"] as? List<*> ?: emptyList<Any>()
    val type = data["type"] as? List<*> ?: emptyList<Any>()
    val tier = data["tier"] as? List<*> ?: emptyList<Any>()

    println(data)
    return if (!premium && name.isEmpty() && nation.isEmpty() && type.isEmpty() && tier.isEmpty()) {
        null
    } else {
        data
    }
}

val fdata = normalise(nation, type, tier)

println(fdata)
val warship = AppGlobalData.get(SAVED.warship)
val filtered = mutableListOf<ShipData>()

if (shipData != null) {
    for (ship in shipData) {
        val curr = warship[ship.shipId]
        if (curr == null) {
            continue
        }
        if (validShip(curr, fname, fdata, premium)) {
            filtered.add(ship)
        }
    }
}

    for ((ID, curr) in warship) {
        if (validShip(curr, fname, fdata, premium)) {
            filtered.add(curr)
        }
    }
}

    filtered.sortedWith(compareByDescending<ShipData> { it.tier }.thenBy { it.type })
} else {
    filtered
}

    var filterTier = false
    var filterName = false
    var filterNation = false
    var filterType = false
    var filterPremium = false

    val ftier = fdata.tier
    val fnation = fdata.nation
    val ftype = fdata.type

    if (curr.name.lowercase().contains(fname.lowercase()) || fname.trim().isEmpty()) {
        filterName = true
    }

    // Additional filtering logic can be added here

    return filterName // Return the result based on the filters applied
}

    filterPremium = true
}

    filterTier = true
}

    filterNation = true
}

    filterType = true
}

    return filterName != null && filterNation != null && filterPremium != null && filterTier != null && filterType != null
}

    return obj.isEmpty()
}

fun normalise(nation: List<Any>, type: List<Any>, tier: List<Any>): Map<String, Map<String, Boolean>> {
    val data = mutableMapOf("nation" to mutableMapOf<String, Boolean>(), "type" to mutableMapOf<String, Boolean>(), "tier" to mutableMapOf<String, Boolean>())

    nation.forEach { i ->
        val key = getKeyByValue(AppGlobalData.get(SAVED.encyclopedia).ship_nations, i)
        if (key == null) {
            println("normalise: Invalid ship nation: $i")
        } else {
            data["nation"]?.put(key, true)
        }
    }
    return data
}

    val key = getKeyByValue(AppGlobalData.get(SAVED.encyclopedia).ship_types, i)
    if (key == null) {
        println("normalise: Invalid ship type: $i")
    } else {
        data.type[key] = true
    }
}

    val index = getTierList().indexOf(i)
    if (index == -1) {
        println("normalise: Invalid ship tier: $i")
    } else {
        data.tier[index + 1] = true
    }
}

    // Your data fetching logic here
    return data
}