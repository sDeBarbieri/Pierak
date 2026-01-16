package ui;

import app.MainApp;
import javafx.scene.Scene;

public class UiUtils {

    public static Scene crearSceneConCss(Object root) {
        Scene scene = new Scene((javafx.scene.Parent) root);
        scene.getStylesheets().addAll(
            MainApp.getScene().getStylesheets()
        );
        return scene;
    }
}
