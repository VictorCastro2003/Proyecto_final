package modelo;

public class Paciente {
    private int SSN;
    private String nombre;
    private String primerAp;
    private String segundoAp;
    private byte edad;
    private String colonia;
    private String calle;

    public Paciente(){

    }
    public Paciente(int SSN, String nombre, String primerAp, String segundoAp, byte edad, String colonia, String calle) {
        this.SSN = SSN;
        this.nombre = nombre;
        this.primerAp = primerAp;
        this.segundoAp = segundoAp;
        this.edad = edad;
        this.colonia = colonia;
        this.calle = calle;
    }

    public int getSSN() {
        return SSN;
    }

    public void setNumControl(int numControl) {
        this.SSN = numControl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerAp() {
        return primerAp;
    }

    public void setPrimerAp(String primerAp) {
        this.primerAp = primerAp;
    }

    public String getSegundoAp() {
        return segundoAp;
    }

    public void setSegundoAp(String segundoAp) {
        this.segundoAp = segundoAp;
    }

    public byte getEdad() {
        return edad;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }
}
