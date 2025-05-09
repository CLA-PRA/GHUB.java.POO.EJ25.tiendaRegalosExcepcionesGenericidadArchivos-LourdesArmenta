package miPrincipal;
import ui.Menu;
import ui.Aplicacion;

import java.util.Scanner;

import datos.Usuario;

public class Principal {
    
    public static void main(String[] args) {
        try {
            // Instanciamos la clase Aplicacion
            Aplicacion app = new Aplicacion();
            Scanner scanner = new Scanner(System.in);

            // Solicitar credenciales al usuario
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║               AUTENTICACIÓN DE USUARIO           ║");
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.print("║ Ingrese su nombre de usuario: ");
            String username = scanner.nextLine();
            System.out.println("║");
            System.out.print("║ Ingrese su contraseña: ");
            String password = scanner.nextLine();
            System.out.println("║");
            System.out.println("╚══════════════════════════════════════════════════╝");

            // Intentar autenticación
            Usuario u = app.autenticarUsuario(username, password);

            // Validar si el login fue exitoso
            if (u == null) {
                System.out.println("Usuario y/o contraseña incorrectos. Intente nuevamente.");
                return; // Salir del programa si el login falla
            }

            // Mostrar mensaje de bienvenida
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║               BIENVENIDO A LA APLICACIÓN         ║");
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.println("║ " + ajustarTexto("Nombre: " + u.getNombreUsuario(), 49) + "║");
            System.out.println("║ " + ajustarTexto("Email: " + u.getEmail(), 49) + "║");
            System.out.println("╚══════════════════════════════════════════════════╝");

            // Obtener la ruta de trabajo desde la clase Aplicacion
            String rutaTrabajo = u.getRuta();
            System.out.println("Ruta de trabajo: " + rutaTrabajo);

            // Iniciar el menú
            Menu menu = new Menu(rutaTrabajo, u.getNombreUsuario(), u.getEmail());
            menu.iniciar();

        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Ajusta el texto al ancho especificado, recortándolo si es demasiado largo
     * o rellenándolo con espacios si es demasiado corto.
     *
     * @param texto El texto a ajustar.
     * @param ancho El ancho máximo permitido.
     * @return El texto ajustado.
     */
    private static String ajustarTexto(String texto, int ancho) {
        if (texto.length() > ancho) {
            return texto.substring(0, ancho - 3) + "..."; // Recortar y agregar "..."
        } else {
            return String.format("%-" + ancho + "s", texto); // Rellenar con espacios
        }
    }
}
