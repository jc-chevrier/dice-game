package ul.idmc.m2.miage.sid.dice_game.vizualisation.setting;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score_factory.*;
import ul.idmc.m2.miage.sid.dice_game.system.Play;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StartSettingView extends SettingView {
    public StartSettingView(@NotNull Play play) {
        super(play);
    }

    protected void paintChildComponents() {
        super.paintChildComponents();

        JPanel voidView5 = new JPanel();
        voidView5.setPreferredSize(new Dimension(400, 15));
        add(voidView5);

        JButton startButton = new JButton("Jouer");
        startButton.addActionListener((e) -> play.start());
        startButton.setMargin(new Insets(0, 0, 0, 0));
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setBackground(LIGHT_BLUE);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font(startButton.getFont().getName(), Font.BOLD, 13));
        startButton.setPreferredSize(new Dimension(120, 30));
        startButton.setEnabled(!play.getPlayer().getName().isEmpty());
        add(startButton);


        saveSupportComboBox.removeItemListener(saveSupportComboBox.getItemListeners()[0]);
        String[] options = {"PostgreSQL", "MySQL", "H2", "S\u00e9rialisation",};
        saveSupportComboBox.addItemListener((e) -> {
            HighScoreFactory highScoreFactory = null;
            switch (options[saveSupportComboBox.getSelectedIndex()]) {
                case "PostgreSQL" -> highScoreFactory = new PostgreSQLHighScoreFactory();
                case "MySQL" -> highScoreFactory = new MySQLHighScoreFactory();
                case "H2" -> highScoreFactory = new H2HighScoreFactory();
                case "S\u00e9rialisation" -> highScoreFactory = new SerializationHighScoreFactory();
            }
            play.setHighScoreFactory(highScoreFactory);
            playerNameInput.setText(play.getPlayer().getName());
            if (!play.getPlayer().getName().isEmpty()) {
                startButton.setEnabled(true);
            }
        });

        playerNameInput.removeKeyListener(playerNameInput.getKeyListeners()[0]);
        playerNameInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                play.getPlayer().setName(playerNameInput.getText());
                startButton.setEnabled(!playerNameInput.getText().isEmpty());
            }
        });
    }
}