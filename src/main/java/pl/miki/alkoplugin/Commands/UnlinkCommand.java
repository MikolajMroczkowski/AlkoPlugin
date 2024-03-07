package pl.miki.alkoplugin.Commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.miki.alkoplugin.Data.Configuration;
import pl.miki.alkoplugin.Data.Linker;

import static pl.miki.alkoplugin.AlkoPlugin.bot;

public class UnlinkCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Linker linker = new Linker();
            bot.jda.getGuildById(new Configuration().getDiscordGuild()).getMemberById(linker.getUserByMCNick(player.getName())).getUser().openPrivateChannel().queue((channel) -> {
                channel.sendMessage("Konto minecraft "+player.getName()+" zostało odłączone od twojego konta discord").queue();
            });
            linker.unlinkByMCNick(player.getName());
            player.sendMessage(Component.text("Konto rozłączone poprawnie").color(NamedTextColor.DARK_PURPLE));
            return true;
        } else {
            sender.sendMessage("You must be a player!");
        }
        return false;

    }
}
