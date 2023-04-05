package io.github.cassioamartim.quiz.listener;

import io.github.cassioamartim.quiz.Quiz;
import io.github.cassioamartim.quiz.account.Account;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class AccountListener implements Listener {

    @EventHandler
    public void login(AsyncPlayerPreLoginEvent event) {
        UUID uniqueId = event.getUniqueId();
        String name = event.getName();

        Account account = Quiz.getMySQL().loadAccount(uniqueId, name);

        if (account == null) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "§cAccount load failed!");
        }
    }

    @EventHandler
    public void join(PlayerJoinEvent event) {
        event.setJoinMessage(null);

        Player player = event.getPlayer();

        player.sendMessage("§ayo!");
    }

    @EventHandler
    public void quit(PlayerQuitEvent event) {
        event.setQuitMessage(null);

        Quiz.getAccountController().remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void kick(PlayerKickEvent event) {
        event.setLeaveMessage(null);

        Quiz.getAccountController().remove(event.getPlayer().getUniqueId());
    }
}
