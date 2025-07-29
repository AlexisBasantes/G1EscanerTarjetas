package LectorTarjeta;

public class Persona{
    private String nombre;
    private String id;

    /**
     * Constructor de la clase Persona.
     *
     * @param nombre El nombre de la persona.
     * @param id 
     */
    public Persona(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
