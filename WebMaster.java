// Hereda de UsuarioWeb (que a su vez hereda de Persona).
// Representa un usuario con privilegios de administración.
// Añade su función dentro del servicio y una foto de perfil.
public class WebMaster extends UsuarioWeb {

    // funcion: cargo o rol que desempeña (ej: "Administrador", "Moderador")
    // foto: ruta o nombre del archivo de imagen de perfil
    private String funcion;
    private String foto;

    // Constructor: al haber tres niveles de herencia, pasamos todos los datos hasta llegar al constructor raíz (Persona) a través de las cadenas super().
    public WebMaster(String nombre, String ap1, String ap2, String pais,
                     String nik, String clave, String funcion, String foto) {
        super(nombre, ap1, ap2, pais, nik, clave); // llama al constructor de UsuarioWeb
        this.funcion = funcion;
        this.foto    = foto;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    // Añade la función y la foto a la cadena devuelta por UsuarioWeb.mostrarDatos().
    @Override
    public String mostrarDatos() {
        return super.mostrarDatos()
                + " | Función: " + funcion
                + " | Foto: "    + foto;
    }

    // Extiende el formato de UsuarioWeb añadiendo funcion y foto al final.
    // Formato: nombre;ap1;ap2;pais;nik;password;funcion;foto
    @Override
    public String toFileString() {
        return super.toFileString() + ";" + funcion + ";" + foto;
    }

    // fromFileString(): reconstruye un objeto WebMaster a partir de una línea del fichero.
    public static WebMaster fromFileString(String linea) {
        String[] partes = linea.split(";");
        if (partes.length != 8) {
            throw new IllegalArgumentException("Línea de WebMaster mal formada: " + linea);
        }
        return new WebMaster(partes[0], partes[1], partes[2], partes[3],
                partes[4], partes[5], partes[6], partes[7]);
    }
}