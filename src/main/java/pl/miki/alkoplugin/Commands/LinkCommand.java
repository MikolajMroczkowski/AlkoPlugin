package pl.miki.alkoplugin.Commands;

import net.dv8tion.jda.api.entities.Guild;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.miki.alkoplugin.Data.Configuration;
import pl.miki.alkoplugin.Data.HomeData;
import pl.miki.alkoplugin.Data.Linker;

import java.io.File;

import static pl.miki.alkoplugin.AlkoPlugin.bot;
import static pl.miki.alkoplugin.AlkoPlugin.plugin;

public class LinkCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                String discordName = args[0];
                Configuration config = new Configuration();
                String guildID = config.getDiscordGuild();
                if (guildID != null && bot != null) {
                    Guild g = bot.jda.getGuildById(guildID);
                    if (g != null) {
                        g.getMembersByName(discordName, true).forEach(member -> {
                            if (member.getUser().getName().equals(discordName)) {
                                String dcID = member.getId();
                                Linker linker = new Linker();
                                linker.link(player.getName(), dcID);
                            }
                        });

                    } else {
                        plugin.getLogger().info("Guild is null");
                    }


                }

            } else {
                player.sendMessage(
                        Component.text()
                                .content("Podaj konto które chcesz połączyć")
                                .color(NamedTextColor.RED));
            }


        } else {
            sender.sendMessage("You must be a player!");
        }
        return false;

    }
}
