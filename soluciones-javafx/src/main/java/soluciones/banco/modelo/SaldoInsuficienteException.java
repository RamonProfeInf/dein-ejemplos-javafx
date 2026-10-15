package soluciones.banco.modelo;

/**
 * Excepción propia del dominio: expresa una regla de negocio con nombre propio.
 */
public class SaldoInsuficienteException extends RuntimeException {

    public SaldoInsuficienteException(double saldo, double importe) {
        super(String.format("Saldo insuficiente: tienes %.2f € y quieres retirar %.2f €", saldo, importe));
    }
}
