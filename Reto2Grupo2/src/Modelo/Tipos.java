package Modelo;

import java.util.HashSet;
import java.util.Set;

public class Tipos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String nameEus;
	@SuppressWarnings("rawtypes")
	private Set userses = new HashSet(0);

	public Tipos() {
	}

	public Tipos(int id) {
		this.id = id;
	}

	public Tipos(int id, String name, String nameEus, @SuppressWarnings("rawtypes") Set userses) {
		this.id = id;
		this.name = name;
		this.nameEus = nameEus;
		this.userses = userses;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEus() {
		return this.nameEus;
	}

	public void setNameEus(String nameEus) {
		this.nameEus = nameEus;
	}

	@SuppressWarnings("rawtypes")
	public Set getUserses() {
		return this.userses;
	}

	@SuppressWarnings("rawtypes")
	public void setUserses(Set userses) {
		this.userses = userses;
	}

}
