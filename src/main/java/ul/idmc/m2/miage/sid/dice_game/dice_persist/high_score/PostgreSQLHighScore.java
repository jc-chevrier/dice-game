package ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score;

import ul.idmc.m2.miage.sid.dice_game.Main;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class PostgreSQLHighScore extends SQLHighScore {
    private final static String CONFIGURATION_FILENAME = "./configuration/postgresql.properties";
    private static Properties CONFIGURATION;

    static {
        try {
            CONFIGURATION = new Properties();
            CONFIGURATION.load(Main.class.getResourceAsStream(CONFIGURATION_FILENAME));
        } catch (IOException e) {
            System.err.println("Erreur ! Connexion impossible à la base de données PostgreSQL !");
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    protected void loadConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://" +
                         CONFIGURATION.get("host") + ":" + CONFIGURATION.get("port") + "/" +
                         CONFIGURATION.get("database"),
                         (String) CONFIGURATION.get("user"), (String) CONFIGURATION.get("password"));
            connection.setAutoCommit(false);
        } catch (Exception e) {
            System.err.println("Erreur ! Connexion impossible à la base de données PostgreSQL !");
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void save() {
        String requestString = "INSERT INTO SCORE (PLAYER_NAME, SCORE_NUMBER) " +
                               "VALUES ";
        for(Score score : scores) {
            requestString += "('" + score.getPlayerName() + "', " + score.getScoreNumber() + "), ";
        }
        requestString = requestString.substring(0, requestString.length() - 2) + " " +
                        "ON CONFLICT (PLAYER_NAME) " +
                        "DO UPDATE " +
                        "SET PLAYER_NAME = excluded.PLAYER_NAME, SCORE_NUMBER = excluded.SCORE_NUMBER";

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
