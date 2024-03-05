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

public class UnlinkCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Linker linker = new Linker();
            linker.unlinkByMCNick(player.getName());
        } else {
            sender.sendMessage("You must be a player!");
        }
        return false;

    }
}
