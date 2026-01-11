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

import models.Archivo;
import models.TIPO_ARCHIVO;

public class ArchivoRepositorio {

    private static final Path ARCHIVO = Path.of("data/archivos.json");

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final Type LISTA_ARCHIVOS_TYPE =
            new TypeToken<List<Archivo>>() {}.getType();

    // 🔹 Cargar
    public static List<Archivo> cargarArchivos() throws IOException {

        if (!Files.exists(ARCHIVO)) {
            return new ArrayList<>();
        }

        String json = Files.readString(ARCHIVO);
        return gson.fromJson(json, LISTA_ARCHIVOS_TYPE);
    }

    // 🔹 Guardar
    public static void guardarArchivos(List<Archivo> archivos) throws IOException {

        Files.createDirectories(ARCHIVO.getParent());
        String json = gson.toJson(archivos);
        Files.writeString(ARCHIVO, json);
    }

    // 🔹 Buscar interno
    private static Archivo buscarArchivo(int id, List<Archivo> archivos) {
    	Archivo archivo = null;
    	Archivo encontrado = null;
    	int i = 0;
    	
    	while (encontrado == null  && i < archivos.size()) {
    		archivo = archivos.get(i);
			if (archivo.getId() == id) {
				encontrado = archivo;
			}else {
				i++;
			}
		}
    	return encontrado;
    }

    // 🔹 ID
    private static int obtenerProximoId(List<Archivo> archivos) {
        int max = 0;
        for (Archivo archivo : archivos) {
            if (archivo.getId() > max) {
                max = archivo.getId();
            }
        }
        return max + 1;
    }

    // 🔹 Crear
    public static Archivo crearArchivo(Archivo archivo) throws IOException {

        List<Archivo> archivos = cargarArchivos();
        archivo.asignarId(obtenerProximoId(archivos));
        archivos.add(archivo);
        guardarArchivos(archivos);

        return archivo;
    }

    public static Archivo crearArchivo(String nombre, Path ruta, TIPO_ARCHIVO tipo)
            throws IOException {

        Archivo archivo = new Archivo(nombre, ruta, tipo);
        return crearArchivo(archivo);
    }

    // 🔹 Editar
    public static Archivo editarArchivo(int id, String nuevoNombre, Path nuevaRuta, TIPO_ARCHIVO nuevoTipo)
            throws IOException {

        List<Archivo> archivos = cargarArchivos();
        Archivo archivo = buscarArchivo(id, archivos);

        if (archivo != null) {
            if (nuevoNombre != null) archivo.actualizarNombre(nuevoNombre);
            if (nuevaRuta != null) archivo.actualizarRuta(nuevaRuta);
            if (nuevoTipo != null) archivo.actualizarTipo(nuevoTipo);
            guardarArchivos(archivos);
        }

        return archivo;
    }

    // 🔹 Eliminar
    public static boolean eliminarArchivo(int id) throws IOException {

        List<Archivo> archivos = cargarArchivos();
        Archivo archivo = buscarArchivo(id, archivos);
    	boolean eliminada = false;

        if (archivo != null) {
            archivos.remove(archivo);
            guardarArchivos(archivos);
            eliminada = true;
        }
        return eliminada;
    }
}
