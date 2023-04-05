package io.github.cassioamartim.quiz.command;

import io.github.cassioamartim.quiz.Quiz;
import io.github.cassioamartim.quiz.account.Account;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class PointCommand extends Command {

    public PointCommand() {
        super("point");
    }

    @Override
    public boolean execute(CommandSender sender, String lb, String[] args) {

        if (args.length == 0) {
            sender.sendMessage("Usage: /point <player>");
            return false;
        }

        Account account = Quiz.getAccountController().get(args[0]);

        if (account == null) {
            sender.sendMessage("Account not found.");
            return false;
        }

        sender.sendMessage("Account §e" + account.getName() + "§f have §a" + account.getPoints() + "§f points!");

        return true;
    }
}
