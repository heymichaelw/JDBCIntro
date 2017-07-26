package application;

import application.helpers.DatabaseManager;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:stats.db")) {
            DatabaseManager db = new DatabaseManager(connection);
            db.dropStatsTable();

            Statement statement = connection.createStatement();

            db.createStatsTable();
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
