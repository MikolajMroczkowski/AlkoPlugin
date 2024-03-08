package pl.miki.alkoplugin.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static pl.miki.alkoplugin.Managers.ShopManager.shopInventory;

public class ShopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.openInventory(shopInventory);
            return true;
        } else {
            sender.sendMessage("You must be a player!");
        }
        return false;

    }
}