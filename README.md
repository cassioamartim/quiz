# Quiz

A simple quiz plugin, with multiple questions and answers, easy to use.

# How to use

Download my PaperSpigot, by clicking [here](https://www.mediafire.com/file/7xah1ctu1w4ie46/PaperSpigot.jar/file).

Run server with plugin, download for plugin [here](https://www.mediafire.com/file/2fz25cud9eockhc/quiz-game-1.0-SNAPSHOT.jar/file).

To create new question categories, go to the GameType class, found [here](src/main/java/io/github/cassioamartim/quiz/game/GameType.java).

# How to config database (MariaDB)

You will need to have XAMPP installed on your computer to be able to start a local MariaDB server.

To install, click on this [link](https://www.apachefriends.org/pt_br/download.html).

You can change the connection address by going into the [MySQL](src/main/java/io/github/cassioamartim/quiz/database/MySQL.java) class and manually changing the requested data.

# How to build

You need to have **Gradle Build System** installed on your computer, install it by clicking [here](https://gradle.org/install/).

Open a terminal, and use the command **gradle build** to build a plugin. 

After that, the **JAR** would be available in the **build/libs** folder.

# Credits

Created by **Cássio Martim**.

This plugin was created for a test requested by the **DevRoom team**, thanks for the support!