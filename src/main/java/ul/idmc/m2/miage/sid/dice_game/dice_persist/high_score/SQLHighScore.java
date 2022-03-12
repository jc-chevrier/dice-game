package ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

abstract class SQLHighScore extends DatabaseHighScore<Connection> {
    @Override
    public void load() {
        scores.clear();

        String requestString = "SELECT * FROM SCORE;";

        try {
            Statement request = connection.createStatement();
            ResultSet result = request.executeQuery(requestString);

            while(result.next()) {
                String playerName = result.getString("PLAYER_NAME");
                Integer scoreNumber = result.getInt("SCORE_NUMBER");
                Score score = new Score(playerName, scoreNumber);
                scores.add(score);
            }

            result.close();
            request.close();
        } catch (SQLException e) {
            System.err.println("Erreur ! Une requête se sélection a échouée : \"" + requestString + "\" !");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
