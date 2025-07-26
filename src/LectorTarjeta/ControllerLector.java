package LectorTarjeta;

public class ControllerLector {


    public void startRead() {
        // Simular estudiante
        Estudiante estudiante = new Estudiante(
            "...............",
            "...............",
            "Computación",
            "..............."
        );

        
        System.out.println(" Tarjeta escaneada. Datos del estudiante:");
        System.out.println(estudiante);

        
    }
}
