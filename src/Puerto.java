import java.util.ArrayList;

public class Puerto {
	private int numeroPuerto;
	private String estado;
	private ArrayList<Vulnerabilidad> vulnerabilidades;
	
	public Puerto(int numeroPuerto, String estado) {
		this.numeroPuerto = numeroPuerto;
		this.estado = estado;
		this.vulnerabilidades = new ArrayList<>();
	}
	
	
	
}
