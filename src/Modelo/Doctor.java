package Modelo;

public class Doctor {

    //Atributos
    private int idDoctor;
    private int numeroColegiado;
    private String nombre;

    //CONSTRUCTOR
    public Doctor(int idDoctor, int colegiatura, String nombre){
        this.idDoctor= idDoctor;
        this.numeroColegiado = colegiatura;
        this.nombre = nombre;
    }

    //GETTERS
    public int getIdDoctor() {
        return idDoctor;
    }

    public String getNombreDoctor() {
        return nombre;
    }

    public int getNumeroColegiado() {
        return numeroColegiado;
    }

    //SETTERS
    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public void setNumeroColegiado(int numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
    }

    public void setNombreDoctor(String nombreDoctor) {
        this.nombre = nombreDoctor;
    }


}
