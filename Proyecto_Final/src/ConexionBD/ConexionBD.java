package ConexionBD;


import java.sql.*;

public class ConexionBD {

    private Connection conexion;
    private Statement stm;//Lo mejor es usar prepared statement por seguridad, para evitar sql injection
    private ResultSet rs;


    public ConexionBD(){
        //Busca el dirver para conexion
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String URL= "jdbc:mysql://localhost:3306/BD_Escuela_Topicos";
            conexion= DriverManager.getConnection(URL,"root","itsj");
            System.out.println("Conexion Establecida");

        } catch (ClassNotFoundException e) {
            // throw new RuntimeException(e);
            System.out.println("Error en el controlador de conexion a mysql");

        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error en la ruta de conexion");
        }
    }//Constructor



    //Metodo Para ABC(Altas,Baja,Cambios)

    //Data Manipulation Language
    public boolean ejecutarInstruccionDML(String instruccionDML){

        boolean res= false;

        try {
            stm=conexion.createStatement();
            if(stm.executeUpdate(instruccionDML)>=1){
                res = true;
            }
        } catch (SQLException e) {
            System.out.println("Error en instruccion SQL");
        }
        return res;
    }

    public ResultSet ejecutarConsulta(String consultaSQL){
        rs= null;

        try {
            stm=conexion.createStatement();
            rs=stm.executeQuery(consultaSQL);

        } catch (SQLException e) {
            System.out.println("Error en instruccion SQL");
        }
        return rs;
    }

    public void cerrarConexion(){
        try {
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new ConexionBD();
    }
}//Class

