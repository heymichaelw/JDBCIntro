package application;

import application.helpers.DatabaseManager;
import application.models.Stat;

import java.sql.*;
import java.util.List;

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

            List<Stat> results = Stat.findAll(db);
            for (Stat stat : results){
                System.out.println(stat);
            }

        } catch (SQLException ex) {
            System.out.println("SQL Exception!");
            ex.printStackTrace();
        }
    }
}
