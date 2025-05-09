package negocio.utilidades;
import datos.Producto;

public class CriterioProductoNombre implements Criterio<Producto>{

    @Override
    public int comparar(Producto a, Producto b) {
        return a.getNombre().compareToIgnoreCase(b.getNombre());

    } 
    
}
