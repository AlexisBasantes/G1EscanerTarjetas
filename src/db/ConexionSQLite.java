package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQLite {
    private static final String URL = "jdbc:sqlite:src/bd/estudiantes.db";

    /**
     * Establece una conexión a la base de datos SQLite.
     *
     * @return Connection objeto de conexión a la base de datos.
     */
    public static Connection conectar() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Conexión exitosa a SQLite.");
        } catch (SQLException e) {
            System.out.println("Error al conectar con SQLite: " + e.getMessage());
        }
        return conn;
    }
}
