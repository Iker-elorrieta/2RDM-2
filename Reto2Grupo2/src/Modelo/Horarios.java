package Modelo;

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
	
	
	
	public String[][] getHorarioById(int idUsuario) {
		// TODO Auto-generated method stub
		String[][] planSemanal = {
				{ "1ra", "", "", "", "", "", "", "" }, { "2da", "", "", "", "", "", "", "" },
				{ "3ra", "", "", "", "", "", "", "" }, { "4ta", "", "", "", "", "", "", "" },
				{ "5ta", "", "", "", "", "", "", "" } };

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "from Horarios where users = " + idUsuario + " ";
		Query q = session.createQuery(hql);
		List<?> filas = q.list();

		for (int i = 0; i < filas.size(); i++) {
			Horarios horario = (Horarios) filas.get(i);
			int dia = conseguirDia(horario.getId().getDia());
			int hora = Integer.parseInt(horario.getId().getHora());
			planSemanal[hora-1][dia] = horario.getModulos().getNombre();
		}

		return planSemanal;
	}
	
	private int conseguirDia(String string) {
		// TODO Auto-generated method stub
		int dia = 0;
		if (string.equals("L/A")) {
			dia = 1;
		} else if (string.equals("M/A")) {
			dia = 2;
		} else if (string.equals("X")) {
			dia = 3;
		} else if (string.equals("J/O")) {
			dia = 4;
		} else if (string.equals("V/O")) {
			dia = 5;
		} else if (string.equals("S/L")) {
			dia = 6;
		} else if (string.equals("D/I")) {
			dia = 7;
		}
		return dia;
	}

}
