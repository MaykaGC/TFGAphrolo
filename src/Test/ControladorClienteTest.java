package Vistas;

import javax.swing.SwingUtilities;

import Controlador.ControladorCliente;
import org.junit.jupiter.api.Test;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import Modelo.Usuario;
import Modelo.DAOAphrolo;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ControladorClienteTest {

    //PRUEBA DE INTEGRACIÓN
    /**
     *
     * @throws Lanza una excepción si occure algún error en la interacción con la base de datos
     */
    @Test
    public void testInsertarClientes() throws Exception {

        Usuario cliente = new Usuario();
        cliente.setNombre("TestUser");
        cliente.setEmail("testuser@example.com");
        cliente.setTelefono("123456789");
        cliente.setDireccion("Calle Falsa 123");
        cliente.setDni("31013060P");
        cliente.setPassword("passwordhashed");

        int resultado = DAOAphrolo.insertarClientes(cliente);

        assertTrue(resultado > 0, "La inserción debe devolver un valor mayor que 0");
    }
}
