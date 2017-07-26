package application.helpers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    Connection connection;
    Statement statement;

    public DatabaseManager(Connection connection) throws SQLException{
        this.statement = connection.createStatement();
    }

    public Statement getStatement() {
        return statement;
    }

    public void dropStatsTable() throws SQLException{
        statement.executeUpdate("DROP TABLE IF EXISTS stats");
    }

    public void createStatsTable() throws SQLException{
        statement.executeUpdate("CREATE TABLE stats (id INTEGER PRIMARY KEY, name STRING, wins INTEGER, losses INTEGER)");
    }

    public ResultSet findAll(String table) throws SQLException{
        String formattedSQL = String.format("SELECT * FROM %s", table);
        ResultSet rs = statement.executeQuery(formattedSQL);
        return rs;
    }
}
