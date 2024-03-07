package pl.miki.alkoplugin.Events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import pl.miki.alkoplugin.Data.Configuration;
import pl.miki.alkoplugin.Data.Linker;

import static pl.miki.alkoplugin.AlkoPlugin.*;

public class Protection implements Listener {
    public Protection() {
        BukkitRunnable runable = new BukkitRunnable() {
            @Override
            public void run() {
                for (String user : playerTempWhitelist.keySet()) {
                    playerTempWhitelist.put(user, playerTempWhitelist.get(user) + 5);
                    if (playerTempWhitelist.get(user) > 60) {
                        playerTempWhitelist.remove(user);
                        Linker linker = new Linker();
                        String dcID = linker.getUserByMCNick(user);
                        if (dcID != null) {
                            Configuration config = new Configuration();
                            bot.jda.getGuildById(config.getDiscordGuild()).getMemberById(dcID).getUser().openPrivateChannel().queue((channel) -> {
                                channel.sendMessage("Zgłoszenie wygasło, zgłoś je ponownie aby móc nawiązać połączenie").queue();
                            });
                            playerDiscordIDTemp.remove(user);
                        }
                    }
                }
            }
        };
        runable.runTaskTimerAsynchronously(plugin, 20 * 5, 20 * 5);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (playerTempWhitelist.get(event.getPlayer().getName()) != null) {
            playerTempWhitelist.remove(event.getPlayer().getName());
            bot.jda.getGuildById(new Configuration().getDiscordGuild()).getMemberById(playerDiscordIDTemp.get(event.getPlayer().getName())).getUser().openPrivateChannel().queue((channel) -> {
                channel.sendMessage("**Miłej gry!** Połączenie nawiązane, żeby nawiązać ponowne musisz znowu zgłosić połączenie").queue();
            });
            playerDiscordIDTemp.remove(event.getPlayer().getName());
        } else {
            event.getPlayer().kick(Component.text("").color(NamedTextColor.RED).append(Component.text("Nie zgłosiłeś połączenia").decoration(TextDecoration.BOLD, true)).append(Component.text(", zgłoś je na discordzie! \nużyj /connect lub /connectnolink <Nazwa użytkownika z mc>")));
            Linker linker = new Linker();
            String dcID = linker.getUserByMCNick(event.getPlayer().getName());
            if (dcID != null) {
                Configuration config = new Configuration();
                bot.jda.getGuildById(config.getDiscordGuild()).getMemberById(dcID).getUser().openPrivateChannel().queue((channel) -> {
                    channel.sendMessage("Połączenie nie zgłoszone, zgłoś je za pomocą komendy /connect").queue();
                });
            }
        }

    }
}
