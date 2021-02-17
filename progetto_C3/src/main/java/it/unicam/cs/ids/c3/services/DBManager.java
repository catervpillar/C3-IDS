package it.unicam.cs.ids.c3.services;

import java.sql.*;
import java.util.TimeZone;

public class DBManager {
    private static DBManager instance;
    private String url;
    private String user;
    private String password;
    private Connection connection = null;


    private DBManager() {
    }

    public void setDBManager(String user, String pwd) {
        this.url = "jdbc:mysql://localhost:3306/database_c3?serverTimezone=" + TimeZone.getDefault().getID();
        this.user = user;
        this.password = pwd;
    }


    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private void connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection(url, user, password);
    }

    public void disconnect(ResultSet resultSet) throws SQLException {
        Statement statement = resultSet.getStatement();
        Connection connection = statement.getConnection();
        resultSet.close();
        statement.close();
        connection.close();
    }

    public void disconnect(PreparedStatement preparedStatement) throws SQLException {
        Connection connection = preparedStatement.getConnection();
        preparedStatement.close();
        connection.close();
    }

    public ResultSet executeQuery(String query) throws SQLException {
        connect();
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        connect();
        return connection.prepareStatement(sql);
    }
}
