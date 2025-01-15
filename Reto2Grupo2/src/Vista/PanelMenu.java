package Vista;

import javax.swing.JPanel;
import javax.swing.JButton;

public class PanelMenu extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelMenu() {
		setLayout(null);
		
		JButton btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setBounds(26, 328, 109, 21);
		add(btnDesconectar);

	}

}
