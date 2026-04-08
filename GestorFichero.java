import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Clase de utilidad con métodos estáticos que centraliza todas las operaciones de lectura y escritura en ficheros, separando la lógica de negocio de la lógica de persistencia (principio de responsabilidad única).
public class GestorFichero {

    // leerLineas(): lee todas las líneas de un fichero y las devuelve como lista.
    // Si el fichero no existe todavía devuelve una lista vacía
    // (comportamiento normal al arrancar por primera vez).
    public static List<String> leerLineas(String rutaFichero) {
        List<String> lineas = new ArrayList<>();

        // Comprobamos la existencia antes de intentar abrir el fichero
        File fichero = new File(rutaFichero);
        if (!fichero.exists()) {
            return lineas;
        }

        // BufferedReader envuelve a FileReader para leer línea a línea eficientemente
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            // readLine() devuelve null al llegar al final del fichero
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) { // ignoramos líneas vacías
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR al leer el fichero " + rutaFichero + ": " + e.getMessage());
        }

        return lineas;
    }

    // escribirLineas(): sobreescribe completamente el fichero con la lista recibida.
    // Se llama después de cualquier operación de añadir, editar o borrar
    // para que el fichero siempre refleje el estado actual de la lista en memoria.
    public static void escribirLineas(String rutaFichero, List<String> lineas) {

        // PrintWriter con FileWriter(ruta, false) → modo sobreescritura (no append)
        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaFichero, false))) {
            for (String linea : lineas) {
                pw.println(linea);
            }
        } catch (IOException e) {
            System.out.println("ERROR al escribir en el fichero " + rutaFichero + ": " + e.getMessage());
        }
    }
}