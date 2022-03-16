package ul.idmc.m2.miage.sid.dice_game.system.rule;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.system.dice.Dice;

public class RuleSumFacesLowerThan7 implements Rule {
    @Override
    public @NotNull Boolean win(@NotNull Dice dice1, @NotNull Dice dice2) {
        return (dice1.getFace() + dice2.getFace()) < 7;
    }
}
