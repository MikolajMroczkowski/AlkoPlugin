package pl.miki.alkoplugin.Events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import pl.miki.alkoplugin.Managers.MoneyManager;

import java.util.HashMap;
import java.util.Map;

import static pl.miki.alkoplugin.AlkoPlugin.plugin;

public class AfkEvent implements Listener {
    Map<String, Location> usersPossition = new HashMap();
    Map<String, Integer> usersTimer = new HashMap();
    public AfkEvent()
    {
        BukkitRunnable runable = new BukkitRunnable() {
            @Override
            public void run() {
                for (String user : usersPossition.keySet()) {
                    Location locaion =  plugin.getServer().getPlayer(user).getLocation();
                    if(locaion!=null){
                        Player player = plugin.getServer().getPlayer(user);
                        if(usersPossition.get(user).getBlock().getLocation().getX() == locaion.getBlock().getLocation().getX()&&usersPossition.get(user).getBlock().getLocation().getZ() == locaion.getBlock().getLocation().getZ()&&usersPossition.get(user).getBlock().getLocation().getY() == locaion.getBlock().getLocation().getY()){
                            usersTimer.put(user, usersTimer.get(user) + 5);
                        }
                        else{
                            if(usersTimer.get(user)>100){
                                player.sendMessage(Component.text("Dziękujemy za ruszenie dupy :D").color(NamedTextColor.GREEN).decoration(TextDecoration.BOLD,true));
                            }
                            usersTimer.put(user, 0);
                            usersPossition.put(user,locaion);
                        }
                        if (usersTimer.get(user) == 120) {
                            player.playSound(player.getLocation(),"minecraft:block.anvil.land",1,1);
                            player.sendMessage(Component.text("Za 30 sekund zostaniesz zlikwidowany za afk").color(NamedTextColor.RED).decoration(TextDecoration.BOLD,true));
                        }
                        if (usersTimer.get(user) >= 180) {
                            player.kick(Component.text("Zostałeś wyrzucony za afk, trzeba było ruszyć dupe :3").color(NamedTextColor.RED).decoration(TextDecoration.BOLD,true));
                        }


                    }

                }
            }
        };
        runable.runTaskTimer(plugin, 20*5, 20*5);
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        MoneyManager.playerConnected(event.getPlayer());
        usersPossition.put(event.getPlayer().getName(),event.getPlayer().getLocation());
        usersTimer.put(event.getPlayer().getName(),0);

    }
    @EventHandler
    public void onPlayerLeft(PlayerQuitEvent event)
    {
        MoneyManager.playerDisconnected(event.getPlayer());
        usersTimer.remove(event.getPlayer().getName());
        usersPossition.remove(event.getPlayer().getName());
    }
}
