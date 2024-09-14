/**
 * Get to path of obj and return its value or return default value
 * @param obj Object to get the value from
 * @param path Path to get the value from
 * @param dval Default value if obj is not valid
 */
fun guard(obj: Any?, path: String, dval: Any?): Any? {
    // This is a just a simple check if obj is null or undefined
    if (path == "" && obj != null) {
        return obj
    }
    // check if object is valid and path does not start with or end with '.'
    if (!path.startsWith(".") && !path.endsWith(".")) {
        // get path as an array and it must have at least 2 elements
        val p = path.split(".")
        if (p.isNotEmpty()) {
            // o is the object (accumulator), and n is from path (current value)
            // o != null && o[n] != null -> to go further or just return default value
            // only asking for the object
            return p.fold(obj) { acc, n ->
                if (acc == null || acc !is Map<*, *>) {
                    dval
                } else {
                    acc[n]
                }
            }
        }
    }
    return dval
}

/**
 * Return a default value if obj is not valid
 * @param obj Object to check
 * @param dval Default value if obj is not valid
 */
fun safeValue(obj: Any?, dval: Any?): Any? {
    if (obj == null) {
        return dval
    }
    return obj
}