package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import Modelo.Users;
import Vista.PanelLogin;
import Vista.VentanaPrincipal;
import Vista.VentanaPrincipal.enumAcciones;

public class Controlador implements ActionListener {

	private Vista.VentanaPrincipal vistaPrincipal;
	private Users usuarioLogeado;
	private Socket socketCliente;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private DataOutputStream dos;
	private DataInputStream dis;
	private boolean conexionActiva;
	private final static String TIPO_USUARIO = "profesor";

	public Controlador(VentanaPrincipal ventanaLogin) {
		this.vistaPrincipal = ventanaLogin;
		this.vistaPrincipal.setVisible(true);
		this.inicializarControlador();
	}

	private void inicializarControlador() {

		conexionActiva = true;
		try {
			socketCliente = new Socket("localhost", 2845);
			oos = new ObjectOutputStream(socketCliente.getOutputStream());
			ois = new ObjectInputStream(socketCliente.getInputStream());
			dos = new DataOutputStream(socketCliente.getOutputStream());
			dis = new DataInputStream(socketCliente.getInputStream());
			System.out.println("Conexión establecida con el servidor.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Agregar WindowListener para manejar el cierre de ventana
		this.vistaPrincipal.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// Solo llamar a desconectar si no se ha hecho ya
				if (conexionActiva) {
					desconectar();
				}
				System.exit(0); // Cierra la aplicación después de desconectar
			}
		});

		// VENTANA
		// LOGIN-------------------------------------------------------------------------------------------------
		this.vistaPrincipal.getPanelLogin().getBtnLogin().addActionListener(this);
		;
		this.vistaPrincipal.getPanelLogin().getBtnLogin()
				.setActionCommand(VentanaPrincipal.enumAcciones.LOGIN.toString());

		PanelLogin panelLogin = this.vistaPrincipal.getPanelLogin();

