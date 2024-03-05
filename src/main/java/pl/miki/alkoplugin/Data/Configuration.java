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
        configuration.set("discordToken", "NULL");
        configuration.set("discordChatChannel", "NULL");
        configuration.set("discordGuild", "NULL");
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
    public String getDiscordGuild(){
        String guild =  configuration.getString("discordGuild");
        if(guild==null){
            return null;
        }
        if(guild.equals("NULL")){
            return null;
        }
        else{
            return guild;
        }
    }
    public String getDiscordToken(){
        String token =  configuration.getString("discordToken");
        if(token==null){
            return null;
        }
        if(token.equals("NULL")){
            return null;
        }
        else{
            return token;
        }
    }
    public String getDiscordChannel(){
        String channel =  configuration.getString("discordChatChannel");
        if(channel==null){
            return null;
        }
        if(channel.equals("NULL")){
            return null;
        }
        else{
            return channel;
        }
    }
}
