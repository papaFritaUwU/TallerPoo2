import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static Scanner scan = new Scanner(System.in);
	private static ArrayList<Pc> pcs = new ArrayList<>();
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
	
	public static void main(String[] args) throws FileNotFoundException {
		leerUsuario();
        leerPcs();
        leerPuertos();

        

        

	}

	private static void leerPuertos() throws FileNotFoundException {
        File file = new File("puertos.txt");
		scan = new Scanner(file);
		
		while (scan.hasNextLine()) {
            String linea = scan.nextLine();
            String [] partes = linea.split(";");
            String id = partes[0];
            int numero = Integer.valueOf(partes[1]);
            String estado = partes[2];
            Puerto p = new Puerto(id, numero, estado);
            for (Pc pc : pcs) {
                if (pc.getId().equals(id)) {
                    pc.getPuertos().add(p);
                }

                
            }


        }
    }

    private static void leerPcs() throws FileNotFoundException {
        File file = new File("pcs.txt");
		scan = new Scanner(file);
		
		while (scan.hasNextLine()) {
            String linea = scan.nextLine();
			String [] partes = linea.split(";");
            String id = partes[0];
            String ip = partes[1];
            String so = partes[2];
            Pc p = new Pc(id, ip, so);
            pcs.add(p);
			
        }
    }

    private static void leerUsuario() throws FileNotFoundException {
		File file = new File("usuarios.txt");
		scan = new Scanner(file);
		
		while (scan.hasNextLine()) {
			String linea = scan.nextLine();
			String [] partes = linea.split(";");
			String username = partes[0];
			String contraseña = partes[1];
            String rol = partes[2];
            Usuario u = new Usuario(username, contraseña, rol);
            usuarios.add(u);
        }
	}

}
