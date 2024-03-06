package pl.miki.alkoplugin.Discord.Listeners;

import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static pl.miki.alkoplugin.AlkoPlugin.plugin;

import pl.miki.alkoplugin.Data.Linker;
import pl.miki.alkoplugin.Data.MoneyStore;
import pl.miki.alkoplugin.Managers.MoneyManager;
public class MoneyListener extends ListenerAdapter {
    Map<String,ScheduledExecutorService> timers = new HashMap();
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        addMoney(1,event.getAuthor().getId());
    }

    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent  event){
        plugin.getLogger().info("Runned event");
        if(event.getChannelJoined() != null){
            channelJoined(event);
        }
        else if(event.getChannelLeft() != null){
            channelLeft(event);
        }


    }
    private void channelJoined(GuildVoiceUpdateEvent event){
        timers.put(event.getEntity().getId(),Executors.newScheduledThreadPool(1));
        Runnable task = () -> {
            addMoney(10, event.getEntity().getId());
        };
        timers.get(event.getEntity().getId()).scheduleAtFixedRate(task, 60*10, 60*10, TimeUnit.SECONDS);

    }
    private void channelLeft(GuildVoiceUpdateEvent event){
        if(timers.get(event.getEntity().getId()) == null){
            return;
        }
        timers.get(event.getEntity().getId()).shutdown();
    }
    private void addMoney(int money, String dcID){
        Linker linker = new Linker();
        String mcName = linker.getUserByDCID(dcID);
        if(mcName == null){
            return;
        }
        MoneyManager.addMoney(mcName,money);
    }
}
