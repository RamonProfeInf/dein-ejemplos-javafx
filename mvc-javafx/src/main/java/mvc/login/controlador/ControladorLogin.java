package mvc.login.controlador;

import mvc.login.modelo.UsuarioService;
import mvc.login.vista.VistaLogin;

/**
 * CONTROLADOR del login.
 *
 * Recoge los datos de la vista, se los pasa al modelo para que los valide y,
 * según la respuesta, decide qué mensaje enseñar.
 */
public class ControladorLogin {

    private final UsuarioService servicio;
    private final VistaLogin vista;

    public ControladorLogin(UsuarioService servicio, VistaLogin vista) {
        this.servicio = servicio;
        this.vista = vista;
        vista.alPulsarEntrar(() -> entrar());
    }

    private void entrar() {
        String usuario = vista.getUsuario().trim();
        String clave = vista.getClave();

        // Comprobación de entrada: es lógica del controlador, no de la vista
        if (usuario.isEmpty() || clave.isEmpty()) {
            vista.mostrarMensaje("Rellena usuario y contraseña");
            return;
        }

        // La regla de negocio (¿es correcto?) la decide el MODELO
        if (servicio.validar(usuario, clave)) {
            vista.mostrarMensaje("¡Bienvenido, " + usuario + "!");
        } else {
            vista.mostrarMensaje("Usuario o contraseña incorrectos");
            vista.limpiarClave();
        }
    }
}
