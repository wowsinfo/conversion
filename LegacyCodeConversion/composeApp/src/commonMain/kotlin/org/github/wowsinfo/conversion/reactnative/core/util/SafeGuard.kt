    if (path.isEmpty() && obj != null) {
        return obj
    }
    val keys = path.split(".")
    var current: Any? = obj
    for (key in keys) {
        if (current is Map<*, *>) {
            current = current[key]
        } else {
            return dval
        }
    }
    return current ?: dval
}

    if (!path.startsWith(".") && !path.endsWith(".")) {
        val p = path.split(".")
        if (p.isNotEmpty()) {
            return p.fold(obj as Any?) { acc, n ->
                if (acc is Map<*, *> && acc[n] != null) {
                    acc[n]
                } else {
                    dval
                }
            }
        }
    }
    return dval
}

    return obj ?: dval
}

    return MyObject()
}