package soluciones.conversor.modelo;

/**
 * MODELO: las fórmulas de conversión y la regla del cero absoluto.
 * No importa nada de JavaFX: se podría probar con JUnit sin abrir ninguna ventana.
 */
public class ConversorTemperatura {

    public static final double CERO_ABSOLUTO_C = -273.15;
    public static final double CERO_ABSOLUTO_F = -459.67;

    public double celsiusAFahrenheit(double celsius) {
        if (celsius < CERO_ABSOLUTO_C) {
            throw new IllegalArgumentException("Por debajo del cero absoluto (" + CERO_ABSOLUTO_C + " ºC)");
        }
        return celsius * 9 / 5 + 32;
    }

    public double fahrenheitACelsius(double fahrenheit) {
        if (fahrenheit < CERO_ABSOLUTO_F) {
            throw new IllegalArgumentException("Por debajo del cero absoluto (" + CERO_ABSOLUTO_F + " ºF)");
        }
        return (fahrenheit - 32) * 5 / 9;
    }
}
