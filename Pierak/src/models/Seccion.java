package models;

public class Seccion {

	private int id;
	private String nombre;
	private Archivo foto;
	
	public Seccion(int id, String nombre) {
		setId(id);
		setNombre(nombre);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setFoto(Archivo foto) {
		this.foto = foto;
	}
	
	public String getNombre() {
		return nombre;
	}

	public Archivo getFoto() {
		return foto;
	}
}
