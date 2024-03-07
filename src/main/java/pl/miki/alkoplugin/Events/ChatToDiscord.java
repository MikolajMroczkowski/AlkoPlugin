package pl.miki.alkoplugin.Events;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pl.miki.alkoplugin.Data.Configuration;
import pl.miki.alkoplugin.Data.Linker;
import pl.miki.alkoplugin.Data.NickCache;
import pl.miki.alkoplugin.Discord.DiscordWebhook;

import static pl.miki.alkoplugin.AlkoPlugin.plugin;

public class ChatToDiscord implements Listener {
    @EventHandler
    public void onChat(AsyncChatEvent event) {
        Thread sender = new Thread(() -> {
            Configuration config = new Configuration();
            if (config.getDiscordWebhook() == null) {
                plugin.getLogger().warning("Discord webhook is not set");
                return;
            }
            String nick = event.getPlayer().getName();
            Linker linker = new Linker();
            NickCache nc = new NickCache();
            String dcID =  linker.getUserByMCNick(nick);
            if(dcID==null){
                Player player = event.getPlayer();
                player.sendMessage(Component.text("Wiadomość nie dostarczona").color(NamedTextColor.RED).decoration(TextDecoration.BOLD,true));
                player.sendMessage(Component.text("konto nie jest połączone z discordem!").color(NamedTextColor.DARK_RED));
                return;
            }
            String dcNick = nc.getUserNick(dcID);
            String profileIMG = "https://mc-heads.net/avatar/"+nick;
            if(nc.getUserAvatar(dcID)!=null){
                profileIMG = nc.getUserAvatar(dcID);
            }
            String msg = PlainTextComponentSerializer.plainText().serialize(event.message());
            DiscordWebhook webhook = new DiscordWebhook(config.getDiscordWebhook());
            webhook.sendMessage(dcNick+" ("+nick+")",profileIMG, msg);
        });
        sender.start();
    }

}
