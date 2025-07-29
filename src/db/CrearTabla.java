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
     * La tabla contiene las columnas actualizadas:
     * - ID, Admin: de tipo Integer.
     * - Nombre, Correo, Semestre: de tipo Text.
     * - Carrera: de tipo Integer.
     */
    public static void crearTablaEstudiantes() {
          try (Connection conn = DriverManager.getConnection("jdbc:sqlite:estudiantes.db");
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS estudiantes ("
                       + "ID INTEGER NOT NULL,"
                       + "Nombre TEXT NOT NULL,"
                       + "Correo TEXT NOT NULL,"
                       + "Carrera TEXT NOT NULL,"
                       + "Semestre INTEGER NOT NULL,"
                       + "Admin INTEGER NOT NULL"
                       + ");";

            stmt.execute(sql);
            System.out.println("Tabla creada correctamente.");

        } catch (Exception e) {
            System.err.println("Error al crear la tabla:");
            e.printStackTrace();
        }
    }
}

