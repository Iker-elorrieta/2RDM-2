package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import Modelo.Users;
import Vista.PanelLogin;
import Vista.VentanaPrincipal;
import Vista.VentanaPrincipal.enumAcciones;








public class Controlador  implements ActionListener{

	private Vista.VentanaPrincipal vistaPrincipal;
	private Users usuarioLogeado;
	
	public Controlador(VentanaPrincipal ventanaLogin) {
		// TODO Auto-generated constructor stub
		this.vistaPrincipal = ventanaLogin;
		this.vistaPrincipal.setVisible(true);
		this.inicializarControlador();
	}

	private void inicializarControlador() {

		// VENTANA REGISTRO

		this.vistaPrincipal.getPanelLogin().getBtnLogin().addActionListener(this);;
		this.vistaPrincipal.getPanelLogin().getBtnLogin()
				.setActionCommand(VentanaPrincipal.enumAcciones.LOGIN.toString());

	
	}
	
	public void actionPerformed(ActionEvent e) {

		VentanaPrincipal.enumAcciones accion = VentanaPrincipal.enumAcciones.valueOf(e.getActionCommand());

		switch (accion) {
		case LOGIN:
			this.mConfirmarLogin(accion);
			break;
		
		default:
			break;

		}
	}
	
	private void mConfirmarLogin(enumAcciones accion) {
		PanelLogin panelLogin = this.vistaPrincipal.getPanelLogin();
		String usuarioIntroducido = panelLogin.getTextFieldUser().getText().trim();
		String passIntroducida = new String(panelLogin.getTextFieldPass().getPassword()).trim();
		if (!usuarioIntroducido.isEmpty() && !passIntroducida.isEmpty()) {
			Users usuario = new Users();
			usuarioLogeado = usuario.mObtenerUsuario(usuarioIntroducido, passIntroducida);
			if (usuarioLogeado != null) { 
				
				//mCargarVentanas(PanelPrincipal.enumAcciones.CARGAR_PANEL_WORKOUT);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Algún campo está vacío", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}


	
}
