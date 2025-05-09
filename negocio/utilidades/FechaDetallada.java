package negocio.utilidades;

public class FechaDetallada extends Fecha {
    private static final String[] MESES = {
        "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
        "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
    };

    public FechaDetallada() {
        super();
        // TODO: Inicializar la fecha detallada con valores por defecto.
    }

    public FechaDetallada(int d, int m, int a) {
        super(d, m, a);
        // TODO: Inicializar la fecha detallada con los valores proporcionados.
    }

    public FechaDetallada(String f) {
        super(f);
        // TODO: Inicializar la fecha detallada a partir de una cadena en formato "dd/mm/aaaa".
    }

    @Override
    public String toString() {
        // TODO: Implementar este método para devolver la fecha en formato "dd de Mes de aaaa".
        return null; // Retornar null temporalmente.
    }

    // TODO: Los alumnos pueden agregar métodos adicionales si es necesario,
    // como validaciones o formatos personalizados.
}

