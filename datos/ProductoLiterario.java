package datos;

public abstract class ProductoElectronico extends Producto {
    private String marca;
    private String modelo;
    private String color;
    private int garantia;
    private String voltaje;
    final String familia = "ProductoElectronico";

    public ProductoElectronico(String idProducto, String nombre, double precio, String descripcion,
                                String familia, String marca, String modelo, String color, int garantia, String voltaje) {
        super(idProducto, nombre, precio, descripcion, familia);
        // TODO: Inicializar los atributos específicos de ProductoElectronico.
    }

    // Getters y setters
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getGarantia() {
        return garantia;
    }

    public void setGarantia(int garantia) {
        this.garantia = garantia;
    }

    public String getVoltaje() {
        return voltaje;
    }

    public void setVoltaje(String voltaje) {
        this.voltaje = voltaje;
    }

    @Override
    public String obtenerDetalles() {
        // TODO: Implementar este método para devolver los detalles del producto.
        return null; // Retornar null temporalmente.
    }

    @Override
    public String toString() {
        // TODO: Implementar este método para devolver una representación en texto del producto.
        return null; // Retornar null temporalmente.
    }

    @Override
    public boolean aplicarDescuento(double porcentaje) {
        // TODO: Implementar la lógica para aplicar un descuento de hasta el 20%.
        return false; // Retornar false temporalmente.
    }

    @Override
    public boolean esVendible() {
        // TODO: Implementar la lógica para determinar si el producto es vendible.
        return false; // Retornar false temporalmente.
    }

    public String getFamilia() {
        return familia;
    }

    @Override
    public String toArchivo() {
        // TODO: Implementar este método para devolver una representación en texto para guardar en un archivo.
        return null; // Retornar null temporalmente.
    }
}