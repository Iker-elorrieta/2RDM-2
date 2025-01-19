package Vista;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class PanelOtrosHorarios extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnVolver;
	private JTable tablaHorario;
	private JComboBox comboBoxProfesores;
	/**
	 * Create the panel.
	 */
	public PanelOtrosHorarios() {
		setLayout(null);
		setBounds(100, 100, 707, 584);

		JLabel lblOtrosHorarios = new JLabel("OTROS HORARIOS");
		lblOtrosHorarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblOtrosHorarios.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblOtrosHorarios.setBounds(66, 28, 312, 59);
		add(lblOtrosHorarios);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVolver.setBounds(84, 441, 101, 25);
		add(btnVolver);
		
		comboBoxProfesores = new JComboBox();
		comboBoxProfesores.setBounds(118, 101, 158, 22);
		add(comboBoxProfesores);
		
		tablaHorario = new JTable();
		tablaHorario.setModel(generarModeloTabla());
		tablaHorario.setBounds(111, 174, 436, 200);
		add(tablaHorario);

	}

	

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
	}
	
	
	
	public JTable getTablaHorario() {
		return tablaHorario;
	}



	public void setTablaHorario(JTable tablaHorario) {
		this.tablaHorario = tablaHorario;
	}



	public JComboBox getComboBoxProfesores() {
		return comboBoxProfesores;
	}



	public void setComboBoxProfesores(JComboBox comboBoxProfesores) {
		this.comboBoxProfesores = comboBoxProfesores;
	}



	private DefaultTableModel generarModeloTabla() {
		DefaultTableModel modeloTabla = new DefaultTableModel(
				new String[][] {{"", "L", "M", "X", "J", "V" }, { "8:00-9:00", "", "", "", "", "" }, { "9:00-10:00", "", "", "", "", "" },
						{ "10:00-11:00", "", "", "", "", "" }, { "11:30-12:30", "", "", "", "", "" },
						{ "12:30-13:30", "", "", "", "", "" }, { "13:30-14:30", "", "", "", "", "" }, },
				new String[] { "Hora/Día", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes" });
		return modeloTabla;
	}
}
