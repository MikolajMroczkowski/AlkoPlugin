package pl.miki.alkoplugin.Events;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pl.miki.alkoplugin.Data.Configuration;
import pl.miki.alkoplugin.Data.Linker;
import pl.miki.alkoplugin.Data.NickCache;

import static pl.miki.alkoplugin.AlkoPlugin.bot;
import static pl.miki.alkoplugin.AlkoPlugin.plugin;

public class BeautyChat implements Listener {
    @EventHandler
    public void onChat(AsyncChatEvent event) {
        event.renderer((source, sourceDisplayName, message, viewer) -> {
            if(source.getPlayer() != null) {
                Linker linker = new Linker();
                NickCache nc = new NickCache();
                Configuration config = new Configuration();
                String dcID = linker.getUserByMCNick(source.getPlayer().getName());
                if(dcID==null){
                    return Component.text("");
                }
                String dcNick = nc.getUserNick(dcID);
                String guildID = config.getDiscordGuild();
                return Component.text("")
                        .append(Component.text("[MC] ").color(NamedTextColor.DARK_BLUE).decoration(TextDecoration.BOLD, true))
                        .append(Component.text(dcNick).color(NamedTextColor.DARK_AQUA).decoration(TextDecoration.BOLD, true))
                        .append(Component.text().append(Component.text(" [")).append(sourceDisplayName).append(Component.text("]")).color(NamedTextColor.DARK_AQUA))
                        .append(Component.text(": ").color(NamedTextColor.DARK_AQUA).decoration(TextDecoration.BOLD, false))
                        .append(message.color(NamedTextColor.AQUA));
            }
            else {
                return message;
            }
        });

    }
}
