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

import Modelo.Users;
import Vista.PanelLogin;
import Vista.VentanaPrincipal;
import Vista.VentanaPrincipal.enumAcciones;

public class Controlador implements ActionListener {

	private Vista.VentanaPrincipal vistaPrincipal;
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

		this.vistaPrincipal.getPanelReuniones().getBtnReunionesPendientes().addActionListener(this);
		;
		this.vistaPrincipal.getPanelReuniones().getBtnReunionesPendientes()
				.setActionCommand(VentanaPrincipal.enumAcciones.CARGAR_PANEL_REUNIONES_PENDIENTES.toString());

		// VENTANA
		// REUNIONES
		// PENDIENTES---------------------------------------------------------------------------------------------------------

		this.vistaPrincipal.getPanelReunionesPendientes().getBtnVolver().addActionListener(this);
		;
		this.vistaPrincipal.getPanelReunionesPendientes().getBtnVolver()
				.setActionCommand(VentanaPrincipal.enumAcciones.CARGAR_PANEL_MENU.toString());

		this.vistaPrincipal.getPanelReunionesPendientes().getBtnAceptar().addActionListener(this);
		;
		this.vistaPrincipal.getPanelReunionesPendientes().getBtnAceptar()
				.setActionCommand(VentanaPrincipal.enumAcciones.ACEPTAR_ESTADO_REUNION.toString());

