import java.util.ArrayList;

public class Puerto {
	//Representa un puerto de red asociado a un PC, junto con su estado y las vulnerabilidades detectadas en él.
    private String pc;
	private int numeroPuerto;
	private String estado;
	private ArrayList<Vulnerabilidad> vulnerabilidades;
	
	public Puerto(String pc, int numeroPuerto, String estado) {
		//Es el constructor de la clase :3.
		//Crea un nuevo puerto asignado a un PC específico, indicando su número, estado y preparando su lista de vulnerabilidades.
        this.pc = pc;
		this.numeroPuerto = numeroPuerto;
		this.estado = estado;
		this.vulnerabilidades = new ArrayList<>();
	}

	public String getPc() {
		//Retorna el ID del PC al que pertenece este puerto.
		return pc;
	}

	public int getNumeroPuerto() {
		//Retorna el número del puerto.
		return numeroPuerto;
	}

	public String getEstado() {
		//Retorna el estado del puerto (Abierto o Cerrado).
		return estado;
	}

	public ArrayList<Vulnerabilidad> getVulnerabilidades() {
		// Retorna la lista de vulnerabilidades asociadas a este puerto.
		return vulnerabilidades;
	}
	
	public void imprimirVulnerabilidadesPuerto() {
		// Imprime las vulnerabilidades asociadas al puerto con su nombre y descripción.
		// Si no hay vulnerabilidades, muestra un mensaje indicándolo.
		if (vulnerabilidades.size() == 0) {
			System.out.println("No hay vulnerabilidades.");
		} else {
			for (Vulnerabilidad v : vulnerabilidades) {
				System.out.println(v.getNombreV() + " | " + v.getDescripcion());
			}
		}
	}

	@Override
	public String toString() {
		return "N°: " + numeroPuerto + " | Estado: " + estado;
	}
}
