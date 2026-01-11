package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Nota {

	
	private static final int VALOR_NO_ENCONTRADO = -1;
	private int id;
	private String titulo;
	private String contenido;
	private LocalDateTime fechaCreacion;
	private LocalDateTime ultimaEdicion;
	private ArrayList<Integer> idsArchivos;
	
	private int seccionId; // RELACIÓN CON SECCIÓN.
	
	public Nota(String titulo, String contenido, int seccionId) {
		setTitulo(titulo);
		setContenido(contenido);
		setFechaCreacion();
		setUltimaEdicion();
		setSeccionId(seccionId);
		idsArchivos = new ArrayList<Integer>();
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

	public ArrayList<Integer> getIdsArchivos() {
		return idsArchivos;
	}
	
	public boolean agregarArchivo(int id) {
		boolean agregado = false;
		int posicion = buscarIdArchivo(id);
		if (posicion == VALOR_NO_ENCONTRADO) {
			idsArchivos.add(id);
			actualizarUltimaEdicion();
			agregado = true;
		}
		return agregado;
	}
	
	private int buscarIdArchivo(int idBuscado) {
		int id = 0;
		int posicion = VALOR_NO_ENCONTRADO;
		int i = 0;
		
		while (posicion == VALOR_NO_ENCONTRADO && i < idsArchivos.size()) {
			id = idsArchivos.get(i);
			if (id == idBuscado) {
				posicion = i;
			}else {
				i++;
			}
		}
		return posicion;
	}
	
	public boolean eliminarArchivo(int id) {
		boolean eliminado = false;
		int posicion = buscarIdArchivo(id);
		if (posicion != VALOR_NO_ENCONTRADO) {
			idsArchivos.remove(posicion);
			actualizarUltimaEdicion();
			eliminado = true;
		}
		return eliminado;
	}
}
