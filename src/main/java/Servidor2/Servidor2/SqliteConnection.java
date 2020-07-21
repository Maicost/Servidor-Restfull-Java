package Servidor2.Servidor2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnection {

    public Connection connect() throws ClassNotFoundException, SQLException{
        Connection conn;
        // db parameters
        String url = "jdbc:sqlite:/home/maico/Documentos/java/BaseDados.sql";
        // create a connection to the database
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection(url);
        return conn;
    }
}
