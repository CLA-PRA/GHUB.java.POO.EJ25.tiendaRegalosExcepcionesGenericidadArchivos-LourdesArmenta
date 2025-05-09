package datos;

public class Television extends ProductoElectronico {
    private String tamanio;
    private String resolucion;
    private String tipoPantalla;
    private final String familia = super.getFamilia();

    public Television(String idProducto, String nombre, double precio, String descripcion, String familia,
                      String marca, String modelo, String color, int garantia, String voltaje,
                      String tamanio, String resolucion, String tipoPantalla) {
        super(idProducto, nombre, precio, descripcion, familia, marca, modelo, color, garantia, voltaje);
        // TODO: Inicializar los atributos específicos de Television.
    }

    public static Television fromString(String[] partes) {
        // TODO: Implementar la lógica para crear un objeto Television a partir de un arreglo de Strings.
        // Sugerencia: Validar que el arreglo tenga al menos 13 elementos y convertir los valores necesarios.
        return null; // Retornar null temporalmente.
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getTipoPantalla() {
        return tipoPantalla;
    }

    public void setTipoPantalla(String tipoPantalla) {
        this.tipoPantalla = tipoPantalla;
    }

    @Override
    public String obtenerDetalles() {
        // TODO: Implementar este método para devolver los detalles de la televisión.
        return null; // Retornar null temporalmente.
    }

    @Override
    public boolean aplicarDescuento(double porcentaje) {
        // TODO: Implementar la lógica para aplicar un descuento de hasta el 20%.
        return false; // Retornar false temporalmente.
    }

    @Override
    public String toString() {
        // TODO: Implementar este método para devolver una representación en texto de la televisión.
        return null; // Retornar null temporalmente.
    }

    @Override
    public boolean esVendible() {
        // TODO: Implementar la lógica para determinar si la televisión es vendible.
        return false; // Retornar false temporalmente.
    }

    @Override
    public String toArchivo() {
        // TODO: Implementar este método para devolver una representación en texto para guardar en un archivo.
        return null; // Retornar null temporalmente.
    }
}