package negocio.utilidades;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MiColeccion<T> implements Lista<T>, Iterable<T> {
    private Object datos[];
    private int cantidad;

    public MiColeccion() {
        this(10); // Tamaño inicial por defecto
    }

    public MiColeccion(int capacidadInicial) {
        datos = new Object[capacidadInicial];
        cantidad = 0;
    }

    @Override
    public void agregar(T elemento) {
        // TODO: Implementar la lógica para agregar un elemento al final de la colección.
    }

    @Override
    public void insertar(T elemento, int indice) {
        // TODO: Implementar la lógica para insertar un elemento en una posición específica.
    }

    @Override
    public int buscar(T elemento) {
        // TODO: Implementar la lógica para buscar un elemento en la colección.
        return -1; // Retornar -1 temporalmente.
    }

    @Override
    public T eliminar(int indice) {
        // TODO: Implementar la lógica para eliminar un elemento en una posición específica.
        return null; // Retornar null temporalmente.
    }

    @Override
    public T obtener(int indice) {
        // TODO: Implementar la lógica para obtener un elemento en una posición específica.
        return null; // Retornar null temporalmente.
    }

    public T[] obtenerColeccion() {
        // TODO: Implementar la lógica para devolver una copia de la colección.
        return null; // Retornar null temporalmente.
    }

    public void limpiar() {
        // TODO: Implementar la lógica para reiniciar la colección.
    }

    @Override
    public boolean esVacio() {
        // TODO: Implementar la lógica para verificar si la colección está vacía.
        return false; // Retornar false temporalmente.
    }

    @Override
    public int tamanio() {
        // TODO: Implementar la lógica para devolver el tamaño de la colección.
        return 0; // Retornar 0 temporalmente.
    }

    @Override
    public Iterator<T> iterator() {
        // TODO: Implementar un iterador para recorrer los elementos de la colección.
        return null; // Retornar null temporalmente.
    }

    // TODO: Los alumnos pueden agregar métodos adicionales si es necesario,
    // como ordenamiento, filtrado o búsqueda avanzada.
}
