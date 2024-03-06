package pl.miki.alkoplugin.Discord.Listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import pl.miki.alkoplugin.Data.Configuration;
import pl.miki.alkoplugin.Data.Linker;

import static pl.miki.alkoplugin.AlkoPlugin.plugin;

public class DiscordToChat extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.isWebhookMessage()) {
            return;
        }
        String id = event.getChannel().getId();
        Configuration config = new Configuration();
        String nick = event.getMember().getEffectiveName();
        String content = event.getMessage().getContentDisplay();
        Linker linker = new Linker();
        String mcNick = "NOT-LINKED";
        String linkerNick = linker.getUserByDCID(event.getAuthor().getId());
        mcNick = linkerNick;
        if (config.getDiscordChannel().equals(id)) {
            if (linkerNick == null) {
                event.getMessage().delete().queue();
                event.getAuthor().openPrivateChannel().queue(channel -> {
                    channel.sendMessage("Twoje konto discord nie jest połączone z kontem minecraft. Użyj komendy /link <Nazwa konta minecraft> na serwerze discord aby połączyć konto lub /link <nazwa konta discord> na serwerze mc").queue();
                });
                return;
            }
            plugin.getServer().sendMessage(Component.text("")
                    .append(Component.text("[DC] ").color(NamedTextColor.BLUE).decoration(TextDecoration.BOLD, true))
                    .append(Component.text(nick).color(NamedTextColor.DARK_PURPLE).decoration(TextDecoration.BOLD, true))
                    .append(Component.text(" [" + mcNick + "]").color(NamedTextColor.DARK_PURPLE).decoration(TextDecoration.BOLD, true))
                    .append(Component.text(": ").color(NamedTextColor.DARK_PURPLE).decoration(TextDecoration.BOLD, true))
                    .append(Component.text(content).color(NamedTextColor.LIGHT_PURPLE)));
            super.onMessageReceived(event);
        }

    }
}
