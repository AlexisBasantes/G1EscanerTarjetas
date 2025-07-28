package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CrearTabla {

    public static void main(String[] args) {
        crearTablaEstudiantes();
    }
    /**
     * Crea la tabla 'estudiantes' en la base de datos si no existe.
     * La tabla contiene los campos id, nombre y  correo.
     */
    public static void crearTablaEstudiantes() {
          try (Connection conn = DriverManager.getConnection("jdbc:sqlite:estudiantes.db");
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS estudiantes ("
                       + "id TEXT NOT NULL,"
                       + "nombre TEXT NOT NULL,"
                       + "correo TEXT NOT NULL,"
                       + "carrera TEXT NOT NULL,"
                       + "semestre TEXT NOT NULL"
                       + ");";

            stmt.execute(sql);
            System.out.println("Tabla creada correctamente.");

        } catch (Exception e) {
            System.err.println("Error al crear la tabla:");
            e.printStackTrace();
        }
    }
}

