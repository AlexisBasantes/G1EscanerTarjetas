package LectorTarjeta;

public class ControllerLector {

    /**
     * Método principal que inicia el sistema y muestra el menú.
     * Permite al usuario escanear un código QR o acceder al menú de administrador.
     */
    public void start() {
        int opcionMenuPrincipal;

        System.out.println("\nIniciando sistema");
            Utility.ToolBox.loading(50);
        do {

            Utility.ToolBox.clearScreen();
            System.out.print("\t--- Menú Principal ---\n"
                    + "1. Escanear QR\n"
                    + "2. Menú de administrador\n"
                    + "3. Salir\n");
            
            opcionMenuPrincipal = Utility.ToolBox.getConsolaEnteroPositivo("Ingrese una opción: ", 1, 3);

            switch (opcionMenuPrincipal) {
                case 1:
                    Utility.ToolBox.clearScreen();
                    Utility.ToolBox.simulacionEscaneo("Estudiante");
                    db.RegistroAcceso.registrarAcceso(8325);
                    break;
                case 2:
                    Utility.ToolBox.simulacionEscaneo("Administrador");
                    Administrador.autenticarYMostrarMenu();
                    break;
                
                case 3:
                    System.out.println("\nSaliendo del sistema...");
                    break;
                
                default:
                    
                    System.out.println("\nOpción no válida.");
                    break;
            }

        } while (opcionMenuPrincipal != 3);
    }
}
