package ul.idmc.m2.miage.sid.dice_game.vizualisation.setting;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score_factory.*;
import ul.idmc.m2.miage.sid.dice_game.system.Play;
import ul.idmc.m2.miage.sid.dice_game.system.rule.Rule;
import ul.idmc.m2.miage.sid.dice_game.system.rule.RuleSameResults;
import ul.idmc.m2.miage.sid.dice_game.system.rule.RuleSumResults7;
import ul.idmc.m2.miage.sid.dice_game.system.rule.RuleSumResultsLowerThan7;
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

    public SettingView(@NotNull Play play) {
        this.play = play;

        paintContent();

        JPanel voidView = new JPanel();
        Integer childComponentsHeight = Arrays.asList(getComponents())
                                              .stream()
                                              .map(component -> (int) component.getPreferredSize().getHeight())
                                              .reduce((height, acc) -> acc + height)
                                              .get();
        voidView.setPreferredSize(new Dimension(400, (375 - childComponentsHeight - 85) / 2));
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

         String[] options = new String[]{"PostgreSQL", "MySQL", "H2", "S\u00e9rialisation"};
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

        JPanel voidView4 = new JPanel();
        voidView4.setPreferredSize(new Dimension(400, 15));
        add(voidView4);

        JLabel labelRule = new JLabel("Règle pour gagner", JLabel.CENTER);
        labelRule.setFont(new Font(labelRule.getFont().getName(), Font.BOLD, 13));
        labelRule.setPreferredSize(new Dimension(400, 20));
        add(labelRule);

        JPanel voidView5 = new JPanel();
        voidView5.setPreferredSize(new Dimension(400, 1));
        add(voidView5);

        final String[] options2 = new String[]{"Somme \u00e9gale \u00e0 7",
                                               "Somme inf\u00e9rieure \u00e0 7",
                                               "M\u00eames faces"};
        ruleComboBox = new JComboBox<String>(options2);
        ruleComboBox.addItemListener((e) -> {
            Rule rule = null;
            switch (options2[ruleComboBox.getSelectedIndex()]) {
                case "Somme \u00e9gale \u00e0 7" -> rule = new RuleSumResults7();
                case "Somme inf\u00e9rieure \u00e0 7" ->  rule = new RuleSumResultsLowerThan7();
                case "M\u00eames faces" -> rule = new RuleSameResults();
            }
            play.setRule(rule);
        });
        Integer selectedOption2 = null;
        switch (play.getRule().getClass().getSimpleName()) {
            case "RuleSumResults7" -> selectedOption2 = 0;
            case "RuleSumResultsLowerThan7" -> selectedOption2 = 1;
            case "RuleSameResults" -> selectedOption2 = 2;
        }
        ruleComboBox.setSelectedIndex(selectedOption2);
        ruleComboBox.setPreferredSize(new Dimension(150, 25));
        ruleComboBox.setBackground(Color.WHITE);
        add(ruleComboBox);
    }
}