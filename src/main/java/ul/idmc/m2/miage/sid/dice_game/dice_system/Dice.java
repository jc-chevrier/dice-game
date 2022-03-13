package ul.idmc.m2.miage.sid.dice_game.dice_system;

public class Dice {
    private Integer result;

    public Dice() {
        result = null;
    }

    public void roll() {
        result = ((Long) Math.round(Math.random() * 6)).intValue();
    }

    public Integer getResult() {
        return result;
    }
}