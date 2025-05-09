package datos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import negocio.utilidades.MiColeccion;

public class ProductoDAO {
   
    private MiColeccion<Producto> productos;
    private String ruta;
    private int registros = 0;
    private static final String[] lista_productos = {
        "Libro", "Celular", "Television", "Calculadora",
        "Licuadora", "Tostadora", "CamisetaPromocional"
    };

    public ProductoDAO(int capacidad, String ruta) {
        this.productos = new MiColeccion<>();
        this.ruta = ruta;

        // TODO: Cargar los productos desde los archivos.
        // Sugerencia: Iterar sobre `lista_productos` y llamar a un método para cargar cada tipo de producto.
    }
    
    public String getRuta() {
        return ruta;
    }

    public int getRegistros() {
        return registros;
    }

    public void agregarProducto(Producto producto) {
        productos.agregar(producto);
        // TODO: Guardar el producto en el archivo correspondiente.
    }

    public boolean eliminarProducto(String nombreProducto) {
        // TODO: Implementar la lógica para eliminar un producto de la colección y del archivo.
        return false; // Retornar false temporalmente.
    }

    public Producto buscarProducto(String nombreProducto) {
        // TODO: Implementar la búsqueda de un producto por nombre.
        return null; // Retornar null temporalmente.
    }

    public MiColeccion<Producto> listarProductos() {
        // TODO: Implementar la lógica para listar todos los productos.
        return productos;
    }

    public void actualizarProducto(Producto producto) {
        // TODO: Implementar la lógica para actualizar un producto en la colección y en el archivo.
    }

    

    public void mostrarFamiliaProducto(String familia) {
        System.out.println("Productos de la familia: " + familia);
        for (int i = 0; i < productos.tamanio(); i++) {
            
            if (productos.obtener(i).getFamilia().toLowerCase().contains(familia.toLowerCase())) {
                System.out.println(productos.obtener(i).obtenerDetalles());
                
            }
            
        }
    }

    public void cargarProductos(String strProducto) {
       
        String archivo = ruta+"/" + strProducto.toLowerCase() + ".txt";
        
        try(BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine();
            
            while (linea != null) {
                Producto producto = null; 
                //split separa la linea en partes
                String[] partes = linea.split(",");
                //verifica que la cantidad de partes sea la esperada
                int atributosEsperados = getAtributosEsperados(strProducto);
                if(partes.length !=  atributosEsperados){
                    System.err.println("Línea malformada en " + archivo + ": " + linea);
                    linea = br.readLine();
                    continue;
                }
                try{
                    switch(strProducto){
                        case "Libro":
                            producto = Libro.fromString(partes);
                            break;
                        case "Celular": 
                            producto = Celular.fromString(partes);
                            break;
                        case "Television":
                            producto = Television.fromString(partes);
                            break;
                        case "Calculadora":
                            producto = Calculadora.fromString(partes);
                            break;
                        case "Licuadora":   
                            producto = Licuadora.fromString(partes);
                            break;
                        case "Tostadora": 
                            producto = Tostadora.fromString(partes);
                            break;
                        case "CamisetaPromocional":
                            producto = Camisetapromocional.fromString(partes);
                            break;
                    }
                    if (producto != null) {
                        productos.agregar(producto);
                        registros++;
                    }
                }
                catch(NumberFormatException e){
                    System.err.println("Error al convertir número: " + e.getMessage());
                }
                linea = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo: " + archivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + archivo);
        }
    }

    public void guardarProductos(Producto producto){
            String archivo = "";
            String linea = "";
            if (producto instanceof Libro) {
                Libro libro = (Libro) producto;
                archivo = ruta+"/libro.txt";
            
                linea =  libro.toString();
            } else if (producto instanceof Celular) {
                Celular celular = (Celular) producto;
                archivo = ruta+"/celular.txt";
                
                linea = celular.toString();
            } else if (producto instanceof Television) {
                Television televisor = (Television) producto;
                archivo = ruta+"/television.txt";
               
                linea = televisor.toString();
            }else if (producto instanceof Calculadora) {
                Calculadora calculadora = (Calculadora) producto;
                archivo = ruta+"/calculadora.txt";
                
                linea =calculadora.toString(); 
            } else if (producto instanceof Licuadora) {
                Licuadora licuadora = (Licuadora) producto;
                archivo = ruta+"/licuadora.txt";
                
                linea = licuadora.toString();
            }else if(producto instanceof Tostadora){
                Tostadora tostadora = (Tostadora) producto;
                archivo = ruta+"/tostadora.txt";
               
                linea =tostadora.toString();
            }else if(producto instanceof Camisetapromocional){
                Camisetapromocional camiseta = (Camisetapromocional) producto;
                archivo = ruta+"/camisetapromocional.txt";
                
                linea =camiseta.toString();
            }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(archivo,true))) {
            
                bw.write(linea);
                bw.newLine();
                registros++;
               
            
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + archivo);
            e.printStackTrace();
        }
    }
    private int getAtributosEsperados(String strProducto) {
        switch (strProducto) {
            case "Calculadora":
                return 11;
            case "CamisetaPromocional":
                return 7;
            case "Celular":
                return 15;
            case "Libro":
                return 11;
            case "Licuadora":
                return 12;
            case "Television":
                return 13;
            case "Tostadora":
                return 11;
            default:
                throw new IllegalArgumentException("Tipo de producto desconocido: " + strProducto);
        }
    }
   


}