package datos;

//la clase debe heredar de Producto Electronico
public class Calculadora   {
    private String tipo;
    final String familia = super.getFamilia();

    public Calculadora(String idProducto, String nombre, double precio, String descripcion, String familia
                       ,String marca, String modelo, String color, int garantia, 
                       String voltaje ,String tipo) {

        super(idProducto, nombre, precio, descripcion, familia, marca, modelo, color, garantia, voltaje);
        this.tipo = tipo;
    }

    public static Calculadora fromString(String[] partes) {
       
        if (partes.length < 11) {
            throw new IllegalArgumentException("Datos incompletos para crear una Calculadora");
        }
        try {
            String idProducto = partes[0].trim();
            String nombre = partes[1].trim();
            double precio = Double.parseDouble(partes[2].trim());
            String descripcion = partes[3].trim();
            String familia = partes[4].trim();
            String marca = partes[5].trim();
            String modelo = partes[6].trim();
            String color = partes[7].trim();
            int garantia = Integer.parseInt(partes[8].trim());
            String voltaje = partes[9].trim();
            String tipo = partes[10].trim();
            return new Calculadora(idProducto, nombre, precio, descripcion, familia, marca, modelo, color, garantia, voltaje, tipo);
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir número: " + e.getMessage());
            throw e; // Re-lanzar la excepción para que pueda ser manejada en un nivel superior
        }
    
    }
    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    //metodo getFamilia sobreescrito de la clase ProductoElectronico
    @Override
    public String getFamilia(){
        return super.getFamilia();
    }

    //TODO:sobreescritura del metodo obtenerDetalles de la clase ProductoElectronico y
    //TODO:agregando el atributo tipo
    

    // TODO: Implementar el método aplicarDescuento 
   

    @Override
    public String toString() {
    
        return super.toString()+"," + tipo ;
    }

    @Override
    public String toArchivo() {
    return super.toArchivo() + "," +
           getMarca() + "," +
           getModelo() + "," +
           getColor() + "," +
           getGarantia() + "," +
           getVoltaje();
    }

}