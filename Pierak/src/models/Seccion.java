package models;

public class Seccion {

	private int id;
	private String nombre;
	private Archivo foto;

	public Seccion(String nombre) {
		setNombre(nombre);
	}

	private void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private void setFoto(Archivo foto) {
		this.foto = foto;
	}

	public String getNombre() {
		return nombre;
	}

	public Archivo getFoto() {
		return foto;
	}

	public void asignarId(int nuevoId) {
		setId(nuevoId);
	}

	public void actualizarNombre(String nuevoNombre) {
		setNombre(nuevoNombre);
	}

	public void actualizarArchivo(Archivo nuevaFoto) {
		setFoto(nuevaFoto);
	}
}
