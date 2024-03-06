package pl.miki.alkoplugin.Discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import pl.miki.alkoplugin.Discord.Listeners.CommandsListener;
import pl.miki.alkoplugin.Discord.Listeners.DiscordToChat;
import pl.miki.alkoplugin.Discord.Listeners.MoneyListener;

public class Bot {
    public JDA jda;
    public Bot(String token){
        JDABuilder builder =  JDABuilder.create(token,GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS,GatewayIntent.GUILD_VOICE_STATES);
        builder.setActivity(Activity.playing("Minecraft"));
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.disableCache(CacheFlag.ACTIVITY, CacheFlag.EMOJI, CacheFlag.STICKER, CacheFlag.CLIENT_STATUS, CacheFlag.ONLINE_STATUS, CacheFlag.SCHEDULED_EVENTS);
        builder.addEventListeners(new CommandsListener());
        builder.addEventListeners(new DiscordToChat());
        builder.addEventListeners(new MoneyListener());
        try {
            jda = builder.build();
            registerCommands();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void registerCommands(){
        jda.updateCommands().addCommands(
                Commands.slash("unlink", "Unlink minecraft account from discord"),
                Commands.slash("link", "Link your minecraft account with discord")
                        .setGuildOnly(true)
                        .addOption(OptionType.STRING, "nick", "Nick minecrafta do połączenia z kontem",true,true)

        ).queue();
    }


}
