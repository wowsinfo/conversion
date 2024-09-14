import androidx.compose.runtime.Composable

@Composable
fun getTierLabel(tier: Int): String {
    return when (tier) {
        in 1..15 -> getTierList()[tier - 1]
        else -> "O"
    }
}

fun getColourWithRange(min: Number, curr: Number, max: Number): String {
    val scale = ((curr.toDouble() - min.toDouble()) / (max.toDouble() - min.toDouble())) * 100
    return when {
        curr < min -> "#FF0000"
        else -> {
            val r = (255 * (100 - scale)) / 100
            val g = (255 * scale) / 100
            rgbToHex(r.toInt(), g.toInt(), 0)
        }
    }
}

fun getKeyByValue(object: Any, value: Any): String? {
    return object.keys.find { it == value }
}

fun getTierList(): List<String> {
    return listOf("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "★")
}

fun filterShip(data: Any, shipData: Array<Any>?): List<Any>? {
    val premium = data.premium
    val name = data.name?.lowercase()
    val nation = data.nation
    val type = data.type
    val tier = data.tier

    if (!premium && name.isNullOrBlank() && nation.isEmpty() && type.isEmpty() && tier.isEmpty()) {
        return null
    }

    // Normalise nation, type and tier
    val fdata = normalise(nation, type, tier)

    // Filter ships
    val warship = AppGlobalData.get(SAVED.warship)
    val filtered = shipData?.filter { validShip(it, name, fdata, premium) } ?: warship.filterValues { validShip(it, name, fdata, premium) }

    return if (shipData == null) {
        // Sort ships
        filtered.sortedByDescending { it.tier }.sortedBy { it.type }
    } else {
        filtered
    }
}

fun validShip(curr: Any, fname: String?, fdata: Any, premium: Boolean): Boolean {
    val filterTier = fdata.tier[curr.tier] ?: isEmpty(fdata.tier)
    val filterName = (curr.name?.lowercase()?.contains(fname) == true) || fname.isNullOrBlank()
    val filterNation = fdata.nation[curr.nation] ?: isEmpty(fdata.nation)
    val filterType = fdata.type[curr.type] ?: isEmpty(fdata.type)
    val filterPremium = curr.premium == premium || !premium

    return filterName && filterNation && filterPremium && filterTier && filterType
}

fun isEmpty(obj: Any): Boolean {
    return obj.keys.isEmpty()
}

fun normalise(nation: List<Any>, type: List<Any>, tier: List<Any>): Any {
    val data = mutableMapOf<String, Boolean>()

    nation.forEach { key ->
        data[getKeyByValue(AppGlobalData.get(SAVED.encyclopedia).ship_nations, key)] = true
    }

    type.forEach { key ->
        data[getKeyByValue(AppGlobalData.get(SAVED.encyclopedia).ship_types, key)] = true
    }

    tier.forEach { i ->
        val index = getTierList().indexOf(i)
        if (index != -1) {
            data[index + 1] = true
        }
    }

    return data.toMap()
}

fun rgbToHex(r: Int, g: Int, b: Int): String {
    val componentToHex = { c: Int -> "%02X".format(c) }
    return "#${componentToHex(r)}${componentToHex(g)}${componentToHex(b)}"
}