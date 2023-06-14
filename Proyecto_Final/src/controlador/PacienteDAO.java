package controlador;
import ConexionBD.ConexionBD;
import modelo.Paciente;
import modelo.Calle;
import modelo.Colonia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//DAO Data Access Objet
public class PacienteDAO {

    //Metodos ABCC (CRUD)
    //==================================Altas===================================
    public  boolean agregarPaciente(Paciente p){
        boolean res= false;

        res = ConexionBD. AgregarPaciente(p);

        return res;
    }

    //==================================Bajas===================================
    public  boolean eliminarPaciente(String SSN){
        boolean res= false;

        String sql="DELETE FROM pacientes WHERE SSN='"+SSN+"'";
        res = ConexionBD.EliminarPacientes(sql);
        return res;
    }


    //==================================Cambios=================================
    public  boolean actualizarAlumno (Paciente p){
        // String sql="UPDATE Miembros SET  Nombre='"+a.getNombre() + "', Apellido='"+a.getApellido()+ "', Edad="+a.getEdad() +", Es_Actor='"+a.getEs_Actor() + "', ID_Calle="+a.getCalle() + " WHERE ID_Miembro = "+a.getID_Miembro()+""; //Esta linea es para poder seleccionar el objeto a modificar por medio de su numero del ID
        boolean res= false;
            res=ConexionBD.ActualizarPaciente(p);
        return res;
    }
    //==================================Consultas===============================
    public Paciente buscarPac(String filtro){

        return null;
    }
    public  ArrayList<Paciente> buscarPacientes(String filtro){
        ArrayList<Paciente> listaPacientes= new ArrayList<>();
        String sql="SELECT * FROM pacientes";
        ResultSet rs= ConexionBD.BuscarPaciente(sql);
        try {
            rs.next();

            do{
                int ssn= rs.getInt(1);//Num control
                String n=rs.getString(2);//Nombre
                String pa= rs.getString(3);
                String sa= rs.getString(4);
                byte e= rs.getByte(5);
                int s= rs.getInt(6);

                listaPacientes.add(new Paciente(ssn,n,pa,sa,e,s));

            }while(rs.next());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaPacientes;
    }
    public ArrayList<Calle> buscarCalle(String filtro){
        ArrayList<Calle> listaCalle= new ArrayList<>();
        String sql="SELECT * FROM calles";

        ResultSet rs= ConexionBD.BuscarPaciente(sql);
        try {
            rs.next();

            do{
                int Id_Calle= rs.getInt(1);
                String nom_calle=rs.getString(2);
                int Id_Colonia= rs.getInt(3);

                listaCalle.add(new Calle(Id_Calle,nom_calle,Id_Colonia));

            }while(rs.next());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaCalle;
    }

    public ArrayList<Colonia> buscarColonia(String filtro){
        ArrayList<Colonia> listaColonia= new ArrayList<>();
        String sql="SELECT * FROM colonias";

        ResultSet rs= ConexionBD.BuscarPaciente(sql);
        try {
            rs.next();

            do{
                int Id_Col= rs.getInt(1);
                String n=rs.getString(2);


                listaColonia.add(new Colonia(Id_Col,n));

            }while(rs.next());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaColonia;
    }

}
