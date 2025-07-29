package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class eliminarEstudiante {

    /**
     * Elimina un estudiante de la base de datos por su ID.
     *
     * @param id El ID del estudiante a eliminar.
     */
    public static void eliminarPorId(String id) {
        String sql = "DELETE FROM estudiantes WHERE id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:estudiantes.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Estudiante con ID " + id + " eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún estudiante con el ID: " + id);
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar el estudiante:");
            e.printStackTrace();
        }
    }
}
