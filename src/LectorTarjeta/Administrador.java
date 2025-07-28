package LectorTarjeta;

import java.util.ArrayList;
import java.util.List;

import LectorTarjeta.QR.GeneradorQR;

public class Administrador extends Persona {

    private String id;
    private String nombre;
    private String correo;

    /*
     * Constructor de la clase Administrador.
     */
    public Administrador(String id, String nombre, String carrera, String nivel, String correo) {
        super(nombre);
        this.id     = id;
        this.nombre = nombre;
        this.correo = correo;
    }

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getcorreo() { return correo; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setcorreo(String correo) { this.correo = correo; }

    private static List<Estudiante> listaEstudiantes = new ArrayList<>();

    /**
     * Método para mostrar el menú del administrador.
     */
    public static void menuAdministrador() {

        // Poner opcion para que escanee el QR del administrador e iniciar el menu

        Utility.ToolBox.loading(75);
        Utility.ToolBox.clearScreen();
        System.out.println("\tBienvenido al menú del Administrador\n1. Registrar estudiante\n2. Buscar estudiante\n3. Actualizar estudiante\n4. Eliminar estudiante\n5. Salir");
        int opcionAdmin = Utility.ToolBox.getConsolaEnteroPositivo("Ingrese una opcion: ", 1, 5);
        switch (opcionAdmin) {
            case 1:
                registrarEstudiante();
                break;
            case 2:
                // buscarEstudiante();
                break;
            case 3:
                // actualizarEstudiante();
                break;
            case 4:
                // eliminarEstudiante();
                break;
            case 5:
                System.out.println("Saliendo del menú del Administrador...");
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }

    }

    /**
     * Método para registrar un nuevo estudiante.
     */
    public static void registrarEstudiante() {

        String id       = Utility.ToolBox.getConsolaString("Ingrese el ID del estudiante: ");
        String nombre   = Utility.ToolBox.getConsolaString("Ingrese el nombre del estudiante: ");
        String carrera  = Utility.ToolBox.getConsolaString("Ingrese la carrera del estudiante: ");
        String semestre = Utility.ToolBox.getConsolaString("Ingrese el semestre del estudiante: ");
        String correo   = Utility.ToolBox.getConsolaString("Ingrese el correo del estudiante: ");

        if (existeEstudiante(id)) {
            System.out.println("Error: Ya existe un estudiante con ID: " + id);
            return;
        }

        GeneradorQR.generarQR(id, correo, nombre);

        Estudiante e = new Estudiante(id, nombre, carrera, semestre, correo);
        listaEstudiantes.add(e);

        System.out.println("Estudiante registrado correctamente.");
        System.out.println("Nombre: "   + nombre);
        System.out.println("Carrera: "  + carrera);
        System.out.println("Semestre: " + semestre);
        System.out.println("Correo: "   + correo);
    }

    /**
     * Método para verificar si un estudiante ya existe por su ID.
     * @param id El ID del estudiante a verificar.
     * 
     */
    public static boolean existeEstudiante(String id) {
        for (Estudiante estudiante : listaEstudiantes) {
            if (estudiante.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

}