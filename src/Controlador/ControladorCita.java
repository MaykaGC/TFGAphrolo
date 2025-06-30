package Controlador;

import Modelo.Cita;
import Modelo.DAOAphrolo;
import Modelo.Tratamiento;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ControladorCita {

    public ControladorCita(){}


    public static List<Cita> obtenerCitas() throws SQLException {
        return DAOAphrolo.obtenerCitasCompletas();
    }

    public static List<Tratamiento> obtenerTratamientos() throws SQLException {
        return DAOAphrolo.obtenerTratamientos();
    }

    public static int confirmarCita(LocalTime horaCita, LocalDate fechaCita, int id_tratamiento, int id_usuario) throws SQLException {
        Cita cita = new Cita(horaCita, fechaCita, id_tratamiento, id_usuario);
        return DAOAphrolo.insertarCita(cita);
    }

    public static List<Object[]> obtenerCitasUsuario(int usuarioId) throws SQLException {
        return DAOAphrolo.obtenerCitasPorUsuario(usuarioId);
    }
}
