import java.util.ArrayList;
import java.util.List;

// Gestiona la colección de UsuarioWeb en memoria y su persistencia en el fichero "usuarios_web.txt".
public class GestorUsuarioWeb {

    // Ruta del fichero donde se almacenan los usuarios web
    private static final String FICHERO = "usuarios_web.txt";

    // Lista en memoria que se carga desde el fichero al iniciar
    // y se vuelca al fichero tras cada modificación
    private List<UsuarioWeb> usuarios;

    // Constructor: al crear el gestor cargamos automáticamente los datos del fichero
    public GestorUsuarioWeb() {
        usuarios = new ArrayList<>();
        cargarDesdeFichero();
    }

    // Lee el fichero línea a línea y reconstruye los objetos UsuarioWeb.
    // Se llama solo en el constructor.
    private void cargarDesdeFichero() {
        List<String> lineas = GestorFichero.leerLineas(FICHERO);
        for (String linea : lineas) {
            try {
                usuarios.add(UsuarioWeb.fromFileString(linea));
            } catch (IllegalArgumentException e) {
                System.out.println("Línea ignorada (formato incorrecto): " + linea);
            }
        }
    }

    // Serializa todos los usuarios de la lista y sobreescribe el fichero.
    // Se invoca después de cada operación que modifica la lista.
    private void guardarEnFichero() {
        List<String> lineas = new ArrayList<>();
        for (UsuarioWeb u : usuarios) {
            lineas.add(u.toFileString());
        }
        GestorFichero.escribirLineas(FICHERO, lineas);
    }

    // anyadir(): comprueba que el nik no esté ya en uso (debe ser único)
    // y añade el nuevo usuario a la lista y al fichero.
    public boolean anyadir(UsuarioWeb nuevo) {
        if (buscarPorNik(nuevo.getNik()) != null) {
            System.out.println("ERROR: Ya existe un usuario con el nik '" + nuevo.getNik() + "'.");
            return false;
        }
        usuarios.add(nuevo);
        guardarEnFichero();
        System.out.println("Usuario web '" + nuevo.getNik() + "' añadido correctamente.");
        return true;
    }

    // listar(): muestra por pantalla todos los usuarios web almacenados.
    public void listar() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios web registrados.");
            return;
        }
        System.out.println("\nLista de usuarios web (" + usuarios.size() + "):");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + ". " + usuarios.get(i).mostrarDatos());
        }
    }

    // buscarPorNik(): recorre la lista y devuelve el primer usuario cuyo nik coincida.
    // Devuelve null si no se encuentra ninguno.
    public UsuarioWeb buscarPorNik(String nik) {
        for (UsuarioWeb u : usuarios) {
            if (u.getNik().equalsIgnoreCase(nik)) { // ignora mayúsculas/minúsculas
                return u;
            }
        }
        return null;
    }

    // editar(): localiza al usuario por nik y actualiza sus datos con los del objeto recibido.
    // El nik actúa como clave primaria y no se modifica aquí.
    public boolean editar(String nik, UsuarioWeb datosNuevos) {
        UsuarioWeb existente = buscarPorNik(nik);
        if (existente == null) {
            System.out.println("ERROR: No se encontró el usuario con nik '" + nik + "'.");
            return false;
        }
        existente.setNombre(datosNuevos.getNombre());
        existente.setApellido1(datosNuevos.getApellido1());
        existente.setApellido2(datosNuevos.getApellido2());
        existente.setPais(datosNuevos.getPais());
        existente.setPassword(datosNuevos.getPassword());
        guardarEnFichero();
        System.out.println("Usuario web '" + nik + "' actualizado correctamente.");
        return true;
    }

    // borrar(): elimina el usuario cuyo nik coincida con el recibido.
    public boolean borrar(String nik) {
        UsuarioWeb aEliminar = buscarPorNik(nik);
        if (aEliminar == null) {
            System.out.println("ERROR: No se encontró el usuario con nik '" + nik + "'.");
            return false;
        }
        usuarios.remove(aEliminar);
        guardarEnFichero();
        System.out.println("Usuario web '" + nik + "' eliminado correctamente.");
        return true;
    }
}