
package ul.idmc.m2.miage.sid.dice_game.vizualisation;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.Main;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class IconManager {
    public final static @NotNull String ICONS_REPOSITORY = "./icon/";
    
    public final static @NotNull Map<String, Image> ICONS;

    static {
        ICONS = new HashMap<String, Image>();
        try {
            ICONS.put("dice.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dice.png")));
            ICONS.put("dice_2.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dice_2.png")));
            ICONS.put("dice_face_1.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dice_face_1.png")));
            ICONS.put("dice_face_2.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dice_face_2.png")));
            ICONS.put("dice_face_3.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dice_face_3.png")));
            ICONS.put("dice_face_4.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dice_face_4.png")));
            ICONS.put("dice_face_5.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dice_face_5.png")));
            ICONS.put("dice_face_6.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dice_face_6.png")));
            ICONS.put("dices_roll.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dices_roll.png")));
            ICONS.put("play_arrow.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "play_arrow.png")));
            ICONS.put("player.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "player.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static @NotNull Image get(@NotNull String iconName) {
        return ICONS.get(iconName);
    }
}