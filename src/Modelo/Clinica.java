package Modelo;

public class Clinica {


    private int idClinica;
    private String nombre;
    private String direccion;
    private String telefono;


    public Clinica(int idClinica, String nombre, String direccion, String telefono){
        this.idClinica = idClinica;
        this.nombre= nombre;
        this.direccion = direccion;
        this.telefono= telefono;
    }


    //GETTERS
    public int getIdClinica() {
        return idClinica;
    }

    public String getNombreClinica() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefonoClinica() {
        return telefono;
    }


    //SETTERS
    public void setIdClinica(int idClinica) {
        this.idClinica = idClinica;
    }

    public void setNombreClinica(String nombreClinica) {
        this.nombre= nombreClinica;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefonoClinica(String telefonoClinica) {
        this.telefono= telefonoClinica;
    }
}
