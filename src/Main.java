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

	private static Scanner scan;
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
        Boolean estado = false;
        Usuario invitado = null;
        do {
        	//Ciclo do-while de checkeo de credenciales correctas.
        	estado = true;
            System.out.print("Usuario: ");
            username = scan.nextLine();
        	System.out.print("Contraseña: ");
            String contraseña = scan.nextLine();
            invitado = null;
            
            for(Usuario u: usuarios) {
            	if (u.getUsername().equals(username)) {
            		invitado = u;
            		estado = true;
            		break;
            		
            	}else {
            		estado = false;
            	}
            }
            
            if(estado) {
            	Boolean hash = EstadoHash(contraseña, invitado);
            	
            	if(hash) {
            		estado = true;
            		System.out.println("Acceso otorgado :).");
            		
            	}else {
            		estado = false;
            		System.out.println("Acceso denegado.");
            		System.out.println("Contraseña equivocada.");
            		return;
            	}
            } else {
            	System.out.println("Acceso denegado.");
            	System.out.println("Usuario equivocado.");
            	return;
            }

        }while(estado == false);
        
        if(invitado.getRol().equals("ADMIN")) {
        	menuAdmin();
        }else {
        	menuUsuario();
        }

	}
	
	private static Boolean EstadoHash(String contraseña, Usuario usuario) throws NoSuchAlgorithmException {
		//Función tipo booleano que recibe la contraseña a revisar y el usuario que la escribio
				//La función revisa la contraseña asociada al Usuario y la compara con la version Hasheada de la contraseña recibida.
		String PassWord = contraseña;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(PassWord.getBytes());
        String textoHash = Base64.getEncoder().encodeToString(hashBytes);		
      
		if(usuario.getContraseña().equals(textoHash)) {
			return true;
		}
		return false;
	}

	//---------------------------------- MENU ADMIN -------------------------------------------
	
	private static void menuAdmin() {
		//En caso que el Usuario ingresado tenga el rol Admin se abre este menú de opciones.
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
                	System.out.println("\n¿Qué quiere hacer?");
                	System.out.println("1. Agregar un PC");
                	System.out.println("2. Eliminar un PC");
                	System.out.print(">> ");
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
		//Función de clasificación de los PCs almacenados.
		//Explora los puertos asignados a cada PC en la lista pcs y va sumando cada vulnerabilidad que poseen.
		//Luego de la lectura entrega un veredicto de su nivel de riesgo y muestra por patalla cada puerto que posee una vulnerabilidad.
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
		//Esta función permite al usuario eliminar un PC a elección.
		//Explora en la lista de pcs para verificar si el PC ingresado es verídico.
		//Si es que encuentra el PC, este se elimina de la lista de pcs y también se le eliminan los puertos asociados.
		imprimirGuiones();
		for (Pc pc : pcs) {
			System.out.println("- " + pc.getId());
		}
		System.out.print("Ingrese el ID del PC a eliminar: ");
		scan = new Scanner(System.in);
        String idIngresado = scan.nextLine();
        boolean encontrado = false;

        for (int i = 0; i < pcs.size(); i++) {
            if (pcs.get(i).getId().equals(idIngresado)) {
                pcs.remove(i);
                encontrado = true;
                break;
            }
        }
        for (int i = puertos.size() - 1; i >= 0; i--) {
            if (puertos.get(i).getPc().equals(idIngresado)) {
                puertos.remove(i);
            }
        }
        if (encontrado) {
        	System.out.println("PC y puertos asociados eliminados.");
        } else {
        	System.out.println("No se encontró un PC con ese ID.");
        }
        imprimirGuiones();
	}

	private static void agregarPC() {
		//Esta función permite al usuario agregar un PC. además de crear los puertos que desee.
		imprimirGuiones();
		scan = new Scanner(System.in);
		System.out.print("ID del PC: ");
        String id = scan.nextLine();
        System.out.print("IP: ");
        String ip = scan.nextLine();
        System.out.print("Sistema operativo: ");
        String so = scan.nextLine();

        Pc pcNuevo = new Pc(id, ip, so);
        pcs.add(pcNuevo);
        
        System.out.print("¿Cuántos puertos desea agregar al PC? ");
        int cantPuertos = Integer.valueOf(scan.nextLine());
        
        for (int i = 0; i < cantPuertos; i++) {
            System.out.print("Indique el número de puerto: ");
            int numP = Integer.valueOf(scan.nextLine());
            
            System.out.print("Indique el estado (Abierto/Cerrado): ");
            String estado = scan.nextLine();
            Puerto p = new Puerto(id, numP, estado);
            pcNuevo.getPuertos().add(p);
            puertos.add(p); 
        }
        System.out.println("PC agregado correctamente.");
        imprimirGuiones();
	}

	//---------------------------------- MENU USUARIO -------------------------------------------
	private static void menuUsuario() {
		//En caso que el usuario ingresado tenga el rol User se abre este menu de opciones.
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
		// Función de lectura de todos lo PCs.
		// Explora cada uno de Pcs en la lista "pcs" y utiliza la función de la clase PC "imprimirPC" para obtener y imprimir las caracteristacas. 
		imprimirGuiones();
		for (Pc pc : pcs) {
			pc.imprimirPC();
			System.out.println();
		} imprimirGuiones();
	}

	private static void escanearPC() {
		//Función de escritura en el archivo reportes.txt.
		//Esta función le solicita al usuario ingresar el PC a reportar, al igual que la hora del reporte.
		//Escribe dentro del archivo txt mediante el comando (FileWriter("reportes.txt",true(True es para agregar texto al final del archivo))).
		imprimirGuiones();
		int i = 1;
		for(Pc p: pcs) {
			System.out.println(i++ + ") " + p.getId());
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
		
		System.out.println("Si desea continuar con el escaneo indique la fecha de hoy con el esquema (DD/MM/AAAA), de lo contrario escriba SALIR: ");
		aux = scan.nextLine();
			
		if(aux.equalsIgnoreCase("salir")) {
			System.out.println("Escaneo Cancelado.");
		
		}else {
			try {
	            FileWriter escribir = new FileWriter("TallerPoo2-main/reportes.txt", true);
	            
	            escribir.write("------------------------------------------\n");
	            escribir.write(PCdeseado.getId()+"\n");
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
	            System.out.println("Reporte creado con exito en el archivo reportes.txt.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		imprimirGuiones();
    }

	private static int nivelRiesgo(Pc pc) {
		//Función auxiliar de escanearPC().
		//Esta devuelve el nivel numérico de riesgo de un PC en especifico.
		int cantVulnerabilidad = 0;
		for(Puerto p: pc.getPuertos()) {
			cantVulnerabilidad += p.getVulnerabilidades().size();
		}
		return cantVulnerabilidad;
	}

	private static void verTotalPuertosAbiertos() {
		//Función de filtro de datos.
		//Esta función utiliza dos for para leer los puertos de cada PC, y si este puerto esta abierto, imprime sus datos asociados.
		System.out.println("Total de puertos abiertos en todos los PCs de la red");
		for (Pc pc : pcs) {
			imprimirGuiones();
			System.out.println("                                                       PC: " + pc.getId());
			for (Puerto pu : pc.getPuertos()) {
				if (pu.getEstado().equals("Abierto")) {
					System.out.println("Puerto: " + pu.getNumeroPuerto());
					System.out.println("Vulnerabilidad | Descripción");
					System.out.print("- ");
					pu.imprimirVulnerabilidadesPuerto();
				}
			}
		}
		imprimirGuiones();
	}

	private static void ordenarSegunIP() {
		//Función de ordenamiento de los PC en la lista pcs según la clase de IP asignado a cada uno.
		ArrayList<Pc> pcsOrdenadas = new ArrayList<>(pcs);
		ordenarPc(pcsOrdenadas);
		imprimirGuiones();
		System.out.println("PCs según su clase IP");
		for (Pc pc : pcsOrdenadas) {
			System.out.println("PC: " + pc.getId() + " | Clase IP: " + claseIp(pc.getIp()) + " | IP: " + pc.getIp());
		} imprimirGuiones();
	}
	
	private static String claseIp(String ip) {
		//Función auxiliar de ordenarSegunIp().
		//Esta función le asigna una "ClaseIP" a cada PC segun su Rango de IP.
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
		//Función auxiliar de ordenarSegunIP().
		//Esta función rellena la lista ArrayList pcsOrdenadas mediante dos ciclos for y la función de comparación de IP "compararIP"
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
		//Función auxiliar de ordenarPC().
		//Esta función separa los numeros de la IP del PC a examinar mediante ".split("\\.")" para luego poder evaluarlos.
		//La función revisa de izquierda a derecha los numeros asociados a cada split de la IP, y en base a la comparación de esos numeros retorna un booleano.
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
		//Lectura del archivo vulnerabilidades.txt.
		//La función crea Vulnerabilidades de la clase Vulnerabilidad.
		//Esta función separa cada linea del archivo mediante ".split" y ocupa los valores que se encuentran en el para satisfacer al constructor de la clase Vulnerabilidad.
		//La función tambien las vulnerabilidades correspondientes a cada puerto mediante "for(Puerto p..)".
		File file = new File("TallerPoo2-main/vulnerabilidades.txt");
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
		//Lectura del archivo puertos.txt.
		//La función crea Puertos de la clase Puerto y los agrega a la ArrayList "puertos".
		//La función tambien agrega a cada PC de la lista pcs sus puertos correspondientes.
		//Esta función separa cada linea del archivo mediante ".split" y ocupa los valores que se encuentran en el para satisfacer al constructor de la clase Puerto.
        File file = new File("TallerPoo2-main/puertos.txt");
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
    	//Lectura del archivo pcs.txt.
    	//La función crea PCs de la clase PC y los agrega a la ArrayList "pcs".
		//Esta función separa cada linea del archivo mediante ".split" y ocupa los valores que se encuentran en el para satisfacer al constructor de la clase PC.
        File file = new File("TallerPoo2-main/pcs.txt");
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
    	//Lectura del archivo usuarios.txt.
    	//La función crea Usuarios de la clase Usuario y los agrega a la ArrayList "usuarios".
		//Esta función separa cada linea del archivo mediante ".split" y ocupa los valores que se encuentran en el para satisfacer al constructor de la clase Usuario.
		File file = new File("TallerPoo2-main/usuarios.txt");
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
    	//Función estetica de impresión de guiones de separación, para evitar escribirlos a cada rato. :3
    	System.out.println("-------------------------------------------------------------------------------------------------------------------");
    }
}
