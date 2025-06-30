package Modelo;

public class Tratamiento {


    private int idTratamiento;
    private String tipo;
    private String subtipo;


    public Tratamiento() {
    }

    //GETTERS
    public int getIdTratamiento() {
        return idTratamiento;
    }

    public String getTipo() {
        return tipo;
    }

    public String getSubtipo() {
        return subtipo;
    }


    //SETTERS
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setIdTratamiento(int idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public void setSubtipo(String subtipo) {
        this.subtipo = subtipo;
    }

    @Override
    public String toString() {
        return this.getIdTratamiento() + " -  " + this.getSubtipo();
    }
}
