package ul.idmc.m2.miage.sid.dice_game.vizualisation.setting;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score_factory.*;
import ul.idmc.m2.miage.sid.dice_game.system.Play;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.Theme;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class SettingView extends JPanel implements Theme {
    protected @NotNull Play play;
    protected @NotNull JTextField playerNameInput;
    protected @NotNull JComboBox<String> storageSpaceComboBox;

    public SettingView(@NotNull Play play) {
        this.play = play;

        paintContent();

        JPanel voidView = new JPanel();
        Integer childComponentsHeight = Arrays.asList(getComponents())
                                              .stream()
                                              .map(component -> (int) component.getPreferredSize().getHeight())
                                              .reduce((height, acc) -> acc + height)
                                              .get();
        voidView.setPreferredSize(new Dimension(400, (375 - childComponentsHeight - 70) / 2));
        add(voidView, 0);

        setPreferredSize(new Dimension(400, 375));
    }

    protected void paintContent() {
        JLabel playerNameLabel = new JLabel("Nom de joueur", JLabel.CENTER);
        playerNameLabel.setFont(new Font(playerNameLabel.getFont().getName(), Font.BOLD, 13));
        playerNameLabel.setPreferredSize(new Dimension(150, 20));
        add(playerNameLabel);

        JPanel voidView = new JPanel();
        voidView.setPreferredSize(new Dimension(400, 1));
        add(voidView);

        playerNameInput = new JTextField(play.getPlayer().getName());
        playerNameInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                play.getPlayer().setName(playerNameInput.getText());//TODO à améliorer.
            }
        });
        playerNameInput.setPreferredSize(new Dimension(150, 25));
        add(playerNameInput);

        JPanel voidView2 = new JPanel();
        voidView2.setPreferredSize(new Dimension(400, 15));
        add(voidView2);

        JLabel labelStorageSpace = new JLabel("Espace de stockage", JLabel.CENTER);
        labelStorageSpace.setFont(new Font(labelStorageSpace.getFont().getName(), Font.BOLD, 13));
        labelStorageSpace.setPreferredSize(new Dimension(400, 20));
        add(labelStorageSpace);

        JPanel voidView3 = new JPanel();
        voidView3.setPreferredSize(new Dimension(400, 1));
        add(voidView3);

        String[] options = {"PostgreSQL", "MySQL", "H2", "S\u00e9rialisation",};
        storageSpaceComboBox = new JComboBox<String>(options);
        storageSpaceComboBox.addItemListener((e) -> {
            HighScoreFactory highScoreFactory = null;
            switch (options[storageSpaceComboBox.getSelectedIndex()]) {
                case "PostgreSQL" -> highScoreFactory = new PostgreSQLHighScoreFactory();
                case "MySQL" -> highScoreFactory = new MySQLHighScoreFactory();
                case "H2" -> highScoreFactory = new H2HighScoreFactory();
                case "S\u00e9rialisation" -> highScoreFactory = new SerializationHighScoreFactory();
            }
            play.setHighScoreFactory(highScoreFactory);
            playerNameInput.setText(play.getPlayer().getName());
        });
        String selectedOption = play.getHighScoreFactory()
                                    .getClass()
                                    .getSimpleName()
                                    .replace("HighScoreFactory", "")
                                    .replace("Serialization", "S\u00e9rialisation");
        storageSpaceComboBox.setSelectedIndex(Arrays.asList(options).indexOf(selectedOption));
        storageSpaceComboBox.setPreferredSize(new Dimension(150, 25));
        storageSpaceComboBox.setBackground(Color.WHITE);
        add(storageSpaceComboBox);
    }
}