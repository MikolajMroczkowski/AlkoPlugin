package pl.miki.superPlugin.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.miki.superPlugin.Data.HomeData;
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
                player.sendMessage( ChatColor.GREEN+"Wiem gdzie mieszkasz");
            }
            else{
                player.sendMessage(ChatColor.RED+"Nie stać cię"+ChatColor.BOLD+" BIEDAKU"+ChatColor.RESET+ChatColor.RED+" Zbierz 7 lvl");
            }

        } else {
            sender.sendMessage("You must be a player!");
            return false;
        }
        return false;
    }
}
