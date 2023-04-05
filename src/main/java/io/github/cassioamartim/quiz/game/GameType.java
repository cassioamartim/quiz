package io.github.cassioamartim.quiz.game;

import io.github.cassioamartim.quiz.game.quest.Quest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum GameType {

    SPORT("Sport", Arrays.asList(
            new Quest("Who won the last world cup?", "Argentina"),
            new Quest("Who is the greatest scorer in Brazilian football?", "Roberto Dinamite"),
            new Quest("Who is the top scorer in US soccer?", "Clint Dempsey")
    )),
    GAMES("Games", Arrays.asList(
            new Quest("Which game came after GOW 4?", "God of War Ragnarok")
    ));

    private final String name;

    private final List<Quest> quests;
}
