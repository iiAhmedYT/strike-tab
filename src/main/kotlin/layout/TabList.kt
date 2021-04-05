package ga.strikepractice.striketab.layout

import ga.strikepractice.StrikePractice
import org.bukkit.entity.Player


data class TabLayout(
    val slots: List<TabSlot>,
    val header: String?,
    val footer: String?,
) {

    companion object {
        fun parse(rawLines: List<String>, header: String?, footer: String?) =
            TabLayout(rawLines.map { TabSlot.fromString(it) }, header, footer)
    }

}

enum class TabLayoutType {
    DEFAULT,
    IN_MATCH,
}

data class TabSlot(
    val text: String,
    val skin: String?,
    val ping: Int = 0
) {

    companion object {

        // TODO: support lunarclient ping that disables the network icon thing
        private const val DEFAULT_PING = 0

        fun fromString(str: String): TabSlot {
            // A stupid way to make skin & ping configurable
            val skin = str.substringAfter("skin=", "").substringBefore(" ")
            val ping = str.substringAfter("ping=").substringBefore(" ")
            val text = str.replace("skin=$skin", "").replace("ping=$ping", "")
            return TabSlot(
                text = text,
                skin = if (skin.isBlank()) null else skin,
                ping = ping.toIntOrNull() ?: DEFAULT_PING
            )
        }

    }

}