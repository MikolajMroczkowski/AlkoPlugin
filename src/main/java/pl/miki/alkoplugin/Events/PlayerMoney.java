package pl.miki.alkoplugin.Events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import pl.miki.alkoplugin.Managers.MoneyManager;

import java.util.HashMap;
import java.util.Map;

import static pl.miki.alkoplugin.AlkoPlugin.plugin;
import static pl.miki.alkoplugin.Managers.MainScoreboard.setScoreboard;

public class PlayerMoney implements Listener {
    Map<String,Integer> usersMoney = new HashMap();
    public PlayerMoney()
    {
        BukkitRunnable runable = new BukkitRunnable() {
            @Override
            public void run() {
                for (String user : usersMoney.keySet()) {
                    usersMoney.put(user, usersMoney.get(user) + 5);
                    if (usersMoney.get(user) > 600) {
                        MoneyManager.addMoney(user,15);
                        Player p = plugin.getServer().getPlayer(user);
                        if(p!=null){
                            p.playSound(p.getLocation(),"minecraft:entity.experience_orb.pickup",1,1);
                            p.sendMessage(Component.text("Otrzymałeś 15 litrów czystej za bycie online").color(NamedTextColor.GREEN).decoration(TextDecoration.ITALIC,true));
                        }
                        usersMoney.put(user, 0);
                    }
                }
            }
        };
        runable.runTaskTimerAsynchronously(plugin, 20*5, 20*5);
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        setScoreboard(event.getPlayer());
        MoneyManager.playerConnected(event.getPlayer());
        usersMoney.put(event.getPlayer().getName(),0);

    }
    @EventHandler
    public void onPlayerLeft(PlayerQuitEvent event)
    {
        MoneyManager.playerDisconnected(event.getPlayer());
        usersMoney.remove(event.getPlayer().getName());
    }
    @EventHandler
    public void onDeth(PlayerDeathEvent event) {
        int money = MoneyManager.getMoney(event.getEntity().getName());
        int moneyLost = (money/10)*2;
        MoneyManager.removeMoney(event.getEntity().getName(),moneyLost);
    }
}
