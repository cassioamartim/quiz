package io.github.cassioamartim.quiz;

import io.github.cassioamartim.quiz.command.GameCommand;
import io.github.cassioamartim.quiz.command.PointCommand;
import io.github.cassioamartim.quiz.controller.AccountController;
import io.github.cassioamartim.quiz.database.MySQL;
import io.github.cassioamartim.quiz.game.Game;
import io.github.cassioamartim.quiz.listener.AccountListener;
import io.github.cassioamartim.quiz.listener.QuestListener;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

public class Quiz extends JavaPlugin {

    @Getter
    private static MySQL mySQL;

    @Getter
    private static AccountController accountController;

    @Getter
    @Setter
    private static Game game;

    @Override
    public void onLoad() {
        mySQL = new MySQL(this);

        accountController = new AccountController();
    }

    @Override
    public void onEnable() {
        mySQL.connect();

        getServer().getCommandMap().register("game", new GameCommand());
        getServer().getCommandMap().register("point", new PointCommand());

        getServer().getPluginManager().registerEvents(new AccountListener(), this);
        getServer().getPluginManager().registerEvents(new QuestListener(), this);
    }

    @Override
    public void onDisable() {
        mySQL.close();
    }
}
