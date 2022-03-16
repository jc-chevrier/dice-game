package ul.idmc.m2.miage.sid.dice_game.persistence.high_score;

import org.jetbrains.annotations.NotNull;

abstract class DatabaseHighScore<CONNECTION> extends HighScore {
    protected @NotNull CONNECTION connection;

    public DatabaseHighScore() {
        loadConnection();
    }

    protected abstract void loadConnection();
}
