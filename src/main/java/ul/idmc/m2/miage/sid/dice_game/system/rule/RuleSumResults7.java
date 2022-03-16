package ul.idmc.m2.miage.sid.dice_game.system.rule;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.system.Dice;

public class RuleSumResults7 implements Rule {
    @Override
    public @NotNull Boolean win(@NotNull Dice dice1, @NotNull Dice dice2) {
        return (dice1.getResult() + dice2.getResult()) == 7;
    }
}
