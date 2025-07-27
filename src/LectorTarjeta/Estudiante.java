package LectorTarjeta;

public class Estudiante {
    private String id;
    private String nombre;
    private String carrera;
    private String nivel;
    private String codigoQR;

    public Estudiante(String id, String nombre, String carrera, String nivel, String codigoQR) {
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
        this.nivel = nivel;
        this.codigoQR = codigoQR;
    }

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCarrera() { return carrera; }
    public String getNivel() { return nivel; }
    public String getCodigoQR() { return codigoQR; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCarrera(String carrera) { this.carrera = carrera; }
    public void setNivel(String nivel) { this.nivel = nivel; }
    public void setCodigoQR(String codigoQR) { this.codigoQR = codigoQR; }
}
