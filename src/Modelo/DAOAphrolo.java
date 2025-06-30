package Modelo;

import Controlador.HashUtil;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class DAOAphrolo extends ArrayList<Object> {

    //ATRIBUTOS y mét0do para conectar la BBDD
    private static final String URL = "jdbc:mysql://localhost:3306/aphrolo";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static Connection conexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    //Mét0do para insertar clientes en la base de datos en el apartado de registro
    public static int insertarClientes(Usuario cliente) throws SQLException {

        int resultado = 0;
        try (Connection con = conexion()) {
            String query = "INSERT INTO usuario (nombre, email, telefono, direccion, dni, password) " +
                    "VALUES (?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getDireccion());
            stmt.setString(5, cliente.getDni());
            stmt.setString(6, cliente.getPassword());

            resultado = stmt.executeUpdate();

            Log.logger("El cliente con email: " + cliente.getEmail() + "" + "ha sido insertado con éxito. ");

        } catch (SQLException e) {

            Log.logger("Error al insertar cliente.");
        }
        return resultado;
    }

    public static Usuario iniciarSesionCliente(String email, String hashedPassword)
            throws SQLException {

        String sql = "SELECT id_usuario, nombre, email, telefono, direccion, dni " +
                "FROM usuario WHERE email = ? AND password = ?";
        try (Connection con = conexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, email);
            //Se comparan los hashes
            stmt.setString(2, hashedPassword);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Log.logger("El usuario con email " + "" + email + "ha iniciado sesión correctamente. ");
                    return new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("nombre"),
                            rs.getString("email"),
                            rs.getString("telefono"),
                            rs.getString("direccion"),
                            rs.getString("dni"),
                            null  // no guardamos la contraseña en el objeto
                    );
                } else {
                    Log.logger("Login fallido para el usuario con email: " + email);
                    return null;
                }
            }
        }
    }

    //Mét0do para iniciar sesión de administrador
    public static Usuario iniciarSesionAdministrador(String email, String hashedPassword) throws SQLException {

        String sql = "SELECT id_usuario, nombre, email, telefono, direccion, dni, rol FROM usuario WHERE email = ? AND password = ?";

        try (Connection con = conexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, hashedPassword);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    //Verificamos que sea administrador
                    String rol = rs.getString("rol");
                    if (!"admin".equalsIgnoreCase(rol)) {
                        return null;  //existe el usuario, pero no es admin
                    }

                    //Creamos el objeto Usuario
                    Usuario admin = new Usuario(
                            rs.getString("nombre"),
                            rs.getString("email"),
                            rs.getString("telefono"),
                            rs.getString("direccion"),
                            rs.getString("dni"),
                            null
                    );
                    admin.setId_Usuario(rs.getInt("id_usuario"));
                    admin.setRol(rol);

                    Log.logger("El administrador ha iniciado sesión correctamente. ");
                    return admin;
                }
            } catch (SQLException e) {

                Log.logger("Error. El administrador no ha podido iniciar sesión correctamente. ");
            }
        }
        return null;
    }

    //Mét0do para mostrar usuarios en tabla
    public static List<Usuario> obtenerUsuarios() throws SQLException {
        List<Usuario> listaUsuarios = new ArrayList<>();

        String sql = "SELECT id_usuario, nombre, email, telefono, direccion, dni, rol "
                + "FROM usuario WHERE rol != 'admin'";

        try (Connection con = conexion();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("dni"),
                        null // no devolvemos contraseña
                );
                usuario.setId_Usuario(rs.getInt("id_usuario"));
                usuario.setRol(rs.getString("rol"));
                listaUsuarios.add(usuario);
            }

            Log.logger("Se ha obtenido la lista de usuarios con éxito (con un total de " + listaUsuarios.size() + " usuarios).");
        } catch (SQLException e) {

            Log.logger("Error al obtener lista de usuarios usuarios: ");
            throw e;
        }

        return listaUsuarios;
    }

    //Mét0do para mostrar las citas en mi tabla
    public static List<Cita> obtenerCitasCompletas() throws SQLException {

        List<Cita> lista = new ArrayList<>();

        String sql = "SELECT * FROM cita";
        try (Connection con = conexion(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cita c = new Cita();
                c.setIdCita(rs.getInt("id_cita"));
                c.setFechaCita(rs.getDate("fecha").toLocalDate());
                c.setHoraCita(LocalTime.parse(rs.getString("hora")));
                c.setId_tratamiento(rs.getInt("id_tratamiento"));
                c.setId_usuario(rs.getInt("id_usuario"));
                lista.add(c);
            }

            Log.logger("Lista de citas con tamaño de" +" " + lista.size() + " recuperada con éxito. ");
            return lista;

        } catch (SQLException e) {

            Log.logger("Error al obtener la cita.");
        }
        return lista;
    }


    //Mét0do para obtener tratamientos
    public static List<Tratamiento> obtenerTratamientos() throws SQLException {
        List<Tratamiento> lista = new ArrayList<>();
        Connection conn = conexion();

        String sql = "SELECT * FROM tratamiento";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Tratamiento c = new Tratamiento();
            c.setIdTratamiento(rs.getInt("id_tratamiento"));
            c.setTipo(rs.getString("tipo"));
            c.setSubtipo(rs.getString("subtipo"));
            lista.add(c);
        }
        return lista;
    }

    //Mét0do para insertar cita
    public static int insertarCita(Cita cita) throws SQLException {

        int resultado = 0;
        try(Connection con = conexion()){
            String sql = "INSERT INTO cita (id_usuario, id_tratamiento, fecha, hora) VALUES (?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, cita.getId_usuario());
            stmt.setInt(2, cita.getId_tratamiento());
            stmt.setDate(3, Date.valueOf(cita.getFechaCita()));
            stmt.setTime(4, Time.valueOf(cita.getHoraCita()));

            stmt.executeUpdate();
            System.out.println("Cita registrada correctamente. ");
            resultado = 1;

            Log.logger("Cita insertada con éxito. ");

        }catch (SQLException e){
           Log.logger("Error al insertar la cita. ");
        }

        return  resultado;
    }

    //Mét0do para insertar los mensajes en la BD
    public static int insertarMensaje(Contacto mensaje) throws SQLException {

        int resultado = 0;
        String query = "INSERT INTO contacto (nombre, email, mensaje) VALUES (?, ?, ?)";

        try (Connection con = conexion(); PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, mensaje.getNombre());
            stmt.setString(2, mensaje.getEmail());
            stmt.setString(3, mensaje.getContenido());

            resultado = stmt.executeUpdate();
            Log.logger("Mensaje insertado con éxito. ");

        } catch (SQLException e) {
           Log.logger("Error al insertar el mensaje. ");
        }

        return resultado;
    }

    //Mét0do para mostrar citas a los usuarios
    public static List<Object[]> obtenerCitasPorUsuario(int usuarioId) throws SQLException {

        List<Object[]> lista = new ArrayList<>();
        String sql =
                "SELECT cita.fecha, cita.hora, tratamiento.tipo, tratamiento.subtipo " +
                        "FROM cita " +
                        "JOIN tratamiento ON cita.id_tratamiento = tratamiento.id_tratamiento " +
                        "WHERE cita.id_usuario = ? " +
                        "ORDER BY cita.fecha ASC, cita.hora ASC";

        try (Connection con = conexion(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Date fecha   = rs.getDate("fecha");
                    Time hora    = rs.getTime("hora");
                    String tipo     = rs.getString("tipo");
                    String subtipo  = rs.getString("subtipo");

                    lista.add(new Object[]{ fecha, hora, tipo, subtipo });
                }
            }
           Log.logger("La lista de citas por usuario se ha obtenido correctamente. ");

            return lista;
        }catch(SQLException e){
            Log.logger("Error al obtener la lista de citas. ");
            throw e;
        }


    }

    //Mét0do para obtener mensajes
    public static List<Contacto> obtenerMensajesCompletos() throws SQLException {

        List<Contacto> lista = new ArrayList<>();
        String sql = "SELECT * FROM contacto";

        try (Connection conn = conexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Contacto m = new Contacto();
                m.setId_mensaje(rs.getInt("id"));
                m.setNombre(rs.getString("nombre"));
                m.setEmail(rs.getString("email"));
                m.setContenido(rs.getString("mensaje"));
                m.setFechaEnvio(rs.getTimestamp("fecha_envio").toLocalDateTime());
                lista.add(m);
            }


            Log.logger("La lista de mensajes con longitud " + lista.size() + " " + "ha sido recuperada con éxito. ");
            return lista;

        } catch (SQLException e) {

            Log.logger("Error al obtener la lista. ");

        }
        return lista;
    }
}




















