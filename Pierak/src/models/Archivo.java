package models;

import java.nio.file.Path;

public class Archivo {

	private String nombre;
	private Path ruta;
	private TIPO_ARCHIVO tipo;
	
	public Archivo(String nombre, Path ruta, TIPO_ARCHIVO tipo) {
		setNombre(nombre);
		setRuta(ruta);
		setTipo(tipo);
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setRuta(Path ruta) {
		this.ruta = ruta;
	}
	
	public void setTipo(TIPO_ARCHIVO tipo) {
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
}
