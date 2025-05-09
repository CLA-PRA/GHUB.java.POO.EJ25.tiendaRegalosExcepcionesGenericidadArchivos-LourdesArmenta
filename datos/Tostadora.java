package datos;

public class Tostadora extends ProductoElectroDomestico {
    private int numRanuras;
    final String familia = super.getFamilia();
    
    public Tostadora(String idProducto, String nombre, double precio, String descripcion, String familia,
                     String marca, String modelo, String color, int garantia, String voltaje, int numRanuras) {
        super(idProducto, nombre, precio, descripcion, familia, marca, modelo, color, garantia, voltaje);
        // TODO: Inicializar los atributos específicos de Tostadora.
    }

    public static Tostadora fromString(String[] partes) {
        // TODO: Implementar la lógica para crear un objeto Tostadora a partir de un arreglo de Strings.
        // Sugerencia: Validar que el arreglo tenga al menos 11 elementos y convertir los valores necesarios.
        return null; // Retornar null temporalmente.
    }

    public int getNumRanuras() {
        return numRanuras;
    }

    public void setNumRanuras(int numRanuras) {
        this.numRanuras = numRanuras;
    }

    @Override
    public String obtenerDetalles() {
        // TODO: Implementar este método para devolver los detalles de la tostadora, incluyendo el número de ranuras.
        return null; // Retornar null temporalmente.
    }

    @Override
    public boolean aplicarDescuento(double porcentaje) {
        // TODO: Implementar la lógica para aplicar un descuento de hasta el 50%.
        return false; // Retornar false temporalmente.
    }

    @Override
    public String toString() {
        // TODO: Implementar este método para devolver una representación en texto de la tostadora.
        return null; // Retornar null temporalmente.
    }

    @Override
    public boolean esVendible() {
        // TODO: Implementar la lógica para determinar si la tostadora es vendible.
        return false; // Retornar false temporalmente.
    }

    @Override
    public String toArchivo() {
        // TODO: Implementar este método para devolver una representación en texto para guardar en un archivo.
        return null; // Retornar null temporalmente.
    }
}