package soluciones.agenda.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AgendaTest {

    private Agenda agenda;

    @BeforeEach
    void setUp() {
        agenda = new Agenda();
        agenda.agregar(new Contacto("Ana García", "600111222", "ana@correo.es"));
        agenda.agregar(new Contacto("Luis Pérez", "611222333", ""));
    }

    @Test
    void noSePermitenDosContactosConElMismoNombre() {
        Contacto repetido = new Contacto("ana garcía", "699999999", "");

        assertThrows(IllegalArgumentException.class, () -> agenda.agregar(repetido));
        assertEquals(2, agenda.getCantidad());
    }

    @Test
    void elResultadoDeLaBusquedaSeMantieneAlDiaTrasUnAlta() {
        agenda.buscar("ana");
        assertEquals(1, agenda.getResultadoBusqueda().size());

        // Un alta que cumple el filtro aparece sola en el resultado...
        agenda.agregar(new Contacto("Mariana López", "633444555", ""));
        // ...y una que no lo cumple, no.
        agenda.agregar(new Contacto("Pedro Gil", "644555666", ""));

        List<String> nombres = agenda.getResultadoBusqueda().stream().map(Contacto::nombre).toList();
        assertEquals(List.of("Ana García", "Mariana López"), nombres);
    }

    @Test
    void buscarConTextoVacioMuestraTodos() {
        agenda.buscar("luis");
        agenda.buscar("   ");

        assertEquals(2, agenda.getResultadoBusqueda().size());
    }
}
