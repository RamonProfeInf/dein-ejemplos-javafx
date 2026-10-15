package mvc.login.modelo;

/**
 * Un usuario de la aplicación.
 *
 * Usamos un "record" (Java 16+): una clase inmutable cuyo constructor, getters
 * (nombre(), clave()), equals, hashCode y toString se generan automáticamente.
 *
 * OJO: en una aplicación real NUNCA se guarda la contraseña en texto plano,
 * sino un hash (por ejemplo, con BCrypt). Aquí lo simplificamos para centrarnos en MVC.
 */
public record Usuario(String nombre, String clave) {
}
