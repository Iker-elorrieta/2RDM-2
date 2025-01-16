package Vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class PanelOtrosHorarios extends JPanel {

	
	private static final long serialVersionUID = 1L;
		private JList<String> listProfesores;
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(84, 153, 499, 264);
		add(scrollPane);
		
		listProfesores = new JList<String>();
		listProfesores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listProfesores);
		
		

	}
	public JList<String> getListProfesores() {
		return listProfesores;
	}
	public void setListProfesores(JList<String> listProfesores) {
		this.listProfesores = listProfesores;
	}
}
