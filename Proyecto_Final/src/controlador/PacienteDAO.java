package controlador;


import ConexionBD.ConexionBD;
import modelo.Paciente;
import modelo.Paciente;

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
                "',"+p.getEdad()+","+p.getColonia()+",'"+p.getCalle()+"')";

        res = conexion.ejecutarInstruccionDML(sql);

        return res;
    }

    //==================================Bajas===================================
    public boolean eliminarPaciente(String ssn){
        boolean res= false;

        String sql="DELETE FROM pacientes WHERE SSN='"+ssn+"'";
        res = conexion.ejecutarInstruccionDML(sql);
        return res;
    }


    //==================================Cambios=================================
    public boolean actualizarAlumno (Paciente p){
        boolean res= false;
        // UPDATE alumnos SET Nombre='x', PrimerAp="x",SegundoAp="x",Edad=0,Semestre=1,Carrera='x' WHERE NumeroControl='02';
        String sql="UPDATE pacientes SET '"+p.getSSN()+"','"+p.getPrimerAp()+"','"+p.getSegundoAp()+
                "',"+p.getEdad()+","+p.getColonia()+",'"+p.getCalle()+"' WHERE ssn='"+p.getSSN()+"'";
        res = conexion.ejecutarInstruccionDML(sql);
        return res;
    }
    //==================================Consultas===============================
    public Paciente buscarPac(String filtro){

        return null;
    }
    public ArrayList<Paciente> buscarAlumnos(String filtro){
        ArrayList<Paciente> listaAlumnos= new ArrayList<>();
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
                String s= rs.getString(6);
                String c= rs.getString(7);

                listaAlumnos.add(new Paciente(ssn,n,pa,sa,e,s,c));

            }while(rs.next());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaAlumnos;
    }


}
