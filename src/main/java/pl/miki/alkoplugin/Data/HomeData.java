package pl.miki.superPlugin.Data;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static pl.miki.superPlugin.MainPlugin.plugin;

public class HomeData {

    final FileConfiguration homes;
    public HomeData() {
        File homesYml = new File(plugin.getDataFolder()+"/homes.yml");
        homes = YamlConfiguration.loadConfiguration(homesYml);
    }
    public Location checkHomeForPlayer(Player p){
        UUID playerUUID = p.getUniqueId();
        ConfigurationSection playerSection = homes.getConfigurationSection(playerUUID.toString());
        if(playerSection != null){
            double x = playerSection.getDouble("X");
            double y = playerSection.getDouble("Y");
            double z = playerSection.getDouble("Z");
            Location home = new Location(p.getWorld(),x,y,z);
            return home;
        }
        else{
            return null;
        }

    }
    public void setPlayerHome(Player p) {
        UUID playerUUID = p.getUniqueId();
        ConfigurationSection playerSection = homes.getConfigurationSection(playerUUID.toString());
        if(playerSection==null){
            playerSection = homes.createSection(playerUUID.toString());
        }
        Location playerLocation = p.getLocation();
        double x = playerLocation.getX();
        double y = playerLocation.getY();
        double z = playerLocation.getZ();
        playerSection.set("X",x);
        playerSection.set("Y",y);
        playerSection.set("Z",z);
        File homesYml = new File(plugin.getDataFolder()+"/homes.yml");
        try {
            homes.save(homesYml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        //new HomeData(homeLo, previouslyOnlinePlayers).saveData("Tutorial.data");
        //Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
}