		panelLogin.getTfUser().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					mConfirmarLogin(VentanaPrincipal.enumAcciones.LOGIN);
				}
			}
		});

		panelLogin.getPfPass().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					mConfirmarLogin(VentanaPrincipal.enumAcciones.LOGIN);
				}
			}
		});

		this.vistaPrincipal.getPanelLogin().getBtnCrearUser().addActionListener(this);
		;
		this.vistaPrincipal.getPanelLogin().getBtnCrearUser()
				.setActionCommand(VentanaPrincipal.enumAcciones.CREAR_USUARIO.toString());

		// VENTANA
		// MENU----------------------------------------------------------------------------------------------------
		this.vistaPrincipal.getPanelMenu().getBtnHorario().addActionListener(this);
		;
		this.vistaPrincipal.getPanelMenu().getBtnHorario()
				.setActionCommand(VentanaPrincipal.enumAcciones.CARGAR_PANEL_HORARIO.toString());

		this.vistaPrincipal.getPanelMenu().getBtnOtrosHorarios().addActionListener(this);
		;
		this.vistaPrincipal.getPanelMenu().getBtnOtrosHorarios()
				.setActionCommand(VentanaPrincipal.enumAcciones.CARGAR_PANEL_OTROS_HORARIOS.toString());

		this.vistaPrincipal.getPanelMenu().getBtnReuniones().addActionListener(this);
		;
		this.vistaPrincipal.getPanelMenu().getBtnReuniones()
				.setActionCommand(VentanaPrincipal.enumAcciones.CARGAR_PANEL_REUNIONES.toString());

		this.vistaPrincipal.getPanelMenu().getBtnDesconectar().addActionListener(this);
		;
		this.vistaPrincipal.getPanelMenu().getBtnDesconectar()
				.setActionCommand(VentanaPrincipal.enumAcciones.DESCONECTAR.toString());

		// VENTANA
		// HORARIO----------------------------------------------------------------------------------------------
		this.vistaPrincipal.getPanelHorario().getBtnVolver().addActionListener(this);
		;
		this.vistaPrincipal.getPanelHorario().getBtnVolver()
				.setActionCommand(VentanaPrincipal.enumAcciones.CARGAR_PANEL_MENU.toString());

		// VENTANA OTROS HORARIOS
		// -----------------------------------------------------------------------------------------------
		this.vistaPrincipal.getPanelOtrosHorarios().getBtnVolver().addActionListener(this);
		;
		this.vistaPrincipal.getPanelOtrosHorarios().getBtnVolver()
				.setActionCommand(VentanaPrincipal.enumAcciones.CARGAR_PANEL_MENU.toString());

		// VENTANA
		// REUNIONES---------------------------------------------------------------------------------------------------------

		this.vistaPrincipal.getPanelReuniones().getBtnVolver().addActionListener(this);
		;
		this.vistaPrincipal.getPanelReuniones().getBtnVolver()
				.setActionCommand(VentanaPrincipal.enumAcciones.CARGAR_PANEL_MENU.toString());
	}

	public void actionPerformed(ActionEvent e) {

		VentanaPrincipal.enumAcciones accion = VentanaPrincipal.enumAcciones.valueOf(e.getActionCommand());

		switch (accion) {
		case LOGIN:
			this.mConfirmarLogin(accion);
			break;
		case CARGAR_PANEL_LOGIN:
			this.vistaPrincipal.mVisualizarPaneles(accion);
			break;
		case CARGAR_PANEL_MENU:
			this.vistaPrincipal.mVisualizarPaneles(accion);
			break;
		case CARGAR_PANEL_HORARIO:
			this.vistaPrincipal.mVisualizarPaneles(accion);

			break;
		case CARGAR_PANEL_OTROS_HORARIOS:
			this.vistaPrincipal.mVisualizarPaneles(accion);
			usuarioLogeado.mObtenerProfesores(usuarioLogeado.getId());
			System.out.println(usuarioLogeado.mObtenerProfesores(usuarioLogeado.getId()));
			break;
		case CARGAR_PANEL_REUNIONES:
			this.vistaPrincipal.mVisualizarPaneles(accion);
			break;
		case DESCONECTAR:
			this.vistaPrincipal.mVisualizarPaneles(enumAcciones.CARGAR_PANEL_LOGIN);
			desconectar();
			break;
		case CREAR_USUARIO:
			new Users().mCrearUsuario();
			break;
		default:
			break;

		}
	}

	private void mConfirmarLogin(enumAcciones accion) {
		PanelLogin panelLogin = this.vistaPrincipal.getPanelLogin();
		String usuarioIntroducido = panelLogin.getTfUser().getText().trim();
		String passIntroducida = new String(panelLogin.getPfPass().getPassword()).trim();

		if (!usuarioIntroducido.isEmpty() && !passIntroducida.isEmpty()) {
			Users usuario = new Users();
			usuarioLogeado = usuario.mObtenerUsuario(usuarioIntroducido, passIntroducida, TIPO_USUARIO); 
			if (usuarioLogeado != null) {
				System.out.println("Usuario correcto");

				// Solo inicializar la conexión si no está abierta
				if (socketCliente == null || socketCliente.isClosed()) {
					inicializarControlador(); // Establecer conexión solo si no está abierta
				}

				// Mostrar el panel del menú
				this.vistaPrincipal.mVisualizarPaneles(VentanaPrincipal.enumAcciones.CARGAR_PANEL_MENU);
				panelLogin.getTfUser().setText("");
				panelLogin.getPfPass().setText("");
			} else {
				JOptionPane.showMessageDialog(null, "No se ha encontrado el usuario", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Algún campo está vacío", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private synchronized void desconectar() {
		// Verifica si la conexión ya está inactiva
		if (!conexionActiva) {
			return; // Si la conexión ya está cerrada, no hacer nada
		}

		conexionActiva = false;
		System.out.println("Cerrando conexión...");

		try {
			// Enviar código de desconexión
			if (dos != null) {
				dos.writeInt(-1); // Enviar código de desconexión
				dos.flush();
			}
		} catch (IOException e) {
			System.err.println("Error enviando el código de desconexión: " + e.getMessage());
		}

		try {
			// Cerrar los streams y socket
			if (oos != null)
				oos.close();
			if (ois != null)
				ois.close();
			if (dos != null)
				dos.close();
			if (dis != null)
				dis.close();
			if (socketCliente != null && !socketCliente.isClosed())
				socketCliente.close();
			System.out.println("Conexión cerrada correctamente.");
		} catch (IOException e) {
			System.err.println("Error cerrando la conexión: " + e.getMessage());
		}

		// Reiniciar los objetos después de cerrar la conexión
		oos = null;
		ois = null;
		dos = null;
		dis = null;
		socketCliente = null;
	}

	private void mMostrarOtrosProfesores() {

	}

}
