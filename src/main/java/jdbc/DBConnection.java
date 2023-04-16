package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    String url ="jdbc:sqlserver://localhost:1433;"
            +"databaseName = batchmanagement;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";
    String user = "sa";
    String pass = "29katarina";
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url,user,pass);
        return connection;
    }
}
