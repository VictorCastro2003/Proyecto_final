package modelo;

public class Usuario {
	private byte noUsuario;
	private String nombre;
	private String contraseña;


	public Usuario(byte noUsuario, String nombre, String contraseña) {
		this.noUsuario = noUsuario;
		this.nombre = nombre;
		this.contraseña = contraseña;

	}



	public byte getNoUsuario() {
		return noUsuario;
	}
	public void setNoUsuario(byte noUsuario) {
		this.noUsuario = noUsuario;
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}






	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}