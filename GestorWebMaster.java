import java.util.ArrayList;
import java.util.List;

// Gestiona la colección de WebMaster en memoria y su persistencia
public class GestorWebMaster {

    // Fichero independiente para los WebMaster (separado del de UsuarioWeb)
    private static final String FICHERO = "webmasters.txt";

    private List<WebMaster> webmasters;

    public GestorWebMaster() {
        webmasters = new ArrayList<>();
        cargarDesdeFichero();
    }

    private void cargarDesdeFichero() {
        List<String> lineas = GestorFichero.leerLineas(FICHERO);
        for (String linea : lineas) {
            try {
                webmasters.add(WebMaster.fromFileString(linea));
            } catch (IllegalArgumentException e) {
                System.out.println("Línea ignorada (formato incorrecto): " + linea);
            }
        }
    }

    private void guardarEnFichero() {
        List<String> lineas = new ArrayList<>();
        for (WebMaster wm : webmasters) {
            lineas.add(wm.toFileString()); // WebMaster.toFileString() incluye 8 campos
        }
        GestorFichero.escribirLineas(FICHERO, lineas);
    }

    // anyadir(): el nik también debe ser único entre los WebMaster
    public boolean anyadir(WebMaster nuevo) {
        if (buscarPorNik(nuevo.getNik()) != null) {
            System.out.println("ERROR: Ya existe un WebMaster con el nik '" + nuevo.getNik() + "'.");
            return false;
        }
        webmasters.add(nuevo);
        guardarEnFichero();
        System.out.println("WebMaster '" + nuevo.getNik() + "' añadido correctamente.");
        return true;
    }

    public void listar() {
        if (webmasters.isEmpty()) {
            System.out.println("No hay WebMasters registrados.");
            return;
        }
        System.out.println("\nLista de WebMasters (" + webmasters.size() + "):");
        for (int i = 0; i < webmasters.size(); i++) {
            System.out.println((i + 1) + ". " + webmasters.get(i).mostrarDatos());
        }
    }

    public WebMaster buscarPorNik(String nik) {
        for (WebMaster wm : webmasters) {
            if (wm.getNik().equalsIgnoreCase(nik)) {
                return wm;
            }
        }
        return null;
    }

    // editar(): actualiza todos los campos editables de un WebMaster existente.
    public boolean editar(String nik, WebMaster datosNuevos) {
        WebMaster existente = buscarPorNik(nik);
        if (existente == null) {
            System.out.println("ERROR: No se encontró el WebMaster con nik '" + nik + "'.");
            return false;
        }
        // Campos heredados de Persona
        existente.setNombre(datosNuevos.getNombre());
        existente.setApellido1(datosNuevos.getApellido1());
        existente.setApellido2(datosNuevos.getApellido2());
        existente.setPais(datosNuevos.getPais());
        // Campo heredado de UsuarioWeb
        existente.setPassword(datosNuevos.getPassword());
        // Campos propios de WebMaster
        existente.setFuncion(datosNuevos.getFuncion());
        existente.setFoto(datosNuevos.getFoto());

        guardarEnFichero();
        System.out.println("WebMaster '" + nik + "' actualizado correctamente.");
        return true;
    }

    public boolean borrar(String nik) {
        WebMaster aEliminar = buscarPorNik(nik);
        if (aEliminar == null) {
            System.out.println("ERROR: No se encontró el WebMaster con nik '" + nik + "'.");
            return false;
        }
        webmasters.remove(aEliminar);
        guardarEnFichero();
        System.out.println("WebMaster '" + nik + "' eliminado correctamente.");
        return true;
    }
}