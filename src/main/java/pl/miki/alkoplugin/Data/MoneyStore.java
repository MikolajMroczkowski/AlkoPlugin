package pl.miki.alkoplugin.Data;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

import static pl.miki.alkoplugin.AlkoPlugin.plugin;

public class MoneyStore {
    final FileConfiguration configuration;
    public MoneyStore() {
        File homesYml = new File(plugin.getDataFolder()+"/money.yml");
        configuration = YamlConfiguration.loadConfiguration(homesYml);
    }
    public void initiateConfig(){
        try {
            configuration.save(new File(plugin.getDataFolder()+"/money.yml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getMoney(String mcNick) {
        return configuration.getInt(mcNick);
    }
    public void addMoney(String mcNick, int money) {
        configuration.set(mcNick, getMoney(mcNick)+money);
        try {
            configuration.save(new File(plugin.getDataFolder()+"/money.yml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setMoney(String mcNick, int money) {
        configuration.set(mcNick,money);
        try {
            configuration.save(new File(plugin.getDataFolder()+"/money.yml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
