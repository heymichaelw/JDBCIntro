package application;

import application.helpers.DatabaseManager;
import application.models.Stat;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:stats.db")) {
            DatabaseManager db = new DatabaseManager(connection);
            db.dropStatsTable();
            db.createStatsTable();
            Statement statement = db.getStatement();

            Stat tommyStat = new Stat("Tommy", 5, 8, statement);
            Stat michaelStat = new Stat("Michael", 3, 6, statement);
            tommyStat.save();
            michaelStat.save();


            ResultSet rs = statement.executeQuery("SELECT * FROM stats");

            while (rs.next()){
                String name = rs.getString("name");
                int wins = rs.getInt("wins");
                int losses = rs.getInt("losses");
                System.out.printf("Name: %s, Wins: %s, Losses: %s \n", name, wins, losses);
            }

        } catch (SQLException ex) {
            System.out.println("SQL Exception!");
            ex.printStackTrace();
        }
    }
}
