package pl.miki.alkoplugin.Commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.miki.alkoplugin.Managers.MoneyManager;


public class TeleportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            int money = MoneyManager.getMoney(player.getName());
            if(money >= 35){
                if(args.length==1){
                    Player target = player.getServer().getPlayer(args[0]);
                    if(target!=null){
                        if(target.getName()==player.getName()){
                            player.sendMessage(
                                    Component.text()
                                            .content("Czy ty właśnie próbujesz złamać prawa fizyki?")
                                            .color(NamedTextColor.GREEN));
                            return true;
                        }
                        player.teleport(target);
                        MoneyManager.removeMoney(player.getName(),35);
                        player.sendMessage(
                                Component.text()
                                        .content("Teleportowałeś się do ")
                                        .color(NamedTextColor.GOLD)
                                        .append(Component.text().content(target.getName()).decoration(TextDecoration.BOLD,true)));
                        target.sendMessage(
                                Component.text()
                                        .content("Gracz ")
                                        .color(NamedTextColor.GOLD)
                                        .append(Component.text().content(player.getName()).decoration(TextDecoration.BOLD,true))
                                        .append(Component.text(" teleportował się do ciebie")));
                        return true;
                    }
                    else{
                        player.sendMessage(
                                Component.text()
                                        .content("Użytkonik nie jest online")
                                        .color(NamedTextColor.RED));
                    }

                }
                else{
                    player.sendMessage(
                            Component.text()
                                    .content("Podaj użytkownika do którego masz zamiar się teleportować")
                                    .color(NamedTextColor.RED));
                }
            }
            else{
                        player.sendMessage(
                                Component.text()
                                        .content("Nie stać cię ")
                                        .color(NamedTextColor.RED)
                                        .append(Component.text().content("BIEDAKU").decoration(TextDecoration.BOLD,true))
                                        .append(Component.text(", zbierz "+(35-money)+" litrów czystej i wróć do mnie!").color(NamedTextColor.RED)));
            }

        } else {
            sender.sendMessage("You must be a player!");
        }
        return false;
    }
}
