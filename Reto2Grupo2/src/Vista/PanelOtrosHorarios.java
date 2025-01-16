package Vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PanelOtrosHorarios extends JPanel {

	private static final long serialVersionUID = 1L;

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

	}

}
