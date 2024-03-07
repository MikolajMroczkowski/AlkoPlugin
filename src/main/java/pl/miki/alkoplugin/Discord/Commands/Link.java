package pl.miki.alkoplugin.Discord.Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import pl.miki.alkoplugin.Data.Linker;
import pl.miki.alkoplugin.Data.NickCache;

import java.util.List;
import java.util.stream.Collectors;

import static pl.miki.alkoplugin.AlkoPlugin.plugin;

public class Link {
    public void run(SlashCommandInteractionEvent event){
        Linker linker = new Linker();
        String mcNick = linker.getUserByDCID(event.getUser().getId());
        OptionMapping nickOption = event.getOption("nick");
        if(nickOption==null){
            event.reply("Podaj nick").setEphemeral(true).queue();
            return;
        }
        String nick = nickOption.getAsString();
        if(nick==mcNick||mcNick!=null){
            event.reply("Konto jest już połączone, użyj komendy unlink").setEphemeral(true).queue();
            return;
        }
        if(nick!=null){
            List<String> playersNicks =  plugin.getServer().getOnlinePlayers().stream().map(player -> player.getName()).collect(Collectors.toList());
            if(playersNicks.contains(nick)){
                Player player = plugin.getServer().getPlayer(nick);
                if(player!=null){
                    linker.link(nick,event.getUser().getId());
                    NickCache nc = new NickCache();
                    nc.fetchNickIntoCache(event.getUser().getId());
                    player.sendMessage(Component.text("Powiązano konto discord "+event.getUser().getName()+" z twoim kontem minecraft").color(NamedTextColor.DARK_PURPLE));
                    event.reply("Powiązano konto minecraft "+nick+" do konta discord "+event.getUser().getEffectiveName()).setEphemeral(true).queue();
                    return;
                }
                else{
                    event.reply("Nie znaleziono gracza. Upewnij się że jesteś online w minecraft i spróbuj ponownie").setEphemeral(true).queue();
                    return;
                }
            }
            else{
                event.reply("Nie znaleziono gracza. Upewnij się że jesteś online w minecraft i spróbuj ponownie").setEphemeral(true).queue();
                return;
            }
        }
        else{
            event.reply("Nie można odczytać nicku").setEphemeral(true).queue();
            return;
        }

    }
}
