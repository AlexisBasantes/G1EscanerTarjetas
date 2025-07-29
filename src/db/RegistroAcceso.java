package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RegistroAcceso {

    /**
     * Verifica si un estudiante existe en la tabla 'estudiantes'.
     * 
     * @param estudianteID El ID del estudiante.
     * @return true si el estudiante existe, false en caso contrario.
     */
    private static boolean existeEstudiante(int estudianteID) {
        String sql = "SELECT COUNT(*) AS total FROM estudiantes WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:estudiantes.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, estudianteID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int total = rs.getInt("total");
                    return total > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia del estudiante:");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Registra el acceso de un estudiante en la tabla 'registros' de la base de datos.
     * 
     * @param estudianteID El ID del estudiante.
     */
    public static void registrarAcceso(int estudianteID) {
        // Primero se verifica la existencia del estudiante
        if (!existeEstudiante(estudianteID)) {
            System.out.println("El estudiante con ID " + estudianteID + " no existe en la base de datos.");
            return;
        }
        
        // Obtiene la fecha y hora actuales
        String fecha = LocalDate.now().toString();
        String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        
        String url = "jdbc:sqlite:registros.db";
        String sql = "INSERT INTO registros (IDEstudiante, Fecha, Hora) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, estudianteID);
            pstmt.setString(2, fecha);
            pstmt.setString(3, hora);

            pstmt.executeUpdate();
            System.out.println("Registro de acceso insertado correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al registrar acceso:");
            e.printStackTrace();
        }
    }
    
    // Método main para prueba
    public static void main(String[] args) {
        // Prueba: registra acceso para el estudiante con ID 1
        registrarAcceso(1234);
    }
}
