package negocio;
import datos.ProductoDAO;
import datos.Producto;
import datos.Libro;
import datos.Celular;

import datos.Television;
import datos.Licuadora;
import datos.Tostadora;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import datos.Calculadora;
import datos.Camisetapromocional;
import datos.ProductoElectronico;
import datos.ProductoElectroDomestico;
import datos.ProductoLiterario;
import datos.ProductoPromocional;

import negocio.utilidades.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class TiendaDeRegalosService {
    private ProductoDAO productoDAO;
    private String ruta;
    private String nombreUsuario;
    private String emailUsuario;

    /**
     * Constructor de la clase TiendaDeRegalosService
     * @param productoDAO
     */
    public TiendaDeRegalosService(int capacidad, String ruta, String nombreUsuario, String emailUsuario) {
        this.productoDAO = new ProductoDAO(capacidad,ruta);
        this.ruta =ruta;
        this.nombreUsuario = nombreUsuario;
        this.emailUsuario = emailUsuario;
    }

    public int getRegistros() {
        return productoDAO.getRegistros();

    }
    /**
     * Método que imprime un mensaje de bienvenida
     */
    public void presentarse() {
        System.out.println("Bienvenido a la Tienda de Regalos");
        System.out.println("Nombre: "+nombreUsuario);
        System.out.println("Email: "+emailUsuario);
        System.out.println("Ruta de trabajo: "+ruta);

    }
    /**
     * Método que muestra los productos de una familia
     * @param familia
     */
    public void mostrarFamiliaProducto(String familia) {
        this.productoDAO.mostrarFamiliaProducto(familia);
        
    }

    /**
     * Método que obtiene la familia de un producto
     * @return
     * @param producto
     *
     */

    public String obtenerFamiliaProductoElectrodomestico() {
        ProductoElectroDomestico producto = new Licuadora("id", "nombre",
                                                         0.0, "descripcion", "familia", "marca", "modelo", "color", 0, "voltaje", 0, 0);   
        return producto.getFamilia();
    }

    public String obtenerFamiliaProductoLiterario() {
        ProductoLiterario producto = new Libro("id", "nombre",
                                                         0.0, "descripcion", "familia", "autor", "editorial", 0, "genero", 0, "isbn");   
        return producto.getFamilia();
    }

    public String obtenerFamiliaProductoElectronico() {
        ProductoElectronico producto = new Celular("id", "nombre",
                                                         0.0, "descripcion", "familia", "marca", "modelo", "color", 0, "voltaje", 0, "sistemaOperativo", 0, 0, "tipoPantalla");
        return producto.getFamilia();
    }

    public String obtenerFamiliaProductoPromocional() {
        ProductoPromocional producto = new Camisetapromocional(null, null, 0, null, null, null, null);
        return producto.getFamilia();
    }


    public void agregarProducto(Producto producto) {
        //invoca al metodo adecuado de productoDAO
        productoDAO.agregarProducto(producto);
       
    }

    public void eliminarProducto(String nombreProducto) {
         //invoca al metodo adecuado de productoDAO
    }

    public Producto buscarProducto(String nombreProducto) {
         //invoca al metodo adecuado de productoDAO
    }

    public MiColeccion<Producto> getProductos() {
         //invoca al metodo adecuado de productoDAO

    }

    public void listarProductos() {

        MiColeccion<Producto> productos = productoDAO.getProductos();

        System.out.println("Número de productos:"+productos.tamanio());

        // Crear un nuevo arreglo de tipo Producto[]
        
        
        // Ordenar el arreglo invocando a Util
       

        // Limpiar la colección y volver a llenarla con los elementos ordenados
        productos.limpiar();
        for (Producto producto : arregloProductos) {
            productos.agregar(producto);
        }

        System.out.println("\nProductos en inventario:\n");
        for (Producto producto : productos) {
            if (producto != null) {
                System.out.println(producto);
            }
        }
    }

    public void venderProducto(String nombreProducto) {
        Producto producto = productoDAO.buscarProducto(nombreProducto);
        if (producto == null) {
            System.out.println("Producto no encontrado: " + nombreProducto);
            return;
        } 

        if ( producto instanceof Vendible) {
            if (((Vendible) producto).esVendible()) {      
                productoDAO.eliminarProducto(nombreProducto);
                System.out.println("Producto vendido: " + nombreProducto);
            }
            else
               System.out.println("Producto no es Vendible: " + nombreProducto);
            
        }
        else
            System.out.println("Producto no es Vendible: " + nombreProducto);
        
    }

    public void actualizarPrecio(String nombreProducto, double nuevoPrecio) {
       //coloca lo necesario
    }
    public void aplicarDescuento(String nombreProducto, double porcentaje) {
        //coloca lo necesario
    }

    public void aplicarDescuentoATodos(double porcentaje) {
        MiColeccion<Producto> productos = productoDAO.getProductos();
        for (int i = 0; i < productoDAO.getRegistros(); i++) {
            productos.obtener(i).aplicarDescuento(porcentaje);
        }    
    }

    public double calcularValorTotalProductos() {
        MiColeccion<Producto> productos = productoDAO.getProductos();
        double total = 0;
        for (Producto producto : productos) {
            if (producto != null) {
               
               //coloca lo necesario
            }
        }
        return total;
    }

    public void mostrarInventario() {
        MiColeccion<Producto> productos = productoDAO.getProductos();
        for (int i = 0; i < productos.tamanio(); i++) {
            System.out.println(productos.obtener(i));
        }
    }

    public void agregarLibro(//completa)  {
        Producto libro = new Libro(idProducto, nombre, precio, descripcion, familia,autor, editorial, 
                                anioPublicacion, genero, numeroPaginas, isbn) ;
         
        
        //Invoca a productoDAO para agregarLibro
    }

    public void agregarCelular(//completa) {
        Producto celular = new Celular(idProducto, nombre, precio, descripcion,familia
                                    ,marca, modelo, color, garantia, voltaje
                                    ,numCamaras, sistemaOperativo, capacidadAlmacenamiento
                                    ,ram, tipoPantalla);
         //Invoca a productoDAO para agregarCelular
    }

    public void agregarTelevision(//completa) {
        Producto producto = new Television(idProducto, nombre, precio, descripcion,familia
                                            ,marca, modelo, color, garantia, voltaje
                                            ,tamanio,  resolucion, tipoPantalla);
         //Invoca a productoDAO para agregarTelevision
    }

    public void agregarLicuadora(//completa) {  
        Producto producto = new Licuadora(idProducto, nombre, precio, descripcion, familia, marca, modelo, color, 
                                         garantia, voltaje, potencia, capacidad);
         //Invoca a productoDAO para agregarLicuadora
    }

    public void agregarTostadora(//completa) {
        Producto producto = new Tostadora(idProducto, nombre, precio,  descripcion,familia
                                        ,marca, modelo, color, garantia, voltaje
                                        ,numRanuras);
        //Invoca a productoDAO para agregarTostadora
    }

    public void agregarCalculadora(//completa) {
        Producto producto = new Calculadora(idProducto, nombre, precio, descripcion,familia
                                            ,marca, modelo, color, garantia
                                            ,voltaje ,tipo);
         //Invoca a productoDAO para agregarCalculadora
    }

    public void agregarProductoPromocional(//completa) {
        Producto producto = new Camisetapromocional(idProducto, nombre, precio, descripcion,familia
                                                    ,talla,color);
        //Invoca a productoDAO para agregarCamisetaPromocional
    }

    public String getRuta(){
        return productoDAO.getRuta();
    }

    public String getNombreUsuario(){
        return this.nombreUsuario;
    }

    public String getEmailUsuario(){
        return this.emailUsuario;
    }

    
    public String generarReporteProductosConEncabezadoId() throws IOException {
        StringBuilder reporte = new StringBuilder();

        // Obtener la fecha actual
        String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    
        // Encabezado del reporte
        reporte.append("=".repeat(130)).append("\n");
        reporte.append("Titulo: Reporde de Productos ordenado por Id\n");
        reporte.append(String.format("Reporte generado por: %s (%s)\n", nombreUsuario, emailUsuario));
        reporte.append(String.format("ruta de trabajo: %s\n",ruta));
        reporte.append(String.format("Fecha: %s\n", fechaActual));
        reporte.append("=".repeat(130)).append("\n");
    
        // Encabezado de la tabla
        reporte.append(String.format("%-10s %-40s %-10s %-20s %-15s\n", "ID", "Nombre", "Precio", "Descripción", "Familia"));
        reporte.append("=".repeat(130)).append("\n");
    
        // Obtener y ordenar la lista de productos

        MiColeccion<Producto> productos = productoDAO.getProductos();

        System.out.println("Número de productos:"+productos.tamanio());

        // Crear un nuevo arreglo de tipo Producto[]
        Producto[] arregloProductos = new Producto[productos.tamanio()];
        for (int i = 0; i < productos.tamanio(); i++) {
            arregloProductos[i] = productos.obtener(i);
        }
        
        // Ordenar el arreglo
        Util.ordenar(arregloProductos);

        // Limpiar la colección y volver a llenarla con los elementos ordenados
        productos.limpiar();
        for (Producto producto : arregloProductos) {
            productos.agregar(producto);
        }
    
        // Recorrer la lista de productos y agregar los detalles al reporte
        int contadorProductos = 0;
        int pagina = 1;
        double totalPrecio = 0;
    
        for (int i = 0; i < productos.tamanio(); i++) {
            Producto producto = productos.obtener(i);
            reporte.append(String.format("%-10s %-40s %-10.2f %-20s %-15s\n",
                    producto.getIdProducto(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getDescripcion(),
                    producto.getFamilia()));
    
            totalPrecio+=producto.getPrecio();
            contadorProductos++;
    
            // Agregar un pie de página cada 10 productos
            if (contadorProductos % 10 == 0 || i == productos.tamanio() - 1) {
                reporte.append("=".repeat(130)).append("\n");
                reporte.append(String.format("Página %d\n", pagina));
                reporte.append("=".repeat(130)).append("\n");
                pagina++;
            }
        }
        // Agregar el total de la columna "Precio" al final del reporte
        reporte.append("\n");
        reporte.append("=".repeat(130)).append("\n");
        reporte.append(String.format("Total de precios: %.2f\n", totalPrecio));
        reporte.append("=".repeat(130)).append("\n");
    
        return reporte.toString();
    }

    public String generarReporteProductosConEncabezadoNombre() throws IOException {
        
        
    }

    public String generarReporteProductosConEncabezadoPrecio() throws IOException {
        
    }

}