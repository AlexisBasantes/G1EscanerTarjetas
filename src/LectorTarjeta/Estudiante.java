package LectorTarjeta;

public class Estudiante extends Persona {
    private String carrera;
    private String nivel;

    public Estudiante(String nombre, String id, String carrera, String nivel) {
        super(nombre, id);
        this.carrera = carrera;
        this.nivel = nivel;
    }

    public String getCarrera() {
        return carrera;
    }

    public String getNivel() {
        return nivel;
    }

    @Override
    public String toString() {
        return " Estudiante " +
            " Nombre= '" + getNombre() + '\'' +
            ", id='" + getId() + '\'' +
            ", Carrera='" + carrera + '\'' +
            '}';
    }
}
