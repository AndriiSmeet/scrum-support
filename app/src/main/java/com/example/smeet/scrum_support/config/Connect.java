package com.example.smeet.scrum_support.config;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Smeet on 01.12.2017.
 */

public class Connect {


    private static final String DATABASE_URL  = "jdbc:postgresql://baasu.db.elephantsql.com:5432/oddbqsmg";
    private static final String DATABASE_USERNAME  = "oddbqsmg";
    private static final String DATABASE_PASSWORD  = "QOX1FAEE_kedZD74fyDk2zUxnV80eaXZ";

    private static Connection connect = null;

    public static Connection getConnection()
    {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        if(connect != null) {
            return connect;
        }

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connect = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No connection");
        }

        return connect;
    }
}
