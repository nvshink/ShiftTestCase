package com.nvshink.data.generic

/**
 * Counties flags emojis code list
 * @param emojiCode for example Australia flag code: `\uD83C\uDDE6\uD83C\uDDFA`
 */
enum class CountryFlags(val emojiCode: String) {
    AU("\uD83C\uDDE6\uD83C\uDDFA"),
    BR("\uD83C\uDDE7\uD83C\uDDF7"),
    CA("\uD83C\uDDE8\uD83C\uDDE6"),
    CH("\uD83C\uDDE8\uD83C\uDDED"),
    DE("\uD83C\uDDE9\uD83C\uDDEA"),
    DK("\uD83C\uDDE9\uD83C\uDDF0"),
    ES("\uD83C\uDDEA\uD83C\uDDF8"),
    FI("\uD83C\uDDEB\uD83C\uDDEE"),
    FR("\uD83C\uDDEB\uD83C\uDDF7"),
    GB("\uD83C\uDDEC\uD83C\uDDE7"),
    IE("\uD83C\uDDEE\uD83C\uDDEA"),
    IN("\uD83C\uDDEE\uD83C\uDDF3"),
    IR("\uD83C\uDDEE\uD83C\uDDF7"),
    MX("\uD83C\uDDF2\uD83C\uDDFD"),
    NL("\uD83C\uDDF3\uD83C\uDDF1"),
    NO("\uD83C\uDDF3\uD83C\uDDF4"),
    NZ("\uD83C\uDDF3\uD83C\uDDFF"),
    RS("\uD83C\uDDF7\uD83C\uDDF8"),
    TR("\uD83C\uDDF9\uD83C\uDDF7"),
    UA("\uD83C\uDDFA\uD83C\uDDE6"),
    US("\uD83C\uDDFA\uD83C\uDDF8"),
    RU("\uD83C\uDDF7\uD83C\uDDFA"),
    KZ("\uD83C\uDDF0\uD83C\uDDFF"),
    UN("\uD83C\uDDFA\uD83C\uDDF3");

    companion object {
        /**
         * Get country double letters code (example `"RU"`) and return emoji code (example `\uD83C\uDDF0\uD83C\uDDFF`)
         */
        fun fromCountryCode(code: String): CountryFlags? =
            entries.firstOrNull { it.name.equals(code, ignoreCase = true) }
    }
}