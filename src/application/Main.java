package application;

import application.helpers.DatabaseManager;
import application.models.Stat;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:stats.db")) {

            WelcomeMenu();

        } catch (SQLException ex) {
            System.out.println("SQL Exception!");
            ex.printStackTrace();
        }
    }

    public static void WelcomeMenu() {
        System.out.println("=====================================================");
        System.out.println("Welcome to Stat Database!  What would you like to do?");
        System.out.println("1.  Show All Stats");
        System.out.println("2.  Add A New Stat");
        System.out.println("3.  Update An Existing Stat");
        System.out.println("=====================================================");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                System.out.println("Now showing all stat data");
                break;
            case 2:
                System.out.println("Let's add a stat");
                break;
            case 3:
                System.out.println("Which entry are you updating?");
                break;
            default:
                System.out.println("INVALID INPUT");
        }

        WelcomeMenu();
    }
}
