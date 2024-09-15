package org.github.wowsinfo.conversion.legacy

import io.github.oshai.kotlinlogging.KotlinLogging

expect class TimeTracker {
    fun log(message: String)
}