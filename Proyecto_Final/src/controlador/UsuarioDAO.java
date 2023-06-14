package controlador;
import modelo.Usuario;
import Vista.Login;
import ConexionBD.ConexionBD;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//DAO - Data Access Object

public class UsuarioDAO implements Runnable{

	ConexionBD conexion;

	private String filtro;

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	//CONSULTAS
	public ArrayList<Usuario> buscarUsuario (String filtro){
		ArrayList<Usuario> listaUsuarios = new ArrayList<>();

		ResultSet rs= ConexionBD.BuscarPaciente(filtro);

		try {
			if(rs.next()) {
				do {
					listaUsuarios.add(new Usuario(rs.getByte(1),
							rs.getString(2),
							rs.getString(3)));
					Login.bandera = true;
				}while(rs.next());
			}else {
				Login.bandera = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Login.bandera = false;
		}

		return listaUsuarios;
	}


	@Override
	public void run() {
		//JOptionPane.showMessageDialog(null,"Buscando Registro!!!!!!!!!");
		buscarUsuario(this.filtro);

	}





}