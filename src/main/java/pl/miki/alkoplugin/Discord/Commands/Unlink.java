package pl.miki.alkoplugin.Discord.Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import pl.miki.alkoplugin.Data.Linker;

import static pl.miki.alkoplugin.AlkoPlugin.plugin;

public class Unlink {
    public void run(SlashCommandInteractionEvent event){
        Linker linker = new Linker();
        String mcNick = linker.getUserByDCID(event.getUser().getId());
        if(mcNick==null){
            event.reply("Konto nie jest połączone").queue();
            return;
        }
        plugin.getServer().getPlayer(mcNick).sendMessage("Twoje konto discord zostało odłączone od konta minecraft");
        linker.unlinkByDCID(event.getUser().getId());
        event.reply("Konto odłączone").queue();
    }
}
