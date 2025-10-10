public class Usuario {
	//Representa a un usuario del sistema, con su nombre de usuario, contraseña y rol.
	private String username;
	private String contraseña;
	private String rol;
	
	public Usuario(String username, String contraseña, String rol) {
		//Crea un nuevo usuario con su nombre, contraseña y rol.
		//Es el constructor de la clase :3.
		this.username = username;
		this.contraseña = contraseña;
		this.rol = rol;
	}

	public String getUsername() {
		//Retorna el nombre de usuario.
		return username;
	}

	public String getContraseña() {
		//Retorna la contraseña del usuario.
		return contraseña;
	}

	public String getRol() {
		//Retorna el rol asignado al usuario.
		return rol;
	}
}
