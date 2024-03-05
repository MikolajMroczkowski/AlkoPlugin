package pl.miki.alkoplugin.Data;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

import static pl.miki.alkoplugin.AlkoPlugin.plugin;

public class Configuration {
    final FileConfiguration configuration;
    public Configuration() {
        File homesYml = new File(plugin.getDataFolder()+"/config.yml");
        configuration = YamlConfiguration.loadConfiguration(homesYml);
    }
    public void initiateConfig(){
        configuration.set("discordWebhook", "NULL");
        try {
            configuration.save(new File(plugin.getDataFolder()+"/config.yml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getDiscordWebhook(){
        String discord =  configuration.getString("discordWebhook");
        if(discord==null){
            return null;
        }
        if(discord.equals("NULL")){
            return null;
        }
        else{
            return discord;
        }
    }
}
