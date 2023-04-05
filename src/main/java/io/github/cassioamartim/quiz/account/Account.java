package io.github.cassioamartim.quiz.account;

import io.github.cassioamartim.quiz.Quiz;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Account {

    private final UUID uniqueId;

    private final String name;

    private int points;

    public Account(UUID uniqueId, String name, int points) {
        this.uniqueId = uniqueId;
        this.name = name;

        this.points = points;
    }

    public void addPoint() {
        setPoints(getPoints() + 1);
    }

    public void setPoints(int points) {
        this.points = points;

        Quiz.getMySQL().updateAccount(this, "points", points);
    }
}
