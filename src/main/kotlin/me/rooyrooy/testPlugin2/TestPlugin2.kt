package me.rooyrooy.testPlugin2


import me.rooyrooy.testPlugin2.shop.Shop
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin

//初期設定
val money_item = Material.EMERALD



class TestPlugin2 : JavaPlugin() {

    override fun onEnable() {

        //config.ymlがないと出力
        saveDefaultConfig();


    }

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String> ): Boolean {
        println("test_console")
        if (cmd.name.equals("shop", ignoreCase = true)){ // #/shop items
            val player = Bukkit.getPlayer(sender.name) ?: return false
            player.sendMessage("TEST")
            if (args.size != 1) return false
            val id = args[0]
            sender.sendMessage("ID:${id}")
            Shop(id,config).open(player)

        }

        return false

    }
    override fun onDisable() {
        // Plugin shutdown logic
    }



}





