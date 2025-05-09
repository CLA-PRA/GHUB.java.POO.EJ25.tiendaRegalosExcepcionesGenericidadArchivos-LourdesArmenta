package negocio.excepciones;

/**
 * Excepción personalizada para representar errores físicos o de hardware.
 * Esta clase extiende Exception y puede ser utilizada para manejar errores
 * relacionados con problemas físicos, como fallos en el disco o en el sistema de archivos.
 */
public class ErrorFisicoException extends Exception {

    /**
     * Constructor que recibe un mensaje de error.
     * @param message El mensaje que describe el error.
     */
    public ErrorFisicoException(String message) {
        super(message);
    }

    /**
     * Constructor que recibe un mensaje de error y una causa.
     * @param message El mensaje que describe el error.
     * @param cause La causa original del error (otra excepción).
     */
    public ErrorFisicoException(String message, Throwable cause) {
        super(message, cause);
    }

    // TODO: Los alumnos pueden agregar métodos adicionales si es necesario,
    // como códigos de error personalizados o lógica específica para manejar errores físicos.
}
