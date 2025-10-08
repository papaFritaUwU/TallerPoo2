/* Nombre: Ninibeth Pérez Cortés, RUT: 21.787.686-9, Carrera: ICCI | Nombre: Rodrigo Reyes Alfaro, RUT: 22.123.808-7, Carrera: ICCI
 * Nombre: Ninibeth Pérez Cortés, RUT: 21.787.686-9, Carrera: ICCI | Nombre: Rodrigo Reyes Alfaro, RUT: 22.123.808-7, Carrera: ICCI
 * Nombre: Ninibeth Pérez Cortés, RUT: 21.787.686-9, Carrera: ICCI | Nombre: Rodrigo Reyes Alfaro, RUT: 22.123.808-7, Carrera: ICCI
 * Nombre: Ninibeth Pérez Cortés, RUT: 21.787.686-9, Carrera: ICCI | Nombre: Rodrigo Reyes Alfaro, RUT: 22.123.808-7, Carrera: ICCI
 * Nombre: Ninibeth Pérez Cortés, RUT: 21.787.686-9, Carrera: ICCI | Nombre: Rodrigo Reyes Alfaro, RUT: 22.123.808-7, Carrera: ICCI
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static Scanner scan = new Scanner(System.in);
	private static ArrayList<Pc> pcs = new ArrayList<>();
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Puerto> puertos = new ArrayList<>();
	
	public static void main(String[] args) throws FileNotFoundException {
		leerUsuario();
        leerPcs();
        leerPuertos();
        leerVulnerabilidades();
        
        scan = new Scanner(System.in);
        System.out.print("Usuario: ");
        String username = scan.nextLine();
        Boolean encontrado = false;
        
        System.out.print("Contraseña: ");
        String contraseña = scan.nextLine();
        //hacer función para hashear la contraseña
        //hashearContraseña(String contraseña);
        String contraseñaHasheada = "";
        
        for (Usuario u : usuarios) {           //& u.getContraseña().equals(contraseñaHasheada)
        	if (u.getUsername().equals(username)) {
    			if (u.getRol().equals("ADMIN")) {
    				menuAdmin();
    			} else {
    				menuUsuario();
    			}
        System.out.println("Acceso denegado. Usuario/Contraseña incorrecto.");
        	}
        }
	}
	
	//---------------------------------- MENU ADMIN -------------------------------------------
	
	private static void menuAdmin() {
		scan = new Scanner(System.in);
        int opcion = -1;
        do {
            System.out.println("\n--- MENÚ ADMIN ---");
            System.out.println("1. Ver lista de PCs");
            System.out.println("2. Agregar o eliminar un PC");
            System.out.println("3. Clasificar PCs por nivel de riesgo");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.valueOf(scan.nextLine());

            switch (opcion) {
                case 1:
                	verPCs();
                	break;
                	
                case 2:
                	System.out.println("¿Qué quiere hacer?");
                	System.out.println("1. Agregar un PC");
                	System.out.println("2. Eliminar un PC");
                	int eleccion = Integer.valueOf(scan.nextLine());
                	
                	if (eleccion == 1) {
                		agregarPC();
                	} else {
                		eliminarPC();
                	}
                	break;
                	
                case 3: 
                	clasificarPCs();
                	break;
                	
                case 0: 
                	System.out.println("Saliendo del menú admin...");
                	break;
                	
                default: 
                	System.out.println("Opción inválida.");
                	break;
            }
        } while (opcion != 0);
    }
	
	
	
	private static void clasificarPCs() {
		// TODO Auto-generated method stub
		
	}

	private static void eliminarPC() {
		// TODO Auto-generated method stub
		
	}

	private static void agregarPC() {
		// TODO Auto-generated method stub
		
	}

	//---------------------------------- MENU USUARIO -------------------------------------------
	private static void menuUsuario() {
		scan = new Scanner(System.in);
        int opcion = -1;
        do {
            System.out.println("\n--- MENÚ USUARIO ---");
            System.out.println("1. Ver lista de PCs");
            System.out.println("2. Escanear PC");
            System.out.println("3. Ver puertos abiertos con vulnerabilidades");
            System.out.println("4. Ordenar PCs según clase de IP");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.valueOf(scan.nextLine());

            switch (opcion) {
                case 1:
                	verPCs();
                	break;
                	
                case 2:
                	escanearPC();
                	break;
                	
                case 3:
                	verTotalPuertosAbiertos();
                	break;
                	
                case 4:
                	ordenarSegunIP();
                	break;
                	
                case 0:
                	System.out.println("Saliendo del menú usuario...");
                	break;
                	
                default:
                	System.out.println("Opción inválida.");
                	break;
            }
        } while (opcion != 0);
    }
	
	private static void verPCs() {
		for (Pc pc : pcs) {
			pc.imprimirPC();
		}
	}

	private static void escanearPC() {
		// TODO Auto-generated method stub
		
	}

	private static void verTotalPuertosAbiertos() {
		for (Pc pc : pcs) {
			System.out.println("-------------------------------------------------------------------------------------------------------------------");
			System.out.println("                                                  PC: " + pc.getId());
			for (Puerto pu : pc.getPuertos()) {
				System.out.println("Puerto: " + pu.getNumeroPuerto());
				pu.imprimirVulnerabilidadesPuerto();
			}
		}
		System.out.println("-------------------------------------------------------------------------------------------------------------------");
	}

	private static void ordenarSegunIP() {
        ArrayList<Pc> pcsOrdenadas = pcs;
		for (Pc pc : pcs) {
            int id = Integer.valueOf(pc.getId());
            //if ()

        }
	}
	
	//------------------------- LECTURA ARCH ---------------------------------

	private static void leerVulnerabilidades() throws FileNotFoundException {
		File file = new File("vulnerabilidades.txt");
		scan = new Scanner(file);
		
		while (scan.hasNextLine()) {
            String linea = scan.nextLine();
            String [] partes = linea.split("\\|");
            int puerto = Integer.valueOf(partes[0]);
            String nombre = partes[1];
            String descripcion = partes[2];
            Vulnerabilidad v = new Vulnerabilidad(puerto, nombre, descripcion);
            
            for (Puerto p : puertos) {
            	if (p.getNumeroPuerto() == puerto) {
            		p.getVulnerabilidades().add(v);
            	}
            }
		}
	}

	private static void leerPuertos() throws FileNotFoundException {
        File file = new File("puertos.txt");
		scan = new Scanner(file);
		
		while (scan.hasNextLine()) {
            String linea = scan.nextLine();
            String [] partes = linea.split("\\|");
            String id = partes[0];
            int numero = Integer.valueOf(partes[1]);
            String estado = partes[2];
            Puerto p = new Puerto(id, numero, estado);
            puertos.add(p);
            
            for (Pc pc : pcs) {
                if (pc.getId().equals(id)) {
                    pc.getPuertos().add(p);
                    break;
                }
            }
        }
    }

    private static void leerPcs() throws FileNotFoundException {
        File file = new File("pcs.txt");
		scan = new Scanner(file);
		
		while (scan.hasNextLine()) {
            String linea = scan.nextLine();
			String [] partes = linea.split("\\|");
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

