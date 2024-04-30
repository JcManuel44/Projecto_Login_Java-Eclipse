package Model;

public class Cargo {
	
	private int id;
	private String nombre_cargo;
	
	public Cargo() {
	}
	
	public Cargo(int id) {
		this.id = id;
	}

	public Cargo(int id, String nombre_cargo) {
		this.id = id;
		this.nombre_cargo = nombre_cargo;
	}
	
	public Cargo(String nombre_cargo) {
		this.nombre_cargo = nombre_cargo;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre_cargo() {
		return nombre_cargo;
	}
	public void setNombre_cargo(String nombre_cargo) {
		this.nombre_cargo = nombre_cargo;
	}
}
