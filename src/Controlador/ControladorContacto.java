package Controlador;

import Modelo.Contacto;
import Modelo.DAOAphrolo;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class ControladorContacto {


    public static int insertarMensaje(String nombre, String email, String contenido) throws SQLException {
        Contacto contacto = new Contacto(nombre, email, contenido);
        return DAOAphrolo.insertarMensaje(contacto);
    }

    public static List<Contacto> obtenerMensajes() throws SQLException {
        return DAOAphrolo.obtenerMensajesCompletos();
    }


}
