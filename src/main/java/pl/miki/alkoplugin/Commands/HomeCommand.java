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
import pl.miki.alkoplugin.Managers.MoneyManager;

public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            int money = MoneyManager.getMoney(player.getName());
            HomeData hd = new HomeData();
            Location home = hd.checkHomeForPlayer(player);
            if(home!=null){
                if(money >= 40){
                    MoneyManager.removeMoney(player.getName(),40);
                    player.teleport(home);
                    return true;
                }
                else{
                    player.sendMessage(
                            Component.text()
                                    .content("Nie stać cię ")
                                    .color(NamedTextColor.RED)
                                    .append(Component.text().content("BIEDAKU").decoration(TextDecoration.BOLD,true))
                                    .append(Component.text(", zbierz "+(40-money)+" litrów czystej i wróć do mnie!").color(NamedTextColor.RED)));
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
