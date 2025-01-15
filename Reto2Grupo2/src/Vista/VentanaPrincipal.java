package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;







public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private JPanel panelContenedor;
	private PanelLogin panelLogin;
	private PanelMenu panelMenu;

	// Acciones
	public static enum enumAcciones {
		CARGAR_PANEL_LOGIN, LOGIN

	}

	
	
	

	public VentanaPrincipal() {
		
		mCrearPanelContenedor();

		// Panel que contiene todo el contenido de la ventana
		mCrearPanelLogin();

		
	}

	// *** Creaci�n de paneles ***
	
	private void mCrearPanelContenedor() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 707, 584);
		panelContenedor = new JPanel();
		panelContenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelContenedor);
		panelContenedor.setLayout(null);

	}

	private void mCrearPanelLogin() {

		panelLogin = new PanelLogin();
		panelLogin.setLocation(0, 11);
		panelContenedor.add(panelLogin);
		panelContenedor.setBounds(panelLogin.getBounds());
		panelLogin.setVisible(true);

	}

	
	// *** FIN creaci�n de paneles ***

	public void mVisualizarPaneles(enumAcciones panel) {

		panelLogin.setVisible(false);
		

		switch (panel) {
		case CARGAR_PANEL_LOGIN:
			panelLogin.setVisible(true);
			break;
		
		default:
			break;

		}
	}

	public PanelLogin getPanelLogin() {
		return panelLogin;
	}

	public void setPanelLogin(PanelLogin panelLogin) {
		this.panelLogin = panelLogin;
	}

	public PanelMenu getPanelMenu() {
		return panelMenu;
	}

	public void setPanelMenu(PanelMenu panelMenu) {
		this.panelMenu = panelMenu;
	}
	
	
	
	
	

	

}
