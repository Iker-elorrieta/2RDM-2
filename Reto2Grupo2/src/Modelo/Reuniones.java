package Modelo;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Reuniones implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idReunion;
	private Users usersByProfesorId;
	private Users usersByAlumnoId;
	private String estado;
	private String estadoEus;
	private String idCentro;
	private String titulo;
	private String asunto;
	private String aula;
	private Timestamp fecha;

	public Reuniones() {
	}

	public Reuniones(Users usersByProfesorId, Users usersByAlumnoId) {
		this.usersByProfesorId = usersByProfesorId;
		this.usersByAlumnoId = usersByAlumnoId;
	}

	public Reuniones(Users usersByProfesorId, Users usersByAlumnoId, String estado, String estadoEus, String idCentro,
			String titulo, String asunto, String aula, Timestamp fecha) {
		this.usersByProfesorId = usersByProfesorId;
		this.usersByAlumnoId = usersByAlumnoId;
		this.estado = estado;
		this.estadoEus = estadoEus;
		this.idCentro = idCentro;
		this.titulo = titulo;
		this.asunto = asunto;
		this.aula = aula;
		this.fecha = fecha;
	}

	public Integer getIdReunion() {
		return this.idReunion;
	}

	public void setIdReunion(Integer idReunion) {
		this.idReunion = idReunion;
	}

	public Users getUsersByProfesorId() {
		return this.usersByProfesorId;
	}

	public void setUsersByProfesorId(Users usersByProfesorId) {
		this.usersByProfesorId = usersByProfesorId;
	}

	public Users getUsersByAlumnoId() {
		return this.usersByAlumnoId;
	}

	public void setUsersByAlumnoId(Users usersByAlumnoId) {
		this.usersByAlumnoId = usersByAlumnoId;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstadoEus() {
		return this.estadoEus;
	}

	public void setEstadoEus(String estadoEus) {
		this.estadoEus = estadoEus;
	}

	public String getIdCentro() {
		return this.idCentro;
	}

	public void setIdCentro(String idCentro) {
		this.idCentro = idCentro;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAsunto() {
		return this.asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getAula() {
		return this.aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	
	public void actualizarReunionesPorID(int reunionId, String estado) {
	    // Obtener la fábrica de sesiones
	    SessionFactory sf = HibernateUtil.getSessionFactory();
	    Session sesion = sf.openSession();
	    Transaction tx = null;

	    try {
	        // Iniciar la transacción
	        tx = sesion.beginTransaction();

	        // Crear una consulta HQL para actualizar directamente
	        String hql = "update Reuniones set estado = :estado where idReunion = :reunionId";
	        Query query = sesion.createQuery(hql);
	        query.setParameter("estado", estado);
	        query.setParameter("reunionId", reunionId);

	        // Ejecutar la actualización
	        int filasActualizadas = query.executeUpdate();

	        // Confirmar la transacción
	        tx.commit();

	        if (filasActualizadas > 0) {
	            System.out.println("Reunión actualizada con éxito.");
	        } else {
	            System.out.println("No se encontró ninguna reunión con el ID proporcionado.");
	        }
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        sesion.close();
	    }
	}

	
	public List<Reuniones> obtenerReunionesPorID(int userId) {
		
		
		List<Reuniones> reuniones = new ArrayList<Reuniones>();
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sesion = sf.openSession();
		String hql = "from Reuniones where usersByProfesorId = " + userId + " ";
		Query q = sesion.createQuery(hql);
		List<?> listaReuniones = q.list();
		
		for (Object res : listaReuniones) {
			Reuniones reunion = (Reuniones) res;
			reuniones.add(reunion);
			
		}
		
		return reuniones;
	}
	
	public List<Reuniones> obtenerReunionesPendientes() {
		
		
		List<Reuniones> reuniones = new ArrayList<Reuniones>();
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sesion = sf.openSession();
		String hql = "from Reuniones where estado = 'pendiente'";
		Query q = sesion.createQuery(hql);
		List<?> listaReuniones = q.list();
		
		for (Object res : listaReuniones) {
			Reuniones reunion = (Reuniones) res;
			reuniones.add(reunion);
			
		}
		
		return reuniones;
	}
	
	
	/*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		
		String[][] horarioReuniones = { { "Hora1", "", "", "", "", "" }, { "Hora2", "", "", "", "", "" },
				{ "Hora3", "", "", "", "", "" }, { "Hora4", "", "", "", "", "" }, { "Hora5", "", "", "", "", "" } };
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sesion = sf.openSession();
		String hql = "from Reuniones where usersByProfesorId = " + userId + " ";
		Query q = sesion.createQuery(hql);
		List<?> reuniones = q.list();
		
		for (Object res : reuniones) {
			Reuniones reunion = (Reuniones) res;
System.out.println(reunion.getFecha().toString());
			LocalDateTime fechaHora = LocalDateTime.parse(reunion.getFecha().toString(), formatter);
			int dia = fechaHora.getDayOfWeek().getValue();
			int hora = fechaHora.getHour();
			
			switch(hora) {
			case 8: 
				hora = 1;
				break;
			case 9: 
				hora = 2;
				break;
			case 10: 
				hora = 3;
				break;
			case 11: 
				hora = 4;
				break;
			case 12: 
				hora = 5;
				break;
			}
			
			 if (hora >= 1 && hora <= 5 && dia >= 1 && dia <= 5) {
		            horarioReuniones[hora - 1][dia] = reunion.getTitulo(); // Ajustar índices para array
		        } else {
		            System.out.println("Hora o día fuera de rango: " + hora + ", " + dia);
		        }
		}
		
		return horarioReuniones;
	}*/

}
