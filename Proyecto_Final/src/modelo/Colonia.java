package modelo;

public class Colonia {
    private int ID_Colonia;
    private String NombreColonia;

    public Colonia(){}
    public Colonia(int ID_Colonia, String nombreColonia) {
        this.ID_Colonia = ID_Colonia;
        NombreColonia = nombreColonia;
    }

    public int getID_Colonia() {
        return ID_Colonia;
    }

    public void setID_Colonia(int ID_Colonia) {
        this.ID_Colonia = ID_Colonia;
    }

    public String getNombreColonia() {
        return NombreColonia;
    }

    public void setNombreColonia(String nombreColonia) {
        NombreColonia = nombreColonia;
    }
}
