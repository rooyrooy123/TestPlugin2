package me.rooyrooy.testPlugin2.shop

import me.rooyrooy.testPlugin2.gui.BackItem
import me.rooyrooy.testPlugin2.gui.ForwardItem
import org.bukkit.Material
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.entity.Player
import xyz.xenondevs.invui.gui.PagedGui
import xyz.xenondevs.invui.gui.structure.Markers
import xyz.xenondevs.invui.item.Item
import xyz.xenondevs.invui.item.builder.ItemBuilder
import xyz.xenondevs.invui.item.impl.SimpleItem
import xyz.xenondevs.invui.window.Window

class Shop(
    private val id: String,
    config: ConfigurationSection
) {
    private val gui = PagedGui.items()
 init {
     val itemsSection = config.getConfigurationSection("items") ?:throw NullPointerException()
     val materials: MutableSet<String> = itemsSection.getKeys(false)
     val items = arrayListOf<ShopBuyButton>()
     val border = SimpleItem(ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName(""))

     materials.forEach { materialString ->
         val material = Material.valueOf(materialString.uppercase())
         val amount = itemsSection.getInt("$materialString.amount", 0)
         val price = itemsSection.getInt("$materialString.price", 0)

         items.add(ShopBuyButton(material, amount, price))


     }
     gui
         .setStructure(
             "# # # # # # # # #",
             "# x x x x x x x #",
             "# x x x x x x x #",
             "# # # < # > # # #")
         .addIngredient('x', Markers.CONTENT_LIST_SLOT_HORIZONTAL) // where paged items should be put
         .addIngredient('#', border)
         .addIngredient('<', BackItem())
         .addIngredient('>', ForwardItem())
         .setContent(items as List<Item>)
         .build()


 }

    fun open(player: Player) {

        val window = Window.single()
            .setViewer(player)
            .setTitle("§e§l§nShop§r §7(${id})")
            .setGui(gui)
            .build()
        window.open()
    }

}