package soluciones.agenda.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * MODELO: la agenda con sus operaciones de alta, baja, modificación y búsqueda.
 *
 * Trabajamos con objetos Contacto en lugar de con números de fila: cuando la
 * tabla muestra un resultado filtrado, la fila 0 de la tabla NO es la posición 0
 * de la agenda, así que usar índices daría errores.
 *
 * La búsqueda usa una FilteredList: una "vista filtrada" de la lista de
 * contactos que se mantiene al día sola. Si se da de alta un contacto que
 * cumple el filtro, aparece en el resultado; si no lo cumple, no. Nadie tiene
 * que repetir la búsqueda tras cada cambio.
 */
public class Agenda {

    private final ObservableList<Contacto> contactos = FXCollections.observableArrayList();
    private final FilteredList<Contacto> resultado = new FilteredList<>(contactos);

    /** ALTA */
    public void agregar(Contacto contacto) {
        comprobarNombreLibre(contacto.nombre(), null);
        contactos.add(contacto);
    }

    /** BAJA */
    public void eliminar(Contacto contacto) {
        if (!contactos.remove(contacto)) {
            throw new IllegalArgumentException("El contacto no existe");
        }
    }

    /** MODIFICACIÓN: sustituye "original" por "nuevo" en la misma posición. */
    public void modificar(Contacto original, Contacto nuevo) {
        int posicion = contactos.indexOf(original);
        if (posicion < 0) {
            throw new IllegalArgumentException("El contacto no existe");
        }
        comprobarNombreLibre(nuevo.nombre(), original);
        contactos.set(posicion, nuevo);
    }

    /** BÚSQUEDA: cambia el filtro. Si el texto está vacío, se ven todos. */
    public void buscar(String texto) {
        if (texto == null || texto.isBlank()) {
            resultado.setPredicate(null); // null = sin filtro
        } else {
            String limpio = texto.trim();
            resultado.setPredicate(c -> c.coincideCon(limpio));
        }
    }

    /**
     * Los contactos que cumplen la búsqueda actual. Es observable y de solo
     * lectura: la tabla lo muestra y se actualiza sola con cada cambio.
     */
    public ObservableList<Contacto> getResultadoBusqueda() {
        return FXCollections.unmodifiableObservableList(resultado);
    }

    public int getCantidad() {
        return contactos.size();
    }

    /** Regla de negocio: no puede haber dos contactos con el mismo nombre. */
    private void comprobarNombreLibre(String nombre, Contacto excepto) {
        for (Contacto c : contactos) {
            if (c != excepto && c.nombre().equalsIgnoreCase(nombre)) {
                throw new IllegalArgumentException("Ya existe un contacto llamado " + nombre);
            }
        }
    }
}
