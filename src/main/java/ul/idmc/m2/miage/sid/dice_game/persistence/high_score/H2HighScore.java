package ul.idmc.m2.miage.sid.dice_game.persistence.high_score;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.Main;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

public class H2HighScore extends SQLHighScore {
    private final static String CONFIGURATION_FILENAME = "configuration/h2.properties";
    private static Properties CONFIGURATION;
    private static @NotNull H2HighScore highScoreSingleton;

    static {
        try {
            CONFIGURATION = new Properties();
            CONFIGURATION.load(Main.class.getResourceAsStream(CONFIGURATION_FILENAME));
        } catch (IOException e) {
            System.err.println("Erreur ! Connexion impossible à la base de données H2 !");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private H2HighScore() {
        super();
    }

    public static @NotNull HighScore getInstance() {
        if (highScoreSingleton == null) {
            highScoreSingleton = new H2HighScore();
        }
        return highScoreSingleton;
    }

    @Override
    protected void loadConnection() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:tcp://" +
                         CONFIGURATION.get("host")  + "/" +
                         CONFIGURATION.get("dataFilename") + ";MODE=MySQL;",
                         (String) CONFIGURATION.get("user"), (String) CONFIGURATION.get("password"));
            connection.setAutoCommit(false);
        } catch (Exception e) {
            System.err.println("Erreur ! Connexion impossible à la base de données H2 !");
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void save() {
        if(!scores.isEmpty()) {
            String requestString = "INSERT INTO SCORE (DATE, PLAYER_NAME, SCORE) " +
                    "VALUES ";
            for (Score score : scores) {
                requestString += "('" + Timestamp.from(score.getDate().toInstant()) + "', '" + score.getPlayerName() + "', " + score.getScore() + "), ";
            }
            requestString = requestString.substring(0, requestString.length() - 2) + " " +
                    "ON DUPLICATE KEY " +
                    "UPDATE ID = ID;";

            try {
                PreparedStatement request = connection.prepareStatement(requestString);
                request.executeUpdate();

                connection.commit();

                request.close();
            } catch (SQLException e) {
                System.err.println("Erreur ! Une requête d'insertion a échouée : \"" + requestString + "\" !");
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
