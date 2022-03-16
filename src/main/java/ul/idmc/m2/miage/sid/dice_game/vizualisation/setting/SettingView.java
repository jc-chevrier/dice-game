package ul.idmc.m2.miage.sid.dice_game.vizualisation.setting;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score_factory.*;
import ul.idmc.m2.miage.sid.dice_game.system.Play;
import ul.idmc.m2.miage.sid.dice_game.system.dice.Dice10Faces;
import ul.idmc.m2.miage.sid.dice_game.system.dice.Dice6Faces;
import ul.idmc.m2.miage.sid.dice_game.system.rule.Rule;
import ul.idmc.m2.miage.sid.dice_game.system.rule.RuleSameFaces;
import ul.idmc.m2.miage.sid.dice_game.system.rule.RuleSumFacesEquals7;
import ul.idmc.m2.miage.sid.dice_game.system.rule.RuleSumFacesLowerThan7;
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
    protected @NotNull JComboBox<String> ruleComboBox;
    protected @NotNull JComboBox<String> typeDiceComboBox;

    public SettingView(@NotNull Play play) {
        this.play = play;

        paintContent();

        JPanel voidView = new JPanel();
        Integer childComponentsHeight = Arrays.asList(getComponents())
                                              .stream()
                                              .map(component -> (int) component.getPreferredSize().getHeight())
                                              .reduce((height, acc) -> acc + height)
                                              .get();
        voidView.setPreferredSize(new Dimension(400, (375 - childComponentsHeight - 100) / 2));
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
        voidView2.setPreferredSize(new Dimension(400, 5));
        add(voidView2);

        JLabel labelStorageSpace = new JLabel("Espace de stockage", JLabel.CENTER);
        labelStorageSpace.setFont(new Font(labelStorageSpace.getFont().getName(), Font.BOLD, 13));
        labelStorageSpace.setPreferredSize(new Dimension(400, 20));
        add(labelStorageSpace);

        JPanel voidView3 = new JPanel();
        voidView3.setPreferredSize(new Dimension(400, 1));
        add(voidView3);

        String[] storageSpaceOptions = new String[]{"PostgreSQL", "MySQL", "H2", "S\u00e9rialisation"};
        storageSpaceComboBox = new JComboBox<String>(storageSpaceOptions);
        storageSpaceComboBox.addItemListener((e) -> {
            HighScoreFactory highScoreFactory = null;
            switch (storageSpaceOptions[storageSpaceComboBox.getSelectedIndex()]) {
                case "PostgreSQL" -> highScoreFactory = new PostgreSQLHighScoreFactory();
                case "MySQL" -> highScoreFactory = new MySQLHighScoreFactory();
                case "H2" -> highScoreFactory = new H2HighScoreFactory();
                case "S\u00e9rialisation" -> highScoreFactory = new SerializationHighScoreFactory();
            }
            play.setHighScoreFactory(highScoreFactory);
            playerNameInput.setText(play.getPlayer().getName());
        });
        String storageSpaceSelectedOption = play.getHighScoreFactory()
                                    .getClass()
                                    .getSimpleName()
                                    .replace("HighScoreFactory", "")
                                    .replace("Serialization", "S\u00e9rialisation");
        storageSpaceComboBox.setSelectedIndex(Arrays.asList(storageSpaceOptions).indexOf(storageSpaceSelectedOption));
        storageSpaceComboBox.setPreferredSize(new Dimension(150, 25));
        storageSpaceComboBox.setBackground(Color.WHITE);
        add(storageSpaceComboBox);

        JPanel voidView4 = new JPanel();
        voidView4.setPreferredSize(new Dimension(400, 5));
        add(voidView4);

        JLabel labelRule = new JLabel("R\u00e8gle pour gagner", JLabel.CENTER);
        labelRule.setFont(new Font(labelRule.getFont().getName(), Font.BOLD, 13));
        labelRule.setPreferredSize(new Dimension(400, 20));
        add(labelRule);

        JPanel voidView5 = new JPanel();
        voidView5.setPreferredSize(new Dimension(400, 1));
        add(voidView5);

        String[] ruleOptions = new String[]{"Somme \u00e9gale \u00e0 7",
                                          "Somme inf\u00e9rieure \u00e0 7",
                                          "M\u00eames faces"};
        ruleComboBox = new JComboBox<String>(ruleOptions);
        ruleComboBox.addItemListener((e) -> {
            Rule rule = null;
            switch (ruleOptions[ruleComboBox.getSelectedIndex()]) {
                case "Somme \u00e9gale \u00e0 7" -> rule = new RuleSumFacesEquals7();
                case "Somme inf\u00e9rieure \u00e0 7" ->  rule = new RuleSumFacesLowerThan7();
                case "M\u00eames faces" -> rule = new RuleSameFaces();
            }
            play.setRule(rule);
        });
        Integer ruleSelectedOption = null;
        switch (play.getRule().getClass().getSimpleName()) {
            case "RuleSumFacesEquals7" -> ruleSelectedOption = 0;
            case "RuleSumFacesLowerThan7" -> ruleSelectedOption = 1;
            case "RuleSameFaces" -> ruleSelectedOption = 2;
        }
        ruleComboBox.setSelectedIndex(ruleSelectedOption);
        ruleComboBox.setPreferredSize(new Dimension(150, 25));
        ruleComboBox.setBackground(Color.WHITE);
        add(ruleComboBox);

        JPanel voidView6 = new JPanel();
        voidView6.setPreferredSize(new Dimension(400, 5));
        add(voidView6);

        JLabel labelTypeDice = new JLabel("Type de d\u00e9", JLabel.CENTER);
        labelTypeDice.setFont(new Font(labelTypeDice.getFont().getName(), Font.BOLD, 13));
        labelTypeDice.setPreferredSize(new Dimension(400, 20));
        add(labelTypeDice);

        JPanel voidView7 = new JPanel();
        voidView7.setPreferredSize(new Dimension(400, 1));
        add(voidView7);

        String[] typeDiceOptions = new String[]{"D\u00e9 \u00e0 6 faces (1-6)",
                                         "D\u00e9 \u00e0 10 faces (0-9)"};
        typeDiceComboBox = new JComboBox<String>(typeDiceOptions);
        typeDiceComboBox.addItemListener((e) -> {
            String typeDice = null;
            switch (typeDiceOptions[typeDiceComboBox.getSelectedIndex()]) {
                case "D\u00e9 \u00e0 6 faces (1-6)" -> typeDice = Dice6Faces.class.getSimpleName();
                case "D\u00e9 \u00e0 10 faces (0-9)" ->  typeDice = Dice10Faces.class.getSimpleName();
            }
            play.setTypeDice(typeDice);
        });
        Integer typeDiceSelectedOption = null;
        switch (play.getTypeDice()) {
            case "Dice6Faces" -> typeDiceSelectedOption = 0;
            case "Dice10Faces" -> typeDiceSelectedOption = 1;
        }
        typeDiceComboBox.setSelectedIndex(typeDiceSelectedOption);
        typeDiceComboBox.setPreferredSize(new Dimension(150, 25));
        typeDiceComboBox.setBackground(Color.WHITE);
        add(typeDiceComboBox);
    }
}