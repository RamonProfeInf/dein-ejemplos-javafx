package mvc.contadorfxml.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import mvc.contador.modelo.Contador;

/**
 * CONTROLADOR de la versión FXML del contador.
 *
 * Aquí la vista es el fichero VistaContador.fxml y NO existe una clase
 * VistaContador. El FXMLLoader crea los nodos descritos en el FXML y los
 * INYECTA en los atributos marcados con @FXML de esta clase.
 *
 * Consecuencia: este controlador sí conoce un Label, cosa que en la versión
 * "en código" evitábamos. Es el precio de usar FXML, y es como trabaja casi todo
 * el mundo en JavaFX. La regla importante sigue en pie: el controlador no hace
 * cálculos (eso es del modelo) ni decide colores o posiciones (eso es del FXML).
 */
public class ControladorContador {

    private final Contador modelo;

    @FXML
    private Label lblValor; // lo rellena el FXMLLoader gracias a fx:id="lblValor"

    /**
     * El FXMLLoader no sabe crear esta clase porque necesita un modelo. Por eso
     * App le da una "fábrica de controladores" que llama a este constructor.
     */
    public ControladorContador(Contador modelo) {
        this.modelo = modelo;
    }

    /**
     * El FXMLLoader llama a initialize() cuando ya ha inyectado los @FXML.
     * En el constructor lblValor todavía es null: no se puede usar ahí.
     */
    @FXML
    private void initialize() {
        refrescarVista(); // mostrar el estado inicial
    }

    @FXML
    private void incrementar() { // onAction="#incrementar" en el FXML
        modelo.incrementar();    // 1) cambiar el modelo
        refrescarVista();        // 2) actualizar la vista
    }

    @FXML
    private void reiniciar() {   // onAction="#reiniciar"
        modelo.reiniciar();
        refrescarVista();
    }

    private void refrescarVista() {
        lblValor.setText(String.valueOf(modelo.getValor()));
    }
}
