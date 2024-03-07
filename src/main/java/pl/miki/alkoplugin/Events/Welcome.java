package pl.miki.alkoplugin.Events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Welcome implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        event.getPlayer().sendMessage(Component.text("Witaj na alko-serwerze!").color(NamedTextColor.GREEN).decoration(TextDecoration.BOLD,true));
        event.joinMessage(Component.text("Witamy ").color(NamedTextColor.DARK_GREEN).append(Component.text(event.getPlayer().getName()).color(NamedTextColor.GREEN).decoration(TextDecoration.BOLD,true)).append(Component.text(" na serwerze!").color(NamedTextColor.DARK_GREEN)));
    }
    public void onPlayerLeft(PlayerQuitEvent event)
    {
        event.quitMessage(Component.text("Pożegnajmy ").color(NamedTextColor.DARK_RED).append(Component.text(event.getPlayer().getName()).color(NamedTextColor.RED).decoration(TextDecoration.BOLD,true)).append(Component.text("!").color(NamedTextColor.DARK_RED)));
    }

}
