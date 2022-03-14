
package ul.idmc.m2.miage.sid.dice_game.vizualisation;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.Main;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class IconManager {
    public final static @NotNull String ICONS_REPOSITORY = "icon/";
    public static @NotNull IconManager iconManagerSingleton;

    public @NotNull Map<String, Image> icons;

    private IconManager() {
        initialize();
    }

    public static @NotNull IconManager getInstance() {
        if(iconManagerSingleton == null) {
            iconManagerSingleton = new IconManager();
        }
        return iconManagerSingleton;
    }

    public void initialize() {
        icons = new HashMap<String, Image>();
        try {
            icons.put("dice.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dice.png")));
            icons.put("dice_2.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dice_2.png")));
            icons.put("dice_face_1.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dice_face_1.png")));
            icons.put("dice_face_2.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dice_face_2.png")));
            icons.put("dice_face_3.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dice_face_3.png")));
            icons.put("dice_face_4.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dice_face_4.png")));
            icons.put("dice_face_5.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dice_face_5.png")));
            icons.put("dice_face_6.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dice_face_6.png")));
            icons.put("dices_roll.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dices_roll.png")));
            icons.put("play_arrow.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "play_arrow.png")));
            icons.put("player.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "player.png")));
        } catch (IOException e) {
            System.out.println("Erreur ! Le chargement des icones a renctré un problème !");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public @NotNull Image get(@NotNull String iconName) {
        return icons.get(iconName);
    }
}