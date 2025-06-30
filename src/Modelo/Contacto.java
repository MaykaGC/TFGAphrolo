package Modelo;

import java.security.Timestamp;
import java.time.LocalDateTime;

public class Contacto {

    private int id_mensaje;
    private String nombre;
    private String email;
    private String contenido;
    private LocalDateTime fechaEnvio;

    public Contacto() {

    }

    public Contacto(String nombre, String email, String contenido) {
        this.nombre = nombre;
        this.email = email;
        this.contenido = contenido;

    }

    public Contacto(int id_mensaje, String nombre, String email, String contenido, LocalDateTime fechaEnvio) {
        this.id_mensaje = id_mensaje;
        this.nombre = nombre;
        this.email = email;
        this.contenido = contenido;
        this.fechaEnvio = fechaEnvio;
    }

    //Getters
    public int getId_mensaje() {
        return id_mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getContenido() {
        return contenido;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    //Setters
    public void setId_mensaje(int id_mensaje) {
        this.id_mensaje = id_mensaje;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }
}
