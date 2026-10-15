package soluciones.banco;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import soluciones.banco.controlador.ControladorBanco;
import soluciones.banco.modelo.CuentaBancaria;
import soluciones.banco.vista.VentanaBanco;

public class App extends Application {

    @Override
    public void start(Stage ventanaPrincipal) {
        CuentaBancaria cuenta = new CuentaBancaria("Ana García");
        VentanaBanco ventana = new VentanaBanco();

        // Las dos vistas observan la misma cuenta
        ventana.getVistaSaldo().mostrar(cuenta.saldoProperty(), cuenta.saldoBajoProperty());
        ventana.getVistaMovimientos().mostrar(cuenta.getMovimientos());

        new ControladorBanco(cuenta, ventana);

        cuenta.ingresar(100); // saldo inicial: las vistas lo muestran solas

        ventanaPrincipal.setTitle("MVC + Observer - Cuenta de " + cuenta.getTitular());
        ventanaPrincipal.setScene(new Scene(ventana, 640, 300));
        ventanaPrincipal.show();
    }
}
