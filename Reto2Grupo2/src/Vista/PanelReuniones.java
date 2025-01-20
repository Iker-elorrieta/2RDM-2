package Vista;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PanelReuniones extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tablaHorario;
	private JButton btnVolver;

	/**
	 * Create the panel.
	 */
	public PanelReuniones() {
		setLayout(null);
		setBounds(100, 100, 707, 584);

		tablaHorario = new JTable();
		tablaHorario.setModel(generarModeloTabla());
		tablaHorario.setBounds(67, 131, 436, 200);
		add(tablaHorario);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVolver.setBounds(67, 354, 101, 25);
		add(btnVolver);
		
		JLabel lblNewLabel = new JLabel("REUNIONES");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(67, 45, 424, 55);
		add(lblNewLabel);

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

	private DefaultTableModel generarModeloTabla() {
		DefaultTableModel modeloTabla = new DefaultTableModel(
				new String[][] { {"", "L", "M", "X", "J", "V" }, {"8:00-9:00", "", "", "", "", "" }, { "9:00-10:00", "", "", "", "", "" },
						{ "10:00-11:00", "", "", "", "", "" }, { "11:30-12:30", "", "", "", "", "" },
						{ "12:30-13:30", "", "", "", "", "" }, { "13:30-14:30", "", "", "", "", "" }, },
				new String[] { "Hora/Día", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes" });
		return modeloTabla;
	}
}
