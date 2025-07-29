package LectorTarjeta;

public class ControllerLector {

    public void start() {
        int opcionMenuPrincipal;

        // Bucle para que el menú se repita hasta que el usuario elija salir.
        do {
            System.out.println("\nIniciando sistema");
            Utility.ToolBox.loading(100);
            Utility.ToolBox.clearScreen();

            System.out.print("\t--- Menú Principal ---\n"
                    + "1. Escanear QR\n"
                    + "2. Menú de administrador\n"
                    + "3. Salir\n");
            
            opcionMenuPrincipal = Utility.ToolBox.getConsolaEnteroPositivo("Ingrese una opción: ", 1, 3);

            switch (opcionMenuPrincipal) {
                case 1:
                    System.out.println("\nFunción 'Escanear QR' no implementada todavía.");
                    
                    Utility.ToolBox.getConsolaString("\nPresione Enter para continuar...");
                    break;
                
                case 2:
                    // --- ESTE ES EL CAMBIO PRINCIPAL ---
                    // Ahora llama al método que primero verifica ID y contraseña.
                    Administrador.autenticarYMostrarMenu();
                    break;
                
                case 3:
                    System.out.println("\nSaliendo del sistema...");
                    break;
                
                default:
                    
                    System.out.println("\nOpción no válida.");
                    break;
            }

        } while (opcionMenuPrincipal != 3); // El bucle continúa mientras la opción no sea 3.
    }
}
