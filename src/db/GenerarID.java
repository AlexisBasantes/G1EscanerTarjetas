package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class GenerarID {

    /*
     * Genera un ID único para un estudiante.
     * El ID es un número entre 1000 y 9999.
     */
    public int generarIdUnico() {
        int id;
        do {
            id = 1000 + (int)(Math.random() * 9000);
        } while (idExiste(id));
        return id;
    }

    /**
     * Comprueba que el ID no exista en la base de datos.
     * @param id El ID a verificar.
     * @return true si el ID ya existe, false si es único.
     */
    private static boolean idExiste(int id) {
        String sql = "SELECT id FROM estudiantes WHERE id = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:estudiantes.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeQuery().next(); 
        } catch (Exception e) {
            e.printStackTrace();
            return true; 
        }
    }

}
