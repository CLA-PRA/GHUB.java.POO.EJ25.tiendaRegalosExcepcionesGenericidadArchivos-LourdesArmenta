package ui;

import negocio.TiendaDeRegalosService;
import java.util.Scanner;
import datos.Configuracion;

import java.io.IOException;
import java.util.InputMismatchException;

public class Menu {
    private TiendaDeRegalosService tiendaService;
    String idProducto, nombre, descripcion;
    String familia;
    double precio;
    String marca, modelo, color;
    String voltaje;
    int garantia, capacidad;
    String autor, editorial, genero;
    int anioPublicacion, numeroPaginas;

    public Menu(String ruta,String nombreUsuario, String emailUsuario) {
        this.tiendaService = new TiendaDeRegalosService(100, ruta,nombreUsuario,emailUsuario); 
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
       
        int opcion = -1;    

        do {
            mostrarMenu();
            
            opcion = leerEntero("",scanner,0,12,false);
            
            switch (opcion) {
                case 1:
                    tiendaService.presentarse();
                    break;
                case 2:
                    mostrarFamiliaProducto(scanner);
                    break;
                case 3:
                   agregarProducto(scanner);
                    break;
                
                case 4:
                    eliminarProducto(scanner);
                    break;
                case 5:
                    listarProductos();
                    break;
                case 6:
                    venderProducto(scanner);
                    break;
                case 7:
                    actualizarPrecio(scanner);
                    break;
                case 8:
                    aplicarDescuento(scanner);
                    break;
                case 9:
                    calcularValorTotalProductos();
                    break;
                case 10:
                    try {
                        generarReporteProductoConEncabezadoId();
                    } catch (IOException e) {
                        
                        e.printStackTrace();
                    }
                    break;
                case 11:
                    try {
                        generarReporteProductoConEncabezadoNombre();
                    } catch (IOException e) {
                        
                        e.printStackTrace();
                    }
                    break;
                case 12:
                    try {
                        generarReporteProductoConEncabezadoPrecio();
                    } catch (IOException e) {
                        
                        e.printStackTrace();
                    }
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
    private void mostrarFamiliaProducto(Scanner scanner) {
        System.out.print("Ingrese el nombre de la familia de producto: ");
        String familia = scanner.nextLine();
        tiendaService.mostrarFamiliaProducto(familia);
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
    
    private void captura_electrodomestico_conNavegacion(Scanner scanner){
      
        String[] datos = new String[5]; // Arreglo para almacenar los datos (marca, modelo, color, garantía, voltaje)
        String[] mensajes = {
        "Ingrese la marca del producto               : ",
        "Ingrese el modelo del producto              : ",
        "Ingrese el color del producto               : ",
        "Ingrese la garantía del producto            : ",
        "Ingrese el voltaje del producto (en voltios): "
    };

    int paso = 0; // Índice para controlar el flujo de captura

    while (paso < mensajes.length) {
        System.out.print("\u001B[34m" + mensajes[paso] + "\u001B[0m"); // Mensaje en azul

        if (paso == 3) { // Validar la garantía (entero)
            int garantiaTemp = leerEntero("", scanner, 1, 60, true);
            if (garantiaTemp == -1) {
                if (paso > 0) {
                    paso--; // Regresa al paso anterior
                } else {
                    System.out.println("\u001B[33mYa estás en el primer paso, no puedes regresar más.\u001B[0m");
                }
                continue;
            }
            datos[paso] = String.valueOf(garantiaTemp);
        } else {
            String entrada = scanner.nextLine();
            if (entrada.equals("-1")) {
                if (paso > 0) {
                    paso--; // Regresa al paso anterior
                } else {
                    System.out.println("\u001B[33mYa estás en el primer paso, no puedes regresar más.\u001B[0m");
                }
                continue;
            }
            datos[paso] = entrada;
        }
        paso++; 
        // Avanza al siguiente paso
    }

        // Asignar los datos capturados a las variables correspondientes
        marca = datos[0];
        modelo = datos[1];
        color = datos[2];
        garantia = Integer.parseInt(datos[3]);
        voltaje = datos[4];

        System.out.println("\u001B[34mCaptura completada:\u001B[0m");
        System.out.println("Marca         : " + marca);
        System.out.println("Modelo        : " + modelo);
        System.out.println("Color         : " + color);
        System.out.println("Garantía      : " + garantia + " meses");
        System.out.println("Voltaje       : " + voltaje + " voltios");
    }
          
    private void captura_Electronico_conNavegacion(Scanner scanner){
        String[] datos = new String[5]; // Arreglo para almacenar los datos (marca, modelo, color, garantía, voltaje)
        String[] mensajes = {
            "Ingrese la marca del producto: ",
            "Ingrese el modelo del producto: ",
            "Ingrese el color del producto: ",
            "Ingrese la garantía del producto (en meses): ",
            "Ingrese el voltaje del producto (en voltios): "
        };
    
        int paso = 0; // Índice para controlar el flujo de captura
    
        while (paso < mensajes.length) {
            System.out.print("\u001B[34m" + mensajes[paso] + "\u001B[0m"); // Mensaje en azul
    
            if (paso == 3) { // Validar la garantía (entero)
                int garantiaTemp = leerEntero("", scanner, 1, 60, true);
                if (garantiaTemp == -1) {
                    if (paso > 0) {
                        paso--; // Regresa al paso anterior
                    } else {
                        System.out.println("\u001B[33mYa estás en el primer paso, no puedes regresar más.\u001B[0m");
                    }
                    continue;
                }
                datos[paso] = String.valueOf(garantiaTemp);
            } else {
                String entrada = scanner.nextLine();
                if (entrada.equals("-1")) {
                    if (paso > 0) {
                        paso--; // Regresa al paso anterior
                    } else {
                        System.out.println("\u001B[33mYa estás en el primer paso, no puedes regresar más.\u001B[0m");
                    }
                    continue;
                }
                datos[paso] = entrada;
            }
            paso++; // Avanza al siguiente paso
        }
    
        // Asignar los datos capturados a las variables correspondientes
        marca = datos[0];
        modelo = datos[1];
        color = datos[2];
        garantia = Integer.parseInt(datos[3]);
        voltaje = datos[4];
    
        System.out.println("\u001B[34mCaptura completada:\u001B[0m");
        System.out.println("Marca         : " + marca);
        System.out.println("Modelo        : " + modelo);
        System.out.println("Color         : " + color);
        System.out.println("Garantía      : " + garantia + " meses");
        System.out.println("Voltaje       : " + voltaje + " voltios");
               
    }
    
    private void captura_literario_conNavegacion(Scanner scanner){
        String[] datos = new String[5]; // Arreglo para almacenar los datos (autor, editorial, año de publicación, género, número de páginas)
        String[] mensajes = {
        "Ingrese Autor: ",
        "Ingrese Editorial: ",
        "Ingrese Año de Publicación: ",
        "Ingrese Género: ",
        "Ingrese Número de Páginas: "
    };

    int paso = 0; // Índice para controlar el flujo de captura

    while (paso < mensajes.length) {
        System.out.print("\u001B[34m" + mensajes[paso] + "\u001B[0m"); // Mensaje en azul

        if (paso == 2) { // Validar el año de publicación
            int anioTemp = leerEntero("", scanner, 1900, 2999, true);
            if (anioTemp == -1) {
                if (paso > 0) {
                    paso--; // Regresa al paso anterior
                } else {
                    System.out.println("\u001B[33mYa estás en el primer paso, no puedes regresar más.\u001B[0m");
                }
                continue;
            }
            datos[paso] = String.valueOf(anioTemp);
        } else if (paso == 4) { // Validar el número de páginas
            int paginasTemp = leerEntero("", scanner, 1, 10000, true);
            if (paginasTemp == -1) {
                if (paso > 0) {
                    paso--; // Regresa al paso anterior
                } else {
                    System.out.println("\u001B[33mYa estás en el primer paso, no puedes regresar más.\u001B[0m");
                }
                continue;
            }
            datos[paso] = String.valueOf(paginasTemp);
        } else {
            String entrada = scanner.nextLine();
            if (entrada.equals("-1")) {
                if (paso > 0) {
                    paso--; // Regresa al paso anterior
                } else {
                    System.out.println("\u001B[33mYa estás en el primer paso, no puedes regresar más.\u001B[0m");
                }
                continue;
            }
            datos[paso] = entrada;
        }
        paso++; // Avanza al siguiente paso
    }
    // Asignar los datos capturados a las variables correspondientes
    autor = datos[0];
    editorial = datos[1];
    anioPublicacion = Integer.parseInt(datos[2]);
    genero = datos[3];
    numeroPaginas = Integer.parseInt(datos[4]);

    System.out.println("\u001B[34mCaptura completada:\u001B[0m");
    System.out.println("Autor           : " + autor);
    System.out.println("Editorial       : " + editorial);
    System.out.println("Año Publicación : " + anioPublicacion);
    System.out.println("Género          : " + genero);
    System.out.println("Número Páginas  : " + numeroPaginas);
            
          
    }
     
    private void captura_Promocionales(Scanner scanner){
        
       
    }

    private String capturaISBN(Scanner scanner) {
        String isbn;
        while (true) {
            System.out.print("\u001B[34mIngrese el ISBN (-1 para regresar): \u001B[0m");
            isbn = scanner.nextLine();
    
            if (isbn.equals("-1")) {
                System.out.println("\u001B[33mRegresando al paso anterior...\u001B[0m");
                return "-1"; // Indica que el usuario desea regresar
            }
    
            if (!isbn.isEmpty()) {
                // Llamar al servicio para agregar el libro
                familia = tiendaService.obtenerFamiliaProductoLiterario();
                tiendaService.agregarLibro(idProducto, nombre, precio, descripcion, familia, autor, editorial, 
                                            anioPublicacion, genero, numeroPaginas, isbn);       
                System.out.println("\u001B[34mLibro agregado exitosamente.\u001B[0m");
                return isbn; // ISBN válido
            } else {
                System.out.println("\u001B[31m***Error: El ISBN no puede estar vacío.***\u001B[0m");
            }
        }
        
    }

    private void capturaTelevision_conNavegacion(Scanner scanner) {
        String[] datos = new String[3]; // Arreglo para almacenar los datos (tamaño, resolución, tipo de pantalla)
        String[] mensajes = {
            "Ingrese el tamaño                                 : ",
            "Ingrese la resolución (en pixeles)                : ",
            "Ingrese el tipo de pantalla (LCD, LED, OLED, etc.): "
        };
    
        int paso = 0; // Índice para controlar el flujo de captura
    
        while (paso < mensajes.length) {
            System.out.print("\u001B[34m" + mensajes[paso] + "\u001B[0m"); // Mensaje en azul
    
            String entrada = scanner.nextLine();
            if (entrada.equals("-1")) {
                if (paso > 0) {
                    paso--; // Regresa al paso anterior
                } else {
                    System.out.println("\u001B[33mYa estás en el primer paso, no puedes regresar más.\u001B[0m");
                }
                continue;
            }
    
            if (entrada.isEmpty()) {
                System.out.println("\u001B[31m***Error: Este campo no puede estar vacío.***\u001B[0m");
            } else {
                datos[paso] = entrada;
                paso++; // Avanza al siguiente paso
            }
        }
    
        // Asignar los datos capturados a las variables correspondientes
        String tamanio = datos[0];
        String resolucion = datos[1];
        String tipoPantalla = datos[2];
    
        // Llamar al servicio para agregar la televisión
        familia = tiendaService.obtenerFamiliaProductoElectronico();
        tiendaService.agregarTelevision(idProducto, nombre, precio, descripcion, familia,
                                         marca, modelo, color, garantia, voltaje,
                                         tamanio, resolucion, tipoPantalla);
    
        System.out.println("\u001B[34mTelevisión agregada exitosamente.\u001B[0m");
    }
    private void capturaCelular_conNavegacion(Scanner scanner) {
        String[] datos = new String[5]; // Arreglo para almacenar los datos (número de cámaras, sistema operativo, almacenamiento, RAM, tipo de pantalla)
        String[] mensajes = {
            "Ingrese el número de cámaras                                 : ",
            "Ingrese Sistema Operativo (Android, iOS, Windows Phone, etc.): ",
            "Ingrese capacidad de almacenamiento                          : ",
            "Ingrese la RAM                                               : ",
            "Ingrese el tipo de pantalla (LCD, IPS, OLED, AMOLED, etc.)   : "
        };
    
        int paso = 0; // Índice para controlar el flujo de captura
    
        while (paso < mensajes.length) {
            System.out.print("\u001B[34m" + mensajes[paso] + "\u001B[0m"); // Mensaje en azul
    
            if (paso == 0) { // Validar el número de cámaras
                int numCamarasTemp = leerEntero("", scanner, 1, 10, true);
                if (numCamarasTemp == -1) {
                    if (paso > 0) {
                        paso--; // Regresa al paso anterior
                    } else {
                        System.out.println("\u001B[33mYa estás en el primer paso, no puedes regresar más.\u001B[0m");
                    }
                    continue;
                }
                datos[paso] = String.valueOf(numCamarasTemp);
            } else if (paso == 2) { // Validar capacidad de almacenamiento
                int almacenamientoTemp = leerEntero("", scanner, 0, 1000, true);
                if (almacenamientoTemp == -1) {
                    if (paso > 0) {
                        paso--; // Regresa al paso anterior
                    } else {
                        System.out.println("\u001B[33mYa estás en el primer paso, no puedes regresar más.\u001B[0m");
                    }
                    continue;
                }
                datos[paso] = String.valueOf(almacenamientoTemp);
            } else if (paso == 3) { // Validar RAM
                int ramTemp = leerEntero("", scanner, 1, 1000, true);
                if (ramTemp == -1) {
                    if (paso > 0) {
                        paso--; // Regresa al paso anterior
                    } else {
                        System.out.println("\u001B[33mYa estás en el primer paso, no puedes regresar más.\u001B[0m");
                    }
                    continue;
                }
                datos[paso] = String.valueOf(ramTemp);
            } else {
                String entrada = scanner.nextLine();
                if (entrada.equals("-1")) {
                    if (paso > 0) {
                        paso--; // Regresa al paso anterior
                    } else {
                        System.out.println("\u001B[33mYa estás en el primer paso, no puedes regresar más.\u001B[0m");
                    }
                    continue;
                }
                datos[paso] = entrada;
            }
            paso++; // Avanza al siguiente paso
        }
    
        // Asignar los datos capturados a las variables correspondientes
        int numCamaras = Integer.parseInt(datos[0]);
        String sistemaOperativo = datos[1];
        int capacidadAlmacenamiento = Integer.parseInt(datos[2]);
        int ram = Integer.parseInt(datos[3]);
        String tipoPantallaCelular = datos[4];
    
        // Llamar al servicio para agregar el celular
        familia = tiendaService.obtenerFamiliaProductoElectronico();
        tiendaService.agregarCelular(idProducto, nombre, precio, descripcion, familia,
                                      marca, modelo, color, garantia, voltaje,
                                      numCamaras, sistemaOperativo, capacidadAlmacenamiento,
                                      ram, tipoPantallaCelular);
    
        System.out.println("\u001B[34mCelular agregado exitosamente.\u001B[0m");
    }
    private void capturaCalculadora_conNavegacion(Scanner scanner) {
        String[] datos = new String[1]; // Arreglo para almacenar el tipo de calculadora
        String[] mensajes = {
            "Ingrese el tipo de calculadora (científica, estándar, programable, etc.): "
        };
    
        int paso = 0; // Índice para controlar el flujo de captura
    
        while (paso < mensajes.length) {
            System.out.print("\u001B[34m" + mensajes[paso] + "\u001B[0m"); // Mensaje en azul
    
            String entrada = scanner.nextLine();
            if (entrada.equals("-1")) {
                if (paso > 0) {
                    paso--; // Regresa al paso anterior
                } else {
                    System.out.println("\u001B[33mYa estás en el primer paso, no puedes regresar más.\u001B[0m");
                }
                continue;
            }
    
            if (entrada.isEmpty()) {
                System.out.println("\u001B[31m***Error: Este campo no puede estar vacío.***\u001B[0m");
            } else {
                datos[paso] = entrada;
                paso++; // Avanza al siguiente paso
            }
        }
    
        // Asignar los datos capturados a las variables correspondientes
        String tipoCalculadora = datos[0];
    
        // Llamar al servicio para agregar la calculadora
        familia = tiendaService.obtenerFamiliaProductoElectronico();
        tiendaService.agregarCalculadora(idProducto, nombre, precio, descripcion, familia,
                                          marca, modelo, color, garantia, voltaje, tipoCalculadora);
    
        System.out.println("\u001B[34mCalculadora agregada exitosamente.\u001B[0m");
    }
    private void capturaLicuadora_conNavegacion(Scanner scanner) {
        String[] datos = new String[2]; // Arreglo para almacenar los datos (potencia, capacidad)
        String[] mensajes = {
            "Ingrese la potencia  : ",
            "Ingrese la capacidad : "
        };
    
        int paso = 0; // Índice para controlar el flujo de captura
    
        while (paso < mensajes.length) {
            System.out.print("\u001B[34m" + mensajes[paso] + "\u001B[0m"); // Mensaje en azul
    
            if (paso == 0) { // Validar la potencia
                int potenciaTemp = leerEntero("", scanner, 1, 9999, true);
                if (potenciaTemp == -1) {
                    if (paso > 0) {
                        paso--; // Regresa al paso anterior
                    } else {
                        System.out.println("\u001B[33mYa estás en el primer paso, no puedes regresar más.\u001B[0m");
                    }
                    continue;
                }
                datos[paso] = String.valueOf(potenciaTemp);
            } else if (paso == 1) { // Validar la capacidad
                int capacidadTemp = leerEntero("", scanner, 1, 10, true);
                if (capacidadTemp == -1) {
                    if (paso > 0) {
                        paso--; // Regresa al paso anterior
                    } else {
                        System.out.println("\u001B[33mYa estás en el primer paso, no puedes regresar más.\u001B[0m");
                    }
                    continue;
                }
                datos[paso] = String.valueOf(capacidadTemp);
            }
            paso++; // Avanza al siguiente paso
        }
    
        // Asignar los datos capturados a las variables correspondientes
        int potencia = Integer.parseInt(datos[0]);
        int capacidad = Integer.parseInt(datos[1]);
    
        // Llamar al servicio para agregar la licuadora
        familia = tiendaService.obtenerFamiliaProductoElectrodomestico();
        tiendaService.agregarLicuadora(idProducto, nombre, precio, descripcion, familia, 
                                        marca, modelo, color, garantia, voltaje, potencia, capacidad);
    
        System.out.println("\u001B[34mLicuadora agregada exitosamente.\u001B[0m");
    }

    private void capturaTostadora_conNavegacion(Scanner scanner) {
        String[] datos = new String[1]; // Arreglo para almacenar el número de ranuras
        String[] mensajes = {
            "Ingrese el número de ranuras: "
        };
    
        int paso = 0; // Índice para controlar el flujo de captura
    
        while (paso < mensajes.length) {
            System.out.print("\u001B[34m" + mensajes[paso] + "\u001B[0m"); // Mensaje en azul
    
            if (paso == 0) { // Validar el número de ranuras
                int numRanurasTemp = leerEntero("", scanner, 1, 4, true);
                if (numRanurasTemp == -1) {
                    if (paso > 0) {
                        paso--; // Regresa al paso anterior
                    } else {
                        System.out.println("\u001B[33mYa estás en el primer paso, no puedes regresar más.\u001B[0m");
                    }
                    continue;
                }
                datos[paso] = String.valueOf(numRanurasTemp);
            }
            paso++; // Avanza al siguiente paso
        }
    
        // Asignar los datos capturados a las variables correspondientes
        int numRanuras = Integer.parseInt(datos[0]);
    
        // Llamar al servicio para agregar la tostadora
        familia = tiendaService.obtenerFamiliaProductoElectrodomestico();
        tiendaService.agregarTostadora(idProducto, nombre, precio, descripcion, familia,
                                        marca, modelo, color, garantia, voltaje, numRanuras);
    
        System.out.println("\u001B[34mTostadora agregada exitosamente.\u001B[0m");
    }
    private void capturaPromocionales_conNavegacion(Scanner scanner) {
        String[] datos = new String[2]; // Arreglo para almacenar los datos (talla, color)
        String[] mensajes = {
            "Ingrese la talla del producto: ",
            "Ingrese el color del producto: "
        };
    
        int paso = 0; // Índice para controlar el flujo de captura
    
        while (paso < mensajes.length) {
            System.out.print("\u001B[34m" + mensajes[paso] + "\u001B[0m"); // Mensaje en azul
    
            String entrada = scanner.nextLine();
            if (entrada.equals("-1")) {
                if (paso > 0) {
                    paso--; // Regresa al paso anterior
                } else {
                    System.out.println("\u001B[33mYa estás en el primer paso, no puedes regresar más.\u001B[0m");
                }
                continue;
            }
    
            if (entrada.isEmpty()) {
                System.out.println("\u001B[31m***Error: Este campo no puede estar vacío.***\u001B[0m");
            } else {
                datos[paso] = entrada;
                paso++; // Avanza al siguiente paso
            }
        }
    
        // Asignar los datos capturados a las variables correspondientes
        String talla = datos[0];
        String colorPromocional = datos[1];
    
        // Llamar al servicio para agregar el producto promocional
        familia = tiendaService.obtenerFamiliaProductoPromocional();
        tiendaService.agregarProductoPromocional(idProducto, nombre, precio, descripcion, 
                                                 familia, talla, colorPromocional);
    
        System.out.println("\u001B[34mProducto promocional agregado exitosamente.\u001B[0m");
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
        int tipoProducto = -1;

        do{
           
            tipoProducto = leerEntero("Ingrese el número correspondiente al tipo de producto: ",scanner,0,7,false);
            //scanner.nextLine();

        } while (tipoProducto < 0 || tipoProducto > 7);

        //Datos comunes del producto
        //captura_productos(scanner);
        capturaConNavegacion(scanner);
        
        switch (tipoProducto) {
            case 1://Libro
                captura_literario_conNavegacion(scanner); 
                //Datos específicos del libro
                capturaISBN(scanner);
                break;
            case 2://Televisor
                captura_Electronico_conNavegacion(scanner);
                //Datos específicos del televisor
                capturaTelevision_conNavegacion(scanner);
                break;
            case 3://Celular
                captura_Electronico_conNavegacion(scanner);
                //Datos específicos del celular
                capturaCelular_conNavegacion(scanner);  
                break;
            case 4://Calculadora
                captura_Electronico_conNavegacion(scanner);
                //Datos específicos de la calculadora
                capturaCalculadora_conNavegacion(scanner);
                break;
            case 5://Licuadora
                captura_electrodomestico_conNavegacion(scanner); 
                //Datos específicos de la licuadora
                capturaLicuadora_conNavegacion(scanner);
                break;

            case 6://Tostadora
                captura_electrodomestico_conNavegacion(scanner);
                //datos específicos de la tostadora
                capturaTostadora_conNavegacion(scanner);
                break;
            case 7://Promocionales
                captura_Promocionales(scanner);
                //Datos específicos del producto promocional
                capturaPromocionales_conNavegacion(scanner);
                break;
            default:
                System.out.println("Tipo de producto no válido.");
                return;
        }

    }

    private void eliminarProducto(Scanner scanner) {
        System.out.print("Ingrese el nombre del producto a eliminar: ");
        String nombre = scanner.nextLine();
        tiendaService.eliminarProducto(nombre);
    }

    private void listarProductos() {
        tiendaService.listarProductos();
    }

    private void venderProducto(Scanner scanner) {
        System.out.print("Ingrese el nombre del producto a vender: ");
        String nombre = scanner.nextLine();
        tiendaService.venderProducto(nombre);
    }

    private void aplicarDescuento(Scanner scanner) {
        System.out.print("Ingrese el nombre del producto a aplicar descuento: ");
        String nombre = scanner.nextLine();
        
            
        double porcentaje = leerDecimal("Ingrese el porcentaje de descuento: ",scanner,0,999,false);
       
                
        tiendaService.aplicarDescuento(nombre, porcentaje);
               
      
    }

    private void actualizarPrecio(Scanner scanner) {
        System.out.print("Ingrese el nombre del producto a actualizar: ");
        String nombre = scanner.nextLine();
        
        double nuevoPrecio = leerDecimal("Ingrese el nuevo precio del producto: ",scanner,0,999999.99,false);
        
        tiendaService.actualizarPrecio(nombre, nuevoPrecio);   
       
    }

    private void calcularValorTotalProductos() {
        double valorTotal = tiendaService.calcularValorTotalProductos();
        System.out.println("Valor total de los productos en inventario: $" + valorTotal);
    }

    private void generarReporteProductoConEncabezadoId() throws IOException{
        System.out.println(tiendaService.generarReporteProductosConEncabezadoId());
    }

    private void generarReporteProductoConEncabezadoNombre() throws IOException{
        System.out.println(tiendaService.generarReporteProductosConEncabezadoNombre());
    }
    private void generarReporteProductoConEncabezadoPrecio() throws IOException{
        System.out.println(tiendaService.generarReporteProductosConEncabezadoPrecio());
    }

    public int leerEntero(String mensaje, Scanner scanner, int min, int max, boolean permitirRegresar) {
        while (true) {
            try {
                System.out.print("\u001B[34m" + mensaje + "\u001B[0m"); // Mensaje en azul
                int valor = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea
    
                // Manejar el centinela para regresar
                if (permitirRegresar && valor == -1) {
                    return -1; // Indica que el usuario desea regresar
                }
    
                // Validar el rango
                if (valor < min || valor > max) {
                    System.out.println("\u001B[31m\t***Error: El valor debe estar entre " + min + " y " + max + ".***\u001B[0m");
                } else {
                    return valor; // Valor válido
                }
            } catch (InputMismatchException e) {
                System.out.println("\u001B[31m\t***Error: Debe ingresar un número entero válido.***\u001B[0m");
                scanner.nextLine(); // Consumir la nueva línea
            }
        }
    }

    public double leerDecimal(String mensaje, Scanner scanner, double min, double max, boolean permitirRegresar) {
        while (true) {
            try {
                System.out.print("\u001B[34m" + mensaje + "\u001B[0m"); // Mensaje en azul
                double valor = scanner.nextDouble(); // Leer un número decimal
                scanner.nextLine(); // Consumir la nueva línea
    
                // Manejar el centinela para regresar
                if (permitirRegresar && valor == -1) {
                    return -1; // Indica que el usuario desea regresar
                }
    
                // Validar el rango
                if (valor < min || valor > max) {
                    System.out.println("\u001B[31m\t***Error: El valor debe estar entre " + min + " y " + max + ".***\u001B[0m");
                } else {
                    return valor; // Valor válido
                }
            } catch (InputMismatchException e) {
                System.out.println("\u001B[31m\t***Error: Debe ingresar un número decimal válido.***\u001B[0m");
                scanner.nextLine(); // Consumir la nueva línea
            }
        }
        
    }

    //propiedades solo para pruebas

    public String getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }
    


}