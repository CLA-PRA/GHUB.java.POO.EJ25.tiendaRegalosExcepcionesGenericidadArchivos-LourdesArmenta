package negocio.utilidades;
public interface Lista<T>{
    void agregar(T elemento);
    void insertar(T elemento, int indice);
    int buscar(T elemento);
    T eliminar(int indice);
    T obtener(int indice);
    T[] obtenerColeccion();
    void limpiar();
    int tamanio();
    boolean esVacio();

}