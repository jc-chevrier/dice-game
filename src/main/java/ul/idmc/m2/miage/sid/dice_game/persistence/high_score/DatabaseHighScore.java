package ul.idmc.m2.miage.sid.dice_game.persistence.high_score;

abstract class DatabaseHighScore<CONNECTION> extends HighScore {
    protected CONNECTION connection;

    public DatabaseHighScore() {
        loadConnection();
    }

    protected abstract void loadConnection();
}
