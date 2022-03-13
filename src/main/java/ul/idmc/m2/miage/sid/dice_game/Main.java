package ul.idmc.m2.miage.sid.dice_game;

import ul.idmc.m2.miage.sid.dice_game.system.Play;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.Window;

public class Main {
    public static void main(String[] args) {
        Play play = new Play();
        Window window = new Window(play);
        play.start();
        while(true){
            play.getPlayer().play();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}
        }}
}
