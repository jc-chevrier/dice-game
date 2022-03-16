package ul.idmc.m2.miage.sid.dice_game.system;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.principle.Observable;
import ul.idmc.m2.miage.sid.dice_game.principle.Reinitializable;

public class Player extends Observable implements Reinitializable {
    private @NotNull Play play;
    private @NotNull String name;
    private @NotNull Integer score;
    private @NotNull Dice dice1;
    private @NotNull Dice dice2;

    public Player(@NotNull Play play, @NotNull String name, @NotNull Integer score) {
        super();

        this.play = play;

        this.name = name;

        this.score = score;

        dice1 = new Dice();
        dice2 = new Dice();
    }

    public Player(@NotNull Play play, @NotNull String name) {
        this(play, name, 0);
    }

    public Player(@NotNull Play play) {
        this(play, "");
    }

    @Override
    public void reinitialize() {
        score = 0;
        dice1.reinitialize();
        dice2.reinitialize();
    }

    public void play() {
        getSupport().firePropertyChange(PlayEvent.NEW_TURN.name(), null, null);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

        Integer oldScore = score;
        dice1.roll();
        dice2.roll();
        reevaluateScore();
        getSupport().firePropertyChange(PlayEvent.NEW_SCORE.name(), oldScore.equals(score) ? null : oldScore, score);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}

        getSupport().firePropertyChange(PlayEvent.END_TURN.name(), null, null);
    }

    public Integer getDicesSumResult() {
        return dice1.getResult() + dice2.getResult();
    }

    public Boolean wins() {
        return play.getRule().win(dice1, dice2);
    }

    private void reevaluateScore() {
        if(wins()) {
            score += 10;
        }
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
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
}
