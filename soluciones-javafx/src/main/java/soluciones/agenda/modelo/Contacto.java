package soluciones.agenda.modelo;

/**
 * Un contacto (inmutable). Modificar un contacto = sustituirlo por otro nuevo.
 */
public record Contacto(String nombre, String telefono, String email) {

    public Contacto {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (telefono == null || !telefono.matches("[0-9 +]{9,15}")) {
            throw new IllegalArgumentException("El teléfono debe tener entre 9 y 15 dígitos");
        }
        if (email == null) {
            email = "";
        }
        if (!email.isEmpty() && !email.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+")) {
            throw new IllegalArgumentException("El email no es válido");
        }
    }

    /** ¿Contiene el texto en alguno de sus campos? (sin distinguir mayúsculas) */
    public boolean coincideCon(String texto) {
        String t = texto.toLowerCase();
        return nombre.toLowerCase().contains(t)
                || telefono.contains(t)
                || email.toLowerCase().contains(t);
    }
}
