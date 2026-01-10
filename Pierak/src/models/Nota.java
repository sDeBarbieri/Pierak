package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Nota {

	private int id;
	private String titulo;
	private String contenido;
	private LocalDateTime fechaCreacion;
	private LocalDateTime ultimaEdicion;
	private ArrayList<Archivo> archivos;
	
	private int seccionId; // RELACIÓN CON SECCIÓN.
	
	public Nota(String titulo, String contenido, int seccionId) {
		setTitulo(titulo);
		setContenido(contenido);
		setFechaCreacion();
		setUltimaEdicion();
		setArchivos();
		setSeccionId(seccionId);
	}
	
	private void setId(int id) {
		this.id = id;
	}
	public void asignarId(int id) {
		setId(id);
	}
	
	public int getId() {
		return id;
	}
	
	private void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public void actualizarTitulo(String titulo) {
		setTitulo(titulo);
	}
	
	private void setContenido(String contenido) {
		this.contenido = contenido;
		actualizarUltimaEdicion();
	}
	
	public void actualizarContenido(String contenido) {
		setContenido(contenido);
	}
	
	private void setFechaCreacion() {
		this.fechaCreacion = LocalDateTime.now();
	}
	
	private void setUltimaEdicion() {
		this.ultimaEdicion = LocalDateTime.now();
	}
	
	public void actualizarUltimaEdicion() {
		setUltimaEdicion();
	}
	
	private void setSeccionId(int seccionId) {
		this.seccionId = seccionId;
	}
	
	public void actualizarSeccionId(int seccionId) {
		setSeccionId(seccionId);
	}
	
	private void setArchivos() {
		archivos = new ArrayList<Archivo>();
	}

	public String getTitulo() {
		return titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public LocalDateTime getUltimaEdicion() {
		return ultimaEdicion;
	}

	public int getSeccionId() {
		return seccionId;
	}

	public ArrayList<Archivo> getArchivos() {
		return archivos;
	}
	
}
