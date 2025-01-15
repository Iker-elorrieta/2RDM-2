package Modelo;

import java.util.HashSet;
import java.util.Set;

public class Ciclos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;
	@SuppressWarnings("rawtypes")
	private Set matriculacioneses = new HashSet(0);
	@SuppressWarnings("rawtypes")
	private Set moduloses = new HashSet(0);

	public Ciclos() {
	}

	public Ciclos(int id) {
		this.id = id;
	}

	public Ciclos(int id, String nombre, @SuppressWarnings("rawtypes") Set matriculacioneses,
			@SuppressWarnings("rawtypes") Set moduloses) {
		this.id = id;
		this.nombre = nombre;
		this.matriculacioneses = matriculacioneses;
		this.moduloses = moduloses;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@SuppressWarnings("rawtypes")
	public Set getMatriculacioneses() {
		return this.matriculacioneses;
	}

	@SuppressWarnings("rawtypes")
	public void setMatriculacioneses(Set matriculacioneses) {
		this.matriculacioneses = matriculacioneses;
	}

	@SuppressWarnings("rawtypes")
	public Set getModuloses() {
		return this.moduloses;
	}

	@SuppressWarnings("rawtypes")
	public void setModuloses(Set moduloses) {
		this.moduloses = moduloses;
	}

}
