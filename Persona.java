// Clase base (padre) del modelo de herencia.
// Contiene los datos personales comunes a cualquier usuario.
public class Persona {

    // Atributos privados: solo accesibles desde dentro de esta clase.
    // Para acceder desde fuera usaremos los métodos get/set.
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String pais;

    // Constructor: se llama automáticamente al crear un objeto de tipo Persona.
    // Recibe todos los datos necesarios para inicializarla.
    public Persona(String nombre, String ap1, String ap2, String pais) {
        this.nombre    = nombre;   // "this." distingue el atributo del parámetro
        this.apellido1 = ap1;
        this.apellido2 = ap2;
        this.pais      = pais;
    }

    // Getters: devuelven el valor del atributo correspondiente.

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getPais() {
        return pais;
    }

    // Setters: permiten cambiar el valor de cada atributo desde fuera de la clase.

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido1(String nombre) {
        // El parámetro se llama "nombre" como en el diagrama UML,
        // aunque semánticamente representa el primer apellido.
        this.apellido1 = nombre;
    }

    public void setApellido2(String nombre) {
        this.apellido2 = nombre;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    // mostrarDatos(): devuelve una cadena con la información básica de la persona.
    // Las subclases sobrescribirán (override) este método para añadir sus propios datos.
    public String mostrarDatos() {
        return "Nombre: " + nombre + " " + apellido1 + " " + apellido2
                + " | País: " + pais;
    }
}