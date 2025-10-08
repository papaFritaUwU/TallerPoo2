import java.util.ArrayList;

public class Pc {
	private String id;
	private String ip;
	private String sO;
	private ArrayList<Puerto> puertos;
	
	public Pc(String id, String ip, String sO) {
		this.id = id;
		this.ip = ip;
		this.sO = sO;
		this.puertos = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public String getIp() {
		return ip;
	}

	public String getsO() {
		return sO;
	}

	public ArrayList<Puerto> getPuertos() {
		return puertos;
	}
	
	
	
	
	
}
