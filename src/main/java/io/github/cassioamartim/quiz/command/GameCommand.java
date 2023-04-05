package io.github.cassioamartim.quiz.command;

import io.github.cassioamartim.quiz.Quiz;
import io.github.cassioamartim.quiz.game.Game;
import io.github.cassioamartim.quiz.game.GameType;
import io.github.cassioamartim.quiz.game.quest.Quest;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.Random;

public class GameCommand extends Command {

    public GameCommand() {
        super("game");
    }

    @Override
    public boolean execute(CommandSender sender, String lb, String[] args) {

        if (Quiz.getGame() != null) {
            sender.sendMessage("§cThere is already a game in progress!");
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage("Usage: /game <type>");
            return false;
        }

        GameType type = Arrays.stream(GameType.values()).filter(t -> t.getName().equalsIgnoreCase(args[0])).findFirst().orElse(null);

        if (type == null) {
            sender.sendMessage("§cGame type not found!");
            return false;
        }

        Game game = new Game(type);

        Quest quest = game.getType().getQuests().get(new Random().nextInt(game.getType().getQuests().size()));

        game.setCurrentQuest(quest);

        Bukkit.getOnlinePlayers().forEach(player ->
                player.sendMessage(new String[]{
                        " ",
                        " §6§lGAME §f- §e§l" + game.getName().toUpperCase(),
                        "",
                        "§8* §fQuestion: §a" + quest.getQuestion(),
                        " ",
                }));

        Quiz.setGame(game);

        return true;
    }
}
