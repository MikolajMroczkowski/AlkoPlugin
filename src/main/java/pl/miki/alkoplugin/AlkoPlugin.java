package pl.miki.alkoplugin;

import org.bukkit.plugin.java.JavaPlugin;
import pl.miki.alkoplugin.Commands.*;
import pl.miki.alkoplugin.Data.Configuration;
import pl.miki.alkoplugin.Data.Linker;
import pl.miki.alkoplugin.Data.MoneyStore;
import pl.miki.alkoplugin.Data.NickCache;
import pl.miki.alkoplugin.Discord.Bot;
import pl.miki.alkoplugin.Events.*;
import pl.miki.alkoplugin.TabCompleters.LinkTabCompleter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public final class AlkoPlugin extends JavaPlugin {

    public static org.bukkit.plugin.Plugin plugin = null;
    public static pl.miki.alkoplugin.Discord.Bot bot = null;

    public static HashMap<String, Integer> playerTempWhitelist = new HashMap();
    public static HashMap<String, String> playerDiscordIDTemp = new HashMap();

    @Override
    public void onEnable() {
        this.getLogger().info("AlkoPlugin has been enabled");
        this.getLogger().info("AlkoPlugin starting...");
        plugin = this;
        this.getServer().getPluginManager().registerEvents(new BeautyChat(), this);
        this.getServer().getPluginManager().registerEvents(new ChatToDiscord(), this);
        this.getServer().getPluginManager().registerEvents(new Welcome(), this);
        this.getServer().getPluginManager().registerEvents(new LinkInformation(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerMoney(), this);
        this.getServer().getPluginManager().registerEvents(new AfkEvent(), this);
        this.getServer().getPluginManager().registerEvents(new Protection(), this);
        this.getCommand("link").setTabCompleter(new LinkTabCompleter());
        this.getCommand("setHome").setExecutor(new SetHomeCommand());
        this.getCommand("home").setExecutor(new HomeCommand());
        this.getCommand("top").setExecutor(new TopCommand());
        this.getCommand("teleport").setExecutor(new TeleportCommand());
        this.getCommand("unlink").setExecutor(new UnlinkCommand());
        this.getCommand("link").setExecutor(new LinkCommand());
        this.getCommand("pierun").setExecutor(new PierunCommand());
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }
        File homeFile = new File(getDataFolder(), "homes.yml");
        File configFile = new File(getDataFolder(), "config.yml");
        File linksFile = new File(getDataFolder(), "links.yml");
        File cacheFile = new File(getDataFolder(), "nickCache.yml");
        File xpCache = new File(getDataFolder(), "money.yml");
        if (!homeFile.exists()) {
            try {
                homeFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                Configuration c = new Configuration();
                c.initiateConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!linksFile.exists()) {
            try {
                linksFile.createNewFile();
                Linker link = new Linker();
                link.initiateConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!cacheFile.exists()) {
            try {
                cacheFile.createNewFile();
                NickCache nc = new NickCache();
                nc.initiateConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!xpCache.exists()) {
            try {
                xpCache.createNewFile();
                MoneyStore xc = new MoneyStore();
                xc.initiateConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Configuration config = new Configuration();
        if (config.getDiscordToken() != null) {
            bot = new Bot(config.getDiscordToken());
        } else {
            this.getLogger().warning("Discord token is not set");
        }
        this.getLogger().info("AlkoPlugin started successfully");

    }

    @Override
    public void onDisable() {
        this.getLogger().info("AlkoPlugin stopping...");
        bot.jda.shutdown();
        this.getLogger().info("AlkoPlugin successfully stoped");
        this.getLogger().info("AlkoPlugin has been disabled");
        // Plugin shutdown logic
    }
}
