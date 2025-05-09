package negocio.utilidades;


public class Util {
    
    // Método para ordenar usando generico con restricciones genComparable
    public static <T extends Comparable<T>> void ordenar(T[] arr) {
        if (arr == null || arr.length == 0) {
            return; // No hay nada que ordenar
        }
    
        for (T elemento : arr) {
            //System.out.println(elemento);
            if (elemento == null) {
                throw new IllegalArgumentException("El arreglo contiene elementos nulos.");
            }
        }
    
        boolean ordenado = false;
        while (!ordenado) {
            ordenado = true;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i + 1].compareTo(arr[i]) < 0) {
                    T aux = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = aux;
                    ordenado = false;
                }
            }
        }
    }

    // Método genérico para ordenar usando Comparator
    public static <T> void ordenar(T[] arr, Criterio<T> cr) {
        boolean ordenado = false;
        while (!ordenado) {
            ordenado = true;
            for (int i = 0; i < arr.length - 1; i++) {
                if (cr.comparar(arr[i + 1], arr[i]) < 0) {
                    T aux = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = aux;
                    ordenado = false;
                }
            }
        }
    }
    
    public static <T> void imprimir(T arr[]){
        for (T elemento: arr){
            System.out.println(elemento);
        }

    }

    
    
    
}
