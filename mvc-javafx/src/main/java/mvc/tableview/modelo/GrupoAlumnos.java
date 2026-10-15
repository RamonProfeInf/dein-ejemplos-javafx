package mvc.tableview.modelo;

import java.util.Objects;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * MODELO de dominio: la lista de alumnos de un grupo.
 *
 * Guarda los alumnos en una ObservableList: la TableView se suscribe a ella y
 * se repinta sola cuando cambia. Sigue sin importar nada gráfico (ni
 * javafx.scene ni javafx.stage), así que se puede probar sin ventanas.
 */
public class GrupoAlumnos {

    private final ObservableList<Alumno> alumnos = FXCollections.observableArrayList();
    private final ReadOnlyDoubleWrapper notaMedia = new ReadOnlyDoubleWrapper(0);

    public GrupoAlumnos() {
        // La nota media se recalcula sola cada vez que cambia la lista
        alumnos.addListener((ListChangeListener<Alumno>) cambio -> notaMedia.set(calcularNotaMedia()));
    }

    public void agregar(Alumno alumno) {
        alumnos.add(Objects.requireNonNull(alumno));
    }

    /** Lista de solo lectura, pero observable: quien la reciba se entera de los cambios. */
    public ObservableList<Alumno> getAlumnos() {
        return FXCollections.unmodifiableObservableList(alumnos);
    }

    public double getNotaMedia() {
        return notaMedia.get();
    }

    public ReadOnlyDoubleProperty notaMediaProperty() {
        return notaMedia.getReadOnlyProperty();
    }

    private double calcularNotaMedia() {
        if (alumnos.isEmpty()) {
            return 0;
        }
        double suma = 0;
        for (Alumno a : alumnos) {
            suma += a.getNota();
        }
        return suma / alumnos.size();
    }
}
