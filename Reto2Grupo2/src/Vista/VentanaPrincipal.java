package Vista;

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
	private PanelHorario panelHorario;
	private PanelOtrosHorarios panelOtrosHorarios;
	private PanelReuniones panelReuniones;
	private PanelReunionesPendientes panelReunionesPendientes;

	// Acciones
	public static enum enumAcciones {
		CARGAR_PANEL_LOGIN, LOGIN, 
		CARGAR_PANEL_MENU, 
		CARGAR_PANEL_HORARIO, 
		CARGAR_PANEL_OTROS_HORARIOS,
		CARGAR_PANEL_REUNIONES,
		CARGAR_PANEL_REUNIONES_PENDIENTES,
		DESCONECTAR, CREAR_USUARIO, ACEPTAR_ESTADO_REUNION, DENEGAR_ESTADO_REUNION

	}

	public VentanaPrincipal() {

		mCrearPanelContenedor();

		// Panel que contiene todo el contenido de la ventana
		mCrearPanelLogin();
		mCrearPanelMenu();
		mCrearPanelHorario();
		mCrearPanelOtrosHorarios();
		mCrearPanelReuniones();
		mCrearPanelReunionesPendientes();
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

	private void mCrearPanelMenu() {
		panelMenu = new PanelMenu();
		panelMenu.setLocation(0, 11);
		panelContenedor.add(panelMenu);
		panelContenedor.setBounds(panelMenu.getBounds());
		panelMenu.setVisible(false);

	}

	private void mCrearPanelHorario() {
		panelHorario = new PanelHorario();
		panelHorario.setLocation(0, 11);
		panelContenedor.add(panelHorario);
		panelContenedor.setBounds(panelHorario.getBounds());
		panelHorario.setVisible(false);

	}

	private void mCrearPanelOtrosHorarios() {
		panelOtrosHorarios = new PanelOtrosHorarios();
		panelOtrosHorarios.setLocation(0, 11);
		panelContenedor.add(panelOtrosHorarios);
		panelContenedor.setBounds(panelOtrosHorarios.getBounds());
		panelOtrosHorarios.setVisible(false);

	}
	
	private void mCrearPanelReuniones() {
		panelReuniones = new PanelReuniones();
		panelReuniones.setLocation(0, 11);
		panelContenedor.add(panelReuniones);
		panelContenedor.setBounds(panelReuniones.getBounds());
		panelReuniones.setVisible(false);

	}
	
	private void mCrearPanelReunionesPendientes() {
		panelReunionesPendientes = new PanelReunionesPendientes();
		panelReunionesPendientes.setLocation(0, 11);
		panelContenedor.add(panelReunionesPendientes);
		panelContenedor.setBounds(panelReunionesPendientes.getBounds());
		panelReunionesPendientes.setVisible(false);

	}

	// *** FIN creaci�n de paneles ***

	public void mVisualizarPaneles(enumAcciones panel) {

		panelLogin.setVisible(false);
		panelMenu.setVisible(false);
		panelHorario.setVisible(false);
		panelOtrosHorarios.setVisible(false);
		panelReuniones.setVisible(false);
		panelReunionesPendientes.setVisible(false);

		switch (panel) {
		case CARGAR_PANEL_LOGIN:
			panelLogin.setVisible(true);
			break;
		case CARGAR_PANEL_MENU:
			panelMenu.setVisible(true);
			break;
		case CARGAR_PANEL_HORARIO:
			panelHorario.setVisible(true);
			
			break;
		case CARGAR_PANEL_OTROS_HORARIOS:
			panelOtrosHorarios.setVisible(true);
			break;
		case CARGAR_PANEL_REUNIONES:
			panelReuniones.setVisible(true);
			break;
		case CARGAR_PANEL_REUNIONES_PENDIENTES:
			panelReunionesPendientes.setVisible(true);
			break;
		default:
			break;
			
		}
		panelContenedor.revalidate();
	    panelContenedor.repaint();
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

	public JPanel getPanelContenedor() {
		return panelContenedor;
	}

	public void setPanelContenedor(JPanel panelContenedor) {
		this.panelContenedor = panelContenedor;
	}

	public PanelHorario getPanelHorario() {
		return panelHorario;
	}

	public void setPanelHorario(PanelHorario panelHorario) {
		this.panelHorario = panelHorario;
	}

	public PanelOtrosHorarios getPanelOtrosHorarios() {
		return panelOtrosHorarios;
	}

	public void setPanelOtrosHorarios(PanelOtrosHorarios panelOtrosHorarios) {
		this.panelOtrosHorarios = panelOtrosHorarios;
	}

	public PanelReuniones getPanelReuniones() {
		return panelReuniones;
	}

	public void setPanelReuniones(PanelReuniones panelReuniones) {
		this.panelReuniones = panelReuniones;
	}

	public PanelReunionesPendientes getPanelReunionesPendientes() {
		return panelReunionesPendientes;
	}

	public void setPanelReunionesPendientes(PanelReunionesPendientes panelReunionesPendientes) {
		this.panelReunionesPendientes = panelReunionesPendientes;
	}
	
	
	
	

}
