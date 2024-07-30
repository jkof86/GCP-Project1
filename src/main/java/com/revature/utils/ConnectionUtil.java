package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection getConnection() throws SQLException {

        String url = "" ;
        String user = "";
        String password = "" ;

        //use this to fix classpath issues on different platforms
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return DriverManager.getConnection(url, user, password);

    }

}
