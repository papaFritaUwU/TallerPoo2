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
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {

	private static Scanner scan = new Scanner(System.in);
	private static ArrayList<Pc> pcs = new ArrayList<>();
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Puerto> puertos = new ArrayList<>();
	private static String username;
	public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException {
		leerUsuario();
        leerPcs();
        leerPuertos();
        leerVulnerabilidades();
        
        scan = new Scanner(System.in);
    
        Boolean Estado = false;
 
        do {
        	Estado = true;
        	
            System.out.print("Usuario: ");
            username = scan.nextLine();
        	System.out.print("Contraseña: ");
            String contraseña = scan.nextLine();
            Usuario Invitado = null;
            
            for(Usuario u: usuarios) {
            	
            	if (u.getUsername().equals(username)) {
            		Invitado = u;
            		
            		Estado = true;
            		break;
            		
            	}else {
            		Estado = false;
            	}
            	
            }
            
 
            
            if(Estado) {
            	Boolean hash = EstadoHash(contraseña, Invitado);
            	
            	if(hash) {
            		Estado = true;
            		System.out.println("Acceso otorgado");
            		
            	}else {
            		Estado = false;
            		System.out.println("Contraseña equivocada");
            	}

            } else {
            	System.out.println("Ingrese un usuario valido.");
            }

        }while(Estado == false);
        
        
        
        
        
	}
	
	private static Boolean EstadoHash(String contraseña, Usuario usuario) throws NoSuchAlgorithmException {
		
		
		String PassWord = contraseña;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(PassWord.getBytes());
        String textoHash = Base64.getEncoder().encodeToString(hashBytes);		
        System.out.println(textoHash);
		System.out.println(usuario.getContraseña());
		if(usuario.getContraseña().equals(textoHash)) {
			return true;

		}
		
		return false;
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
		imprimirGuiones();
		System.out.println("Nivel de riesgo de PCs");
		int cantV = 0;
		for (Pc pc : pcs) {
			for (Puerto p : pc.getPuertos()) {
				cantV += p.getVulnerabilidades().size();
			}
			String nivelR = "";
			if (cantV == 0) {
				nivelR = "Bajo riesgo";
			} else if (cantV <= 2) {
				nivelR = "Medio riesgo";
			} else {
				nivelR = "Alto riesgo";
			}
			
			System.out.println("PC: " + pc.getId() + " | Nivel de riesgo: " + nivelR);
			System.out.println("\n                 Vulnerabilidad        |        Descripción");
			for (Puerto p : pc.getPuertos()) {
				System.out.print("Puerto: " + p.getNumeroPuerto() + " | ");
				p.imprimirVulnerabilidadesPuerto();
			}
			imprimirGuiones();
		}
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
		imprimirGuiones();
		for (Pc pc : pcs) {
			pc.imprimirPC();
		} imprimirGuiones();
	}

	private static void escanearPC() {
		imprimirGuiones();
		int i = 1;
		for(Pc p: pcs) {
			System.out.println(i++ + ") " + p);

		}
		scan = new Scanner(System.in);
		int opcion = 0;
		
		do {
			System.out.println("Ingrese el indice correspondiente al PC a Escanear: ");
			opcion = Integer.valueOf(scan.nextLine());
		}while(opcion >= i);
		
		Pc PCdeseado = pcs.get(opcion-1);
		imprimirGuiones();
		System.out.println("Puertos correspondientes al PC numero " + opcion + ":");
		for(Puerto p: PCdeseado.getPuertos()) {
			System.out.println(p);
		}
		String aux;
		
		System.out.println("Si desea continuar con la Escaneo indique la fecha de hoy con el esquema (dia/mes/año), de lo contrario escriba SALIR: ");
		aux = scan.nextLine();
			
		if(aux.equals("SALIR")) {
			System.out.println("Escaneo Cancelado");

		
		}else {
			try {
				
	            FileWriter escribir = new FileWriter("Metricas.txt", true);
	            
	            escribir.write("------------------------------------------\n");
	            escribir.write(PCdeseado.toString()+"\n");
	            escribir.write("Puertos correspondientes al PC: \n");
	            
	            for(Puerto p: PCdeseado.getPuertos()) {
	            	escribir.write(p.toString()+"\n");
	            }
	            escribir.write("Usuario que solicito el escaneo: " + username + "\n");
	            escribir.write("Nivel de Riesgo segun sus vulnerabilidades: \n");
	            int nivel = nivelRiesgo(PCdeseado);
	            
	            if (nivel <= 1) {
		            escribir.write("Bajo riesgo \n");

	            }else if(nivel <= 2) {
		            escribir.write("Medio riesgo \n");
	            	
	            }else if(nivel >=3) {
		            escribir.write("Alto riesgo \n");
	            	
	            }
	            escribir.write("Fecha del Escaneo: " + aux + "\n");
 
	            
	            
	            escribir.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
    }

	private static int nivelRiesgo(Pc pc) {
		int cantVulnerabilidad = 0;
		
		for(Puerto p: pc.getPuertos()) {
			cantVulnerabilidad += p.getVulnerabilidades().size();
			
		}
        	
		
		return cantVulnerabilidad;
	}

	private static void verTotalPuertosAbiertos() {
		System.out.println("Total de puertos abiertos en todos los PCs de la red");
		for (Pc pc : pcs) {
			imprimirGuiones();
			System.out.println("                                                       PC: " + pc.getId());
			for (Puerto pu : pc.getPuertos()) {
				System.out.println("Puerto: " + pu.getNumeroPuerto());
				System.out.println("Vulnerabilidad | Descripción");
				System.out.print("- ");
				pu.imprimirVulnerabilidadesPuerto();
			}
		}
		imprimirGuiones();
	}

	private static void ordenarSegunIP() {
		ArrayList<Pc> pcsOrdenadas = new ArrayList<>(pcs);
		ordenarPc(pcsOrdenadas);
		imprimirGuiones();
		System.out.println("PCs según su clase IP");
		for (Pc pc : pcsOrdenadas) {
			System.out.println("PC: " + pc.getId() + " | Clase IP: " + claseIp(pc.getIp()) + " | IP: " + pc.getIp());
		} imprimirGuiones();
	}
	
	private static String claseIp(String ip) {
		String [] ipPartes = ip.split("\\.");
		int ipParte1 = Integer.valueOf(ipPartes[0]);
		if (0 <= ipParte1 & ipParte1 <= 127) {
			return "A";
		} else if (128 <= ipParte1 & ipParte1 <= 191) {
			return "B";
		} else {
			return "C";
		}
	}

	private static void ordenarPc(ArrayList<Pc> pcsOrdenadas) {
		for (int i = 0; i < pcs.size() - 1; i++) {
        	for (int j = i + 1; j < pcs.size(); j++) {
        		if (compararIp(pcsOrdenadas.get(i).getIp(), pcsOrdenadas.get(j).getIp())) {
        			Pc aux = pcsOrdenadas.get(i);
                    pcsOrdenadas.set(i, pcsOrdenadas.get(j));
                    pcsOrdenadas.set(j, aux);
        		}
        	}
        }
	}

	private static boolean compararIp(String ip, String ip2) {
		String [] ipPartes = ip.split("\\.");
		String [] ipPartes2 = ip2.split("\\.");
		
		for (int i = 0; i < 4; i++) {
			int iP1 = Integer.valueOf(ipPartes[i]);
			int iP2 = Integer.valueOf(ipPartes2[i]);
			
			if (iP1 > iP2) {
				return true;  
			}
	        if (iP1 < iP2) { 
	        	return false;  
	        }
	    }
	    return false; 
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
    
    private static void imprimirGuiones() {
    	System.out.println("-------------------------------------------------------------------------------------------------------------------");
    }
}
