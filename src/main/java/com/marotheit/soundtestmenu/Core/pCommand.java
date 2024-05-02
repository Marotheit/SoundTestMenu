package com.marotheit.soundtestmenu.Core;

import com.marotheit.soundtestmenu.Other.Utils;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class pCommand implements CommandExecutor{
    
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] strings) {
        if (!sender.hasPermission("soundtest.menu")) {
            sender.sendMessage(Utils.cc("&7[&3SoundTestMenu&7] &cYou do not have permission to perform this command."));
        } else {
            pHandler.openMain((Player) sender);
        }
        return true;
    }
}