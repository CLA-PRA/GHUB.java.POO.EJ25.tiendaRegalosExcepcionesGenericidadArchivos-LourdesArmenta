package negocio.utilidades;
import datos.Producto;

public class CriterioProductoClave implements Criterio<Producto> {

    @Override
    public int comparar(Producto a, Producto b) {
        return a.getIdProducto().compareToIgnoreCase(b.getIdProducto());

    }
      
}
