package soluciones.banco.modelo;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * MODELO OBSERVABLE: una cuenta bancaria cuyo saldo y cuyos movimientos avisan
 * solos a quien los escuche (propiedades y listas observables de JavaFX).
 *
 * Solo importa javafx.beans y javafx.collections, que no son gráficos: el
 * modelo sigue sin saber que existe una ventana y se prueba con JUnit.
 *
 * Nota: usamos double por sencillez. En una aplicación bancaria real se usa
 * BigDecimal, porque double no representa exactamente cantidades como 0.10.
 */
public class CuentaBancaria {

    /** Regla de negocio: por debajo de esta cantidad, la cuenta tiene "saldo bajo". */
    public static final double LIMITE_SALDO_BAJO = 100;

    private final String titular;
    private final ReadOnlyDoubleWrapper saldo = new ReadOnlyDoubleWrapper(0);
    private final ReadOnlyBooleanWrapper saldoBajo = new ReadOnlyBooleanWrapper();
    private final ObservableList<Movimiento> movimientos = FXCollections.observableArrayList();

    public CuentaBancaria(String titular) {
        if (titular == null || titular.isBlank()) {
            throw new IllegalArgumentException("El titular es obligatorio");
        }
        this.titular = titular;

        // "saldoBajo" queda ENLAZADO al saldo: vale true siempre que saldo < 100,
        // y se recalcula solo cada vez que el saldo cambia.
        saldoBajo.bind(saldo.lessThan(LIMITE_SALDO_BAJO));
    }

    public void ingresar(double importe) {
        validarImporte(importe);
        cambiarSaldo(getSaldo() + importe, Movimiento.Tipo.INGRESO, importe);
    }

    public void retirar(double importe) {
        validarImporte(importe);
        if (importe > getSaldo()) {
            throw new SaldoInsuficienteException(getSaldo(), importe);
        }
        cambiarSaldo(getSaldo() - importe, Movimiento.Tipo.RETIRADA, importe);
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo.get();
    }

    public ReadOnlyDoubleProperty saldoProperty() {
        return saldo.getReadOnlyProperty();
    }

    public boolean isSaldoBajo() {
        return saldoBajo.get();
    }

    public ReadOnlyBooleanProperty saldoBajoProperty() {
        return saldoBajo.getReadOnlyProperty();
    }

    /** Lista de solo lectura, pero observable. */
    public ObservableList<Movimiento> getMovimientos() {
        return FXCollections.unmodifiableObservableList(movimientos);
    }

    private void validarImporte(double importe) {
        if (importe <= 0) {
            throw new IllegalArgumentException("El importe debe ser mayor que 0");
        }
    }

    /**
     * Único sitio donde cambia el saldo. No hay que avisar a nadie a mano:
     * saldo.set() y movimientos.add() avisan solos a sus observadores.
     */
    private void cambiarSaldo(double nuevoSaldo, Movimiento.Tipo tipo, double importe) {
        saldo.set(nuevoSaldo);
        movimientos.add(new Movimiento(tipo, importe, nuevoSaldo));
    }
}
