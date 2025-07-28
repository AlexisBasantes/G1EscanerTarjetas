package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertarEstudiante {
    public static void insertarEstudiante(String nombre, String correo, String carrera, int semestre) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:src/bd/estudiantes.db")) {

            String sql = "INSERT INTO estudiantes(nombre, correo, carrera, semestre) VALUES (?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombre);
            pstmt.setString(2, correo);
            pstmt.setString(3, carrera);
            pstmt.setInt(4, semestre);

            pstmt.executeUpdate();
            System.out.println("Estudiante insertado correctamente.");

        } catch (Exception e) {
            System.err.println("Error al insertar estudiante:");
            e.printStackTrace();
        }
    }
}
