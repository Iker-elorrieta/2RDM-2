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
	private JTextField tfUser;
	private JPasswordField pfPass;
	private JButton btnLogin, btnCrearUser;
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

		tfUser = new JTextField();
		tfUser.setBounds(280, 200, 200, 30);
		tfUser.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(tfUser);

		JLabel lblPass = new JLabel("Contraseña:");
		lblPass.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPass.setBounds(176, 253, 100, 20);
		add(lblPass);

		pfPass = new JPasswordField();
		pfPass.setBounds(280, 250, 200, 30);
		pfPass.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(pfPass);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(220, 310, 243, 40);
		btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
		btnLogin.setBackground(new Color(100, 149, 237));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBorderPainted(false);
		btnLogin.setFocusPainted(false);
		add(btnLogin);
		
		btnCrearUser = new JButton("Crear Usuario");
		btnCrearUser.setBounds(31, 50, 144, 23);
		add(btnCrearUser);
	}

	public JTextField getTfUser() {
		return tfUser;
	}

	public void setTfUser(JTextField textFieldUser) {
		this.tfUser = textFieldUser;
	}

	public JPasswordField getPfPass() {
		return pfPass;
	}

	public void setPfPass(JPasswordField pfPass) {
		this.pfPass = pfPass;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}

	public JButton getBtnCrearUser() {
		return btnCrearUser;
	}

	public void setBtnCrearUser(JButton btnCrearUser) {
		this.btnCrearUser = btnCrearUser;
	}
	
	
}
