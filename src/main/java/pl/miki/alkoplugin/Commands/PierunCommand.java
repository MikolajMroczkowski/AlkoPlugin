package pl.miki.alkoplugin.Commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.miki.alkoplugin.Managers.MoneyManager;

public class PierunCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            int money = MoneyManager.getMoney(player.getName());
            if(money >= 200){
                if(args.length==1){
                    Player target = player.getServer().getPlayer(args[0]);
                    if(target!=null){
                        player.getWorld().strikeLightning(target.getLocation());
                        MoneyManager.removeMoney(player.getName(),200);
                        player.sendMessage(
                                Component.text()
                                        .content("Jebłeś piorunem ")
                                        .color(NamedTextColor.GREEN)
                                        .append(Component.text().content(target.getName()).decoration(TextDecoration.BOLD,true)));
                        target.sendMessage(
                                Component.text()
                                        .content("Gracz ")
                                        .color(NamedTextColor.DARK_AQUA)
                                        .append(Component.text().content(player.getName()).decoration(TextDecoration.BOLD,true))
                                        .append(Component.text(" wpierdolił ci piorunem!")));
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
                                    .content("Podaj użytkownika którego chcesz trafić piorunem!")
                                    .color(NamedTextColor.RED));
                }
            }
            else{
                player.sendMessage(
                        Component.text()
                                .content("Nie stać cię ")
                                .color(NamedTextColor.RED)
                                .append(Component.text().content("BIEDAKU").decoration(TextDecoration.BOLD,true))
                                .append(Component.text(", zbierz "+(200-money)+" litrów czystej i wróć do mnie!").color(NamedTextColor.RED)));
            }

        } else {
            sender.sendMessage("You must be a player!");
        }
        return false;
    }
}
