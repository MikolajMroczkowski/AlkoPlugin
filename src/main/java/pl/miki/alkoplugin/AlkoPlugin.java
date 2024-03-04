package pl.miki.alkoplugin;

import org.bukkit.plugin.java.JavaPlugin;
import pl.miki.alkoplugin.Commands.HomeCommand;
import pl.miki.alkoplugin.Commands.SetHomeCommand;
import pl.miki.alkoplugin.Commands.TeleportCommand;
import pl.miki.alkoplugin.Commands.TopCommand;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public final class AlkoPlugin extends JavaPlugin {

    public static org.bukkit.plugin.Plugin plugin = null;
    @Override
    public void onEnable() {
        this.getLogger().info("AlkoPlugin has been enabled");
        this.getLogger().info("AlkoPlugin starting...");
        this.getCommand("setHome").setExecutor(new SetHomeCommand());
        this.getCommand("home").setExecutor(new HomeCommand());
        this.getCommand("top").setExecutor(new TopCommand());
        this.getCommand("teleport").setExecutor(new TeleportCommand());
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }
        File homeFile = new File(getDataFolder(), "homes.yml");
        if(!homeFile.exists()) {
            try {
                homeFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        plugin = this;
        this.getLogger().info("AlkoPlugin started successfully");

    }

    @Override
    public void onDisable() {
        this.getLogger().info("AlkoPlugin has been disabled");
        // Plugin shutdown logic
    }
}
