package Modelo;

import java.util.HashSet;
import java.util.Set;

public class Modulos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Ciclos ciclos;
	private String nombre;
	private String nombreEus;
	private int horas;
	private Integer curso;
	@SuppressWarnings("rawtypes")
	private Set horarioses = new HashSet(0);

	public Modulos() {
	}

	public Modulos(int id, int horas) {
		this.id = id;
		this.horas = horas;
	}

	public Modulos(int id, Ciclos ciclos, String nombre, String nombreEus, int horas, Integer curso,
			@SuppressWarnings("rawtypes") Set horarioses) {
		this.id = id;
		this.ciclos = ciclos;
		this.nombre = nombre;
		this.nombreEus = nombreEus;
		this.horas = horas;
		this.curso = curso;
		this.horarioses = horarioses;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ciclos getCiclos() {
		return this.ciclos;
	}

	public void setCiclos(Ciclos ciclos) {
		this.ciclos = ciclos;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreEus() {
		return this.nombreEus;
	}

	public void setNombreEus(String nombreEus) {
		this.nombreEus = nombreEus;
	}

	public int getHoras() {
		return this.horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public Integer getCurso() {
		return this.curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	@SuppressWarnings("rawtypes")
	public Set getHorarioses() {
		return this.horarioses;
	}

	public void setHorarioses(@SuppressWarnings("rawtypes") Set horarioses) {
		this.horarioses = horarioses;
	}

}
