package pl.miki.alkoplugin.Discord.Listeners;

import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import pl.miki.alkoplugin.Discord.Commands.Connect;
import pl.miki.alkoplugin.Discord.Commands.Link;
import pl.miki.alkoplugin.Discord.Commands.Unlink;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pl.miki.alkoplugin.AlkoPlugin.plugin;
//import pl.miki.alkoplugin.Discord.Commands.*;

public class CommandsListener extends ListenerAdapter {


    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        // make sure we handle the right command
        switch (event.getName()) {
            case "link":
                new Link().run(event);
                break;
            case "unlink":
                new Unlink().run(event);
                break;
            case "connect":
                new Connect().run(event);
                break;
            default:
                System.out.printf("Unknown command %s used by %#s%n", event.getName(), event.getUser());
        }
    }

    @Override
    public void onCommandAutoCompleteInteraction(CommandAutoCompleteInteractionEvent event) {
        String[] playersNicks = plugin.getServer().getOnlinePlayers().stream().map(player -> player.getName()).toArray(String[]::new);
        if (event.getName().equals("link") && event.getFocusedOption().getName().equals("nick")) {
            List<Command.Choice> options = Stream.of(playersNicks)
                    .filter(playersNick -> playersNick.startsWith(event.getFocusedOption().getValue())) // only display words that start with the user's current input
                    .map(playersNick -> new Command.Choice(playersNick, playersNick)) // map the words to choices
                    .collect(Collectors.toList());
            event.replyChoices(options).queue();
        }
    }
}
