package LectorTarjeta;

public class Estudiante extends Persona {
    private String id;
    private String nombre;
    private String carrera;
    private String semestre;
    private String correo;

    /*
     * Contructor de la clase Estudiante.
     */
    public Estudiante(String id, String nombre, String carrera, String semestre, String correo) {
        super(nombre);
        this.id         = id;
        this.nombre     = nombre;
        this.carrera    = carrera;
        this.semestre   = semestre;
        this.correo     = correo;
    }

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCarrera() { return carrera; }
    public String getsemestre() { return semestre; }
    public String getCorreo() { return correo; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCarrera(String carrera) { this.carrera = carrera; }
    public void setsemestre(String semestre) { this.semestre = semestre; }
    public void setCorreo(String correo) { this.correo = correo; }
}
