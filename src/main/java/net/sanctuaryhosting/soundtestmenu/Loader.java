package net.sanctuaryhosting.soundtestmenu;

import net.sanctuaryhosting.soundtestmenu.Core.pCommand;
import net.sanctuaryhosting.soundtestmenu.Core.pHandler;
import net.sanctuaryhosting.soundtestmenu.Core.pListener;
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