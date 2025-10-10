
public class Vulnerabilidad {
	private int puerto;
	private String nombreVulnerabilidad;
	private String descripcion;
	
	public Vulnerabilidad(int puerto, String nombreV, String descripcion) {
		this.puerto = puerto;
		this.nombreVulnerabilidad = nombreV;
		this.descripcion = descripcion;
	}

	public String getNombreV() {
		return nombreVulnerabilidad;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public int getPuerto() {
		return puerto;
	}
}
