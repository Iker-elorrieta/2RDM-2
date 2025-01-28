package Modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;



public class Horarios implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private HorariosId id;
	private Users users;
	private Modulos modulos;

	public Horarios() {
	}

	public Horarios(HorariosId id, Users users, Modulos modulos) {
		this.id = id;
		this.users = users;
		this.modulos = modulos;
	}

	public HorariosId getId() {
		return this.id;
	}

	public void setId(HorariosId id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Modulos getModulos() {
		return this.modulos;
	}

	public void setModulos(Modulos modulos) {
		this.modulos = modulos;
	}
	
	
	
	
	
	
	public String[][] obtenerHorarioPorId(int userId) {
		String[][] horarioSemanal = { { "", "", "", "", "", "" }, { "", "", "", "", "", "" },
				{ "", "", "", "", "", "" }, { "", "", "", "", "", "" }, { "", "", "", "", "", "" }, { "", "", "", "", "", "" }};

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sesion = sf.openSession();
		String hql = "from Horarios where users = " + userId + " ";
		Query q = sesion.createQuery(hql);
		List<?> horarios = q.list();

		for (Object res : horarios) {
			Horarios horario = (Horarios) res;
			int dia = convertirDia(horario.getId().getDia());
			int hora = Integer.parseInt(horario.getId().getHora());
			horarioSemanal[hora - 1][dia] = horario.getModulos().getNombre();
		}
		return horarioSemanal;
	}
	
	private int convertirDia(String diaString) {
		int dia = 0;
		switch (diaString) {
		case "L/A":
			dia = 1;
			break;
		case "M/A":
			dia = 2;
			break;
		case "X":
			dia = 3;
			break;
		case "J/O":
			dia = 4;
			break;
		case "V/O":
			dia = 5;
			break;
		case "S/L":
			dia = 6;
			break;
		case "D/I":
			dia = 7;
			break;

		default:
			dia = 0;
			break;
		}
		return dia;
	}

}
