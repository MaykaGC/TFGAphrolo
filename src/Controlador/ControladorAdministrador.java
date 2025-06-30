package Controlador;

import Modelo.DAOAphrolo;
import Modelo.Usuario;

import java.sql.SQLException;
import java.util.List;

public class ControladorAdministrador {

    public ControladorAdministrador() {
    }


    public static List<Usuario> obtenerUsuariosNormales() throws SQLException {
        List<Usuario> usuarios = DAOAphrolo.obtenerUsuarios();
        System.out.println("Usuarios obtenidos: " + usuarios.size());
        return usuarios;
    }


    public Usuario iniciarSesionAdmin(String email, String password) throws SQLException {
        return DAOAphrolo.iniciarSesionAdministrador(email, password);
    }

}
