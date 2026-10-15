package mvc.login.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * VISTA del login: dos campos, un botón y una etiqueta para mensajes.
 *
 * La vista NO comprueba si la contraseña es correcta ni decide qué mensaje
 * mostrar: solo entrega lo que ha escrito el usuario y pinta lo que le digan.
 */
public class VistaLogin extends VBox {

    private final TextField txtUsuario = new TextField();
    private final PasswordField txtClave = new PasswordField();
    private final Button btnEntrar = new Button("Entrar");
    private final Label lblMensaje = new Label(" ");

    public VistaLogin() {
        super(10); // 10 píxeles de separación entre los elementos

        GridPane formulario = new GridPane();
        formulario.setHgap(5);
        formulario.setVgap(5);
        formulario.addRow(0, new Label("Usuario:"), txtUsuario);
        formulario.addRow(1, new Label("Contraseña:"), txtClave);

        btnEntrar.setMaxWidth(Double.MAX_VALUE); // que ocupe todo el ancho
        btnEntrar.setDefaultButton(true);        // pulsar Intro equivale a pulsar "Entrar"

        getChildren().addAll(formulario, btnEntrar, lblMensaje);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10));
    }

    public String getUsuario() {
        return txtUsuario.getText();
    }

    public String getClave() {
        return txtClave.getText();
    }

    public void mostrarMensaje(String mensaje) {
        lblMensaje.setText(mensaje);
    }

    public void limpiarClave() {
        txtClave.clear();
        txtClave.requestFocus();
    }

    public void alPulsarEntrar(Runnable accion) {
        btnEntrar.setOnAction(e -> accion.run());
    }
}
