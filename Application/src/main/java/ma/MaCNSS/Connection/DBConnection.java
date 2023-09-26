package ma.MaCNSS.Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String DB_HOST = "localhost";
    private static final int DB_PORT = 5432;
    private static final String USERNAME = "postgres";

    private static final String PASSWORD = "root";
    private static final String DB_NAME = "maCNSSdb";
    public static Connection connection;

    public DBConnection() {
        try {
            connection = DriverManager.getConnection
                    (String.format("jdbc:postgresql://%s:%d/%s", DB_HOST, DB_PORT, DB_NAME), USERNAME, PASSWORD);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if(connection == null)
            connection = new DBConnection().getConnection();

        return connection;
    }
}
