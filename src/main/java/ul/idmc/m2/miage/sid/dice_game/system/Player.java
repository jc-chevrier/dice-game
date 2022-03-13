package ul.idmc.m2.miage.sid.dice_game.system;

import org.jetbrains.annotations.NotNull;
import java.beans.PropertyChangeSupport;

public class Player {
    private @NotNull String name;
    private @NotNull Integer score;
    private @NotNull Dice dice1;
    private @NotNull Dice dice2;
    private @NotNull PropertyChangeSupport support;

    public Player(@NotNull String name, @NotNull Integer score) {
        this.name = name;
        this.score = score;
        dice1 = new Dice();
        dice2 = new Dice();
        support = new PropertyChangeSupport(this);
    }

    public Player(@NotNull String name) {
        this(name, 0);
    }

    public Player() {
        this("");
    }

    public void play() {
        support.firePropertyChange(PlayEvent.NEW_TURN.name(), null, null);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

        dice1.roll();
        dice2.roll();
        evaluateScore();
        support.firePropertyChange(PlayEvent.NEW_SCORE.name(), null, null);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}

        support.firePropertyChange(PlayEvent.END_TURN.name(), null, null);
    }

    public Integer getDicesSumResult() {
        return dice1.getResult() + dice2.getResult();
    }

    public Boolean wins() {
        return getDicesSumResult()  == 7;
    }

    private void evaluateScore() {
        if(wins()) {
            score += 10;
        }
    }

    public @NotNull String getName() {
        return name;
    }

    public @NotNull Integer getScore() {
        return score;
    }

    public Dice getDice1() {
        return dice1;
    }

    public Dice getDice2() {
        return dice2;
    }

    public PropertyChangeSupport getSupport() {
        return support;
    }
}
