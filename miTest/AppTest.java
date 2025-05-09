package miTest;
import ui.Aplicacion;
import ui.Menu;
import datos.Producto;
import datos.ProductoDAO;
import datos.ProductoElectroDomestico;
import datos.ProductoElectronico;
import datos.ProductoLiterario;
import datos.ProductoPromocional;
import datos.Libro;
import datos.Television;
import datos.Celular; 
import datos.Licuadora;
import datos.Tostadora;
import datos.Usuario;
import negocio.TiendaDeRegalosService;
import negocio.utilidades.MiColeccion;
import datos.Calculadora;
import datos.Camisetapromocional;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;


class AppTest {

    private TiendaDeRegalosService tienda;
    private Producto producto1;
    private Producto producto2;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    private final String rutaArchivos = "miTest/archivos";
    private final String rutaRespaldo = "miTest/archivos_respaldo";

    @BeforeEach
    public void setUp() throws IOException{

          // Respaldar los archivos antes de modificarlos
        respaldarArchivos();

        // Simular la autenticación del usuario
        Usuario u = new Usuario();
        u.setNombreUsuario("admin");
        u.setEmail("admin@example.com");
        u.setRuta("miTest/archivos");

        // Validar que el usuario no sea nulo
        assertNotNull(u, "El usuario no debería ser nulo después de la autenticación.");

        // Mostrar mensaje de bienvenida
        System.out.println("Bienvenido(a)!!!!!");
        System.out.println("Nombre: " + u.getNombreUsuario());
        System.out.println("Email: " + u.getEmail());

        // La ruta siempre deberá apuntar a las pruebas para que no altere los archivos productivos
        String rutaTrabajo = "miTest/archivos";
        System.out.println("Ruta de trabajo: " + rutaTrabajo);

        // Crear la instancia de TiendaDeRegalosService
        tienda = new TiendaDeRegalosService(100, rutaTrabajo, u.getNombreUsuario(), u.getEmail());

        // Crear una tienda de regalos con productos
        tienda.agregarProducto(new Television("1", "Televisor 4K", 499.99, "Televisor de alta definición", "ProductoElectronico",
                "Samsung", "QLED", "Negro", 2, "220V", "65 pulg", "8K: 7680 x 4320", "LCD"));
        tienda.agregarProducto(new Celular("2", "Celular Android", 299.99, "Celular de última generación", "ProductoElectronico",
                "Samsung", "Galaxy S21", "Azul", 1, "110V", 3, "IOS", 4, 64, "tactil"));
        tienda.agregarProducto(new Calculadora("3", "Calculadora Científica", 19.99, "Calculadora con funciones avanzadas", "ProductoElectronico",
                "Casio", "FX-991ES", "Gris", 1, "N/A", "Científica"));
        tienda.agregarProducto(new Licuadora("7", "Licuadora Oster", 79.99, "Licuadora de alta velocidad con 10 velocidades", "ProductoElectrodomestico",
                "Oster", "BLSTMG-W00", "Blanco", 2, "110V", 600, 1500));
        tienda.agregarProducto(new Tostadora("8", "Tostadora Philips", 49.99, "Tostadora de 4 rebanadas con control de temperatura", "ProductoElectrodomestico",
                "Philips", "HD2581/00", "Negro", 2, "110V", 4));
        tienda.agregarProducto(new Camisetapromocional("9", "Camiseta Deportiva", 19.99, "Camiseta de algodón para deportes", "ProductoPromocional",
                "L", "Rojo"));
        tienda.agregarProducto(new Libro("4", "Cien Años de Soledad", 19.99, "Descripción de prueba", "ProductoLiterario",
                "Gabriel Garcia Marquez", "Editorial", 1967, "Realismo mágico", 500, "978-84-376-0494-7"));

        // Agregar productos adicionales para pruebas
        producto1 = new ProductoLiterario("3", "Revista", 50.00, "Revista National Geographic", "ProductoLiterario",
                "National Geographic Society", "Editorial", 2000, "Ciencia y Naturaleza", 20);
        producto2 = new ProductoLiterario("2", "LibroABC", 250.00, "Libro de prueba", "ProductoLiterario",
                "autor", "editorial", 2000, "genero", 0);

        tienda.agregarProducto(producto1);
        tienda.agregarProducto(producto2);

        // Redirigir la salida estándar para capturar el output
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Restaurar los archivos originales después de la prueba
        restaurarArchivos();
    }

