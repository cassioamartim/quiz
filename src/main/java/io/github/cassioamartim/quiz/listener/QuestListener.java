package io.github.cassioamartim.quiz.listener;

import io.github.cassioamartim.quiz.Quiz;
import io.github.cassioamartim.quiz.account.Account;
import io.github.cassioamartim.quiz.game.Game;
import io.github.cassioamartim.quiz.game.quest.Quest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class QuestListener implements Listener {

    @EventHandler
    public void chat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);

        Player player = event.getPlayer();

        Account account = Quiz.getAccountController().get(player.getUniqueId());

        if (account == null) return;

        Game game = Quiz.getGame();

        String message = event.getMessage();

        if (game != null) {
            Quest quest = game.getCurrentQuest();

            if (quest != null && message.equalsIgnoreCase(quest.getResponse())) {
                player.sendMessage("§a§lYOU GOT THE QUESTION RIGHT, CONGRATULATIONS!!");

                Quiz.setGame(null);

                Bukkit.getOnlinePlayers()
                        .forEach(p -> p.sendMessage("§a" + player.getName() + "§f won the §cgame§f!"));

                account.addPoint();

                return;
            }
        }

        Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(player.getName() + ": " + message));
    }
}
