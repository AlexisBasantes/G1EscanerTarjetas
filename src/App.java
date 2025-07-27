import LectorTarjeta.ControllerLector;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ControllerLector lector = new ControllerLector();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Registro de estudiantes ===");

        System.out.print("Ingrese ID: ");
        String id = scanner.nextLine();

        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese carrera: ");
        String carrera = scanner.nextLine();

        System.out.print("Ingrese nivel: ");
        String nivel = scanner.nextLine();

        
        lector.registrarEstudiante(id, nombre, carrera, nivel);

        System.out.println("\n=== Simulación de escaneo de QR ===");
        System.out.print("Ingrese el código QR escaneado: ");
        String qrEscaneado = scanner.nextLine();

        
        lector.buscarEstudiantePorQR(qrEscaneado);

        scanner.close();
    }
}
