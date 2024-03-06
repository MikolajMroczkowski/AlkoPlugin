package pl.miki.alkoplugin.Managers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import pl.miki.alkoplugin.Data.MoneyStore;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import pl.miki.alkoplugin.Managers.MainScoreboard;

import static pl.miki.alkoplugin.AlkoPlugin.plugin;

public class MoneyManager {

    public static void addMoney(String playerName, int money) {
        int currentMoney = getMoney(playerName);
        setMoney(playerName, currentMoney + money);
    }

    public static void removeMoney(String playerName, int money) {
        int currentMoney = getMoney(playerName);
        setMoney(playerName, currentMoney - money);
    }

    public static void setMoney(String playerName, int money) {
        Player p = plugin.getServer().getPlayer(playerName);
        if(p!=null){
            p.getScoreboard().getObjective(playerName).getScore("money").setScore(money);
            MainScoreboard.updateScore(p,"money",money);
        }
        else{
            MoneyStore moneyStore = new MoneyStore();
            moneyStore.setMoney(playerName, money);
        }
    }
    public static void playerDisconnected(Player player){
        MoneyStore moneyStore = new MoneyStore();
        int money = getMoney(player.getName());
        moneyStore.setMoney(player.getName(),money);
    }
    public static void playerConnected(Player player){
        MoneyStore moneyStore = new MoneyStore();
        int money = moneyStore.getMoney(player.getName());
        player.getScoreboard().getObjective(player.getName()).getScore("money").setScore(money);
    }
    public static int getMoney(String playerName) {
        Player p = plugin.getServer().getPlayer(playerName);
        if (p != null) {
            return p.getScoreboard().getObjective(playerName).getScore("money").getScore();
        }
        MoneyStore moneyStore = new MoneyStore();
        return moneyStore.getMoney(playerName);
    }


}

