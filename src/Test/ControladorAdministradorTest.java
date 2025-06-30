
import static org.junit.jupiter.api.Assertions.*;

import Controlador.ControladorAdministrador;
import org.junit.jupiter.api.Test;

import Modelo.Usuario;
import java.sql.SQLException;
import java.util.List;


class ControladorAdministradorTest {

    //PRUEBA DE REGRESIÓN
    /**
     *
     * @throws SQLException si ocurre algún error al consultar sla BD
     */
    @Test
    public void testObtenerUsuariosNormales_regresion() throws SQLException {
        try {

            List<Usuario> usuarios = ControladorAdministrador.obtenerUsuariosNormales();

            assertNotNull(usuarios, "La lista de usuarios no debe ser nula");

            //Comprobamos que la lista no esté vacía
            assertFalse(usuarios.isEmpty(), "La lista de usuarios no debe estar vacía");

        } catch (SQLException e) {
            fail("Se lanzó una excepción SQL: " + e.getMessage());
        }
    }

}