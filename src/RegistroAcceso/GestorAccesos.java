package RegistroAcceso;

import java.util.ArrayList;
import java.util.List;

public class GestorAccesos {
    private List<RegistroAcceso> accesos;

    public GestorAccesos() {
        accesos = new ArrayList<>();
    }

    public void registrarAcceso(String nombreEstudiante) {
        RegistroAcceso nuevoAcceso = new RegistroAcceso(nombreEstudiante, java.time.LocalDateTime.now());
        accesos.add(nuevoAcceso);
        System.out.println("Acceso registrado: " + nuevoAcceso);
    }

    public List<RegistroAcceso> obtenerAccesos() {
        return accesos;
    }

    public void mostrarAccesos() {
        System.out.println("📋 Historial de accesos:");
        for (RegistroAcceso acceso : accesos) {
            System.out.println(acceso);
        }
    }
}