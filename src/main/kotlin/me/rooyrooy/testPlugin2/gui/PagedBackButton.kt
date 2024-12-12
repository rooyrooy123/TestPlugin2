package me.rooyrooy.testPlugin2.gui

import org.bukkit.Material
import xyz.xenondevs.invui.gui.PagedGui
import xyz.xenondevs.invui.item.ItemProvider
import xyz.xenondevs.invui.item.builder.ItemBuilder
import xyz.xenondevs.invui.item.impl.controlitem.PageItem


class BackItem : PageItem(false) {

    override fun getItemProvider(gui: PagedGui<*>): ItemProvider {
        val builder = ItemBuilder(Material.RED_STAINED_GLASS_PANE)
        builder.setDisplayName("Previous page")
            .addLoreLines(
                if (gui.hasPreviousPage())
                    "Go to page " + gui.currentPage + "/" + gui.pageAmount
                else "You can't go further back"
            )
        return builder
    }

}