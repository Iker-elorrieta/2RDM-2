package Controlador;

import java.io.ObjectOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Modelo.Horarios;
import Modelo.Reuniones;
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
		try (ObjectOutputStream oos = new ObjectOutputStream(clienteSocket.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(clienteSocket.getInputStream());
				DataOutputStream dos = new DataOutputStream(clienteSocket.getOutputStream());
				DataInputStream dis = new DataInputStream(clienteSocket.getInputStream())) {
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
						cargarProfesores(ois, oos, dis);
						break;
					case 4:
						cargarHorarioProfesorSeleccionado(dis, oos);
						break;
					case 5:
						new Users().mCrearUsuario();
						break;
					case 6:
						cargarReunionesProfesorLogeado(ois, oos);
						break;
					case 7:
						cargarReunionesPendientes(ois, oos);
						break;
					case -1:
						System.out.println("Cliente solicitó desconexión.");
						continuar = false;
						userLogeado = null;
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

	private void cargarReunionesPendientes(ObjectInputStream ois, ObjectOutputStream oos) {
		// TODO Auto-generated method stub

		try {
			
		
		
		List<Reuniones> reunionesPendientes = new Reuniones().obtenerReunionesPendientes();

		List<Object[]> listaReunionesPendientes = new ArrayList<>();

		for (Reuniones reunion : reunionesPendientes) {

			listaReunionesPendientes.add(new Object[]{
				    reunion.getEstado() != null && !reunion.getEstado().isEmpty() ? reunion.getEstado() : "Campo vacío",
				    reunion.getIdReunion() != null ? reunion.getIdReunion() : "Campo vacío",
				    reunion.getEstadoEus() != null && !reunion.getEstadoEus().isEmpty() ? reunion.getEstadoEus() : "Campo vacío",
				    reunion.getUsersByProfesorId() != null ? reunion.getUsersByProfesorId().toString() : "Campo vacío",
				    reunion.getUsersByAlumnoId() != null ? reunion.getUsersByAlumnoId().toString() : "Campo vacío",
				    reunion.getIdCentro() != null ? reunion.getIdCentro() : "Campo vacío",
				    reunion.getTitulo() != null && !reunion.getTitulo().isEmpty() ? reunion.getTitulo() : "Campo vacío",
				    reunion.getAsunto() != null && !reunion.getAsunto().isEmpty() ? reunion.getAsunto() : "Campo vacío",
				    reunion.getAula() != null && !reunion.getAula().isEmpty() ? reunion.getAula() : "Campo vacío",
				    reunion.getFecha() != null ? reunion.getFecha() : "Campo vacío"
				});

		}
		
		
			oos.writeObject(listaReunionesPendientes);
			oos.flush();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void cargarReunionesProfesorLogeado(ObjectInputStream ois, ObjectOutputStream oos) {

		try {

			String[][] horarioReuniones = { { "Hora1", "", "", "", "", "" }, { "Hora2", "", "", "", "", "" },
					{ "Hora3", "", "", "", "", "" }, { "Hora4", "", "", "", "", "" }, { "Hora5", "", "", "", "", "" } };

			List<Reuniones> reunionesUser = new Reuniones().obtenerReunionesPorID(userLogeado.getId());

			for (Reuniones reunion : reunionesUser) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime fechaHora = LocalDateTime.parse(reunion.getFecha().toString(), formatter);

				int dia = fechaHora.getDayOfWeek().getValue();
				int hora = fechaHora.getHour();

				// Ajuste de hora para índice de la tabla
				switch (hora) {
				case 8:
					hora = 1;
					break;
				case 9:
					hora = 2;
					break;
				case 10:
					hora = 3;
					break;
				case 11:
					hora = 4;
					break;
				case 12:
					hora = 5;
					break;
				}

				if (hora >= 1 && hora <= 5 && dia >= 1 && dia <= 5) {
					String tituloConEstado = reunion.getTitulo() + "|" + reunion.getEstado();
					horarioReuniones[hora - 1][dia] = tituloConEstado;
				}
			}

			System.out.println("Enviando objeto reunionesUser");
			oos.writeObject(horarioReuniones);
			oos.flush();
			System.out.println("Reuniones enviado correctamente.");
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	private void cargarHorarioProfesorSeleccionado(DataInputStream dis, ObjectOutputStream oos) {

		try {

			String[][] horarioUser;
			int id = 0;

			List<Users> listaProfesores = userLogeado.mObtenerProfesores(userLogeado.getId());

			String nombreProfesorSeleccionado = dis.readUTF();

			for (Users users : listaProfesores) {
				if (users.getNombre().equals(nombreProfesorSeleccionado)) {
					id = users.getId();
				}

			}

			horarioUser = new Horarios().obtenerHorarioPorId(id);

			System.out.println("Enviando objeto horarioUser");

			oos.writeObject(horarioUser);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Horario enviado correctamente.");
	}

	private void login(DataInputStream dis, DataOutputStream dos, ObjectOutputStream oos) {
		try {
			boolean loginCorrecto = false;

			String nombreUser = dis.readUTF();

			String pass = dis.readUTF();

			String tipoUsuario = dis.readUTF();

			userLogeado = new Users().mObtenerUsuario(nombreUser, pass, tipoUsuario);

			if (userLogeado != null) {
				loginCorrecto = true;
				dos.writeBoolean(loginCorrecto);
				dos.flush();
			} else {

				dos.writeBoolean(loginCorrecto);
				dos.flush();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void mostrarHorario(ObjectInputStream ois, ObjectOutputStream oos) {
		try {

			String[][] horarioUser = new Horarios().obtenerHorarioPorId(userLogeado.getId()); // Generar horario
			System.out.println("Horario generado: " + Arrays.deepToString(horarioUser));

			System.out.println("Enviando objeto horarioUser");
			oos.writeObject(horarioUser);
			oos.flush();
			System.out.println("Horario enviado correctamente.");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void cargarProfesores(ObjectInputStream ois, ObjectOutputStream oos, DataInputStream dis) {
		try {
			List<String> listaProfes = new ArrayList<String>();
			List<Users> listaProfesores = userLogeado.mObtenerProfesores(userLogeado.getId());

			for (Users user : listaProfesores) {

				listaProfes.add(user.getNombre().toString());
			}

			oos.writeObject(listaProfes);
			oos.flush();
			System.out.println("lista de profesores enviada correctamente.");

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
}
