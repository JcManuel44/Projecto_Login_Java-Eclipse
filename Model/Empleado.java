package Model;

import java.sql.Date;

public class Empleado {
	
	private int id;
	private String nombre;
	private String apellido;
	private char sexo;
	private int telefono;
	private Date fecha;
	private int usuario_id;
	
	public Empleado() {
	}
	
	public Empleado(String nombre, String apellido, char sexo, int telefono, Date fecha, int usuario_id) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.telefono = telefono;
		this.fecha = fecha;
		this.usuario_id = usuario_id;
	}

	public Empleado(int id, String nombre, String apellido, char sexo, int telefono, Date fecha, int usuario_id) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.telefono = telefono;
		this.fecha = fecha;
		this.usuario_id = usuario_id;
	}

	public Empleado(int id) {
		this.id = id;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
}
