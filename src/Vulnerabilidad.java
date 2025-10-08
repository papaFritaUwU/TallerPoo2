public class Vulnerabilidad {
	private int puerto;
	private String nombreV;
	private String descripcion;
	
	public Vulnerabilidad(int puerto, String nombreV, String descripcion) {
		this.puerto = puerto;
		this.nombreV = nombreV;
		this.descripcion = descripcion;
	}

	public String getNombreV() {
		return nombreV;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public int getPuerto() {
		return puerto;
	}
	
	
}
