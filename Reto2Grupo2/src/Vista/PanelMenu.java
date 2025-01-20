package Vista;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PanelMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnDesconectar, btnHorario, btnOtrosHorarios, btnReuniones;
	private JLabel lblMenu;

	/**
	 * Create the panel.
	 */
	public PanelMenu() {
		setLayout(null);
		setBounds(100, 100, 707, 584);


		btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDesconectar.setBounds(26, 319, 133, 30);
		add(btnDesconectar);

		btnHorario = new JButton("Consultar horario");
		btnHorario.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnHorario.setBounds(93, 87, 249, 30);
		add(btnHorario);

		btnOtrosHorarios = new JButton("Consultar otros horarios");
		btnOtrosHorarios.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnOtrosHorarios.setBounds(93, 139, 249, 30);
		add(btnOtrosHorarios);

		btnReuniones = new JButton("Ver reuniones");
		btnReuniones.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReuniones.setBounds(93, 180, 249, 30);
		add(btnReuniones);
		
		lblMenu = new JLabel("MENÚ");
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblMenu.setBounds(95, 24, 372, 50);
		add(lblMenu);

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
