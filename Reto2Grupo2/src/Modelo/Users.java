package Modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Users implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Tipos tipos;
	private String email;
	private String username;
	private String password;
	private String nombre;
	private String apellidos;
	private String dni;
	private String direccion;
	private Integer telefono1;
	private Integer telefono2;
	private byte[] argazkia;
	@SuppressWarnings("rawtypes")
	private Set matriculacioneses = new HashSet(0);
	@SuppressWarnings("rawtypes")
	private Set reunionesesForProfesorId = new HashSet(0);
	@SuppressWarnings("rawtypes")
	private Set reunionesesForAlumnoId = new HashSet(0);
	@SuppressWarnings("rawtypes")
	private Set horarioses = new HashSet(0);

	public Users() {
	}

	public Users(int id, Tipos tipos) {
		this.id = id;
		this.tipos = tipos;
	}

	public Users(int id, Tipos tipos, String email, String username, String password, String nombre, String apellidos,
			String dni, String direccion, Integer telefono1, Integer telefono2, byte[] argazkia,
			@SuppressWarnings("rawtypes") Set matriculacioneses,
			@SuppressWarnings("rawtypes") Set reunionesesForProfesorId,
			@SuppressWarnings("rawtypes") Set reunionesesForAlumnoId, @SuppressWarnings("rawtypes") Set horarioses) {
		this.id = id;
		this.tipos = tipos;
		this.email = email;
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.direccion = direccion;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.argazkia = argazkia;
		this.matriculacioneses = matriculacioneses;
		this.reunionesesForProfesorId = reunionesesForProfesorId;
		this.reunionesesForAlumnoId = reunionesesForAlumnoId;
		this.horarioses = horarioses;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Tipos getTipos() {
		return this.tipos;
	}

	public void setTipos(Tipos tipos) {
		this.tipos = tipos;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getTelefono1() {
		return this.telefono1;
	}

	public void setTelefono1(Integer telefono1) {
		this.telefono1 = telefono1;
	}

	public Integer getTelefono2() {
		return this.telefono2;
	}

	public void setTelefono2(Integer telefono2) {
		this.telefono2 = telefono2;
	}

	public byte[] getArgazkia() {
		return this.argazkia;
	}

	public void setArgazkia(byte[] argazkia) {
		this.argazkia = argazkia;
	}

	@SuppressWarnings("rawtypes")
	public Set getMatriculacioneses() {
		return this.matriculacioneses;
	}

	public void setMatriculacioneses(@SuppressWarnings("rawtypes") Set matriculacioneses) {
		this.matriculacioneses = matriculacioneses;
	}

	@SuppressWarnings("rawtypes")
	public Set getReunionesesForProfesorId() {
		return this.reunionesesForProfesorId;
	}

	public void setReunionesesForProfesorId(@SuppressWarnings("rawtypes") Set reunionesesForProfesorId) {
		this.reunionesesForProfesorId = reunionesesForProfesorId;
	}

	@SuppressWarnings("rawtypes")
	public Set getReunionesesForAlumnoId() {
		return this.reunionesesForAlumnoId;
	}

	public void setReunionesesForAlumnoId(@SuppressWarnings("rawtypes") Set reunionesesForAlumnoId) {
		this.reunionesesForAlumnoId = reunionesesForAlumnoId;
	}

	@SuppressWarnings("rawtypes")
	public Set getHorarioses() {
		return this.horarioses;
	}

	public void setHorarioses(@SuppressWarnings("rawtypes") Set horarioses) {
		this.horarioses = horarioses;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", tipos=" + tipos + ", email=" + email + ", username=" + username + ", password="
				+ password + ", nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + ", direccion="
				+ direccion + ", telefono1=" + telefono1 + ", telefono2=" + telefono2 + ", argazkia="
				+ Arrays.toString(argazkia) + ", matriculacioneses=" + matriculacioneses + ", reunionesesForProfesorId="
				+ reunionesesForProfesorId + ", reunionesesForAlumnoId=" + reunionesesForAlumnoId + ", horarioses="
				+ horarioses + "]";
	}

	public Users mObtenerUsuario(String userIntroducido, String passIntroducida, String tipoUsuario) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sesion = sf.openSession();
		String hql = "from Users where username = :username AND password = :password AND tipos.name = :tipoUsuario";
		Query q = sesion.createQuery(hql);
		q.setParameter("username", userIntroducido);
		q.setParameter("password", passIntroducida);
		q.setParameter("tipoUsuario", tipoUsuario);
		Users user = (Users) q.uniqueResult();

		return user;

	}

	public Users mCrearUsuario() {
	    SessionFactory sf = HibernateUtil.getSessionFactory();
	    Session sesion = sf.openSession();
	    Users user = null;

	    try {
	        // Iniciar la transacción
	        sesion.beginTransaction();

	        // Crear un tipo de usuario
	        Tipos tipo = new Tipos();
	        tipo.setId(4);
	        tipo.setName("profesor");
	        tipo.setNameEus("irakaslea");

	        // Crear el usuario
	        user = new Users();
	        user.setId(11); // Si el ID es generado automáticamente, elimina esta línea
	        user.setEmail("aaa@a.com");
	        user.setUsername("a");
	        user.setPassword("1");
	        user.setNombre("a");
	        user.setApellidos("AAAA");
	        user.setDni("11111");
	        user.setDireccion("Calle32432");
	        user.setTelefono1(Integer.parseInt("666666666"));
	        user.setTelefono2(Integer.parseInt("22222222"));
	        user.setTipos(tipo);

	        // Guardar el usuario
	        sesion.save(user);

	        // Confirmar la transacción
	        sesion.getTransaction().commit();

	        System.out.println("Usuario creado y guardado correctamente.");

	    } catch (org.hibernate.exception.ConstraintViolationException e) {
	        if (sesion.getTransaction() != null) {
	            sesion.getTransaction().rollback(); // En caso de error, revertir los cambios
	        }
	        // Mostrar un cuadro de mensaje en lugar de imprimir en consola
	        JOptionPane.showMessageDialog(null, "El usuario ya está creado.", "Error", JOptionPane.ERROR_MESSAGE);

	    } catch (Exception e) {
	        if (sesion.getTransaction() != null) {
	            sesion.getTransaction().rollback(); // En caso de error genérico, revertir los cambios
	        }
	        System.err.println("Error al crear el usuario: " + e.getMessage());
	        e.printStackTrace();

	    } finally {
	        sesion.close(); // Asegurarse de cerrar la sesión
	    }
	    return user;
	}



	public List<Users> mObtenerProfesores(int userId) {
		List<Users> listaProfesores = new ArrayList<Users>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sesion = sf.openSession();

		String hqlNombre = "from Users where id != " + userId + " AND tipos.name = 'profesor'";
		Query q = sesion.createQuery(hqlNombre);
		List<?> filas = q.list();

		for (Object res : filas) {
			Users user = (Users) res;

			listaProfesores.add(user);
		}
		return listaProfesores;
	}

	

}