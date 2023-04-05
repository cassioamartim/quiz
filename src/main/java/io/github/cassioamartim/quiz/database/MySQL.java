package io.github.cassioamartim.quiz.database;

import io.github.cassioamartim.quiz.Quiz;
import io.github.cassioamartim.quiz.account.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.UUID;
import java.util.logging.Level;

@Getter
@RequiredArgsConstructor
public class MySQL {

    private final Quiz quiz;

    private Connection connection;

    public boolean hasConnection() {
        return connection != null;
    }

    public void connect() {
        if (!hasConnection()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");

                quiz.getLogger().info("Successfully connected to MySQL!");
            } catch (SQLException e) {
                quiz.getLogger().log(Level.SEVERE, "Connection to MySQL failed!", e);
            }
        }

        if (hasConnection()) {
            // Create table

            try {
                PreparedStatement statement = getStatement(
                        "CREATE TABLE IF NOT EXISTS accounts (UUID VARCHAR(100), NAME VARCHAR(100), POINTS INT(100))");

                statement.executeUpdate();
            } catch (SQLException e) {
                quiz.getLogger().log(Level.SEVERE, "Table 'accounts' creation failed!", e);
            }

        }
    }

    public void close() {
        try {
            if (hasConnection()) {
                connection.close();
            }

        } catch (SQLException e) {
            quiz.getLogger().log(Level.SEVERE, "Error when trying to close MySQL connection!", e);
        }
    }

    public Account loadAccount(UUID uniqueId, String name) {
        Account account;

        if (hasAccount(uniqueId)) {
            account = new Account(uniqueId, name, getPoints(uniqueId));
        } else {
            account = new Account(uniqueId, name, 0);

            registerAccount(account);
        }

        Quiz.getAccountController().load(account);

        return account;
    }

    public boolean hasAccount(UUID uniqueId) {
        try {
            PreparedStatement statement = getStatement("SELECT * FROM accounts WHERE UUID=?");

            statement.setString(1, uniqueId.toString());

            ResultSet result = statement.executeQuery();

            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void registerAccount(Account account) {
        if (!hasAccount(account.getUniqueId())) {

            try {

                PreparedStatement statement = getStatement("INSERT INTO accounts (UUID, NAME, POINTS) VALUES ('"
                        + account.getUniqueId().toString() + "', '"
                        + account.getName() + "', '"
                        + account.getPoints() + "')");

                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateAccount(Account account, String column, Object value) {
        if (hasAccount(account.getUniqueId())) {

            try {
                PreparedStatement statement = getStatement("UPDATE accounts SET " + column + "='" + value + "' WHERE UUID='" + account.getUniqueId().toString() + "'");

                statement.executeUpdate();

                Quiz.getAccountController().update(account);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getPoints(UUID uniqueId) {
        if (hasAccount(uniqueId)) {

            try {
                ResultSet result = getResult("SELECT * FROM accounts WHERE UUID='" + uniqueId + "'");

                result.next();

                return result.getInt("points");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }

    public PreparedStatement getStatement(String sql) {

        if (hasConnection()) {
            try {
                return connection.prepareStatement(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public ResultSet getResult(String sql) {

        if (hasConnection()) {
            try {
                PreparedStatement statement = this.getStatement(sql);

                return statement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
