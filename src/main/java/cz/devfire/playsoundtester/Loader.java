package cz.devfire.playsoundtester;

import cz.devfire.playsoundtester.Core.pCommand;
import cz.devfire.playsoundtester.Core.pHandler;
import cz.devfire.playsoundtester.Core.pListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Loader extends JavaPlugin {
    public static Loader plugin;

    @Override
    public void onEnable() {
        plugin = this;

        pHandler.init();
        Bukkit.getPluginCommand("playsoundtester").setExecutor(new pCommand());
        Bukkit.getPluginManager().registerEvents(new pListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
