package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    protected String url;
    protected String user;
    protected String password;

    public DBConnection(String url, String user, String  password){
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: " + e);
        }
        return connection;
    }
}