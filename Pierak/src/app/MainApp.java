package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/views/HomeView.fxml")
        );

        scene = new Scene(loader.load(), 600, 400);
        scene.getStylesheets().add(
            getClass().getResource("/styles/app.css").toExternalForm()
        );

        stage.setTitle("Pierak");
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(Parent root) {
        scene.setRoot(root);
    }




    public static void main(String[] args) {
        launch();
    }

	public static Scene getScene() {
		return scene;
	}
}
