package negocio.utilidades;
import datos.Producto;

public class CriterioProductoPrecio implements Criterio<Producto>{

    @Override
    public int comparar(Producto a, Producto b) {
       if (a.getPrecio()>b.getPrecio())
          return 1;
       else if(a.getPrecio()<b.getPrecio())
          return -1;
       else
          return 0;

    }
      
}
