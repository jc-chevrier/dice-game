package ul.idmc.m2.miage.sid.dice_game.system.rule;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.system.dice.Dice;

public class RuleSumFacesEquals7 implements Rule {
    @Override
    public @NotNull Boolean win(@NotNull Dice dice1, @NotNull Dice dice2) {
        return (dice1.getCurrentFace() + dice2.getCurrentFace()) == 7;
    }
}
