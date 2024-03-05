package pl.miki.alkoplugin.Data;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

import static pl.miki.alkoplugin.AlkoPlugin.plugin;

public class Linker {
    final FileConfiguration configuration;
    public Linker() {
        File homesYml = new File(plugin.getDataFolder()+"/links.yml");
        configuration = YamlConfiguration.loadConfiguration(homesYml);
    }
    public void initiateConfig(){
        try {
            configuration.save(new File(plugin.getDataFolder()+"/links.yml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void unlinkByDCID(String dcID) {
        String mc = getUserByDCID(dcID);
        configuration.set(mc, "NULL");
        configuration.set(dcID, "NULL");
        try {
            configuration.save(new File(plugin.getDataFolder() + "/links.yml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void unlinkByMCNick(String mcNick) {
        String dcID = getUserByMCNick(mcNick);
        configuration.set(mcNick, "NULL");
        configuration.set(dcID, "NULL");
        try {
            configuration.save(new File(plugin.getDataFolder() + "/links.yml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void link(String mcNick, String dcID) {
        configuration.set(mcNick, dcID);
        configuration.set(dcID, mcNick);
        try {
            configuration.save(new File(plugin.getDataFolder() + "/links.yml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getUserByMCNick(String mcNick){
        String discordID = configuration.getString(mcNick);
        if(discordID==null){
            return null;
        }
        if(discordID.equals("NULL")){
            return null;
        }
        else{
            return discordID;
        }
    }
    public String getUserByDCID(String dcID){
        String mcNick =  configuration.getString(dcID);
        if(mcNick==null){
            return null;
        }
        if(mcNick.equals("NULL")){
            return null;
        }
        else{
            return mcNick;
        }
    }
}
