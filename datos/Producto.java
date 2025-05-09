package datos;
import negocio.Vendible;

public abstract class Producto implements Vendible, Comparable<Producto> {
    private String idProducto;
    private String nombre;
    private double precio;
    private String descripcion;
    private String familia;

    // Constructor vacío
    public Producto() {
        // TODO: Completar el constructor vacío si es necesario.
    }

    // Constructor parametrizado
    public Producto(String idProducto, String nombre, double precio, String descripcion, String familia) {
        // TODO: Implementar el constructor para inicializar los atributos.
    }

    // Getters y setters
    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    // Método toString
    @Override
    public String toString() {
        // TODO: Implementar este método para devolver una representación en texto del producto.
        return null; // Retornar null temporalmente.
    }

    // Método obtenerDetalles
    public String obtenerDetalles() {
        // TODO: Implementar este método para devolver los detalles del producto.
        return null; // Retornar null temporalmente.
    }

    // Métodos abstractos
    public abstract boolean aplicarDescuento(double porcentaje);

    public abstract boolean esVendible();

    public abstract String toArchivo();

    // Método compareTo
    @Override
    public int compareTo(Producto o) {
        // TODO: Implementar la comparación entre productos por idProducto.
        return 0; // Retornar 0 temporalmente.
    }
}