package Modelo;

import java.time.LocalDate;
import java.time.LocalTime;


public class Cita {

    private int idCita;
    private LocalTime horaCita;
    private LocalDate fechaCita;
    private int id_tratamiento;
    private int id_usuario;


    public Cita() {
    }

    public Cita(LocalTime horaCita, LocalDate fechaCita, int id_tratamiento, int id_usuario) {
        this.horaCita = horaCita;
        this.fechaCita = fechaCita;
        this.id_tratamiento = id_tratamiento;
        this.id_usuario = id_usuario;
    }


    //GETTERS
    public int getIdCita() {
        return idCita;
    }

    public LocalTime getHoraCita() {
        return horaCita;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public int getId_tratamiento() {
        return id_tratamiento;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    //SETTERS
    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public void setHoraCita(LocalTime horaCita) {
        this.horaCita = horaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }

    public void setId_tratamiento(int idTratamiento) {
        this.id_tratamiento = idTratamiento;
    }

    public void setId_usuario(int idUsuario) {
        this.id_usuario = idUsuario;
    }
}
