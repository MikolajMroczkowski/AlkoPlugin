package pl.miki.alkoplugin.Events;

import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import pl.miki.alkoplugin.Data.Configuration;
import pl.miki.alkoplugin.Functions.DiscordWebhook;

import static pl.miki.alkoplugin.AlkoPlugin.plugin;

public class ChatToDiscord implements Listener{
    @EventHandler
    public void onChat(AsyncChatEvent event) {
        Thread sender = new Thread(()->{
            Configuration config = new Configuration();
            if(config.getDiscordWebhook()==null){
                plugin.getLogger().warning("Discord webhook is not set");
                return;
            }
            String msg = PlainTextComponentSerializer.plainText().serialize(event.message());
            DiscordWebhook webhook = new DiscordWebhook(config.getDiscordWebhook());
            webhook.sendMessage(event.getPlayer().getName(), msg);
        });
        sender.start();

    }

}
