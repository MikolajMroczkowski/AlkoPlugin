package pl.miki.alkoplugin.Events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.miki.alkoplugin.Data.Linker;
import pl.miki.alkoplugin.Data.NickCache;

public class LinkInformation implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Linker linker = new Linker();
        String dcID = linker.getUserByMCNick(event.getPlayer().getName());
        NickCache cache = new NickCache();
        cache.reFetchCache();
        event.getPlayer().sendMessage(Component.text("Witaj na alko-serwerze!").color(NamedTextColor.GREEN).decoration(TextDecoration.BOLD,true));
        if(dcID==null){
            event.getPlayer().sendMessage(Component.text("Aby połączyć konto z Discordem, Wejdź na serwer discord i Wpisz /link <Twój nick Minecraft>").color(NamedTextColor.DARK_GREEN));
            event.getPlayer().sendMessage(Component.text("lub użyj komendy /link <Twój nick discord> bezpośrednio na chacie").color(NamedTextColor.DARK_GREEN));
            //event.getPlayer().sendMessage(Component.text("Połącz konto discord i zbieraj XP za aktywaność na serwerze discord").color(NamedTextColor.DARK_GREEN));
        }
    }

}
