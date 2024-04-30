package Model;

public class Usuario {
	
	private int id;
	private String nombre;
	private String clave;
	private boolean estado;
	private int cargo_id;
	private Cargo cargo;
	
	
	
	public Usuario() {
	}
	
	public Usuario(int id) {
		this.id = id;
	}

	public Usuario(String nombre, String clave, boolean estado, int cargo_id) {
		this.nombre = nombre;
		this.clave = clave;
		this.estado = estado;
		this.cargo_id = cargo_id;
	}

	public Usuario(int id, String nombre, String clave, boolean estado, int cargo_id) {
		this.id = id;
		this.nombre = nombre;
		this.clave = clave;
		this.estado = estado;
		this.cargo_id = cargo_id;
	}
	

	public Usuario(int id, String nombre, String clave, boolean estado, Cargo cargo) {
		this.id = id;
		this.nombre = nombre;
		this.clave = clave;
		this.estado = estado;
		this.cargo = cargo;
	}
	 
	//Mapeo
	public Usuario(int id, String nombre, boolean estado, Cargo cargo) {
		this.id = id;
		this.nombre = nombre; 
		this.estado = estado;
		this.cargo = cargo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public int getCargo_id() {
		return cargo_id;
	}

	public void setCargo_id(int cargo_id) {
		this.cargo_id = cargo_id;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
}
