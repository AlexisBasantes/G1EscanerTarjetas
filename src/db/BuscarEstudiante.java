package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuscarEstudiante {
    
    /**
     * Busca un estudiante por su ID y muestra su información.
     * @param id El ID del estudiante a buscar.
     */
    public static void buscarPorId(String id) {
        String sql = "SELECT * FROM estudiantes WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:estudiantes.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                System.out.println("\nInformación del estudiante:");
                System.out.println("ID: " + rs.getString("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Correo: " + rs.getString("correo"));
                System.out.println("Carrera: " + rs.getString("carrera"));
                System.out.println("Semestre: " + rs.getString("semestre"));
            } else {
                System.out.println("No se encontró ningún estudiante con el ID: " + id);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al buscar estudiante:");
            e.printStackTrace();
        }
    }
    
    /**
     * Busca estudiantes por nombre y muestra su información.
     * @param nombre El nombre o parte del nombre a buscar.
     */
    public static void buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM estudiantes WHERE nombre LIKE ?";
        
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:estudiantes.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + nombre + "%");
            ResultSet rs = pstmt.executeQuery();
            
            boolean encontrado = false;
            while (rs.next()) {
                if (!encontrado) {
                    System.out.println("\nResultados de la búsqueda:");
                    encontrado = true;
                }
                System.out.println("-------------------------");
                System.out.println("ID: " + rs.getString("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Correo: " + rs.getString("correo"));
                System.out.println("Carrera: " + rs.getString("carrera"));
                System.out.println("Semestre: " + rs.getString("semestre"));
            }
            
            if (!encontrado) {
                System.out.println("No se encontraron estudiantes con el nombre: " + nombre);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al buscar estudiante:");
            e.printStackTrace();
        }
    }
}