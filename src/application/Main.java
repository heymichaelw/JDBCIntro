package application;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:stats.db")) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS stats");
            statement.executeUpdate("CREATE TABLE stats (id INTEGER PRIMARY KEY, name STRING, wins INTEGER, losses INTEGER)");
            statement.executeUpdate("INSERT INTO stats (name, wins, losses) VALUES ('Michael', 4, 3)");
            ResultSet rs = statement.executeQuery("SELECT * FROM stats");

            while (rs.next()){
                String name = rs.getString("name");
                int wins = rs.getInt("wins");
                int losses = rs.getInt("losses");
                System.out.printf("Name: %s, Wins: %s, Losses: %s", name, wins, losses);
            }

        } catch (SQLException ex) {
            System.out.println("SQL Exception!");
            ex.printStackTrace();
        }
    }
}
