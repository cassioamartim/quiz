package io.github.cassioamartim.quiz.game;

import io.github.cassioamartim.quiz.game.quest.Quest;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

@Getter
public class Game {

    private final String name;

    private final GameType type;

    private final Set<Player> players;

    @Setter
    private Quest currentQuest;

    public Game(GameType type) {
        name = type.getName();

        this.type = type;

        players = new HashSet<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }
}
