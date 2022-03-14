package ul.idmc.m2.miage.sid.dice_game.persistence.high_score;

import java.sql.*;

abstract class SQLHighScore extends DatabaseHighScore<Connection> {
    @Override
    public void load() {
        scores.clear();

        String requestString = "SELECT * FROM SCORE " +
                               "ORDER BY DATE;";

        try {
            Statement request = connection.createStatement();
            ResultSet result = request.executeQuery(requestString);

            while(result.next()) {
                java.util.Date date = result.getTimestamp("DATE");
                String playerName = result.getString("PLAYER_NAME");
                Integer scoreNumber = result.getInt("SCORE");
                Score score = new Score(date, playerName, scoreNumber);
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
