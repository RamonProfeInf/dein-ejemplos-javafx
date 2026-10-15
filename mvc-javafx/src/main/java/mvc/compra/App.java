package mvc.compra;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mvc.compra.controlador.ControladorCompra;
import mvc.compra.modelo.ListaCompra;
import mvc.compra.modelo.Producto;
import mvc.compra.vista.VentanaCompra;

public class App extends Application {

    @Override
    public void start(Stage ventanaPrincipal) {
        ListaCompra modelo = new ListaCompra();
        VentanaCompra ventana = new VentanaCompra();

        // Patrón Observer: las dos vistas se conectan a las partes OBSERVABLES del modelo.
        // A partir de aquí, cualquier cambio en el modelo les llega solo.
        ventana.getVistaProductos().mostrar(modelo.getProductos());
        ventana.getVistaTotal().mostrar(modelo.totalProperty());

        new ControladorCompra(modelo, ventana);

        // Datos iniciales: fíjate en que las vistas se rellenan sin que
        // nadie las refresque a mano.
        modelo.agregar(new Producto("Leche", 0.95));
        modelo.agregar(new Producto("Pan", 1.20));
        modelo.agregar(new Producto("Huevos (docena)", 2.80));

        ventanaPrincipal.setTitle("MVC + Observer - Lista de la compra");
        ventanaPrincipal.setScene(new Scene(ventana, 520, 350));
        ventanaPrincipal.show();
    }
}
