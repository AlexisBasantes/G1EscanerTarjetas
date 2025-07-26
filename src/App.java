import LectorTarjeta.ControllerLector;
import RegistroAcceso.GestorAccesos;

public class App {
    public static void main(String[] args) {

        ControllerLector lector = new ControllerLector();
        lector.startRead();

        GestorAccesos gestorAccesos = new GestorAccesos();
        gestorAccesos.mostrarAccesos();
    }
}