package pl.miki.alkoplugin.Discord.Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import pl.miki.alkoplugin.Data.Linker;

import static pl.miki.alkoplugin.AlkoPlugin.plugin;

public class Unlink {
    public void run(SlashCommandInteractionEvent event){
        Linker linker = new Linker();
        String mcNick = linker.getUserByDCID(event.getUser().getId());
        if(mcNick==null){
            event.reply("Konto nie jest połączone").setEphemeral(true).queue();
            return;
        }
        Player player = plugin.getServer().getPlayer(mcNick);
        if(player!=null){
            player.sendMessage(Component.text("Konto discord "+event.getUser().getName()+" zostało odłączone od twojego konta minecraft").color(NamedTextColor.DARK_PURPLE));
        }
        linker.unlinkByDCID(event.getUser().getId());
        event.reply("Konto odłączone").setEphemeral(true).queue();
    }
}
