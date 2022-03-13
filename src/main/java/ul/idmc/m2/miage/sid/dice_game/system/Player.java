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
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
        dice1.roll();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
        dice2.roll();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
        evaluateScore();
        //support.firePropertyChange(PlayEvent.NEW_SCORE.name(), null, null);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
        support.firePropertyChange(PlayEvent.END_TURN.name(), null, null);
    }

    public void evaluateScore() {
        Integer result = dice1.getResult() + dice2.getResult();
        if(result == 7) {
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
