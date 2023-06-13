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
    int num=0;
    //Metodos ABCC (CRUD)
    ConexionBD conexion = new ConexionBD();
    //==================================Altas===================================
    public boolean agregarPaciente(Paciente p){
        boolean res= false;
        /*
         *Proceso Altas en MySQL
         *  INSERT INTO alumnos VALUES("01","Goku","-","-",34,10,'ISC');
         *
         */
        String sql="INSERT INTO pacientes VALUES('"+p.getSSN()+"','"+p.getNombre()+"','"+p.getPrimerAp()+"','"+p.getSegundoAp()+
                "',"+p.getEdad()+",'"+p.getCalle()+"')";

        res = conexion.ejecutarInstruccionDML(sql);

        return res;
    }

    //==================================Bajas===================================
    public boolean eliminarPaciente(String SSN){
        boolean res= false;

        String sql="DELETE FROM pacientes WHERE SSN='"+SSN+"'";
        res = conexion.ejecutarInstruccionDML(sql);
        return res;
    }


    //==================================Cambios=================================
    public boolean actualizarAlumno (Paciente p){

        // String sql="UPDATE Miembros SET  Nombre='"+a.getNombre() + "', Apellido='"+a.getApellido()+ "', Edad="+a.getEdad() +", Es_Actor='"+a.getEs_Actor() + "', ID_Calle="+a.getCalle() + " WHERE ID_Miembro = "+a.getID_Miembro()+""; //Esta linea es para poder seleccionar el objeto a modificar por medio de su numero del ID
        boolean res= false;
        // UPDATE pacientes SET Nombre='x', PrimerAp="x",SegundoAp="x",edad=12,ID_calle=1 WHERE ssn='1'
        String sql="UPDATE pacientes SET nombre='"+p.getNombre()+"',primerAp='"+p.getPrimerAp()+"',segundoAp='"+p.getSegundoAp()+
                "',edad="+p.getEdad()+",ID_Calle="+p.getCalle()+" WHERE SSN= "+p.getSSN()+"";
        res = conexion.ejecutarInstruccionDML(sql);
        System.out.println(sql);
        return res;
    }
    //==================================Consultas===============================
    public Paciente buscarPac(String filtro){

        return null;
    }
    public ArrayList<Paciente> buscarPacientes(String filtro){
        ArrayList<Paciente> listaPacientes= new ArrayList<>();
        String sql="SELECT * FROM pacientes";

        ResultSet rs= conexion.ejecutarConsulta(sql);
        try {
            rs.next();
            num++;
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

        ResultSet rs= conexion.ejecutarConsulta(sql);
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

        ResultSet rs= conexion.ejecutarConsulta(sql);
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
