package it.unicam.cs.ids.c3.database;

import java.sql.*;
import java.util.TimeZone;

/**
 * Questa classe e' un singleton ed ha la responsabilita' di effettuare la connessione al database e
 * di dialogare con lo stesso.
 */
public class DBManager {
    private static DBManager instance;
    private String url;
    private String user;
    private String password;
    private Connection connection = null;

    /**
     * Costruttore privato usato solamente all'interno di questa classe.
     */
    private DBManager() {
    }

    /**
     * Imposta le credenziali necessarie per la connessione al database.
     *
     * @param user - L'utente per connettersi.
     * @param pwd  - La password per connettersi.
     */
    public void setDBManager(String user, String pwd) {
        this.url = "jdbc:mysql://localhost:3306/database_c3?serverTimezone=" + TimeZone.getDefault().getID();
        this.user = user;
        this.password = pwd;
    }

    /**
     * Metodo getter per l'attributo instance. Se instance è nulla, viene inizializzata.
     *
     * @return l'attributo instance.
     */
    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    /**
     * Tenta di eseguire la connessione al database.
     *
     * @throws SQLException in caso di problemi col database.
     */
    private void connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection(url, user, password);
    }

    /**
     * Esegue la disconnessione dal database.
     *
     * @param resultSet - L'ultimo resultSet ottenuto da cui si ottengono tutti gli elementi da chiudere.
     * @throws SQLException in caso di problemi col database.
     */
    public void disconnect(ResultSet resultSet) throws SQLException {
        Statement statement = resultSet.getStatement();
        Connection connection = statement.getConnection();
        resultSet.close();
        statement.close();
        connection.close();
    }

    /**
     * Esegue la disconnessione dal database.
     *
     * @param preparedStatement - L'ultimo preparedStatement ottenuto da cui si ottengono tutti gli elementi da chiudere.
     * @throws SQLException in caso di problemi col database.
     */
    public void disconnect(PreparedStatement preparedStatement) throws SQLException {
        Connection connection = preparedStatement.getConnection();
        preparedStatement.close();
        connection.close();
    }

    /**
     * Tenta di eseguire una query e ne ritorna il risultato.
     *
     * @param query - La query da eseguire.
     * @return il risultato dell'esecuzione.
     * @throws SQLException in caso di problemi col database.
     */
    public ResultSet executeQuery(String query) throws SQLException {
        connect();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.TYPE_SCROLL_INSENSITIVE);
        return statement.executeQuery(query);
    }

    /**
     * Ritorna un {@link PreparedStatement} relativo alla connessione.
     *
     * @param sql - La query da eseguire.
     * @return il {@link PreparedStatement} creato.
     * @throws SQLException in caso di problemi col database.
     */
    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        connect();
        return connection.prepareStatement(sql);
    }
}
