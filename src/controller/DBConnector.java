package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {

    public static Connection DBConnector() {

        Connection c = null;
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Szemjuel\\Documents\\Distiller.sqlite");
            System.out.println("Connected");
            return c;
        }catch ( Exception e ){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return null;
        }
    }
}
