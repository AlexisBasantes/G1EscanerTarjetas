package LectorTarjeta;

import LectorTarjeta.QR.GeneradorQR;
import db.BuscarEstudiante;
import db.GenerarID;
import db.InsertarEstudiante;
import db.eliminarEstudiante;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Administrador extends Persona {

    // --- CONSTANTES PARA LA AUTENTICACIÓN ---
   
    private static final String URL_DATABASE = "jdbc:sqlite:estudiantes.db"; 
    private static final String CONTRASENA_ADMIN = "admin02";

    private String id;
    private String nombre;
    private String correo;

    public Administrador(String id, String nombre, String carrera, String nivel, String correo) {
        super(nombre); 
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }

    // --- Getters ---
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getcorreo() { return correo; }

    // --- Setters ---
    public void setId(String id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setcorreo(String correo) { this.correo = correo; }

    // --- NUEVO MÉTODO DE ENTRADA (PUNTO DE ACCESO) ---
    /**
     * Pide credenciales (ID y contraseña) y si son correctas, muestra el menú de administrador.
   
     */
    public static void autenticarYMostrarMenu() {
        System.out.println(); // Salto de línea para claridad

        Utility.ToolBox.clearScreen();

        Utility.ToolBox.loading(100);

        Utility.ToolBox.clearScreen();

        String idIngresado = Utility.ToolBox.getConsolaString("Por favor, ingrese su ID de usuario: ");

        // 1. Comprueba si el usuario tiene permisos de administrador en la BD
        if (esUsuarioAdmin(idIngresado)) {

            Utility.ToolBox.clearScreen();

            System.out.println("\n✅ El usuario tiene permisos de administrador.");

            Utility.ToolBox.loading(100);

            Utility.ToolBox.clearScreen();

            // 2. Pide la contraseña universal
            String contrasenaIngresada = Utility.ToolBox.getConsolaString("Ingrese la contraseña de administrador: ");

            // 3. Valida la contraseña
            if (contrasenaIngresada.equals(CONTRASENA_ADMIN)) {
                // Si todo es correcto, llama al menú original del administrador
                menuAdministrador();
            } else {
                System.out.println("\n❌ Contraseña incorrecta.");
                Utility.ToolBox.getConsolaString("\nPresione Enter para continuar...");
            }
        } else {
            // Mensaje si el usuario no es admin o no existe en la BD
            System.out.println("\n❌ El usuario no posee permisos de administrador.");
            Utility.ToolBox.getConsolaString("\nPresione Enter para continuar...");
        }
    }

    /**
     * Método privado para verificar los permisos en la base de datos.
     * @param userId El ID del usuario a verificar.
     * @return true si el usuario existe y su columna 'Admin' es 1.
     */
    private static boolean esUsuarioAdmin(String userId) {
       
        String sql = "SELECT Admin FROM estudiantes WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL_DATABASE);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userId); // El ID es un String en tu código

            try (ResultSet rs = pstmt.executeQuery()) {
                // Si se encontró un resultado y la columna 'Admin' es 1, retorna true
                if (rs.next() && rs.getInt("Admin") == 1) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la base de datos: " + e.getMessage());
        }
        
        // Retorna false si el usuario no existe, no es admin, o si hubo un error
        return false;
    }

    
    
    /**
     * Menú principal del administrador.
     */
    public static void menuAdministrador() {
        boolean salir = false;

        do {
            Utility.ToolBox.loading(75);
            Utility.ToolBox.clearScreen();
            System.out.print("\tBienvenido al menú del Administrador\n"
                    + "1. Registrar estudiante\n"
                    + "2. Buscar estudiante\n"
                    + "3. Actualizar estudiante\n"
                    + "4. Eliminar estudiante\n"
                    + "5. Salir\n");

            int opcionAdmin = Utility.ToolBox.getConsolaEnteroPositivo("Ingrese una opcion: ", 1, 5);

            switch (opcionAdmin) {
                case 1:
                    registrarEstudiante();
                    break;
                case 2:
                    buscarEstudiante();
                    break;
                case 3:
                    actualizarEstudiante();
                    break;
                case 4:
                    eliminarEstudiante();
                    break;
                case 5:
                    salir = true;
                    System.out.println("Saliendo del menú del Administrador...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

            // Pide presionar Enter solo si no se ha elegido la opción de salir
            if (!salir) {
                Utility.ToolBox.getConsolaString("\nPresione Enter para continuar...");
            }

        } while (!salir);
    }

    public static void registrarEstudiante() {
        try {
            String nombre   = Utility.ToolBox.getConsolaString("Ingrese el nombre del estudiante: ");
            String carrera  = Utility.ToolBox.getConsolaString("Ingrese la carrera del estudiante: ");
            String semestre = Utility.ToolBox.getConsolaString("Ingrese el semestre del estudiante: ");
            String correo   = Utility.ToolBox.getConsolaString("Ingrese el correo del estudiante: ");

            GenerarID idGenerator = new GenerarID();
            String id = String.valueOf(idGenerator.generarIdUnico());

            GeneradorQR.generarQR(id, correo, nombre);

            InsertarEstudiante insertarEstudiante = new InsertarEstudiante();
            insertarEstudiante.insertarEstudiante(id, nombre, carrera, semestre, correo);

            System.out.println("Estudiante registrado correctamente.");
            System.out.println("Nombre: "   + nombre);
            System.out.println("ID: "       + id);
            System.out.println("Carrera: "  + carrera);
            System.out.println("Semestre: " + semestre);
            System.out.println("Correo: "   + correo);

        } catch (Exception e) {
            System.out.println("Ocurrió un error al registrar el estudiante: " + e.getMessage());
        }
    }

    public static void buscarEstudiante() {
        boolean volver = false;

        do {
            Utility.ToolBox.clearScreen();
            System.out.print("\tBuscar Estudiante\n1. Buscar por ID\n2. Buscar por nombre\n3. Volver al menú principal\n");
            int opcionBusqueda = Utility.ToolBox.getConsolaEnteroPositivo("Ingrese una opción: ", 1, 3);

            switch (opcionBusqueda) {
                case 1:
                    String id = Utility.ToolBox.getConsolaString("Ingrese el ID del estudiante: ");
                    BuscarEstudiante.buscarPorId(id);
                    break;
                case 2:
                    String nombre = Utility.ToolBox.getConsolaString("Ingrese el nombre o parte del nombre: ");
                    BuscarEstudiante.buscarPorNombre(nombre);
                    break;
                case 3:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

            if (!volver) {
                Utility.ToolBox.getConsolaString("\nPresione Enter para continuar...");
            }

        } while (!volver);
    }

    public static void actualizarEstudiante() {
        String id = Utility.ToolBox.getConsolaString("Ingrese el ID del estudiante a actualizar: ");

        try (Connection conn = DriverManager.getConnection(URL_DATABASE)) {
            String sqlBuscar = "SELECT * FROM estudiantes WHERE id = ?";
            PreparedStatement stmtBuscar = conn.prepareStatement(sqlBuscar);
            stmtBuscar.setString(1, id);
            ResultSet rs = stmtBuscar.executeQuery();

            if (!rs.next()) {
                System.out.println("No se encontró un estudiante con el ID proporcionado.");
                return;
            }

            System.out.println("\nDatos actuales del estudiante:");
            System.out.println("Nombre: " + rs.getString("nombre"));
            System.out.println("Correo: " + rs.getString("correo"));

            String nuevoNombre = Utility.ToolBox.getConsolaString("Ingrese el nuevo nombre: ");
            String nuevoCorreo = Utility.ToolBox.getConsolaString("Ingrese el nuevo correo: ");

            String sqlUpdate = "UPDATE estudiantes SET nombre = ?, correo = ? WHERE id = ?";
            PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
            stmtUpdate.setString(1, nuevoNombre);
            stmtUpdate.setString(2, nuevoCorreo);
            stmtUpdate.setString(3, id);

            int filasAfectadas = stmtUpdate.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Estudiante actualizado correctamente.");
            } else {
                System.out.println("Error al actualizar el estudiante.");
            }

        } catch (SQLException e) {
            System.out.println("Error en la base de datos: " + e.getMessage());
        }
    }

    public static void eliminarEstudiante() {
        String id = Utility.ToolBox.getConsolaString("Ingrese el ID del estudiante a eliminar: ");

        System.out.println("\nVerificando existencia del estudiante...");
        BuscarEstudiante.buscarPorId(id);  // Muestra los datos si existe

        String confirmacion = Utility.ToolBox.getConsolaString("\n¿Desea eliminar este estudiante? (s/n): ");
        if (confirmacion.equalsIgnoreCase("s")) {
            eliminarEstudiante.eliminarPorId(id);
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }
}