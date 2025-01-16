package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
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


public class Controlador  implements ActionListener{

	private Vista.VentanaPrincipal vistaPrincipal;
	private Users usuarioLogeado;
	private Socket socketCliente;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public Controlador(VentanaPrincipal ventanaLogin) {
		// TODO Auto-generated constructor stub
		this.vistaPrincipal = ventanaLogin;
		this.vistaPrincipal.setVisible(true);
		this.inicializarControlador();
		
	}

	private void inicializarControlador() {
		
		try {
			socketCliente = new Socket("localhost", 2845);
			oos = new ObjectOutputStream(socketCliente.getOutputStream());
			ois = new ObjectInputStream(socketCliente.getInputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		// VENTANA LOGIN-------------------------------------------------------------------------------------------------
		this.vistaPrincipal.getPanelLogin().getBtnLogin().addActionListener(this);;
		this.vistaPrincipal.getPanelLogin().getBtnLogin()
				.setActionCommand(VentanaPrincipal.enumAcciones.LOGIN.toString());

		
		// VENTANA MENU----------------------------------------------------------------------------------------------------
		this.vistaPrincipal.getPanelMenu().getBtnHorario().addActionListener(this);;
		this.vistaPrincipal.getPanelMenu().getBtnHorario()
				.setActionCommand(VentanaPrincipal.enumAcciones.CARGAR_PANEL_HORARIO.toString());
		
		this.vistaPrincipal.getPanelMenu().getBtnOtrosHorarios().addActionListener(this);;
		this.vistaPrincipal.getPanelMenu().getBtnOtrosHorarios()
				.setActionCommand(VentanaPrincipal.enumAcciones.CARGAR_PANEL_OTROS_HORARIOS.toString());
		
		this.vistaPrincipal.getPanelMenu().getBtnReuniones().addActionListener(this);;
		this.vistaPrincipal.getPanelMenu().getBtnReuniones()
				.setActionCommand(VentanaPrincipal.enumAcciones.CARGAR_PANEL_REUNIONES.toString());
		
		this.vistaPrincipal.getPanelMenu().getBtnDesconectar().addActionListener(this);;
		this.vistaPrincipal.getPanelMenu().getBtnDesconectar()
				.setActionCommand(VentanaPrincipal.enumAcciones.DESCONECTAR.toString());
		
		//VENTANA HORARIO----------------------------------------------------------------------------------------------
		this.vistaPrincipal.getPanelHorario().getBtnVolver().addActionListener(this);;
		this.vistaPrincipal.getPanelHorario().getBtnVolver()
				.setActionCommand(VentanaPrincipal.enumAcciones.CARGAR_PANEL_MENU.toString());
	
	
		//VENTANA OTROS HORARIOS -----------------------------------------------------------------------------------------------
		
		
		//VENTANA REUNIONES---------------------------------------------------------------------------------------------------------
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
			break;
		case CARGAR_PANEL_REUNIONES:
			this.vistaPrincipal.mVisualizarPaneles(accion);
			break;
		case DESCONECTAR:
			this.vistaPrincipal.mVisualizarPaneles(enumAcciones.CARGAR_PANEL_LOGIN);
			desconectar();
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
			usuarioLogeado = usuario.mObtenerUsuario(usuarioIntroducido, passIntroducida);
			if (usuarioLogeado != null) { 
				System.out.println("Usuario correcto");
				this.vistaPrincipal.mVisualizarPaneles(VentanaPrincipal.enumAcciones.CARGAR_PANEL_MENU);
				this.vistaPrincipal.getPanelLogin().getTfUser().setText("");
				this.vistaPrincipal.getPanelLogin().getPfPass().setText("");
				//mCargarVentanas(PanelPrincipal.enumAcciones.CARGAR_PANEL_WORKOUT);
			} else {
				JOptionPane.showMessageDialog(null, "No se ha encontrado el usuario", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Algún campo está vacío", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	private void desconectar() {
		System.out.println("cambiar a login");
		
		try {
			oos.close();
			ois.close();
			socketCliente.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void mMostrarOtrosProfesores() {
		
	}


	
}
