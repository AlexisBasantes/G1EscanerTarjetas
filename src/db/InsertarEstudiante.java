package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertarEstudiante {

    /**
     * Inserta un estudiante en la tabla 'estudiantes' con la estructura actualizada.
     * 
     * @param id        El ID del estudiante (se convertirá a Integer).
     * @param nombre    El nombre del estudiante (primera letra en mayúscula).
     * @param correo    El correo del estudiante.
     * @param carrera   La carrera (se convertirá a Integer).
     * @param semestre  El semestre del estudiante.
     */
    public void insertarEstudiante(String id, String nombre, String correo, String carrera, String semestre) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:estudiantes.db")) {

            // Se insertan las columnas (Admin se asigna por defecto)
            String sql = "INSERT INTO estudiantes (ID, Nombre, Correo, Carrera, Semestre, Admin) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(id));
            pstmt.setString(2, nombre);
            pstmt.setString(3, correo);
            pstmt.setString(4, carrera);
            pstmt.setInt(5, Integer.parseInt(semestre));
            pstmt.setInt(6, 0);

            pstmt.executeUpdate();
            System.out.println("Estudiante insertado correctamente.");

        } catch (Exception e) {
            System.err.println("Error al insertar estudiante:");
            e.printStackTrace();
        }
    }
}