		this.vistaPrincipal.getPanelReunionesPendientes().getBtnRechazar().addActionListener(this);
		;
		this.vistaPrincipal.getPanelReunionesPendientes().getBtnRechazar()
				.setActionCommand(VentanaPrincipal.enumAcciones.DENEGAR_ESTADO_REUNION.toString());
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
		case CARGAR_PANEL_REUNIONES_PENDIENTES:
			this.vistaPrincipal.mVisualizarPaneles(accion);
			mMostrarReunionesPendientes();
			break;
		case DESCONECTAR:
			this.vistaPrincipal.mVisualizarPaneles(enumAcciones.CARGAR_PANEL_LOGIN);
			desconectar();
			break;
		case CREAR_USUARIO:
			mCrearUsuario();
			break;
		case ACEPTAR_ESTADO_REUNION:
			mAceptarEstado();
			mMostrarReunionesPendientes();
			break;
		case DENEGAR_ESTADO_REUNION:
			mDenegarEstado();
			mMostrarReunionesPendientes();
		default:
			break;

		}
	}
	


	private void mAceptarEstado() {

		try {
			int column = 1;
			int row = this.vistaPrincipal.getPanelReunionesPendientes().getTable().getSelectedRow();

			if (row != -1) {

				int idReunion = (int) this.vistaPrincipal.getPanelReunionesPendientes().getTable().getModel()
						.getValueAt(row, column);

				System.out.println(idReunion);

				dos.writeInt(8);
				dos.flush();

				dos.writeUTF("aceptada");

				dos.writeInt(idReunion);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void mDenegarEstado() {

		try {
			int column = 1;
			int row = this.vistaPrincipal.getPanelReunionesPendientes().getTable().getSelectedRow();

			if (row != -1) {

				int idReunion = (int) this.vistaPrincipal.getPanelReunionesPendientes().getTable().getModel()
						.getValueAt(row, column);

				System.out.println(idReunion);

				dos.writeInt(8);
				dos.flush();

				dos.writeUTF("denegada");

				dos.writeInt(idReunion);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void mMostrarReunionesPendientes() {

		try {

			dos.writeInt(7);
			dos.flush();

			DefaultTableModel modelo = (DefaultTableModel) this.vistaPrincipal.getPanelReunionesPendientes().getTable()
					.getModel();

			modelo.setRowCount(0);

			List<Object[]> listaReunionesPendientes = (List<Object[]>) ois.readObject();

			for (Object[] reunion : listaReunionesPendientes) {

				modelo.addRow(reunion);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void mMostrarReuniones() {
	    try {
	        DefaultTableModel modelo = (DefaultTableModel) this.vistaPrincipal.getPanelReuniones().getTablaHorario()
	                .getModel();

	        // Solicitar datos de reuniones
	        dos.writeInt(6);
	        dos.flush();

	        Thread.sleep(500);

	        String[][] reuniones = (String[][]) ois.readObject(); // Leer las reuniones

	        // Solicitar datos del horario del usuario
	        dos.writeInt(2);
	        dos.flush();

	        Thread.sleep(500);

	        String[][] horarioUser = (String[][]) ois.readObject(); // Leer el horario

	        // Actualizar la tabla con los datos combinados de reuniones y horarioUser
	        for (int i = 0; i < reuniones.length; i++) {
	            for (int j = 1; j < reuniones[i].length; j++) { // Ignorar la columna de las horas

	                String celdaReuniones = reuniones[i][j];
	                String celdaHorario = horarioUser[i][j];
	                String contenidoFinal = "";

	                boolean tieneReunion = !celdaReuniones.isEmpty();
	                boolean tieneAsignatura = !celdaHorario.isEmpty();

	                if (tieneReunion && tieneAsignatura) {
	                    // Si hay reunión y asignatura, separarlas con un salto de línea
	                    contenidoFinal = celdaReuniones + "\n" + celdaHorario;

	                    // Si la reunión no tiene estado, marcarla como conflicto
	                    if (!celdaReuniones.contains("|")) {
	                        reuniones[i][j] += "|conflicto";
	                    }
	                } else if (tieneReunion) {
	                    contenidoFinal = celdaReuniones;
	                } else if (tieneAsignatura) {
	                    contenidoFinal = celdaHorario;
	                }

	                // Actualizar el modelo de la tabla
	                modelo.setValueAt(contenidoFinal, i, j);
	            }
	        }

	        // Renderizador de colores para las celdas según estado (solo reuniones)
	        DefaultTableCellRenderer renderizador = new DefaultTableCellRenderer() {
	            @Override
	            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	                    boolean hasFocus, int row, int column) {
	                Component componente = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
	                        column);

	                if (column > 0 && value != null) { // Ignorar la columna de las horas
	                    String texto = value.toString();
	                    if (texto.contains("|")) {
	                        String[] partes = texto.split("\\|");
	                        String estado = partes[1];

	                        // Configurar colores según el estado de reuniones
	                        switch (estado) {
	                            case "pendiente":
	                                componente.setBackground(Color.YELLOW);
	                                componente.setForeground(Color.BLACK);
	                                break;
	                            case "aceptada":
	                                componente.setBackground(Color.GREEN);
	                                componente.setForeground(Color.BLACK);
	                                break;
	                            case "denegada":
	                                componente.setBackground(Color.RED);
	                                componente.setForeground(Color.BLACK);
	                                break;
	                            case "conflicto":
	                                componente.setBackground(Color.GRAY);
	                                componente.setForeground(Color.BLACK);
	                                break;
	                            default:
	                                componente.setBackground(Color.WHITE);
	                                componente.setForeground(Color.BLACK);
	                                break;
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

	        // Asignar el renderizador ANTES de modificar el modelo
	        this.vistaPrincipal.getPanelReuniones().getTablaHorario().setDefaultRenderer(Object.class, renderizador);

	    } catch (IOException | ClassNotFoundException | InterruptedException e) {
	        e.printStackTrace();
	    }
	}




	private void desconectar() {

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

			this.vistaPrincipal.getPanelOtrosHorarios().getComboBoxProfesores().removeAllItems();

			dos.writeInt(3);
			dos.flush();

			List<String> listaProfesores = (List<String>) ois.readObject();

			for (String profesor : listaProfesores) {

				this.vistaPrincipal.getPanelOtrosHorarios().getComboBoxProfesores().addItem(profesor);
			}

			this.vistaPrincipal.getPanelOtrosHorarios().getComboBoxProfesores().addActionListener(e -> {

				String selectedProfesor = (String) this.vistaPrincipal.getPanelOtrosHorarios().getComboBoxProfesores()
						.getSelectedItem();

				for (String profesor : listaProfesores) {
					if (profesor == selectedProfesor) {
						try {

							dos.writeInt(4);
							dos.flush();

							dos.writeUTF(profesor);
							dos.flush();

							String[][] horarioUser = (String[][]) ois.readObject();
							System.out.println("Horario recibido: " + Arrays.deepToString(horarioUser));

							DefaultTableModel modelo = (DefaultTableModel) this.vistaPrincipal.getPanelOtrosHorarios()
									.getTablaHorario().getModel();

							for (int i = 0; i < horarioUser.length; i++) {
								for (int j = 1; j < horarioUser[i].length; j++) {
									modelo.setValueAt(horarioUser[i][j], i, j);
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
					modelo.setValueAt(horarioUser[i][j], i, j);
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

			int id = dis.readInt();
			
			String tipoUsuario = dis.readUTF();
			
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
