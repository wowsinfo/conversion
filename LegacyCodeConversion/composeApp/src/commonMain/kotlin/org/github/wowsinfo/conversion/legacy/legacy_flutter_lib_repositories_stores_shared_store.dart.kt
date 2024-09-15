
class SharedStore {
    companion object {
        private lateinit var _context: Context
        fun init(context: Context) {
            _context = context
        }

        suspend fun <T> get(key: String, defaultValue: T): T {
            return withContext(Dispatchers.IO) {
                val gson = Gson()
                when (defaultValue) {
                    is Int -> {
                        val value = _context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                            .getInt(key, defaultValue)
                        if (value == 0 && defaultValue != 0) {
                            set(key, defaultValue)
                            return@withContext defaultValue
                        } else {
                            return@withContext value
                        }
                    }

                    is Long -> {
                        val value = _context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                            .getLong(key, defaultValue)
                        if (value == 0L && defaultValue != 0L) {
                            set(key, defaultValue)
                            return@withContext defaultValue
                        } else {
                            return@withContext value
                        }
                    }

                    is Boolean -> {
                        val value = _context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                            .getBoolean(key, defaultValue)
                        if (value != defaultValue) {
                            set(key, defaultValue)
                            return@withContext defaultValue
                        } else {
                            return@withContext value
                        }
                    }

                    is String -> {
                        val value = _context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                            .getString(key, defaultValue)
                        if (value == null) {
                            set(key, defaultValue)
                            return@withContext defaultValue
                        } else {
                            return@withContext value
                        }
                    }

                    is Float -> {
                        val value = _context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                            .getFloat(key, defaultValue)
                        if (value == 0f && defaultValue != 0f) {
                            set(key, defaultValue)
                            return@withContext defaultValue
                        } else {
                            return@withContext value
                        }
                    }

                    is Double -> {
                        val value = _context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                            .getDouble(key, defaultValue)
                        if (value == 0.0 && defaultValue != 0.0) {
                            set(key, defaultValue)
                            return@withContext defaultValue
                        } else {
                            return@withContext value
                        }
                    }

                    is Array<*> -> {
                        val value = _context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                            .getString(key, "")
                        if (value == "") {
                            set(key, gson.toJson(defaultValue))
                            return@withContext defaultValue
                        } else {
                            return@withContext gson.fromJson(value, defaultValue::class.java)
                        }
                    }

                    is List<*> -> {
                        val value = _context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                            .getString(key, "")
                        if (value == "") {
                            set(key, gson.toJson(defaultValue))
                            return@withContext defaultValue
                        } else {
                            return@withContext gson.fromJson(value, defaultValue::class.java)
                        }
                    }

                    is Set<*> -> {
                        val value = _context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                            .getString(key, "")
                        if (value == "") {
                            set(key, gson.toJson(defaultValue))
                            return@withContext defaultValue
                        } else {
                            return@withContext gson.fromJson(value, defaultValue::class.java)
                        }
                    }

                    is Map<*, *> -> {
                        val value = _context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                            .getString(key, "")
                        if (value == "") {
                            set(key, gson.toJson(defaultValue))
                            return@withContext defaultValue
                        } else {
                            return@withContext gson.fromJson(value, defaultValue::class.java)
                        }
                    }

                    else -> throw IllegalArgumentException("Unsupported type")
                }
            }
        }

        suspend fun <T> set(key: String, value: T) {
            val gson = Gson()
            withContext(Dispatchers.IO) {
                when (value) {
                    is Int -> _context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                        .edit()
                        .putInt(key, value)
                        .apply()
                    is Long -> _context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                        .edit()
                        .putLong(key, value)
                        .apply()
                    is Boolean -> _context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                        .edit()
                        .putBoolean(key, value)
                        .apply()
                    is String -> _context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                        .edit()
                        .putString(key, value)
                        .apply()
                    is Float -> _context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                        .edit()
                        .putFloat(key, value)
                        .apply()
                    is Double -> _context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                        .edit()
                        .putDouble(key, value)
                        .apply()
                    is Array<*> -> {
                        val json = gson.toJson(value)
                        _context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                            .edit()
                            .putString(key, json)
                            .apply()
                    }

                    is List<*> -> {
                        val json = gson.toJson(value)
                        _context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                            .edit()
                            .putString(key, json)
                            .apply()
                    }

                    is Set<*> -> {
                        val json = gson.toJson(value)
                        _context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                            .edit()
                            .putString(key, json)
                            .apply()
                    }

                    is Map<*, *> -> {
                        val json = gson.toJson(value)
                        _context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                            .edit()
                            .putString(key, json)
                            .apply()
                    }
                }
            }
        }

        suspend fun remove(key: String) {
            withContext(Dispatchers.IO) {
                _context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                    .edit()
                    .remove(key)
                    .apply()
            }
        }

        suspend fun clear() {
            withContext(Dispatchers.IO) {
                _context.getSharedPreferences("shared", Context.MODE_PRIVATE).edit().clear().apply()
            }
        }

        fun hasKey(key: String): Boolean {
            return _context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                .contains(key)
        }
    }
}