     private void respaldarArchivos() throws IOException {
        Files.createDirectories(Paths.get(rutaRespaldo));
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(rutaArchivos))) {
            for (Path archivo : stream) {
                Path destino = Paths.get(rutaRespaldo, archivo.getFileName().toString());
                Files.copy(archivo, destino, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    private void restaurarArchivos() throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(rutaRespaldo))) {
            for (Path archivo : stream) {
                Path destino = Paths.get(rutaArchivos, archivo.getFileName().toString());
                Files.copy(archivo, destino, StandardCopyOption.REPLACE_EXISTING);
            }
        }
        // Eliminar los archivos de respaldo
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(rutaRespaldo))) {
            for (Path archivo : stream) {
                Files.delete(archivo);
            }
        }
    }

    @Test
    public void testCalcularValorTotalConProductosAgregados() {
        // Total inicial del inventario de los archivos de texto de miTest/archivos
        double totalInicial = 5408.34;
    
        // Productos agregados con descuentos aplicados
        //double totalProductosAgregados = 399.99 + 269.99 + 17.99 + 71.99 + 44.99 + 19.99 + 17.99;
        
        // Productos agregados sin descuentos aplicados
        double totalProductosAgregados = 499.99 + 299.99 + 19.99 + 79.99 + 49.99 + 19.99 + 19.99+50+250;
        
        // Total esperado
        double totalEsperado = totalInicial + totalProductosAgregados;
    
        // Verificar que el valor total calculado sea igual al esperado
        assertEquals(totalEsperado, tienda.calcularValorTotalProductos(), 0.01);
    }

    @Test
    public void testAplicarDescuentoATodos() {
        tienda.aplicarDescuentoATodos(10); // Aplicar un descuento del 10% a todos los productos
        
        assertEquals(449.99, tienda.buscarProducto("Televisor 4K").getPrecio(), 0.01); // Televisor
        assertEquals(269.99, tienda.buscarProducto("Celular Android").getPrecio(), 0.01); // Celular
        assertEquals(17.99, tienda.buscarProducto("Calculadora Científica").getPrecio(), 0.01);  // Calculadora
        assertEquals(71.99, tienda.buscarProducto("Licuadora Oster").getPrecio(), 0.01);  // Licuadora
        assertEquals(44.99, tienda.buscarProducto("Tostadora Philips").getPrecio(), 0.01);  // Tostadora
        assertEquals(19.99, tienda.buscarProducto("Camiseta Deportiva").getPrecio(), 0.01);  // Camiseta
        assertEquals(17.99, tienda.buscarProducto("Cien Años de Soledad").getPrecio(), 0.01);  // Libro
        
    }

    @Test
    public void testMostrarInventario() {
        // Redirigir la salida estándar para capturar el output de mostrarInventario
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        tienda.mostrarInventario();

        // Verificar que la salida contiene los productos esperados
        String output = outContent.toString();
        assertTrue(output.contains("Televisor 4K"));
        assertTrue(output.contains("Celular Android"));
        assertTrue(output.contains("Calculadora Científica"));
        assertTrue(output.contains("Licuadora Oster"));
        assertTrue(output.contains("Tostadora Philips"));
        assertTrue(output.contains("Camiseta Deportiva"));
        assertTrue(output.contains("Cien Años de Soledad"));

        // Restaurar la salida estándar
        System.setOut(System.out);
    }
    @Test
    public void testVenderProductoExistente() {
        tienda.venderProducto("LibroABC");
        assertNull(tienda.buscarProducto("LibroABC"));
        assertTrue(outContent.toString().contains("Producto vendido: LibroABC"));
    }

    @Test
    public void testNoVenderProductoPromocional() {
        Producto productoPromocional = new ProductoPromocional("9", "Camiseta Deportiva", 
                19.99, "Camiseta de algodón para deportes","ProductoPromocional");
        tienda.agregarProducto(productoPromocional);
        
        // Intentar vender el producto promocional
        tienda.venderProducto("Promo123");
        
        // Verificar que el producto sigue existiendo en el inventario
        assertNotNull(tienda.buscarProducto("Camiseta Deportiva"));
        
        // Verificar que no se imprimió el mensaje de producto vendido
        assertFalse(outContent.toString().contains("Producto vendido: Camiseta Deportiva"));
    }

    @Test 
    public void testMenuInitialization() {
        // Simular la entrada del usuario para autenticación
        String simulatedInput = "admin\n12345\n";
        ByteArrayInputStream inContent = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inContent);

        // Instanciar la clase Aplicacion
        Aplicacion app = new Aplicacion();
        
        // Realizar la autenticación para obtener los datos del usuario
        Usuario usuario = null;
        try {
            usuario = app.autenticarUsuario("LourdesA", "123"); // Simular autenticación con credenciales
        } catch (Exception e) {
            fail("Error durante la autenticación: " + e.getMessage());
        }
        
        // Validar que el usuario no sea nulo
        assertNotNull(usuario, "El usuario no debería ser nulo después de la autenticación.");
        
        // Obtener la ruta de trabajo desde el usuario autenticado
        String rutaTrabajo = usuario.getRuta();
        assertNotNull(rutaTrabajo, "La ruta de trabajo no debería ser nula.");
        
        // Crear el menú con los datos obtenidos
        Menu menu = new Menu(rutaTrabajo, usuario.getNombreUsuario(), usuario.getEmail());
        assertNotNull(menu, "El menú debe inicializarse correctamente.");

        // Restaurar la entrada estándar
        System.setIn(System.in);
    }

    @Test
    public void testProducto() {
        // Prueba de constructor y getNombre
        Producto producto = new Libro("1", "cien años de soledad", 19.99, "Descripción de prueba", "Productoliterario", "Gabriel Garcia Marquez", "Editorial", 1967, "Realismo mágico", 500, "978-84-376-0494-7");  
       
        assertEquals("cien años de soledad", producto.getNombre(), "El nombre del producto debe ser 'cien años de soledad'");

        // Prueba de setNombre
        producto.setNombre("Producto de Prueba 2");
        assertEquals("Producto de Prueba 2", producto.getNombre(), "El nombre del producto debe ser 'Producto de Prueba 2' después de usar setNombre");

        // Prueba de getPrecio
        assertEquals(19.99, producto.getPrecio(), "El precio del producto debe ser 19.99");

        // Prueba de setPrecio
        producto.setPrecio(14.99);
        assertEquals(14.99, producto.getPrecio(), "El precio del producto debe ser 14.99 después de usar setPrecio");

        // Prueba de getDescripcion
        assertEquals("Descripción de prueba", producto.getDescripcion(), "La descripción del producto debe ser 'Descripción de prueba'");

        // Prueba de setDescripcion
        producto.setDescripcion("Nueva descripción de prueba");
        assertEquals("Nueva descripción de prueba", producto.getDescripcion(), "La descripción del producto debe ser 'Nueva descripción de prueba' después de usar setDescripcion");

        // Prueba de getFamilia
        assertEquals("ProductoLiterario", producto.getFamilia(), "La familia del producto debe ser 'ProductoLiterario'");
    
    }
    
    @Test
    public void testAplicarDescuentoHerenciaProducto() {
        // Crear una instancia de Producto usando Libro
        Producto producto = new Libro("1", "Cien Años de Soledad", 19.99, "Descripción de prueba", "Productoliterario", "Gabriel Garcia Marquez", "Editorial", 1967, "Realismo mágico", 500, "978-84-376-0494-7");  

        // Aplicar un descuento válido
        producto.aplicarDescuento(20);
        assertEquals(16.0, producto.getPrecio(), 0.01);

        // Aplicar un descuento no válido, porque es mayor a 80%
        //entonces el costo no debe cambiar
        producto.aplicarDescuento(90);
        assertEquals(16.0, producto.getPrecio(), 0.01); // El precio no debe cambiar

        // Aplicar otro descuento válido
        //se aplica un descuento del 80% al precio original
        //19.99 - 80% = 3.2
        producto.aplicarDescuento(80);
        assertEquals(3.2, producto.getPrecio(), 0.01);
    }

    @Test
    public void testAplicarDescuentoHerenciaProductoElectronico() {
        // Crear una instancia de ProductoElectronico usando Televisor
        ProductoElectronico productoElectronico = new Television("1", "Televisor 4K", 499.99, "Televisor de alta definición", "ProductoElectronico",
                                                    "Samsung", "QLED", "Negro", 2, "220V", "65 pulg", "8K: 7680 x 4320", "LCD");

        // Aplicar un descuento válido
        productoElectronico.aplicarDescuento(20);
        assertEquals(399.99, productoElectronico.getPrecio(), 0.01);

        // Aplicar un descuento no válido, porque es mayor a 20%
        // entonces el costo no debe cambiar
        productoElectronico.aplicarDescuento(90);
        assertEquals(399.99, productoElectronico.getPrecio(), 0.01); // El precio no debe cambiar

        // Aplicar otro descuento válido
        // se aplica un descuento del 10% al precio original
        // 399.99 - 10% = 39.99
        productoElectronico.aplicarDescuento(10);
        assertEquals(360.0, productoElectronico.getPrecio(), 0.01);
    }

    @Test
    public void testAplicarDescuentoHerenciaProductoElectroDomestico() {
        // Crear una instancia de ProductoElectroDomestico usando Licuadora
        ProductoElectroDomestico productoElectroDomestico = new Licuadora("7", "Licuadora Oster", 79.99, "Licuadora de alta velocidad con 10 velocidades","Electrodomesticos",
                                                                     "Oster","BLSTMG-W00","Blanco",2,"110V", 600,1500);

        // Aplicar un descuento válido
        productoElectroDomestico.aplicarDescuento(20);
        assertEquals(63.99, productoElectroDomestico.getPrecio(), 0.01);

        // Aplicar un descuento no válido, porque es mayor a 50%
        // entonces el costo no debe cambiar
        productoElectroDomestico.aplicarDescuento(55);
        assertEquals(63.99, productoElectroDomestico.getPrecio(), 0.01); // El precio no debe cambiar

        // Aplicar otro descuento válido
        // se aplica un descuento del 80% al precio original
        // 63.99 - 20% = 51.1
        productoElectroDomestico.aplicarDescuento(20);
        assertEquals(51.19, productoElectroDomestico.getPrecio(), 0.01);
    }

    @Test
    public void testAplicarDescuentoHerenciaProductoLiterario() {
        // Crear una instancia de ProductoLiterario usando Libro
        ProductoLiterario productoLiterario = new Libro("4", "Cien Años de Soledad", 19.99, "Descripción de prueba", "ProductoLiterario",
                            "Gabriel Garcia Marquez", "Editorial", 1967, "Realismo mágico", 500, "978-84-376-0494-7");

        // Aplicar un descuento válido
        productoLiterario.aplicarDescuento(20);
        assertEquals(15.99, productoLiterario.getPrecio(), 0.01);

        // Aplicar un descuento no válido, porque es mayor a 80%
        // entonces el costo no debe cambiar
        productoLiterario.aplicarDescuento(90);
        assertEquals(15.99, productoLiterario.getPrecio(), 0.01); // El precio no debe cambiar

        // Aplicar otro descuento válido
        // se aplica un descuento del 80% al precio original
        // 15.99 - 80% = 3.2
        productoLiterario.aplicarDescuento(80);
        assertEquals(3.2, productoLiterario.getPrecio(), 0.01);
    }

    @Test
    public void testPolimorfismoProducto() {
        // Crear instancias de Televisor, Celular y Calculadora
        Producto television = new Television("1", "Televisor 4K", 499.99, "Televisor de alta definición", "ProductoElectronico",
                                                        "Samsung", "QLED", "Negro", 2, "220V",
                                                        "65 pulg","8K: 7680 x 4320","LCD");
        Producto celular = new Celular("2", "Celular Android", 299.99, "Celular de última generación", "ProductoElectronico",
                                                 "Samsung", "Galaxy S21", "Azul", 1, "110V",
                                                 3,"IOS",4,64,"tactil");
        Producto calculadora = new Calculadora("3", "Calculadora Científica", 19.99, "Calculadora con funciones avanzadas", "ProductoEletroico",
                                                "Casio", "FX-991ES", "Gris", 1, "N/A","Científica");

        Producto licuadora = new Licuadora("7", "Licuadora Oster", 79.99, "Licuadora de alta velocidad con 10 velocidades","Electrodomesticos",
                                                "Oster","BLSTMG-W00","Blanco",2,"110V", 600,1500);
        Producto tostadora = new Tostadora("8","Tostadora Philips",49.99, "Tostadora de 4 rebanadas con control de temperatura","Electrodomesticos",
                                                 "Philips", "HD2581/00","Negro", 2,  "110V",4);

        Producto libro = new Libro("4","Cien Años de Soledad", 19.99, "Descripción de prueba", "Productoliterario", 
                                                "Gabriel Garcia Marquez", "Editorial", 1967, "Realismo mágico", 500, "978-84-376-0494-7"); 
       
        Producto camisetaPromocional = new Camisetapromocional( "9","Camiseta Deportiva",19.99,"Camiseta de algodón para deportes","ProductoPromocional",
                                                 "L","Rojo"); 

        // Verificar que las instancias son también instancias de Producto
        assertTrue(television instanceof Producto, "Televisor debe ser una instancia de Producto");
        assertTrue(celular instanceof Producto, "Celular debe ser una instancia de Producto");
        assertTrue(calculadora instanceof Producto, "Calculadora debe ser una instancia de Producto");
        assertTrue(libro instanceof Producto, "Libro debe ser una instancia de Producto");
        assertTrue(licuadora instanceof Producto,"Licuadora debe ser una instancia de Producto");
        assertTrue(tostadora instanceof Producto,"Tostadora debe ser una instancia de Producto");
        assertTrue(camisetaPromocional instanceof Producto,"Camiseta Promocional debe ser una instancia de Producto");

        // Verificar que las instancias son también instancias de Familias
        assertTrue(television instanceof ProductoElectronico, "Televisor debe ser una instancia de ProductoElectronico");
        assertTrue(celular instanceof ProductoElectronico, "Celular debe ser una instancia de ProductoElectronico");
        assertTrue(calculadora instanceof ProductoElectronico, "Calculadora debe ser una instancia de ProductoElectronico");
        assertTrue(libro instanceof ProductoLiterario, "Libro debe ser una instancia de ProductoLiterario");
        assertTrue(licuadora instanceof ProductoElectroDomestico, "Licuadora  debe ser una instancia de ProductoElectroDomestico");
        assertTrue(tostadora instanceof ProductoElectroDomestico, "Tostadora debe ser una instancia de ProductoElectroDomestico");
        assertTrue(camisetaPromocional instanceof ProductoPromocional, "Camiseta Promocional debe ser una instancia de ProductoPromocional");
    }

    @Test
    public void testAplicarDescuentoHerenciaProductoPromocional() {
        // Crear una instancia de ProductoPromocional usando Camiseta
        ProductoPromocional productoPromocional = new Camisetapromocional("9","Camiseta Deportiva",19.99,"Camiseta de algodón para deportes","ProductoPromocional",
                                                                    "L","Rojo");

        // Aplicar un descuento no válido, porque es el 
        //método aplicarDescuento no existe en la clase CamisetaPromocional
        productoPromocional.aplicarDescuento(20);
        assertEquals(19.99, productoPromocional.getPrecio(), 0.01);

        // Aplicar un descuento no válido, porque es el 
        //método aplicarDescuento no existe en la clase CamisetaPromocional
        productoPromocional.aplicarDescuento(110);
        assertEquals(19.99, productoPromocional.getPrecio(), 0.01); // El precio no debe cambiar

        // Aplicar otro descuento válido
        productoPromocional.aplicarDescuento(100);
        assertEquals(19.99, productoPromocional.getPrecio(), 0.01);
    }
    @Test
    public void testMiColeccionOperacionesBasicas() {
        // Crear una instancia de MiColeccion
        MiColeccion<Producto> coleccion = new MiColeccion<>(10);

        // Verificar que la colección esté inicialmente vacía
        assertEquals(0, coleccion.tamanio(), "La colección debería estar vacía al inicio.");

        // Agregar elementos a la colección
        Producto libro1 = new Libro("1", "Cien Años de Soledad", 19.99, "Descripción de prueba", "ProductoLiterario",
                "Gabriel Garcia Marquez", "Editorial", 1967, "Realismo mágico", 500, "978-84-376-0494-7");
        Producto libro2 = new Libro("2", "1984", 15.99, "Descripción de prueba", "ProductoLiterario",
                "George Orwell", "Editorial", 1949, "Distopía", 328, "978-0-452-28423-4");

        coleccion.agregar(libro1);
        coleccion.agregar(libro2);

        // Verificar que los elementos se hayan agregado correctamente
        assertEquals(2, coleccion.tamanio(), "La colección debería tener 2 elementos.");
        assertEquals(libro1, coleccion.obtener(0), "El primer elemento debería ser 'Cien Años de Soledad'.");
        assertEquals(libro2, coleccion.obtener(1), "El segundo elemento debería ser '1984'.");

        // Eliminar un elemento de la colección
        coleccion.eliminar(0);

        // Verificar que el elemento se haya eliminado correctamente
        assertEquals(1, coleccion.tamanio(), "La colección debería tener 1 elemento después de eliminar.");
        assertEquals(libro2, coleccion.obtener(0), "El primer elemento debería ser '1984' después de eliminar.");

        // Limpiar la colección
        coleccion.limpiar();

        // Verificar que la colección esté vacía después de limpiar
        assertEquals(0, coleccion.tamanio(), "La colección debería estar vacía después de limpiar.");
    }

    @Test
    public void testListarProductosOrdenadosPorID() {
       // Redirigir la salida estándar para capturar el output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Crear una instancia de TiendaDeRegalosService
        TiendaDeRegalosService tienda = new TiendaDeRegalosService(10, "miTest/archivos", "usuario", "email");

        // Agregar productos en un orden desordenado
        tienda.agregarProducto(new Libro("3", "La ciudad y los perros", 20.99, "Novela de Mario Vargas Llosa", "ProductoLiterario",
                "Mario Vargas Llosa", "Editorial", 1963, "Realismo", 400, "978-84-376-0494-8"));
        tienda.agregarProducto(new Libro("1", "Balún Canán", 19.99, "Novela de Rosario Castellanos", "ProductoLiterario",
                "Rosario Castellanos", "Editorial", 1957, "Realismo", 300, "978-84-376-0494-7"));
        tienda.agregarProducto(new Libro("2", "El Principito", 10.99, "Descripción de prueba", "ProductoLiterario",
                "Antoine de Saint-Exupéry", "Editorial", 1943, "Fábula", 96, "978-0-15-601219-5"));

        // Llamar al método listarProductos
        tienda.listarProductos();

        // Capturar la salida y verificar el orden
        String output = outContent.toString();
        String[] lineas = output.split("\n");

        // Buscar las líneas correspondientes a los productos agregados
        int indexBalunCanan = -1;
        int indexElPrincipito = -1;
        int indexCiudadPerros = -1;

        for (int i = 0; i < lineas.length; i++) {
            if (lineas[i].contains("Balún Canán")) {
                indexBalunCanan = i;
            } else if (lineas[i].contains("El Principito")) {
                indexElPrincipito = i;
            } else if (lineas[i].contains("La ciudad y los perros")) {
                indexCiudadPerros = i;
            }
        }

        // Verificar que los índices sean válidos y que estén en el orden correcto
        assertTrue(indexBalunCanan != -1, "No se encontró 'Balún Canán' en la salida.");
        assertTrue(indexElPrincipito != -1, "No se encontró 'El Principito' en la salida.");
        assertTrue(indexCiudadPerros != -1, "No se encontró 'La ciudad y los perros' en la salida.");
        assertTrue(indexBalunCanan < indexElPrincipito, "'Balún Canán' debe aparecer antes que 'El Principito'.");
        assertTrue(indexElPrincipito < indexCiudadPerros, "'El Principito' debe aparecer antes que 'La ciudad y los perros'.");

        // Restaurar la salida estándar
        System.setOut(System.out);
    }

    @Test
    public void testCapturaConNavegacion() {
        // Simular la entrada del usuario
        String simulatedInput = "123\nProducto de prueba\n99.99\nDescripción del producto\n";
        ByteArrayInputStream inContent = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inContent);

        // Redirigir la salida estándar para capturar el output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Crear una instancia de Menu
        Menu menu = new Menu("ruta/prueba", "usuario", "email");

        // Llamar al método capturaConNavegacion
        Scanner scanner = new Scanner(System.in);
        menu.capturaConNavegacion(scanner);
        menu.getIdProducto();


        // Verificar que los datos capturados sean correctos
        assertEquals("123",  menu.getIdProducto(), "El ID del producto no se capturó correctamente.");
        assertEquals("Producto de prueba", menu.getNombre(), "El nombre del producto no se capturó correctamente.");
        assertEquals(99.99, menu.getPrecio(), "El precio del producto no se capturó correctamente.");
        assertEquals("Descripción del producto", menu.getDescripcion(), "La descripción del producto no se capturó correctamente.");

        // Restaurar la entrada y salida estándar
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    public void testProductoDAO() throws IOException {
        // Crear una instancia de ProductoDAO con una ruta de prueba
        String rutaArchivosPrueba = "miTest/archivos"; // Ruta de prueba
        ProductoDAO productoDAO = new ProductoDAO(10, rutaArchivosPrueba); // Capacidad inicial y ruta

        // 1. Verificar que los productos se carguen correctamente
        MiColeccion<Producto> productos = productoDAO.getProductos();
        assertNotNull(productos, "La colección de productos no debería ser nula.");
        int tamanioInicial = productos.tamanio();

        // 2. Agregar un nuevo producto
        Producto nuevoProducto = new Libro("10", "Don Quijote de la Mancha", 29.99, "Novela clásica", "ProductoLiterario",
                "Miguel de Cervantes", "Editorial", 1605, "Novela", 1000, "978-84-376-0494-9");
        productoDAO.agregarProducto(nuevoProducto);

        // Verificar que el producto se haya agregado
        Producto productoAgregado = productoDAO.buscarProducto("Don Quijote de la Mancha");
        assertNotNull(productoAgregado, "El producto debería haberse agregado correctamente.");
        assertEquals("Don Quijote de la Mancha", productoAgregado.getNombre(), "El nombre del producto no coincide.");

        // 3. Eliminar el producto agregado
        boolean eliminado = productoDAO.eliminarProducto("Don Quijote de la Mancha");
        assertTrue(eliminado, "El producto debería haberse eliminado correctamente.");

        // Verificar que el producto ya no exista
        Producto productoEliminado = productoDAO.buscarProducto("Don Quijote de la Mancha");
        assertNull(productoEliminado, "El producto no debería existir después de eliminarlo.");

        // Verificar que el tamaño de la colección sea el mismo que al inicio
        assertEquals(tamanioInicial, productoDAO.getProductos().tamanio(), "El tamaño de la colección debería ser el mismo después de eliminar el producto.");
    }
    
    
}

