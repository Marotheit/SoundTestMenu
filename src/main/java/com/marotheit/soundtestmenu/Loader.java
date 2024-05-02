package com.marotheit.soundtestmenu;

import com.marotheit.soundtestmenu.Core.pCommand;
import com.marotheit.soundtestmenu.Core.pHandler;
import com.marotheit.soundtestmenu.Core.pListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Loader extends JavaPlugin{
    public static Loader plugin;
    
    @Override
    public void onEnable() {
        plugin = this;
        
        pHandler.init();
        Bukkit.getPluginCommand("soundtestmenu").setExecutor(new pCommand());
        Bukkit.getPluginManager().registerEvents(new pListener(), this);
    }
    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}