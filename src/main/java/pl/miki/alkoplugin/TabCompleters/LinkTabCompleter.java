package pl.miki.alkoplugin.TabCompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import pl.miki.alkoplugin.Data.Configuration;

import java.util.List;

import static pl.miki.alkoplugin.AlkoPlugin.bot;

public class LinkTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String CommandLabel, String[] args) {
        if(args.length == 1) {
            Configuration config = new Configuration();
            List<String> playersNicks = bot.jda.getGuildById(config.getDiscordGuild()).getMembers().stream().map(member -> member.getUser().getName()).toList();
            return playersNicks;
        }
        // TODO Auto-generated method stub
        return null;
    }
}
