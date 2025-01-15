package Controlador;

import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

import Modelo.Users;

public class HiloServidor extends Thread {

	Socket clienteSocket;

	public HiloServidor(Socket clienteSocket) {
		this.clienteSocket = clienteSocket;
	}

	@Override
	public void run() {
		boolean continuar = true;

		try (ObjectOutputStream oos = new ObjectOutputStream(clienteSocket.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(clienteSocket.getInputStream())) {

			while (continuar) {
				int accion = (int) ois.readObject();

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
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void login(ObjectInputStream ois, ObjectOutputStream oos) {
		try {
			String nombreUser = (String) ois.readObject();
			String pass = (String) ois.readObject();
			int res = new Users().comprobarCredenciaales(nombreUser, pass);
			oos.writeObject(res);
			oos.flush();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void mostrarHorario(ObjectInputStream ois, ObjectOutputStream oos) {
		try {
			int userId = (int) ois.readObject();
			String[][] horarioUser = new Users().obtenerHorarioPorId(userId);
			oos.writeObject(horarioUser);
			oos.flush();
		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	private void mostrarOtrosHorarios(ObjectInputStream ois, ObjectOutputStream oos) {
		try {
			int userId = (int) ois.readObject();
			List<Users> listaProfesores = new Users().obtenerProfesores(userId);
			oos.writeObject(listaProfesores);
			oos.flush();
		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}

	}

}