package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertarEstudiante {
    public void insertarEstudiante(String id, String nombre, String correo, String carrera, String semestre) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:estudiantes.db")) {

            String sql = "INSERT INTO estudiantes (id, nombre, correo, carrera, semestre) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, nombre);
            pstmt.setString(3, correo);
            pstmt.setString(4, carrera);
            pstmt.setString(5, semestre);

            pstmt.executeUpdate();
            System.out.println("Estudiante insertado correctamente.");

        } catch (Exception e) {
            System.err.println("Error al insertar estudiante:");
            e.printStackTrace();
        }
    }
}
