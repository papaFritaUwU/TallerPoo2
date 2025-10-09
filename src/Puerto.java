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

	public String getPc() {
		return pc;
	}

	public int getNumeroPuerto() {
		return numeroPuerto;
	}

	public String getEstado() {
		return estado;
	}

	public ArrayList<Vulnerabilidad> getVulnerabilidades() {
		return vulnerabilidades;
	}
	
	public void imprimirVulnerabilidadesPuerto() {
		for (Vulnerabilidad v : vulnerabilidades) {
			System.out.println(v.getNombreV() + " | " + v.getDescripcion());
		}
	}

	@Override
	public String toString() {
		return "N°: " + numeroPuerto + " | Estado: " + estado;
	}
}
