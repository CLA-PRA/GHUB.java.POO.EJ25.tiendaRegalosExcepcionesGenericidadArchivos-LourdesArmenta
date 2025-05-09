package ui;

import negocio.TiendaDeRegalosService;
import java.util.Scanner;

public class Menu {
    private TiendaDeRegalosService tiendaService;
    String idProducto, nombre, descripcion;
    double precio;

    public Menu(String ruta, String nombreUsuario, String emailUsuario) {
        this.tiendaService = new TiendaDeRegalosService(100, ruta, nombreUsuario, emailUsuario);
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ", scanner, 0, 7, false);

            switch (opcion) {
                case 1:
                    agregarProducto(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 0);

        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║               MENÚ DE TIENDA DE REGALOS            ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║  1.  Presentarse                                   ║");
        System.out.println("║  2.  Mostrar Familia de Producto                   ║");
        System.out.println("║  3.  Agregar Producto                              ║");
        System.out.println("║  4.  Eliminar Producto                             ║");
        System.out.println("║  5.  Listar Productos ordenado por Id              ║");
        System.out.println("║  6.  Vender Producto                               ║");
        System.out.println("║  7.  Actualizar Precio                             ║");
        System.out.println("║  8.  Aplicar Descuento                             ║");
        System.out.println("║  9.  Calcular valor total de productos             ║");
        System.out.println("║ 10.  Reporte de Productos ordenados por clave      ║");
        System.out.println("║ 11.  Reporte de Productos ordenados por nombre     ║");
        System.out.println("║ 12.  Reporte de Productos ordenados por precio     ║");
        System.out.println("║  0.  Salir                                         ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.print("Seleccione una opción: ");
    }

    public void capturaConNavegacion(Scanner scanner) {
        String[] datos = new String[4]; // Arreglo para almacenar los datos (idProducto, nombre, precio, descripción)
        String[] mensajes = {
            "Ingrese el Id del producto         : ",
            "Ingrese el nombre del producto.    : ",
            "Ingrese el precio del producto.    : ",
            "Ingrese la descripción del producto: "
        };

        int paso = 0; // Índice para controlar el flujo de captura

        while (paso < mensajes.length) {
            System.out.print(mensajes[paso]);

            if (paso == 2) { // Validar el precio
                double precio = leerDecimal("", scanner, 0.00, 999999.99, true);
                if (precio == -1) {
                    if (paso > 0) {
                        paso--; // Regresa al paso anterior
                    } else {
                        System.out.println("Ya estás en el primer paso, no puedes regresar más.");
                    }
                    continue;
                }
                datos[paso] = String.valueOf(precio);
            } else {
                String entrada = scanner.nextLine();
                if (entrada.equals("-1")) {
                    if (paso > 0) {
                        paso--; // Regresa al paso anterior
                    } else {
                        System.out.println("Ya estás en el primer paso, no puedes regresar más.");
                    }
                    continue;
                }
                datos[paso] = entrada;
            }
            paso++; // Avanza al siguiente paso
        }

        // Asignar los datos capturados a las variables correspondientes
        idProducto = datos[0];
        nombre = datos[1];
        precio = Double.parseDouble(datos[2]);
        descripcion = datos[3];

        System.out.println("\u001B[34mCaptura completada:\u001B[0m");
        System.out.println("Id del producto: " + idProducto);
        System.out.println("Nombre         : " + nombre);
        System.out.println("Precio         : $" + precio);
        System.out.println("Descripción    : " + descripcion);
    }

    private void agregarProducto(Scanner scanner) {
        System.out.println("Seleccione el tipo de producto:");
        System.out.println("1. Libro");
        System.out.println("2. Televisor");
        System.out.println("3. Celular");
        System.out.println("4. Calculadora");
        System.out.println("5. Licuadora");
        System.out.println("6. Tostadora");
        System.out.println("7. Promocionales");
        System.out.println("0. Cancelar/Terminar");

        int tipoProducto = leerEntero("Ingrese el número correspondiente al tipo de producto: ", scanner, 0, 7, false);

        // Captura de datos comunes
        capturaConNavegacion(scanner);

        // TODO: Implementar capturas específicas para cada tipo de producto.
        switch (tipoProducto) {
            case 1:
                // Ejemplo: captura_literario_conNavegacion(scanner);
                System.out.println("Captura de libro no implementada.");
                break;
            case 2:
                System.out.println("Captura de televisor no implementada.");
                break;
            case 3:
                System.out.println("Captura de celular no implementada.");
                break;
            case 4:
                System.out.println("Captura de calculadora no implementada.");
                break;
            case 5:
                System.out.println("Captura de licuadora no implementada.");
                break;
            case 6:
                System.out.println("Captura de tostadora no implementada.");
                break;
            case 7:
                System.out.println("Captura de promocionales no implementada.");
                break;
            default:
                System.out.println("Tipo de producto no válido.");
        }
    }

    public int leerEntero(String mensaje, Scanner scanner, int min, int max, boolean permitirRegresar) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea

                if (permitirRegresar && valor == -1) {
                    return -1; // Indica que el usuario desea regresar
                }

                if (valor < min || valor > max) {
                    System.out.println("Error: El valor debe estar entre " + min + " y " + max + ".");
                } else {
                    return valor;
                }
            } catch (Exception e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
                scanner.nextLine(); // Consumir la nueva línea
            }
        }
    }

    public double leerDecimal(String mensaje, Scanner scanner, double min, double max, boolean permitirRegresar) {
        while (true) {
            try {
                System.out.print(mensaje);
                double valor = scanner.nextDouble();
                scanner.nextLine(); // Consumir la nueva línea

                if (permitirRegresar && valor == -1) {
                    return -1; // Indica que el usuario desea regresar
                }

                if (valor < min || valor > max) {
                    System.out.println("Error: El valor debe estar entre " + min + " y " + max + ".");
                } else {
                    return valor;
                }
            } catch (Exception e) {
                System.out.println("Error: Debe ingresar un número decimal válido.");
                scanner.nextLine(); // Consumir la nueva línea
            }
        }
    }
}



