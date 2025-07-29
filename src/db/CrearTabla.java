package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CrearTabla {

    public static void main(String[] args) {
        crearTablaRegistros();
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

    /**
     * Crea la tabla 'registros' en la base de datos si no existe.
     * La tabla contiene las columnas:
     * - EstudianteID: de tipo Integer, referencia a ID en estudiantes.
     * - Fecha: de tipo Text.
     * - Hora: de tipo Text.
     */
    public static void crearTablaRegistros() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:registros.db");
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS registros ("
                       + "IDEstudiante INTEGER NOT NULL,"
                       + "Fecha TEXT NOT NULL,"
                       + "Hora TEXT NOT NULL,"
                       + "FOREIGN KEY (IDEstudiante) REFERENCES estudiantes(ID)"
                       + ");";

            stmt.execute(sql);
            System.out.println("Tabla de registros creada correctamente.");

        } catch (Exception e) {
            System.err.println("Error al crear la tabla de registros:");
            e.printStackTrace();
        }
    }
}

