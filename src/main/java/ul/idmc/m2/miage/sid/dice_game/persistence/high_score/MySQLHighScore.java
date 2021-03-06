package ul.idmc.m2.miage.sid.dice_game.persistence.high_score;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.Main;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class MySQLHighScore extends SQLHighScore {
    private final static @NotNull String CONFIGURATION_FILENAME = "configuration/mysql.properties";
    private static @NotNull Properties CONFIGURATION;
    private static @NotNull MySQLHighScore highScoreSingleton;

    static {
        try {
            CONFIGURATION = new Properties();
            CONFIGURATION.load(Main.class.getResourceAsStream(CONFIGURATION_FILENAME));
        } catch (IOException e) {
            System.err.println("Erreur ! Connexion impossible à la base de données MySQL !");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private MySQLHighScore() {
        super();
    }

    public static @NotNull HighScore getInstance() {
        if (highScoreSingleton == null) {
            highScoreSingleton = new MySQLHighScore();
        }
        return highScoreSingleton;
    }

    @Override
    protected void loadConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" +
                         CONFIGURATION.get("host") + ":" + CONFIGURATION.get("port") + "/" +
                         CONFIGURATION.get("database"),
                         (String) CONFIGURATION.get("user"), (String) CONFIGURATION.get("password"));
            connection.setAutoCommit(false);
        } catch (Exception e) {
            System.err.println("Erreur ! Connexion impossible à la base de données MySQL !");
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
