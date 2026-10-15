package mvc.compra.modelo;

/**
 * Un producto de la lista de la compra (record inmutable).
 *
 * El "constructor compacto" de abajo valida los datos: así es imposible
 * crear un Producto incorrecto. Las reglas de los datos viven en el MODELO.
 */
public record Producto(String nombre, double precio) {

    public Producto {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
    }

    /** Texto que mostrará el ListView (usa toString() para pintar cada elemento). */
    @Override
    public String toString() {
        return String.format("%s  -  %.2f €", nombre, precio);
    }
}
