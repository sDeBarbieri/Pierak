package models;

public class Seccion {

	private int id;
	private String nombre;
	private Integer fotoId;

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

	private void setFotoId(Integer fotoId) {
		this.fotoId = fotoId;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getFotoId() {
		return fotoId;
	}

	public void asignarId(int nuevoId) {
		setId(nuevoId);
	}

	public void actualizarNombre(String nuevoNombre) {
		setNombre(nuevoNombre);
	}

	public void actualizarFoto(Integer nuevaFoto) {
		setFotoId(nuevaFoto);
	}
	
	public boolean tieneFoto() {
		return fotoId != null;
	}

	public void eliminarFoto() {
		setFotoId(null);
	}

}
