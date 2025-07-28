package LectorTarjeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ControllerLector {
    private Random rand = new Random();

    public void start() {
        int opcionMenuPrincipal =   0;
        System.out.println("Iniciando sistema");
        Utility.ToolBox.loading(100);
        Utility.ToolBox.clearScreen();
        System.out.println("\tMenu Principal\n1. Escanear QR\n2. Menu de administrador\n3. Salir");
        opcionMenuPrincipal = Utility.ToolBox.getConsolaEnteroPositivo("Ingrese una opcion: ", 1, 3);
        switch (opcionMenuPrincipal) {
            case 1:
                
                // escanearQR();
                break;
            case 2:
                Administrador.menuAdministrador();
                break;
            case 3:
                System.out.println("Saliendo del sistema...");  
            default:
                System.out.println("Opción no válida.");
        }
    }
}
