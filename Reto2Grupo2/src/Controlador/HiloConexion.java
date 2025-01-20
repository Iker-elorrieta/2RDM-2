package Controlador;

import java.io.ObjectOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Modelo.Users;

public class HiloConexion extends Thread {

	Socket clienteSocket;
	boolean continuar = true;
	Users userLogeado;

	public HiloConexion(Socket clienteSocket) {
		this.clienteSocket = clienteSocket;
	}

	@Override
	public void run() {
	    try (
	        ObjectOutputStream oos = new ObjectOutputStream(clienteSocket.getOutputStream());
	        ObjectInputStream ois = new ObjectInputStream(clienteSocket.getInputStream());
	        DataOutputStream dos = new DataOutputStream(clienteSocket.getOutputStream());
	        DataInputStream dis = new DataInputStream(clienteSocket.getInputStream())
	    ) {
	        while (continuar) {
	            try {
	                int accion = dis.readInt(); // Leer acción enviada por el cliente
	                switch (accion) {
	                    case 1:
	                        login(dis, dos, oos);
	                        
	                        break;
	                    case 2: 
	                    	mostrarHorario(ois, oos);
	                    	break;
	                    case 3: 
	                    	mostrarOtrosHorarios(ois, oos, dis);
	                    	break;
	                    case 4:
	                    	cargarHorarioPorfesorSeleccionado(dis, oos);
	                    	break;
	                    case -1:
	                        System.out.println("Cliente solicitó desconexión.");
	                        continuar = false;
	                        break;
	                    default:
	                        System.out.println("Acción no reconocida: " + accion);
	                        break;
	                }
	            } catch (EOFException | SocketException e) {
	                System.out.println("Cliente desconectado.");
	                continuar = false;
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (clienteSocket != null && !clienteSocket.isClosed()) {
	                clienteSocket.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}



	private void cargarHorarioPorfesorSeleccionado(DataInputStream dis, ObjectOutputStream oos) {
		
		
        try {
        	
        	int id = dis.readInt();
    		String[][] horarioUser = new Users().obtenerHorarioPorId(id); 
            

            System.out.println("Enviando objeto horarioUser");
        	
			oos.writeObject(horarioUser);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
        System.out.println("Horario enviado correctamente.");
		
	}

	private void login(DataInputStream dis, DataOutputStream dos, ObjectOutputStream oos ) {
	    try {
	    	boolean loginCorrecto = false;
	    	
	        String nombreUser = dis.readUTF();

	        String pass = dis.readUTF();

	        String tipoUsuario = dis.readUTF();

	        userLogeado = new Users().mObtenerUsuario(nombreUser, pass, tipoUsuario);
	        
	        if(userLogeado!=null) {
	        	loginCorrecto = true;
	        	dos.writeBoolean(loginCorrecto);
	        	dos.flush();
	        }else {
	        	
	        	dos.writeBoolean(loginCorrecto);
	        	dos.flush();
	        	
	        }
	        
	        
	        
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	private void mostrarHorario(ObjectInputStream ois, ObjectOutputStream oos) {
	    try {
	       

	        String[][] horarioUser = new Users().obtenerHorarioPorId(userLogeado.getId()); // Generar horario
	        System.out.println("Horario generado: " + Arrays.deepToString(horarioUser));

	        System.out.println("Enviando objeto horarioUser");
	        oos.writeObject(horarioUser); 
	        oos.flush();
	        System.out.println("Horario enviado correctamente.");
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	}


	private void mostrarOtrosHorarios(ObjectInputStream ois, ObjectOutputStream oos, DataInputStream dis) {
		try {
			
			List<Users> listaProfesores = userLogeado.mObtenerProfesores(userLogeado.getId());
			
			oos.writeObject(listaProfesores);
			oos.flush();
			System.out.println("lista de profesores enviada correctamente.");
			
			int id = dis.readInt();
			String[][] horarioUser = new Users().obtenerHorarioPorId(id); 
	        

	        System.out.println("Enviando objeto horarioUser");
	        oos.writeObject(horarioUser); 
	        oos.flush();
	        System.out.println("Horario enviado correctamente.");
	        
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
}
