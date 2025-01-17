package Controlador;

import java.io.ObjectOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import Modelo.Users;

public class HiloConexion extends Thread {

	Socket clienteSocket;
	 boolean continuar = true;

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
	                        login(dis, dos);
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



	private void login(DataInputStream dis, DataOutputStream dos) {
	    try {
	        String nombreUser = dis.readUTF();
	        String pass = dis.readUTF();
	        Users user = new Users().mObtenerUsuario(nombreUser, pass);
	        dos.writeUTF(user.getNombre());
	        dos.writeUTF(user.getPassword());
	        dos.flush();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	private void mostrarHorario(DataInputStream ois, DataOutputStream oos) {
		/*try {
			int userId = (int) ois.readObject();
			String[][] horarioUser = new Users().obtenerHorarioPorId(userId);
			oos.writeObject(horarioUser);
			oos.flush();
		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}*/
	}

	private void mostrarOtrosHorarios(DataInputStream ois, DataOutputStream oos) {
		/*try {
			int userId = (int) ois.readObject();
			List<String> listaProfesores = new Users().mObtenerProfesores(userId);
			oos.writeObject(listaProfesores);
			oos.flush();
		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}*/

	}
	
	


}
