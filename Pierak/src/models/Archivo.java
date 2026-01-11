package models;

import java.nio.file.Path;

public class Archivo {

	private int id;
	private String nombre;
	private Path ruta;
	private TIPO_ARCHIVO tipo;
	
	public Archivo(String nombre, Path ruta, TIPO_ARCHIVO tipo) {
		setNombre(nombre);
		setRuta(ruta);
		setTipo(tipo);
	}
	
	private void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void asignarId(int id) {
		setId(id);
	}
	
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	private void setRuta(Path ruta) {
		this.ruta = ruta;
	}
	
	private void setTipo(TIPO_ARCHIVO tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public Path getRuta() {
		return ruta;
	}

	public TIPO_ARCHIVO getTipo() {
		return tipo;
	}
	
	public void actualizarRuta(Path ruta) {
		setRuta(ruta);
	}
	
	public void actualizarNombre(String nombre) {
		setNombre(nombre);
	}
	
	public void actualizarTipo(TIPO_ARCHIVO tipo) {
		setTipo(tipo);
	}
}
