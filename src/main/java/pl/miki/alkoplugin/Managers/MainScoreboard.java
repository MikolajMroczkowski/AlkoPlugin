package pl.miki.alkoplugin.Managers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.w3c.dom.Text;

public class MainScoreboard {
    public static void setScoreboard(Player player) {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.getObjective(player.getName());
        if(objective==null){
            objective = scoreboard.registerNewObjective(player.getName(), Criteria.DUMMY, Component.text("Alko-server").decoration(TextDecoration.BOLD,true).color(NamedTextColor.GOLD));
        }
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        // Example scores
        objective.getScore("money").customName(Component.text("Litry czystej").decoration(TextDecoration.BOLD,true).color(NamedTextColor.DARK_GREEN));
        objective.getScore("money").setScore(0);

        player.setScoreboard(scoreboard);
    }

    public static void updateScore(Player player, String scoreName, int scoreValue) {
        Scoreboard scoreboard = player.getScoreboard();
        Objective objective = scoreboard.getObjective(player.getName());
        objective.getScore(scoreName).setScore(scoreValue);
    }
}
