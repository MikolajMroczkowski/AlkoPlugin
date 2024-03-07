package pl.miki.alkoplugin.Discord.Listeners;

import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import pl.miki.alkoplugin.Data.Configuration;
import pl.miki.alkoplugin.Data.Linker;
import pl.miki.alkoplugin.Managers.MoneyManager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static pl.miki.alkoplugin.AlkoPlugin.bot;
import static pl.miki.alkoplugin.AlkoPlugin.plugin;

public class MoneyListener extends ListenerAdapter {
    Map<String, Integer> usersTimes = new HashMap();

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        addByMessage( event.getAuthor().getId());
    }

    ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);

    public MoneyListener() {
        ses.scheduleAtFixedRate(() -> {
            for (String user : usersTimes.keySet()) {
                usersTimes.put(user, usersTimes.get(user) + 5);
                if (usersTimes.get(user) > 600) {
                    addByVC(user);
                    usersTimes.put(user, 0);
                }
            }
        }, 5, 5, TimeUnit.SECONDS);
    }


    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        if (event.getChannelJoined() != null) {
            channelJoined(event);
        } else if (event.getChannelLeft() != null) {
            channelLeft(event);
        }


    }

    private void channelJoined(GuildVoiceUpdateEvent event) {
        usersTimes.put(event.getEntity().getId(), 0);
    }

    private void channelLeft(GuildVoiceUpdateEvent event) {
        usersTimes.remove(event.getEntity().getId());
    }

    private void addByMessage(String dcID){
        Linker linker = new Linker();
        String mcName = linker.getUserByDCID(dcID);
        if (mcName == null) {
            return;
        }
        MoneyManager.addMoney(mcName, 1);
    }
    private void addByVC(String dcID) {
        Linker linker = new Linker();
        Configuration c = new Configuration();
        String mcName = linker.getUserByDCID(dcID);
        int money = 4;
        if (bot.jda.getGuildById(c.getDiscordGuild()).getMemberById(dcID).getVoiceState().isMuted()) {
            money = 1;
        }
        else if (bot.jda.getGuildById(c.getDiscordGuild()).getMemberById(dcID).getVoiceState().isSendingVideo()) {
            money = 8;
        }
        if (mcName == null) {
            return;
        }
        Player player = plugin.getServer().getPlayer(mcName);
        if (player != null) {
            player.playSound(player.getLocation(),"minecraft:entity.experience_orb.pickup",1,1);
            player.sendMessage(Component.text("Dostałeś " + money + " litrów czystej za aktywność na discordzie!").color(NamedTextColor.DARK_PURPLE).decoration(TextDecoration.ITALIC, true));
        }
        MoneyManager.addMoney(mcName, money);
    }
}
