package com.Shawn.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    public static Connection get_connect(String user, String password) throws Exception{
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            // if I use "jdbc:mysql://localhost:3306/QG?autoReconnect=false&useSSL=false"
            // I can login after signing up;
            //193.112.71.212
            String url = "jdbc:mysql://localhost:3306/QG?";
            Class.forName(driver);
            System.out.println("MySQL connected");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("SUCCESS");

            return conn;
        } catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (Exception e){
            System.err.println(e);
        }
        return null;
    }
}


