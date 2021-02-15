package it.unicam.cs.ids.c3.services;

import java.sql.*;

public class DBManager {
    private static DBManager instance;
    private String url;
    private String user;
    private String pwd;
    private Connection conn = null;

    private Statement statement;
    private ResultSet resultSet;
    private String sql;


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

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
            e.printStackTrace();
        }
        try {
//            conn = DriverManager.getConnection(url, user, pwd);
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/DB_C3?user=root&password=rootroot");
            System.out.println("Database connected, ready to go!");

            sql = "SELECT * FROM articolo;";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                System.out.print(resultSet.getString("ID") + "\t" + resultSet.getString("nome"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Problems in opening a connection to the DB");
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Problems in closing the connection to the DB");
            e.printStackTrace();
        }
    }
}
