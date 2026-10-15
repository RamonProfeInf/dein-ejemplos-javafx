package mvc.calculadoraespagueti;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * CALCULADORA "ESPAGUETI" — EJEMPLO DE LO QUE NO HAY QUE HACER.
 *
 * Funciona, pero TODO está mezclado en una sola clase: la ventana (vista),
 * los datos y las operaciones (modelo) y la reacción a los botones (controlador).
 * Los comentarios [OLOR] señalan algunos "code smells". Busca más.
 *
 * TAREA PARA EL ALUMNO
 * --------------------
 * 1. Refactoriza la calculadora en los paquetes modelo, vista y controlador,
 *    con una clase App que cree y conecte las piezas y un Main que la lance.
 * 2. El modelo (por ejemplo, una clase Calculadora) no puede importar
 *    javafx.scene ni javafx.stage, y debe poder usarse sin ventana.
 * 3. La vista no puede contener ningún cálculo ni ningún if sobre operaciones.
 * 4. Escribe al menos un test JUnit 5 del modelo.
 * 5. PISTA: esta calculadora tiene un fallo escondido. Prueba "2 + 3 + 4 =".
 *    Con el modelo separado, escribe un test que lo detecte y arréglalo.
 */
public class CalculadoraEspagueti extends Application {

    TextField pantalla = new TextField("0"); // [OLOR] atributos sin private
    double num1 = 0;                         // [OLOR] nombres poco descriptivos
    String op = "";                          // [OLOR] la operación es un String "mágico"
    boolean limpiar = true;
    Stage ventana;                           // [OLOR] guardamos la ventana para que la lógica abra diálogos

    @Override
    public void start(Stage stage) {
        ventana = stage;
        pantalla.setEditable(false);
        pantalla.setAlignment(Pos.CENTER_RIGHT);
        pantalla.setFont(Font.font("Monospaced", FontWeight.BOLD, 24));

        GridPane p = new GridPane();
        p.setHgap(4);
        p.setVgap(4);
        String[] t = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "C", "0", "=", "+"};
        for (int i = 0; i < t.length; i++) {
            String texto = t[i];
            Button b = new Button(texto);
            b.setFont(Font.font("SansSerif", FontWeight.BOLD, 18));
            b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            GridPane.setHgrow(b, Priority.ALWAYS);
            GridPane.setVgrow(b, Priority.ALWAYS);
            b.setOnAction(e -> pulsar(texto)); // [OLOR] un único método para 16 botones distintos
            p.add(b, i % 4, i / 4);
        }

        BorderPane raiz = new BorderPane();
        raiz.setTop(pantalla);
        raiz.setCenter(p);
        raiz.setPadding(new Insets(4));
        BorderPane.setMargin(pantalla, new Insets(0, 0, 4, 0));

        stage.setTitle("Calculadora espagueti");
        stage.setScene(new Scene(raiz, 260, 320));
        stage.show();
    }

    // [OLOR] un método enorme que hace de todo: lee la vista, calcula, valida y pinta
    void pulsar(String c) {
        if (c.equals("C")) {
            pantalla.setText("0");
            num1 = 0;
            op = "";
            limpiar = true;
        } else if (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/")) {
            // [OLOR] el dato se lee del texto de la pantalla: la vista hace de modelo
            num1 = Double.parseDouble(pantalla.getText());
            op = c;
            limpiar = true;
        } else if (c.equals("=")) {
            if (op.equals("")) {
                return;
            }
            double num2 = Double.parseDouble(pantalla.getText());
            double r = 0;
            if (op.equals("+")) {
                r = num1 + num2;
            } else if (op.equals("-")) {
                r = num1 - num2;
            } else if (op.equals("*")) {
                r = num1 * num2;
            } else if (op.equals("/")) {
                if (num2 == 0) {
                    // [OLOR] la lógica de negocio abre ventanas de diálogo
                    Alert a = new Alert(Alert.AlertType.ERROR, "No se puede dividir entre 0");
                    a.initOwner(ventana);
                    a.showAndWait();
                    pantalla.setText("0"); // [OLOR] código copiado de la rama "C"
                    num1 = 0;
                    op = "";
                    limpiar = true;
                    return;
                }
                r = num1 / num2;
            }
            // [OLOR] formatear el resultado mezclado con el cálculo
            if (r == (long) r) {
                pantalla.setText(String.valueOf((long) r));
            } else {
                pantalla.setText(String.valueOf(r));
            }
            op = "";
            limpiar = true;
        } else {
            // Es un dígito
            if (limpiar || pantalla.getText().equals("0")) {
                pantalla.setText(c);
                limpiar = false;
            } else {
                pantalla.setText(pantalla.getText() + c);
            }
        }
    }
}
