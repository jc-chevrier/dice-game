package ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score;

abstract class DatabaseHighScore<CONNECTION> extends HighScore {
    protected CONNECTION connection;

    public DatabaseHighScore() {
        loadConnection();
    }

    protected abstract void loadConnection();
}
