package cz.devfire.playsoundtester.Core;

import cz.devfire.playsoundtester.Other.Utils;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class pCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] strings) {
        if (!sender.hasPermission("playsoundtester.use")) {
            sender.sendMessage("&c&lSoundTester &8&l» &7You don't have enought permissions!");
            return true;
        } else {
            if (Utils.getServerVersion().contains("v1_8")) {
                pHandler.openSound((Player) sender, "ALL");
            } else {
                pHandler.openMain((Player) sender);
            }

            sender.sendMessage("&c&lSoundTester &8&l» &7Opening menu!");
            return true;
        }
    }
}
