package org.github.wowsinfo.conversion

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform