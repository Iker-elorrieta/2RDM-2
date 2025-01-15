package Controlador;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	private final static int PORT = 2845;

	public static void main(String[] args) {
		ServerSocket ss;
		boolean aceptando = true;

		try {
			ss = new ServerSocket(PORT);

			System.out.println("El servidor está encendido.");

			while (aceptando) {
				Socket socket = ss.accept();
				HiloConexion hConexion = new HiloConexion(socket);
				hConexion.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
