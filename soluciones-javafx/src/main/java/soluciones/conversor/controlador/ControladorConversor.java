package soluciones.conversor.controlador;

import soluciones.conversor.modelo.ConversorTemperatura;
import soluciones.conversor.vista.VistaConversor;

public class ControladorConversor {

    private final ConversorTemperatura modelo;
    private final VistaConversor vista;

    public ControladorConversor(ConversorTemperatura modelo, VistaConversor vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.alPulsarCelsiusAFahrenheit(() -> celsiusAFahrenheit());
        vista.alPulsarFahrenheitACelsius(() -> fahrenheitACelsius());
    }

    private void celsiusAFahrenheit() {
        try {
            double celsius = leerNumero(vista.getCelsius());
            vista.mostrarFahrenheit(modelo.celsiusAFahrenheit(celsius));
        } catch (NumberFormatException ex) {
            vista.mostrarError("Escribe una temperatura válida en ºC");
        } catch (IllegalArgumentException ex) {
            vista.mostrarError(ex.getMessage());
        }
    }

    private void fahrenheitACelsius() {
        try {
            double fahrenheit = leerNumero(vista.getFahrenheit());
            vista.mostrarCelsius(modelo.fahrenheitACelsius(fahrenheit));
        } catch (NumberFormatException ex) {
            vista.mostrarError("Escribe una temperatura válida en ºF");
        } catch (IllegalArgumentException ex) {
            vista.mostrarError(ex.getMessage());
        }
    }

    /** Acepta coma o punto decimal ("36,5" o "36.5"). */
    private static double leerNumero(String texto) {
        return Double.parseDouble(texto.trim().replace(',', '.'));
    }
}
