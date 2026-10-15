package mvc.login.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * MODELO: contiene los datos (los usuarios) y la regla de negocio (cuándo un
 * login es válido).
 *
 * Los usuarios están "en memoria", en una lista. Si mañana los leemos de una base de datos, solo cambia esta
 * clase: la vista y el controlador ni se enteran.
 */
public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();

    public UsuarioService() {
        // Datos de prueba
        usuarios.add(new Usuario("admin", "1234"));
        usuarios.add(new Usuario("alumno", "dam2"));
        usuarios.add(new Usuario("profe", "javafx"));
    }

    /**
     * @return true si existe un usuario con ese nombre y esa clave.
     */
    public boolean validar(String usuario, String clave) {
        if (usuario == null || clave == null) {
            return false;
        }
        for (Usuario u : usuarios) {
            if (u.nombre().equals(usuario) && u.clave().equals(clave)) {
                return true;
            }
        }
        return false;
    }
}
