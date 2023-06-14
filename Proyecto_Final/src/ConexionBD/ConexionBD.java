package ConexionBD;

import modelo.*;

import java.sql.*;

public class ConexionBD {
    private static Connection conexion= null;
    //PreparedStatement stm = conexion.prepareStatement(sql);
    private static PreparedStatement pstm; //Lo mejor es utilizar PREPARE STATEMEN por seguridad, para evitar SQL INJECTION
    private static ResultSet rs;

    private ConexionBD(){
        //buscar el driver para la conexion con mysql
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String URL = "jdbc:mysql://localhost:3306/poyecto_farmacias";

            conexion = DriverManager.getConnection(URL, "root", "itsj");

        } catch (ClassNotFoundException e) {
            // throw new RuntimeException(e);
            System.out.println("Error en el controlador de conexion a MySQL");
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error en la ruta de conexión");
        }
    }//constructor

    public static Connection getConexion(){
        if (conexion == null){
            new ConexionBD();
        }
        return conexion;
    }

    static void cerrarConexion(){
        try {
            pstm.close();
            conexion.close();
        }catch (SQLException e){
            System.out.println("Error al cerrar la conexion");
            e.printStackTrace();
        }
    }


    //metodo para ABC (Altas, Bajas, Cambios)
    public static boolean AgregarPaciente(Paciente p){
        try {
            pstm = conexion.prepareStatement("insert into pacientes values(?,?,?,?,?,?)");

            pstm.setInt(1,p.getSSN());
            pstm.setString(2, p.getNombre());
            pstm.setString(3,p.getPrimerAp());
            pstm.setString(4,p.getSegundoAp());
            pstm.setByte(5,p.getEdad());
            pstm.setInt(6,p.getCalle());

            pstm.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en instruccion DMl");
        }
        return false;
    }

    public static boolean EliminarPacientes(String instruccion){
        try {
            String consulta = instruccion;
            pstm = conexion.prepareStatement(consulta);
            pstm.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("ERROR");
        }
        return false;
    }

    public static boolean ActualizarPaciente(Paciente p){

        try {
            pstm = conexion.prepareStatement("UPDATE pacientes SET Nombre=?,primerAp=?,segundoAp=?,Edad=?,ID_Calle=? where SSN="+p.getSSN()+"");
            pstm.setString(1,p.getNombre());
            pstm.setString(2,p.getPrimerAp());
            pstm.setString(3,p.getSegundoAp());
            pstm.setByte(4,p.getEdad());
            pstm.setInt(5,p.getCalle());

            pstm.executeUpdate();

            return true;

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return false;
    }


    public static ResultSet BuscarPaciente(String consulta){

        try {
            pstm = conexion.prepareStatement(consulta);
            return pstm.executeQuery();

        } catch (SQLException e) {
            System.out.println("Error en instruccion SQL");
        }
        return null;
    }





    public static void main(String[] args) {
        new ConexionBD();
    }
}