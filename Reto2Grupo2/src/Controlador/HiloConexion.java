package Controlador;

import java.io.ObjectOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

import Modelo.Users;

public class HiloConexion extends Thread {

	Socket clienteSocket;

	public HiloConexion(Socket clienteSocket) {
		this.clienteSocket = clienteSocket;
	}

	@Override
	public void run() {
	    boolean continuar = true;

	    try (DataOutputStream oos = new DataOutputStream(clienteSocket.getOutputStream());
	    		DataInputStream ois = new DataInputStream(clienteSocket.getInputStream())) {

	        while (continuar) {
	            try {
	                int accion = (int) ois.readInt();
	                switch (accion) {
	                case 1:
	                    login(ois, oos);
	                    break;
	                case 2:
	                    mostrarHorario(ois, oos);
	                    break;
	                case 3:
	                    mostrarOtrosHorarios(ois, oos);
	                    break;
	                default:
	                    continuar = false;
	                    break;
	                }
	            } catch (EOFException e) {
	                System.out.println("EOF alcanzado. Cerrando la conexión...");
	                continuar = false;
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	private void login(DataInputStream ois, DataOutputStream oos) {
		try {
			String nombreUser = (String) ois.readUTF();
			String pass = (String) ois.readUTF();
			Users user = new Users().mObtenerUsuario(nombreUser, pass);
			oos.writeUTF(user.getNombre());
			oos.writeUTF(user.getPassword());
			oos.flush();
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
			List<Users> listaProfesores = new Users().mObtenerProfesores(userId);
			oos.writeObject(listaProfesores);
			oos.flush();
		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}*/

	}

}
