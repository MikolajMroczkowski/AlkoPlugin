package pl.miki.alkoplugin.Commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.miki.alkoplugin.Data.HomeData;

public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            int level =  player.getLevel();
            HomeData hd = new HomeData();
            Location home = hd.checkHomeForPlayer(player);
            if(home!=null){
                if(level >= 3){
                    player.setLevel(level-3);
                    player.teleport(home);
                }
                else{
                    player.sendMessage(
                            Component.text()
                                    .content("Nie stać cię ")
                                    .color(NamedTextColor.RED)
                                    .append(Component.text().content("BIEDAKU").decoration(TextDecoration.BOLD,true))
                                    .append(Component.text(", zbierz 3 lvl")));
                }
            }
            else{
                player.sendMessage(Component.text()
                        .content("Nie masz domu :c")
                        .color(NamedTextColor.RED));
            }
        } else {
            sender.sendMessage("You must be a player!");
            return false;
        }
        return false;
    }
}
