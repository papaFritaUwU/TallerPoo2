
public class Vulnerabilidad {
	//Representa una vulnerabilidad de un puerto, indicando su nombre, descripción y puerto.
	private int puerto;
	private String nombreVulnerabilidad;
	private String descripcion;
	
	public Vulnerabilidad(int puerto, String nombreV, String descripcion) {
		//Es el constructor de la clase :3.
		//Crea una nueva vulnerabilidad asociada a un puerto, con su nombre y descripción.
		this.puerto = puerto;
		this.nombreVulnerabilidad = nombreV;
		this.descripcion = descripcion;
	}

	public String getNombreV() {
		//Retorna el nombre de la vulnerabilidad.
		return nombreVulnerabilidad;
	}

	public String getDescripcion() {
		//Retorna la descripción de la vulnerabilidad.
		return descripcion;
	}
	
	public int getPuerto() {
		//Retorna el número de puerto donde está la vulnerabilidad.
		return puerto;
	}
}
