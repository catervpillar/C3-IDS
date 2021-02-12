package it.unicam.cs.ids.c3.services;

import java.sql.*;

public class DBManager {
    private static DBManager instance;
    private String url;
    private String user;
    private String pwd;
    private Connection conn = null;

    private DBManager() {
    }

    public void setDBManager(String url, String user, String pwd) {
        this.url = url;
        this.user = user;
        this.pwd = pwd;
    }

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
            e.printStackTrace();
        }
        try {
//            conn = DriverManager.getConnection(url, user, pwd);
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nomedb?user=root&password=");
            System.out.println("Database connected, ready to go!");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Problems in opening a connection to the DB");
            e.printStackTrace();
        }
    }

    private void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Problems in closing the connection to the DB");
            e.printStackTrace();
        }
    }
}
