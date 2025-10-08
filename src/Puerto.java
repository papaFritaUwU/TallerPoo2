import java.util.ArrayList;

public class Puerto {
    private String pc;
	private int numeroPuerto;
	private String estado;
	private ArrayList<Vulnerabilidad> vulnerabilidades;
	
	public Puerto(String pc, int numeroPuerto, String estado) {
        this.pc = pc;
		this.numeroPuerto = numeroPuerto;
		this.estado = estado;
		this.vulnerabilidades = new ArrayList<>();
	}
	
	
	
}
