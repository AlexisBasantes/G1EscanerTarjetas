package LectorTarjeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ControllerLector {
    private List<Estudiante> listaEstudiantes = new ArrayList<>();
    private Random rand = new Random();

    // Registra estudiante, genera QR único
    public void registrarEstudiante(String id, String nombre, String carrera, String nivel) {
        if (existeEstudiantePorId(id)) {
            System.out.println("Error: Ya existe un estudiante con ID: " + id);
            return;
        }
        String codigoQR = generarCodigoQR();
        Estudiante e = new Estudiante(id, nombre, carrera, nivel, codigoQR);
        listaEstudiantes.add(e);

        System.out.println("Estudiante registrado correctamente.");
        System.out.println("Nombre: " + nombre);
        System.out.println("Carrera: " + carrera);
        System.out.println("Nivel: " + nivel);
        System.out.println("Código QR generado: " + codigoQR);
    }

    // Busca estudiante por código QR y muestra datos
    public void buscarEstudiantePorQR(String qr) {
        for (Estudiante e : listaEstudiantes) {
            if (e.getCodigoQR().equals(qr)) {
                System.out.println("Datos del estudiante:");
                System.out.println("ID: " + e.getId());
                System.out.println("Nombre: " + e.getNombre());
                System.out.println("Carrera: " + e.getCarrera());
                System.out.println("Nivel: " + e.getNivel());
                return;
            }
        }
        System.out.println("Código QR no encontrado.");
    }

    // Genera código QR numérico único
    private String generarCodigoQR() {
        String codigo;
        do {
            codigo = String.valueOf(100000 + rand.nextInt(900000));
        } while (yaExisteQR(codigo));
        return codigo;
    }

    private boolean yaExisteQR(String qr) {
        for (Estudiante e : listaEstudiantes) {
            if (e.getCodigoQR().equals(qr)) return true;
        }
        return false;
    }

    private boolean existeEstudiantePorId(String id) {
        for (Estudiante e : listaEstudiantes) {
            if (e.getId().equals(id)) return true;
        }
        return false;
    }
}
