package org.github.wowsinfo.conversion.legacy

fun Number.asPercentString(): String {
    return "${toDecimalString()}%"
}

expect fun Number.toFixedString(fractionDigits: Int): String

expect fun Number.toDecimalString(): String

expect fun Number.toPercentString(): String
