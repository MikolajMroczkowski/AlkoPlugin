package pl.miki.alkoplugin.Data;

import net.dv8tion.jda.api.entities.Guild;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

import static pl.miki.alkoplugin.AlkoPlugin.bot;
import static pl.miki.alkoplugin.AlkoPlugin.plugin;

public class NickCache {
    final FileConfiguration configuration;
    public NickCache() {
        File homesYml = new File(plugin.getDataFolder()+"/nickCache.yml");
        configuration = YamlConfiguration.loadConfiguration(homesYml);
    }
    public void initiateConfig(){
        try {
            configuration.save(new File(plugin.getDataFolder()+"/nickCache.yml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getUserNick(String dcID) {
        if(dcID == null) {
            return "NOT-LINKED";
        }
        String discordNick = configuration.getString(dcID+"|NICK");
        if(discordNick==null){
            return "NOT-LINKED";
        }
        if(discordNick.equals("NULL")){
            return "NOT-LINKED";
        }
        else{
            return discordNick;
        }
    }
    public String getUserAvatar(String dcID) {
        if(dcID == null) {
            return null;
        }
        String discordAvatar = configuration.getString(dcID+"|AVATAR");
        if(discordAvatar==null){
            return null;
        }
        if(discordAvatar.equals("NULL")){
            return null;
        }
        else{
            return discordAvatar;
        }
    }
    public void fetchNickIntoCache(String dcID){
        Configuration config = new Configuration();
        String guildID = config.getDiscordGuild();
        if(dcID != null&& guildID != null && bot!=null){
            Guild g = bot.jda.getGuildById(guildID);
            if(g != null) {
                g.retrieveMemberById(dcID).queue(u->{
                    if(u != null) {
                        String avatar = u.getEffectiveAvatarUrl();
                        configuration.set(dcID+"|NICK", u.getEffectiveName());
                        configuration.set(dcID+"|AVATAR",avatar );
                        try {
                            configuration.save(new File(plugin.getDataFolder()+"/nickCache.yml"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        plugin.getLogger().info("Member is null");
                    }
                });

            }
            else{
                plugin.getLogger().info("Guild is null");
            }


        }
    }
    public void reFetchCache(){
        for(String key : configuration.getKeys(false)){
            plugin.getLogger().info(key.split("\\|")[0]);
            if(key.split("\\|")[1].equals("NICK")){
                fetchNickIntoCache(key.split("\\|")[0]);
            }
        }
    }
}
