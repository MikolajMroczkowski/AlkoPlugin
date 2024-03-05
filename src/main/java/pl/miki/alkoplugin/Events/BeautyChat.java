package pl.miki.alkoplugin.Events;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pl.miki.alkoplugin.Data.Configuration;
import pl.miki.alkoplugin.Functions.DiscordWebhook;

import static pl.miki.alkoplugin.AlkoPlugin.plugin;

public class BeautyChat implements Listener {
    @EventHandler
    public void onChat(AsyncChatEvent event) {
        event.renderer((source, sourceDisplayName, message, viewer) -> Component.text("").append(sourceDisplayName.color(NamedTextColor.BLUE).decoration(TextDecoration.BOLD,true))
                .append(Component.text(": ").color(NamedTextColor.BLUE).decoration(TextDecoration.BOLD,false))
                .append(message.color(NamedTextColor.AQUA)));

    }
}
