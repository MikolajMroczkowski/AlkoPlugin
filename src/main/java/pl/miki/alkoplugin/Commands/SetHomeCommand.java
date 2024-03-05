package pl.miki.alkoplugin.Commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.miki.alkoplugin.Data.HomeData;
public class SetHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            int level =  player.getLevel();
            if(level >= 7){
                player.setLevel(level-7);
                HomeData hd = new HomeData();
                hd.setPlayerHome(player);
                player.sendMessage(Component.text()
                        .content("Wiem gdzie mieszkasz :D")
                        .color(NamedTextColor.GREEN));
            }
            else{
                player.sendMessage(
                        Component.text()
                                .content("Nie stać cię ")
                                .color(NamedTextColor.RED)
                                .append(Component.text().content("BIEDAKU").decoration(TextDecoration.BOLD,true))
                                .append(Component.text(", zbierz 7 lvl")));
            }

        } else {
            sender.sendMessage("You must be a player!");
            return false;
        }
        return false;
    }
}
