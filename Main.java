import java.util.Scanner;


public class Main {

    // Scanner compartido por todos los métodos de entrada de datos.
    // Lo declaramos static para no tener que pasarlo como parámetro.
    private static final Scanner sc = new Scanner(System.in);

    // Gestores que encapsulan el acceso a los ficheros
    private static final GestorUsuarioWeb gestorWeb = new GestorUsuarioWeb();
    private static final GestorWebMaster  gestorWM  = new GestorWebMaster();

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE USUARIOS ===");

        int opcion;
        do {
            opcion = leerMenuPrincipal();

            switch (opcion) {
                case 1 -> menuUsuariosWeb();
                case 2 -> menuWebMasters();
                case 0 -> System.out.println("Saliendo... ¡Hasta pronto!");
                default -> System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 0);

        sc.close();
    }

    private static int leerMenuPrincipal() {
        System.out.println("\nMenú principal:");
        System.out.println("1. Gestionar Usuarios Web");
        System.out.println("2. Gestionar WebMasters");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
        return leerEntero();
    }

    // Submenú de Usuarios Web: gestión completa con opciones CRUD
    private static void menuUsuariosWeb() {
        int opcion;
        do {
            System.out.println("\nGestión de Usuarios Web:");
            System.out.println("1. Añadir usuario web");
            System.out.println("2. Listar usuarios web");
            System.out.println("3. Buscar usuario web por nik");
            System.out.println("4. Editar usuario web");
            System.out.println("5. Borrar usuario web");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> anyadirUsuarioWeb();
                case 2 -> gestorWeb.listar();
                case 3 -> buscarUsuarioWeb();
                case 4 -> editarUsuarioWeb();
                case 5 -> borrarUsuarioWeb();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    // Pide los datos por teclado y añade un nuevo UsuarioWeb
    private static void anyadirUsuarioWeb() {
        System.out.println("\nAñadir usuario web:");
        System.out.print("Nombre: ");       String nombre = sc.nextLine().trim();
        System.out.print("Apellido 1: ");   String ap1    = sc.nextLine().trim();
        System.out.print("Apellido 2: ");   String ap2    = sc.nextLine().trim();
        System.out.print("País: ");         String pais   = sc.nextLine().trim();
        System.out.print("Nik: ");          String nik    = sc.nextLine().trim();
        System.out.print("Contraseña: ");   String clave  = sc.nextLine().trim();

        UsuarioWeb nuevo = new UsuarioWeb(nombre, ap1, ap2, pais, nik, clave);
        gestorWeb.anyadir(nuevo);
    }

    // Busca un UsuarioWeb por nik y muestra sus datos si existe
    private static void buscarUsuarioWeb() {
        System.out.print("Introduce el nik a buscar: ");
        String nik = sc.nextLine().trim();
        UsuarioWeb encontrado = gestorWeb.buscarPorNik(nik);
        if (encontrado != null) {
            System.out.println("Encontrado: " + encontrado.mostrarDatos());
        } else {
            System.out.println("No se encontró ningún usuario con nik '" + nik + "'.");
        }
    }

    // Pide el nik a editar, verifica que existe y solicita los nuevos datos
    private static void editarUsuarioWeb() {
        System.out.print("Nik del usuario a editar: ");
        String nik = sc.nextLine().trim();

        if (gestorWeb.buscarPorNik(nik) == null) {
            System.out.println("No existe un usuario con ese nik.");
            return;
        }

        System.out.println("Introduce los nuevos datos:");
        System.out.print("Nuevo nombre: ");       String nombre = sc.nextLine().trim();
        System.out.print("Nuevo apellido 1: ");   String ap1    = sc.nextLine().trim();
        System.out.print("Nuevo apellido 2: ");   String ap2    = sc.nextLine().trim();
        System.out.print("Nuevo país: ");         String pais   = sc.nextLine().trim();
        System.out.print("Nueva contraseña: ");   String clave  = sc.nextLine().trim();

        // Creamos un objeto temporal con los datos nuevos para pasarlo al gestor
        UsuarioWeb datosNuevos = new UsuarioWeb(nombre, ap1, ap2, pais, nik, clave);
        gestorWeb.editar(nik, datosNuevos);
    }

    private static void borrarUsuarioWeb() {
        System.out.print("Nik del usuario a borrar: ");
        String nik = sc.nextLine().trim();
        gestorWeb.borrar(nik);
    }

    // Submenú de WebMasters: gestión completa con opciones CRUD
    private static void menuWebMasters() {
        int opcion;
        do {
            System.out.println("\nGestión de WebMasters:");
            System.out.println("1. Añadir WebMaster");
            System.out.println("2. Listar WebMasters");
            System.out.println("3. Buscar WebMaster por nik");
            System.out.println("4. Editar WebMaster");
            System.out.println("5. Borrar WebMaster");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> anyadirWebMaster();
                case 2 -> gestorWM.listar();
                case 3 -> buscarWebMaster();
                case 4 -> editarWebMaster();
                case 5 -> borrarWebMaster();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    // Pide los datos por teclado y añade un nuevo WebMaster
    private static void anyadirWebMaster() {
        System.out.println("\nAñadir WebMaster:");
        System.out.print("Nombre: ");       String nombre  = sc.nextLine().trim();
        System.out.print("Apellido 1: ");   String ap1     = sc.nextLine().trim();
        System.out.print("Apellido 2: ");   String ap2     = sc.nextLine().trim();
        System.out.print("País: ");         String pais    = sc.nextLine().trim();
        System.out.print("Nik: ");          String nik     = sc.nextLine().trim();
        System.out.print("Contraseña: ");   String clave   = sc.nextLine().trim();
        System.out.print("Función: ");      String funcion = sc.nextLine().trim();
        System.out.print("Foto (ruta): ");  String foto    = sc.nextLine().trim();

        WebMaster nuevo = new WebMaster(nombre, ap1, ap2, pais, nik, clave, funcion, foto);
        gestorWM.anyadir(nuevo);
    }

    private static void buscarWebMaster() {
        System.out.print("Introduce el nik a buscar: ");
        String nik = sc.nextLine().trim();
        WebMaster encontrado = gestorWM.buscarPorNik(nik);
        if (encontrado != null) {
            System.out.println("Encontrado: " + encontrado.mostrarDatos());
        } else {
            System.out.println("No se encontró ningún WebMaster con nik '" + nik + "'.");
        }
    }

    private static void editarWebMaster() {
        System.out.print("Nik del WebMaster a editar: ");
        String nik = sc.nextLine().trim();

        if (gestorWM.buscarPorNik(nik) == null) {
            System.out.println("No existe un WebMaster con ese nik.");
            return;
        }

        System.out.println("Introduce los nuevos datos:");
        System.out.print("Nuevo nombre: ");       String nombre  = sc.nextLine().trim();
        System.out.print("Nuevo apellido 1: ");   String ap1     = sc.nextLine().trim();
        System.out.print("Nuevo apellido 2: ");   String ap2     = sc.nextLine().trim();
        System.out.print("Nuevo país: ");         String pais    = sc.nextLine().trim();
        System.out.print("Nueva contraseña: ");   String clave   = sc.nextLine().trim();
        System.out.print("Nueva función: ");      String funcion = sc.nextLine().trim();
        System.out.print("Nueva foto (ruta): ");  String foto    = sc.nextLine().trim();

        WebMaster datosNuevos = new WebMaster(nombre, ap1, ap2, pais, nik, clave, funcion, foto);
        gestorWM.editar(nik, datosNuevos);
    }

    private static void borrarWebMaster() {
        System.out.print("Nik del WebMaster a borrar: ");
        String nik = sc.nextLine().trim();
        gestorWM.borrar(nik);
    }

    // leerEntero(): gestiona posibles errores al leer un número entero.
    // Devuelve -1 como centinela si la entrada es inválida para que el switch
    // caiga en el caso 'default' y muestre "Opción no válida".
    private static int leerEntero() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}