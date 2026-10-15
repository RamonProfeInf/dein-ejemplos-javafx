package mvc.tableview.modelo;

/**
 * Un alumno. Las reglas de validación están aquí, en el modelo.
 */
public class Alumno {

    private final String nombre;
    private final int edad;
    private final double nota;

    public Alumno(String nombre, int edad, double nota) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (edad < 16 || edad > 99) {
            throw new IllegalArgumentException("La edad debe estar entre 16 y 99");
        }
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 10");
        }
        this.nombre = nombre;
        this.edad = edad;
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double getNota() {
        return nota;
    }
}
