// Hereda de Persona (extends Persona).
// Añade las credenciales de acceso: nick y contraseña.
public class UsuarioWeb extends Persona {

    // Además de los atributos heredados de Persona (nombre, apellidos, pais),
    // un usuario web necesita un identificador único (nik) y su contraseña.
    private String nik;
    private String password;

    // Constructor: llama al de la clase padre con super() para inicializar
    // los atributos heredados, y luego inicializa los propios.
    public UsuarioWeb(String nombre, String ap1, String ap2, String pais,
                      String nik, String clave) {
        super(nombre, ap1, ap2, pais); // super() invoca el constructor de Persona
        this.nik      = nik;
        this.password = clave;  // "clave" en el diagrama UML equivale a la contraseña
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String clave) {
        this.password = clave;
    }

    // validarPassword(): compara la clave recibida con la almacenada.
    // Devuelve true si coinciden, false en caso contrario.
    public boolean validarPassword(String clave) {
        return this.password.equals(clave);
    }

    // Sobreescribe el método de Persona para incluir también el nik.
    // La contraseña NO se muestra por seguridad.
    @Override
    public String mostrarDatos() {
        return super.mostrarDatos() + " | Nik: " + nik;
    }

    // Serializa el objeto a una línea de texto con campos separados por ';'
    // para poder guardarlo en un fichero de texto plano.
    // Formato: nombre;ap1;ap2;pais;nik;password
    public String toFileString() {
        return getNombre() + ";" + getApellido1() + ";" + getApellido2() + ";"
                + getPais()   + ";" + nik            + ";" + password;
    }

    // fromFileString(): hace el proceso inverso: lee una línea del fichero
    // y reconstruye el objeto. Es estático porque no necesita un objeto previo.
    public static UsuarioWeb fromFileString(String linea) {
        String[] partes = linea.split(";");
        if (partes.length != 6) {
            throw new IllegalArgumentException("Línea de UsuarioWeb mal formada: " + linea);
        }
        return new UsuarioWeb(partes[0], partes[1], partes[2],
                partes[3], partes[4], partes[5]);
    }
}