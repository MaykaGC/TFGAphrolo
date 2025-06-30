package Controlador;

import Modelo.Usuario;
import Modelo.DAOAphrolo;

import java.sql.SQLException;

public class ControladorCliente {

    public ControladorCliente() {

    }

    public static int insertarClientesApp(String nombre, String email, String telefono, String direccion, String dni, String password) throws SQLException {
        Usuario usuario = new Usuario(nombre, email, telefono, direccion, dni, password);
        return DAOAphrolo.insertarClientes(usuario);
    }

    public Usuario iniciarSesionCliente(String email, String password) throws SQLException {
        return DAOAphrolo.iniciarSesionCliente(email, password);
    }
}

