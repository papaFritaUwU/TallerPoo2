import java.util.ArrayList;

public class Pc {
	// Representa un computador dentro de la red, con su id, dirección ip, sistema operativo y los puertos que posee.
	private String id;
	private String ip;
	private String sO;
	private ArrayList<Puerto> puertos;
	
	
	public Pc(String id, String ip, String sO) {
		//Es el constructor de la clase :3.
		//Crea un nuevo PC con su id, ip y sistema operativo, iniciando una lista vacía de puertos.
		this.id = id;
		this.ip = ip;
		this.sO = sO;
		this.puertos = new ArrayList<>();
	}

	public String getId() {
		//Retorna el id del PC.
		return id;
	}
	
	public String getIp() {
		//Retorna la dirección ip del PC.
		return ip;
	}

	public String getsO() {
		//Retorna el sistema operativo del PC.
		return sO;
	}

	public ArrayList<Puerto> getPuertos() {
		//Retorna la lista de puertos asociados al PC.
		return puertos;
	}

	public void imprimirPC() {
		//Imprime los datos del PC (ID, IP, sistema operativo) y todos sus puertos.
		System.out.println("Id: " + id + " | IP: " + ip + " | Sistema Operativo: " + sO);
		System.out.println("Puertos: ");
		for (Puerto p : puertos) {
			System.out.println(p);
		}
	}
}
