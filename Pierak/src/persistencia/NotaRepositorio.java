package persistencia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.Nota;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class NotaRepositorio {

    private static final Path ARCHIVO = Path.of("data/notas.json");

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(
                java.time.LocalDateTime.class,
                new LocalDateTimeAdapter()
            )
            .create();

    private static final Type LISTA_NOTAS_TYPE =
            new TypeToken<List<Nota>>() {}.getType();

    // 🔹 Cargar todas las notas
    public static List<Nota> cargarNotas() throws IOException {

        if (!Files.exists(ARCHIVO)) {
            return new ArrayList<>();
        }

        String json = Files.readString(ARCHIVO);

        return gson.fromJson(json, LISTA_NOTAS_TYPE);
    }

    // 🔹 Guardar todas las notas
	public static void guardarNotas(List<Nota> notas) throws IOException {

		Files.createDirectories(ARCHIVO.getParent());

		String json = gson.toJson(notas);

		Files.writeString(ARCHIVO, json);
	}
    
    public static List<Nota> buscarPorSeccion(int seccionId) throws Exception{
    	List<Nota> todasLasNotas = cargarNotas();
    	List<Nota> notasDeLaSeccion = new ArrayList<Nota>();
    	
    	for (Nota nota : todasLasNotas) {
			if (nota.getSeccionId() == seccionId) {
				notasDeLaSeccion.add(nota);
			}
		}
    	return notasDeLaSeccion;
    }
    
    private static Nota buscarNota(int id, List<Nota> notas) {
    	Nota encontrada = null;
    	Nota nota = null;
    	int i = 0;
    	
    	while (encontrada == null  && i < notas.size()) {
    		nota = notas.get(i);
			if (nota.getId() == id) {
				encontrada = nota;
			}else {
				i++;
			}
		}
    	return encontrada;
    }
    
    private static int obtenerProximoId(List<Nota> notas) throws Exception{
        int max = 0;
        for (Nota nota : notas) {
            if (nota.getId() > max) {
                max = nota.getId();
            }
        }
        return max + 1;
    }

    public static Nota crearNota(Nota nota) throws Exception{
    	List<Nota> notas = cargarNotas();
    	
    	int nuevoId = obtenerProximoId(notas);
    	nota.asignarId(nuevoId);
    	
    	notas.add(nota);
    	guardarNotas(notas);
    	
    	return nota;
    }
    
    public static Nota crearNota(String titulo, String contenido, int seccionId) throws Exception {
        Nota nota = new Nota(titulo, contenido, seccionId);
        return crearNota(nota);
    }

    
    public static boolean eliminarNota(int id) throws Exception{
    	List<Nota> notas = cargarNotas();
    	boolean eliminada = false;
    	Nota nota = buscarNota(id, notas);
    	if (nota != null) {
			notas.remove(nota);
			eliminada = true;
		}
    	guardarNotas(notas);
    	return eliminada;
    }
    
	public static Nota editarNota(int id, String nuevoTitulo, String nuevoContenido, int nuevaSeccionId)
			throws Exception {
		
		List<Nota> notas = cargarNotas();
		Nota nota = buscarNota(id, notas);
		
			if (nota != null) {

				if (nuevoTitulo != null) {nota.actualizarTitulo(nuevoTitulo);}
				if (nuevoContenido != null) {nota.actualizarContenido(nuevoContenido);}
				if (nuevaSeccionId > 0) {nota.actualizarSeccionId(nuevaSeccionId);}

				guardarNotas(notas);
			}
		
		return nota;
	}
}
