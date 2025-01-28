package Vista;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
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
		btnVolver.setBounds(10, 507, 101, 25);
		add(btnVolver);

		comboBoxProfesores = new JComboBox();
		comboBoxProfesores.setBounds(118, 101, 158, 22);

		add(comboBoxProfesores);

		// Crear la tabla con el modelo inicial
		tablaHorario = new JTable(generarModeloTabla()) {
			// Usar un JTextArea como renderizador para las celdas
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (c instanceof JTextArea) {
					((JTextArea) c).setLineWrap(true); // Habilitar salto de línea
					((JTextArea) c).setWrapStyleWord(true); // Ajustar por palabras
				}
				return c;
			}
		};

		// Configuración básica de la tabla
		tablaHorario.setRowHeight(65); // Ajusta la altura de las filas (más espacio para texto en varias líneas)

		// Configuración del renderizador para todas las columnas
		tablaHorario.setDefaultRenderer(Object.class, new TextAreaRenderer());

		// Envolver la tabla en un JScrollPane
		JScrollPane scrollPane = new JScrollPane(tablaHorario);
		scrollPane.setBounds(10, 154, 637, 320);
		add(scrollPane);

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
				new String[][] { { "8:00-9:00", "", "", "", "", "" }, { "9:00-10:00", "", "", "", "", "" },
						{ "10:00-11:00", "", "", "", "", "" }, { "11:30-12:30", "", "", "", "", "" },
						{ "12:30-13:30", "", "", "", "", "" }, { "13:30-14:30", "", "", "", "", "" }, },
				new String[] { "Hora/Día", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes" });
		return modeloTabla;
	}

	// Renderizador personalizado para usar JTextArea en las celdas
	private static class TextAreaRenderer extends JTextArea implements TableCellRenderer {

		private static final long serialVersionUID = 1L;

		public TextAreaRenderer() {
			setLineWrap(true); // Habilitar salto de línea
			setWrapStyleWord(true); // Ajustar por palabras
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			setText(value != null ? value.toString() : ""); // Asignar el valor a la celda
			return this;
		}
	}
}
