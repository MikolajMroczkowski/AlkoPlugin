package pl.miki.superPlugin.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.miki.superPlugin.Data.HomeData;

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
                    player.sendMessage(ChatColor.RED+"Nie stać cię"+ChatColor.BOLD+" BIEDAKU"+ChatColor.RESET+ChatColor.RED+" Zbierz 3 lvl");
                }
            }
            else{
                player.sendMessage(ChatColor.RED+"Nie masz domu :c");
            }
        } else {
            sender.sendMessage("You must be a player!");
            return false;
        }
        return false;
    }
}
