package Vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;



import javax.swing.JButton;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PanelReuniones extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tablaHorario;
	private JButton btnVolver, btnReunionesPendientes;

	/**
	 * Create the panel.
	 */
	public PanelReuniones() {
		setLayout(null);
		setBounds(100, 100, 707, 584);

		// Crear la tabla con el modelo inicial
		tablaHorario = new JTable(generarModeloTabla());

		tablaHorario.setRowHeight(65); 

		JScrollPane scrollPane = new JScrollPane(tablaHorario);
		scrollPane.setBounds(10, 111, 641, 392);
		add(scrollPane);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVolver.setBounds(30, 514, 101, 25);
		add(btnVolver);

		JLabel lblNewLabel = new JLabel("REUNIONES");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(67, 45, 424, 55);
		add(lblNewLabel);

		btnReunionesPendientes = new JButton("Reuniones pendientes");
		btnReunionesPendientes.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReunionesPendientes.setBounds(258, 514, 203, 25);
		add(btnReunionesPendientes);

	}

	public JTable getTablaHorario() {
		return tablaHorario;
	}

	public void setTablaHorario(JTable tablaHorario) {
		this.tablaHorario = tablaHorario;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
	}

	public JButton getBtnReunionesPendientes() {
		return btnReunionesPendientes;
	}

	public void setBtnReunionesPendientes(JButton btnReunionesPendientes) {
		this.btnReunionesPendientes = btnReunionesPendientes;
	}

	private DefaultTableModel generarModeloTabla() {
		DefaultTableModel modeloTabla = new DefaultTableModel(
				new String[][] { { "8:00-9:00", "", "", "", "", "" }, { "9:00-10:00", "", "", "", "", "" },
						{ "10:00-11:00", "", "", "", "", "" }, { "11:30-12:30", "", "", "", "", "" },
						{ "12:30-13:30", "", "", "", "", "" }, { "13:30-14:30", "", "", "", "", "" }, },
				new String[] { "Hora/Día", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes" });
		return modeloTabla;
	}

	
	
}
