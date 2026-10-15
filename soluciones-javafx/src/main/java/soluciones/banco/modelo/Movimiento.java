package soluciones.banco.modelo;

/**
 * Un movimiento de la cuenta (inmutable).
 */
public record Movimiento(Tipo tipo, double importe, double saldoResultante) {

    public enum Tipo { INGRESO, RETIRADA }
}
