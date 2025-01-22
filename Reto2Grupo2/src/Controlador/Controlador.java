package Controlador;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Modelo.Reuniones;
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
	private boolean loginCorrecto;

	public Controlador(VentanaPrincipal ventanaLogin) {
		this.vistaPrincipal = ventanaLogin;
		this.vistaPrincipal.setVisible(true);
		this.inicializarControlador();
	}

	private void inicializarControlador() {

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
					cerrarVentana();
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
			this.mMostrarHorarios();

			break;
		case CARGAR_PANEL_OTROS_HORARIOS:
			this.vistaPrincipal.mVisualizarPaneles(accion);
			mCargarProfesores();
			break;
		case CARGAR_PANEL_REUNIONES:
			this.vistaPrincipal.mVisualizarPaneles(accion);
			mMostrarReuniones();
			break;
		case DESCONECTAR:
			this.vistaPrincipal.mVisualizarPaneles(enumAcciones.CARGAR_PANEL_LOGIN);
			desconectar();
			break;
		case CREAR_USUARIO:
			mCrearUsuario();
			break;
		default:
			break;

		}
	}

	private void mMostrarReuniones() {

		try {
			
			DefaultTableModel modelo = (DefaultTableModel) this.vistaPrincipal.getPanelReuniones().getTablaHorario()
					.getModel();
			
			String[][] horarioReuniones = { { "Hora1", "", "", "", "", "" }, { "Hora2", "", "", "", "", "" },
					{ "Hora3", "", "", "", "", "" }, { "Hora4", "", "", "", "", "" }, { "Hora5", "", "", "", "", "" } };
			
			dos.writeInt(6);
			dos.flush();

			Thread.sleep(500);

			List<Reuniones> reuniones =  (List<Reuniones>) ois.readObject(); // Leer el objeto
			
			for (Reuniones reunion : reuniones) {
			    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
			    LocalDateTime fechaHora = LocalDateTime.parse(reunion.getFecha().toString(), formatter);

			    int dia = fechaHora.getDayOfWeek().getValue();
			    int hora = fechaHora.getHour();

			    // Ajuste de hora para índice de la tabla
			    switch (hora) {
			        case 8: hora = 1; break;
			        case 9: hora = 2; break;
			        case 10: hora = 3; break;
			        case 11: hora = 4; break;
			        case 12: hora = 5; break;
			    }

			    if (hora >= 1 && hora <= 5 && dia >= 1 && dia <= 5) {
			        String tituloConEstado = reunion.getTitulo() + "|" + reunion.getEstado();
			        horarioReuniones[hora - 1][dia] = tituloConEstado;
			    }
			}

			// Actualizar la tabla con los datos del horario
			for (int i = 0; i < horarioReuniones.length; i++) {
			    for (int j = 1; j < horarioReuniones[i].length; j++) { // Ignorar la columna de las horas
			        modelo.setValueAt(horarioReuniones[i][j], i + 1, j);
			    }
			}

			// Renderizador de colores para las celdas según estado
			DefaultTableCellRenderer renderizador = new DefaultTableCellRenderer() {
			    @Override
			    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			                                                   boolean hasFocus, int row, int column) {
			        Component componente = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

			        if (column > 0 && value != null) { // Ignorar la columna de las horas
			            String texto = value.toString();
			            if (texto.contains("|")) {
			                String[] partes = texto.split("\\|");
			                String estado = partes[1];

			                if ("pendiente".equals(estado)) {
			                    componente.setBackground(Color.GRAY); // Cambiar a gris para "pendiente"
			                    componente.setForeground(Color.BLACK);
			                } else if ("finalizada".equals(estado)) {
			                    componente.setBackground(Color.GREEN);
			                    componente.setForeground(Color.BLACK);
			                } else {
			                    componente.setBackground(Color.WHITE);
			                    componente.setForeground(Color.BLACK);
			                }

			                // Mostrar solo el título, sin el estado
			                setText(partes[0]);
			            } else {
			                componente.setBackground(Color.WHITE);
			                componente.setForeground(Color.BLACK);
			            }
			        } else {
			            componente.setBackground(Color.WHITE);
			            componente.setForeground(Color.BLACK);
			        }

			        if (isSelected) {
			            componente.setBackground(Color.BLUE);
			            componente.setForeground(Color.WHITE);
			        }

			        return componente;
			    }
			};

			this.vistaPrincipal.getPanelReuniones().getTablaHorario().setDefaultRenderer(Object.class, renderizador);
			
			
			
			

			
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void desconectar() {

		usuarioLogeado = null;

		this.vistaPrincipal.getPanelLogin().getTfUser().setText("");
		this.vistaPrincipal.getPanelLogin().getPfPass().setText("");
	}

	private void mCrearUsuario() {

		try {

			dos.writeInt(5);
			dos.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void mCargarProfesores() {

		try {

			dos.writeInt(3);
			dos.flush();

			List<Users> listaProfesores = (List<Users>) ois.readObject();

			for (Users profesor : listaProfesores) {

				this.vistaPrincipal.getPanelOtrosHorarios().getComboBoxProfesores().addItem(profesor.getNombre());
			}

			this.vistaPrincipal.getPanelOtrosHorarios().getComboBoxProfesores().addActionListener(e -> {

				String selectedProfesor = (String) this.vistaPrincipal.getPanelOtrosHorarios().getComboBoxProfesores()
						.getSelectedItem();

				for (Users users : listaProfesores) {
					if (users.getNombre() == selectedProfesor) {
						try {

							dos.writeInt(4);
							dos.flush();

							dos.writeInt(users.getId());
							dos.flush();

							String[][] horarioUser = (String[][]) ois.readObject();
							System.out.println("Horario recibido: " + Arrays.deepToString(horarioUser));

							DefaultTableModel modelo = (DefaultTableModel) this.vistaPrincipal.getPanelOtrosHorarios()
									.getTablaHorario().getModel();

							for (int i = 0; i < horarioUser.length; i++) {
								for (int j = 1; j < horarioUser[i].length; j++) {
									modelo.setValueAt(horarioUser[i][j], i + 1, j);
								}
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

			});

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void mMostrarHorarios() {
		try {
			dos.writeInt(2);
			dos.flush();

			Thread.sleep(500);

			String[][] horarioUser = (String[][]) ois.readObject(); // Leer el objeto
			System.out.println("Horario recibido: " + Arrays.deepToString(horarioUser));

			// Actualizar la tabla con los datos del horario
			DefaultTableModel modelo = (DefaultTableModel) this.vistaPrincipal.getPanelHorario().getTablaHorario()
					.getModel();

			for (int i = 0; i < horarioUser.length; i++) {
				for (int j = 1; j < horarioUser[i].length; j++) { // Ignorar la columna de las horas
					modelo.setValueAt(horarioUser[i][j], i + 1, j);
				}
			}
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void mConfirmarLogin(enumAcciones accion) {
		// TODO Auto-generated method stub
		try {
			dos.writeInt(1);
			dos.flush();
			dos.writeUTF(this.vistaPrincipal.getPanelLogin().getTfUser().getText());
			dos.flush();
			dos.writeUTF(new String(this.vistaPrincipal.getPanelLogin().getPfPass().getPassword()));
			dos.flush();
			dos.writeUTF(TIPO_USUARIO);
			dos.flush();
			loginCorrecto = dis.readBoolean();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (loginCorrecto) {

			this.vistaPrincipal.mVisualizarPaneles(enumAcciones.CARGAR_PANEL_MENU);
			conexionActiva = true;

		} else {
			JOptionPane.showMessageDialog(null, "Login incorrecto");
		}
	}

	private synchronized void cerrarVentana() {
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

}
