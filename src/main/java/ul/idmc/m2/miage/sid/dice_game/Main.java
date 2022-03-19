package ul.idmc.m2.miage.sid.dice_game;

import ul.idmc.m2.miage.sid.dice_game.system.Play;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.Window;

public class Main {
    public static void main(String[] args) {
        Play play = new Play();
        new Window(play);
        play.setUp();
    }
}


