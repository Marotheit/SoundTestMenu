package net.sanctuaryhosting.soundTestMenu;

import net.sanctuaryhosting.soundTestMenu.Core.pCommand;
import net.sanctuaryhosting.soundTestMenu.Core.pHandler;
import net.sanctuaryhosting.soundTestMenu.Core.pListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SoundTestMenuPlugin extends JavaPlugin{
    public static SoundTestMenuPlugin plugin;
    
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