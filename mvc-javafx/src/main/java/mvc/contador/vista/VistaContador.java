package mvc.contador.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * VISTA: solo sabe DIBUJAR y AVISAR de que el usuario ha hecho algo.
 *
 * - No decide qué ocurre al pulsar un botón (eso es cosa del controlador).
 * - No guarda el valor del contador (eso es cosa del modelo).
 * - No conoce ni al modelo ni al controlador: no hay ningún import de ellos.
 *
 * La vista no es la ventana: la ventana (Stage) la crea el propio JavaFX y nos
 * la entrega en Application.start(). Por eso la vista hereda de un PANEL
 * (BorderPane) que luego se coloca dentro de esa ventana.
 */
public class VistaContador extends BorderPane {

    private final Label lblValor = new Label("0");
    private final Button btnIncrementar = new Button("+");
    private final Button btnReset = new Button("Reset");

    public VistaContador() {
        lblValor.setFont(Font.font("System", FontWeight.BOLD, 48));

        HBox panelBotones = new HBox(10, btnIncrementar, btnReset);
        panelBotones.setAlignment(Pos.CENTER);
        panelBotones.setPadding(new Insets(10));

        setCenter(lblValor);       // el centro de un BorderPane se centra solo
        setBottom(panelBotones);
    }

    /** El controlador le dice a la vista qué número enseñar; la vista solo lo pinta. */
    public void mostrar(int valor) {
        lblValor.setText(String.valueOf(valor));
    }

    // La vista ofrece "enganches" para que el controlador se suscriba a los botones.
    // Reciben un Runnable ("algo que ejecutar") en lugar de un EventHandler<ActionEvent>:
    // así el controlador no necesita importar nada de JavaFX.
    //
    // OJO: setOnAction SUSTITUYE la acción anterior: un botón solo tiene una.

    public void alPulsarIncrementar(Runnable accion) {
        btnIncrementar.setOnAction(e -> accion.run());
    }

    public void alPulsarReset(Runnable accion) {
        btnReset.setOnAction(e -> accion.run());
    }
}
