package pl.miki.alkoplugin.Discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import pl.miki.alkoplugin.Discord.Listeners.CommandsListener;
import pl.miki.alkoplugin.Discord.Listeners.DiscordToChat;

public class Bot {
    public JDA jda;
    public Bot(String token){
        JDABuilder builder =  JDABuilder.createLight(token,GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS);
        builder.setActivity(Activity.playing("Minecraft"));
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.addEventListeners(new CommandsListener());
        builder.addEventListeners(new DiscordToChat());
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
