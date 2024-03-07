package pl.miki.alkoplugin.Discord.Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import pl.miki.alkoplugin.Data.Linker;

import static pl.miki.alkoplugin.AlkoPlugin.playerDiscordIDTemp;
import static pl.miki.alkoplugin.AlkoPlugin.playerTempWhitelist;

public class Connect {
    public void run(SlashCommandInteractionEvent event){
        Linker linker = new Linker();
        OptionMapping mcNick = event.getOption("nick");
        if(mcNick==null){
            String linkedNick = linker.getUserByDCID(event.getUser().getId());
            if(linkedNick==null){
                event.reply("Konto nie jest zlinkowane, podaj parametr nick").queue();
                return;
            }
            playerTempWhitelist.put(linkedNick,0);
            playerDiscordIDTemp.put(linkedNick,event.getUser().getId());
            event.reply("Nawiązanie połączenia dla konta "+linkedNick+" dozwolone przez minutę").setEphemeral(true).queue();

            return;
        }
        if(mcNick!=null){
            String nick = mcNick.getAsString();
            if(nick==null){
                event.reply("Konto nie jest zlinkowane, podaj parametr nick").setEphemeral(true).queue();
                return;
            }
            playerTempWhitelist.put(nick,0);
            playerDiscordIDTemp.put(nick,event.getUser().getId());
            event.reply("Nawiązanie połączenia dla konta "+nick+" dozwolone przez minutę").setEphemeral(true).queue();
        }
        else{
            event.reply("Nie można odczytać nicku").setEphemeral(true).queue();
            return;
        }
    }
}
