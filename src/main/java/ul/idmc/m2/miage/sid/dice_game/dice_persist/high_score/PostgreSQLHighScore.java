package ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score;

import java.sql.*;

public class PostgreSQLHighScore extends SQLHighScore {
    public PostgreSQLHighScore() {
        configurationFilename = "./configuration/postgresql.properties";
        loadConfiguration();
        loadConnection();
    }

    @Override
    protected void loadConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://" +
                            configuration.get("host") + ":" + configuration.get("port") + "/" +
                            configuration.get("database"),
                            (String) configuration.get("user"), (String) configuration.get("password"));
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
        } catch (Exception e) {
            System.err.println("Erreur ! Une requête d'insertion a échouée : \"" + requestString + "\" !");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
