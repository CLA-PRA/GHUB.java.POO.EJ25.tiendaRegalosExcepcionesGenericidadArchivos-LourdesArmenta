package negocio.utilidades;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MiColeccion<T> implements Lista<T>, Iterable<T>{
    Object datos[];
    int cantidad;
    public MiColeccion(){
        this(10);//Producto[] productos
    

    }
    public MiColeccion(int i){
        datos = new Object[i];
        cantidad=0;
    }
    @Override
    public void agregar(T ele){
        insertar(ele, cantidad);
    }

    @Override
    public void insertar(T ele, int i){
        if(cantidad==datos.length){
           Object aux[] = datos;
           datos = new Object[datos.length*2];
           for (int j=0;j<cantidad;j++){
                datos[j]=aux[j];
           }
           aux=null;

        }
        for (int j=cantidad-1;j>=i;j--){
            datos[j+1]=datos[j];
        }
        datos[i]=ele;
        cantidad++;
    }

    @Override
    public int buscar(T elm){
        int i=0;
        for(;i<cantidad && !datos[i].equals(elm);i++);
        return i<cantidad?i:-1;
    }

    @Override
    public T eliminar(int i){
        Object aux = datos[i];
        for(int j=i;j<cantidad-1;j++){
            datos[j]=datos[j+1];
        }
        cantidad--;
        return (T)aux;
    }

    @Override
    public T obtener(int i){
        return (T)datos[i];
    }

    public T[] obtenerColeccion(){
        return Arrays.copyOf((T[]) datos, cantidad);
       
    }

    public void limpiar() {
        datos = new Object[10]; // Reinicia el arreglo con un tamaño inicial
        cantidad = 0; // Reinicia el contador de elementos
    }

    @Override
    public boolean esVacio() {
       return cantidad==0;
    }
    @Override
    public int tamanio() {
        return cantidad;
    }

    /*
     * Una clase interna anónima es una clase que no tiene un nombre explícito 
     * y se define directamente en el lugar donde se necesita. 
     * En este caso, la clase interna anónima implementa la interfaz Iterator<T> 
     * y se utiliza para recorrer los elementos de la colección.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int indice = 0;

            @Override
            public boolean hasNext() {
                return indice < cantidad;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) datos[indice++];
            }
        };
    }




   
    
    
   
    


}