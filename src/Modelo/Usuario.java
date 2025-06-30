package Modelo;

public class Usuario {


    private int id_Usuario;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private String dni;
    private String password;
    private String rol;


    public Usuario() {
    }

    public Usuario(String nombre, String email, String telefono, String direccion, String dni, String password) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.dni = dni;
        this.password = password;
    }

    public Usuario(int id_Usuario, String nombre, String email, String telefono, String direccion, String dni, String password) {
        this.id_Usuario = id_Usuario;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.dni = dni;
        this.password = password;
    }

    //GETTERS
    public String getRol() {
        return rol;
    }

    public int getId_Usuario() {
        return id_Usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getDni() {
        return dni;
    }

    public String getPassword() {
        return password;
    }


    //SETTERS
    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
