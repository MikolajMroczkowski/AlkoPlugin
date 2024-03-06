package pl.miki.alkoplugin.Events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import pl.miki.alkoplugin.Data.MoneyStore;
import pl.miki.alkoplugin.Managers.MainScoreboard;
import pl.miki.alkoplugin.Managers.MoneyManager;

import java.util.HashMap;
import java.util.Map;

import static pl.miki.alkoplugin.AlkoPlugin.plugin;
import static pl.miki.alkoplugin.Managers.MainScoreboard.setScoreboard;

public class PlayerMoney implements Listener {
    Map<String,BukkitRunnable> events = new HashMap();
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        setScoreboard(event.getPlayer());
        MoneyManager.playerConnected(event.getPlayer());
        String playerName = event.getPlayer().getName();
        BukkitRunnable runable = new BukkitRunnable() {
            @Override
            public void run() {
                MoneyManager.addMoney(playerName,20);
                event.getPlayer().sendMessage(Component.text("Dostałeś 20 litrów czystej :D za 10 minut aktywności").color(NamedTextColor.GOLD).decoration(TextDecoration.ITALIC,true));
            }
        };
        runable.runTaskTimerAsynchronously(plugin, 20*60*10, 20*60*10);
        events.put(playerName,runable );
    }
    @EventHandler
    public void onPlayerLeft(PlayerQuitEvent event)
    {
        MoneyManager.playerDisconnected(event.getPlayer());
        BukkitRunnable runable =events.get(event.getPlayer().getName());
        runable.cancel();
    }
}
