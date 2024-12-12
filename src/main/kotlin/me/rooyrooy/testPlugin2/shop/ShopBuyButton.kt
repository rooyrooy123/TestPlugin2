package me.rooyrooy.testPlugin2.shop

import me.rooyrooy.testPlugin2.money_item
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.invui.item.ItemProvider
import xyz.xenondevs.invui.item.builder.ItemBuilder
import xyz.xenondevs.invui.item.impl.AbstractItem

class ShopBuyButton(
    private val item: Material,
    private val amount: Int,
    private val price: Int,
) : AbstractItem() {

    override fun getItemProvider(): ItemProvider {
        return ItemBuilder(item)
            .setAmount(amount)
            .addLoreLines("amount: $amount")
            .addLoreLines("price: $amount")

    }

    override fun handleClick(clickType: ClickType, player: Player, event: InventoryClickEvent) {
        if (canBuy(player)) {
            player.inventory.addItem(ItemStack(item))
            player.inventory.remove(
                ItemStack(Material.AIR).apply { amount = price }
            )

            player.playSound(player, "entity.villager.yes", 1f, 1f)
            player.sendMessage("§6§l§n購入しました!!!")

            return
        }

        player.playSound(player, "entity.villager.no", 1f, 1f)
        player.sendMessage("§4§l購入不可§7§l(コスト${price}Emerald)")
    }

    private fun canBuy(player: Player): Boolean {
        val sum = player.inventory
            .filter { it.type == money_item }
            .sumOf { it.amount }

        return sum >= price

    }
}

