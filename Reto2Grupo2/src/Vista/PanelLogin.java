package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PanelLogin extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldUser;
	private JPasswordField pfPass;
	private JButton btnRegistrarse;
	private JButton btnLogin;
	JLabel lblNewLabel;

	/**
	 * Create the panel.
	 */
	public PanelLogin() {
		setBackground(new Color(230, 230, 250));
		setBounds(288, 11, 688, 541);
		setLayout(null);

		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Arial", Font.BOLD, 28));
		lblLogin.setBounds(263, 50, 200, 50);
		add(lblLogin);

		JLabel lblNewLabel = new JLabel("LOGO");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(300, 100, 144, 89);
		add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(new ImageIcon("Multimedia/Logo.png").getImage()
				.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH)));

		JLabel lblUser = new JLabel("Usuario:");
		lblUser.setFont(new Font("Arial", Font.PLAIN, 16));
		lblUser.setBounds(176, 203, 80, 20);
		add(lblUser);

		textFieldUser = new JTextField();
		textFieldUser.setBounds(280, 200, 200, 30);
		textFieldUser.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(textFieldUser);

		JLabel lblPass = new JLabel("Contraseña:");
		lblPass.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPass.setBounds(176, 253, 100, 20);
		add(lblPass);

		pfPass = new JPasswordField();
		pfPass.setBounds(280, 250, 200, 30);
		pfPass.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(pfPass);

		btnLogin = new JButton("Aceptar");
		btnLogin.setBounds(220, 310, 100, 40);
		btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
		btnLogin.setBackground(new Color(100, 149, 237));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBorderPainted(false);
		btnLogin.setFocusPainted(false);
		add(btnLogin);

		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBounds(350, 310, 120, 40);
		btnRegistrarse.setFont(new Font("Arial", Font.BOLD, 14));
		btnRegistrarse.setBackground(new Color(255, 105, 180));
		btnRegistrarse.setForeground(Color.WHITE);
		btnRegistrarse.setBorderPainted(false);
		btnRegistrarse.setFocusPainted(false);
		add(btnRegistrarse);
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}

	public JButton getBtnRegistrarse() {
		return btnRegistrarse;
	}

	public void setBtnRegistrarse(JButton btnRegistrarse) {
		this.btnRegistrarse = btnRegistrarse;
	}

	public JTextField getTextFieldUser() {
		return textFieldUser;
	}

	public void setTextFieldUser(JTextField textFieldUser) {
		this.textFieldUser = textFieldUser;
	}

	public JPasswordField getTextFieldPass() {
		return pfPass;
	}

	public void setTextFieldPass(JPasswordField pfPass) {
		this.pfPass = pfPass;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}

}
