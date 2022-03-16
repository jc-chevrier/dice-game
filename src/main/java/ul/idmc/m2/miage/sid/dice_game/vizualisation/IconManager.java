
package ul.idmc.m2.miage.sid.dice_game.vizualisation;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.Main;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class IconManager {
    public final static @NotNull String ICONS_REPOSITORY = "icon/";
    public static @NotNull IconManager iconManagerSingleton;

    public @NotNull Map<String, Image> icons;

    private IconManager() {
        load();
    }

    public static @NotNull IconManager getInstance() {
        if(iconManagerSingleton == null) {
            iconManagerSingleton = new IconManager();
        }
        return iconManagerSingleton;
    }

    public void load() {
        icons = new HashMap<String, Image>();
        try {
            icons.put("dice.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dice.png")));

            icons.put("play_arrow.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "play_arrow.png")));

            icons.put("player.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "player.png")));

            icons.put("star.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "star.png")));
            icons.put("star_more.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "star_more.png")));

            icons.put("dice_6_faces/base.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dice_6_faces/base.png")));
            icons.put("dice_6_faces/roll.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dice_6_faces/roll.png")));
            for(Integer i = 1; i < 7; i++) {
                String iconName = "dice_6_faces/face_" + i + ".png";
                icons.put(iconName, ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + iconName)));
            }

            icons.put("dice_10_faces/base.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dice_10_faces/base.png")));
            icons.put("dice_10_faces/roll.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dice_10_faces/roll.png")));
            for(Integer i = 0; i < 10; i++) {
                String iconName = "dice_10_faces/face_" + i + ".png";
                icons.put(iconName, ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + iconName)));
            }

            icons.put("dice_20_faces/base.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dice_20_faces/base.png")));
            icons.put("dice_20_faces/roll.png", ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + "dice_20_faces/roll.png")));
            for(Integer i = 1; i < 21; i++) {
                String iconName = "dice_20_faces/face_" + i + ".png";
                icons.put(iconName, ImageIO.read(Main.class.getResourceAsStream(ICONS_REPOSITORY + iconName)));
            }
        } catch (IOException e) {
            System.err.println("Erreur ! Le chargement des icones a rencontré un problème !");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public @NotNull Image get(@NotNull String iconName) {
        return icons.get(iconName);
    }
}