package app;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) { 
        Label label = new Label("Probando Pierak 👋"); // Creo un componente visual, similar a un <p> en HTML.
        Scene scene = new Scene(new StackPane(label), 400, 200); // StackPane es un layout. Puede contener muchos nodos. Los apila uno sobre otro y centra el contenido por defecto. Se le asigna el label al layout.
        // La Scene representa todo el contenido Visual. Tiene UN SOLO nodo raíz, ahi el StackPane. Y se le define un tamaño.
        

        stage.setTitle("Pierak"); // Titulo de la ventana
        stage.setScene(scene); // Se le acopla el contenido a la ventana.
        stage.show(); // Hace visible la ventana
    }
 // Este método lo llama JavaFX automáticamente.
 // Se ejecuta cuando la app está lista para mostrar UI. 
 // JavaFX lo invoca internamente.
    

    public static void main(String[] args) {
        launch();
        // launch() inicializa JavaFX, crea el thread gráfico, crea el Stage, llama a start(stage) automáticamente.
        // Nunca se llama a start() manualmente.
        }
}
