package pl.miki.superPlugin.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            int level =  player.getLevel();
            player.sendMessage(args[0]);
            return true;
            //Player target = player.getServer().getPlayer(strings[0]);
//            if(level >= 5){
//                if(target!=null){
//                    player.teleport(target);
//                    player.setLevel(level-5);
//                    target.sendMessage(ChatColor.GOLD+"Gracz "+player.getName()+" teleportował się do ciebie");
//                    return true;
//                }
//                else{
//                    player.sendMessage(ChatColor.GOLD+"Gracz nie jest online");
//                }
//            }
//            else{
//                player.sendMessage(ChatColor.RED+"Nie stać cię"+ChatColor.BOLD+" BIEDAKU"+ChatColor.RESET+ChatColor.RED+" Zbierz 5 lvl");
//            }

        } else {
            sender.sendMessage("You must be a player!");
            return false;
        }
        //return false;
    }
}
