package pl.miki.alkoplugin.Commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.miki.alkoplugin.Managers.MoneyManager;

public class TopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            int money = MoneyManager.getMoney(player.getName());
            if (money >= 10) {
                Location playerLoc = player.getLocation();
                Block safeBlock = player.getWorld().getHighestBlockAt(playerLoc);
                Location safeLocation = safeBlock.getLocation().add(0,1,0);
                if(playerLoc.getBlockY()==safeLocation.getBlockY()){
                    player.sendMessage(Component.text()
                            .content("Już jesteś TOPem!")
                            .color(NamedTextColor.GREEN));
                }
                else{
                    player.teleport(safeLocation.add(0.5,0,0.5));
                    MoneyManager.removeMoney(player.getName(),10);
                }

                return true;

            } else {
                player.sendMessage(
                        Component.text()
                                .content("Nie stać cię ")
                                .color(NamedTextColor.RED)
                                .append(Component.text().content("BIEDAKU").decoration(TextDecoration.BOLD,true))
                                .append(Component.text(", zbierz jeszcze "+(10-money)+" litrów czystej i wróć do mnie!").color(NamedTextColor.RED)));
            }
        } else {
            sender.sendMessage("You must be a player!");
        }
        return false;
    }
}
