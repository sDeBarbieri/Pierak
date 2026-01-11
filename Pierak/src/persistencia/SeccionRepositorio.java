package persistencia;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import models.Seccion;

public class SeccionRepositorio {

	private static final Path ARCHIVO = Path.of("data/secciones.json");

	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	private static final Type LISTA_SECCIONES_TYPE = new TypeToken<List<Seccion>>() {}.getType();

	public static List<Seccion> cargarSecciones() throws IOException {

		if (!Files.exists(ARCHIVO)) {
			return new ArrayList<>();
		}

		String json = Files.readString(ARCHIVO);

		return gson.fromJson(json, LISTA_SECCIONES_TYPE);
	}
	
	public static void guardarSecciones(List<Seccion> secciones) throws IOException {

		Files.createDirectories(ARCHIVO.getParent());

		String json = gson.toJson(secciones);

		Files.writeString(ARCHIVO, json);
	}
	
	private static Seccion buscarSeccion(int id, List<Seccion> secciones) throws Exception {
		Seccion encontrada = null;
    	Seccion seccion = null;
    	int i = 0;
    	
    	while (encontrada == null  && i < secciones.size()) {
    		seccion = secciones.get(i);
			if (seccion.getId() == id) {
				encontrada = seccion;
			}else {
				i++;
			}
		}
    	return encontrada;
	}
	
	public static Seccion crearSeccion(Seccion seccion) throws Exception{
		List<Seccion> secciones= cargarSecciones();
		
		int nuevoId = obtenerProximoId(secciones);
		seccion.asignarId(nuevoId);
		
		secciones.add(seccion);
		guardarSecciones(secciones);
		
		return seccion;
	}
	
	public static Seccion crearSeccion(String nombre) throws Exception {
        Seccion seccion = new Seccion(nombre);
        return crearSeccion(seccion);
    }

	private static int obtenerProximoId(List<Seccion> secciones) {
		int max = 0;
        for (Seccion seccion : secciones) {
            if (seccion.getId() > max) {
                max = seccion.getId();
            }
        }
        return max + 1;
	}
	
	public static boolean eliminarSeccion(int id) throws Exception{
    	List<Seccion> secciones = cargarSecciones();
    	boolean eliminada = false;
    	Seccion seccion = buscarSeccion(id, secciones);
    	if (seccion != null) {
    		NotaRepositorio.vaciarSeccion(id);
			secciones.remove(seccion);
			eliminada = true;
		}
    	guardarSecciones(secciones);
    	return eliminada;
    }
	
	public static Seccion editarSeccion(int id, String nuevoNombre, Integer nuevaFoto)
			throws Exception {
		
		List<Seccion> secciones = cargarSecciones();
		Seccion seccion = buscarSeccion(id, secciones);
		
			if (seccion != null) {

				if (nuevoNombre != null) {seccion.actualizarNombre(nuevoNombre);}
				if (nuevaFoto != null) {seccion.actualizarFoto(nuevaFoto);}

				guardarSecciones(secciones);
			}
		
		return seccion;
	}
	
	public static boolean asignarFoto(int seccionId, int archivoId) throws Exception {
		List<Seccion> secciones = cargarSecciones();
		Seccion seccion = buscarSeccion(seccionId, secciones);
		boolean asignada = false;
		if (seccion != null) {
			seccion.actualizarFoto(archivoId);
			guardarSecciones(secciones);
			asignada = true;
		}
		return asignada;
	}

	public static boolean eliminarFoto(int seccionId) throws Exception {
		List<Seccion> secciones = cargarSecciones();
		Seccion seccion = buscarSeccion(seccionId, secciones);
		boolean eliminada = false;
		if (seccion != null) { 
			seccion.eliminarFoto();
			guardarSecciones(secciones);
			eliminada = true;
		}
		return eliminada;
	}

}
