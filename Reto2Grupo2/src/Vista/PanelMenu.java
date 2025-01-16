package Vista;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;

public class PanelMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnDesconectar, btnHorario, btnOtrosHorarios, btnReuniones;

	/**
	 * Create the panel.
	 */
	public PanelMenu() {
		setLayout(null);

		btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDesconectar.setBounds(26, 319, 133, 30);
		add(btnDesconectar);

		btnHorario = new JButton("Consultar horario");
		btnHorario.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnHorario.setBounds(148, 100, 249, 30);
		add(btnHorario);

		btnOtrosHorarios = new JButton("Consultar otros horarios");
		btnOtrosHorarios.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnOtrosHorarios.setBounds(148, 153, 249, 30);
		add(btnOtrosHorarios);

		btnReuniones = new JButton("Ver reuniones");
		btnReuniones.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReuniones.setBounds(148, 209, 249, 30);
		add(btnReuniones);

	}

	public JButton getBtnDesconectar() {
		return btnDesconectar;
	}

	public JButton getBtnHorario() {
		return btnHorario;
	}

	public JButton getBtnOtrosHorarios() {
		return btnOtrosHorarios;
	}

	public JButton getBtnReuniones() {
		return btnReuniones;
	}

}
