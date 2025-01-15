package Principal;

public class Principal {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		iniciarAplicacion();

	}

	public static void iniciarAplicacion() {
		// TODO Auto-generated method stub
		try {

			Vista.VentanaPrincipal ventanaPrincipal = new Vista.VentanaPrincipal();
			ventanaPrincipal.setVisible(true);

			new Controlador.Controlador(ventanaPrincipal);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
