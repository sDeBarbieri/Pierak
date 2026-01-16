package controllers;

import app.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Seccion;
import persistencia.SeccionRepositorio;
import ui.UiUtils;

import java.util.List;

public class HomeController {

    @FXML
    private GridPane gridSecciones;

    private static final int COLUMNAS = 3;

    @FXML
    public void initialize() {
        cargarSecciones();
    }

    private void cargarSecciones() {
        try {
            List<Seccion> secciones = SeccionRepositorio.cargarSecciones();
            gridSecciones.getChildren().clear();

            int col = 0;
            int row = 0;

            for (Seccion seccion : secciones) {
                Button btn = crearBotonSeccion(seccion);
                gridSecciones.add(btn, col, row);

                col++;
                if (col == COLUMNAS) {
                    col = 0;
                    row++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Button crearBotonSeccion(Seccion seccion) {
        Button btn = new Button(seccion.getNombre());
        btn.setPrefSize(150, 90);
        btn.setWrapText(true);

        btn.setOnAction(e -> abrirSeccion(seccion));
        return btn;
    }

    private void abrirSeccion(Seccion seccion) {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/views/SeccionView.fxml")
            );

            Parent root = loader.load();

            SeccionController controller = loader.getController();
            controller.setSeccion(seccion);

            MainApp.setRoot(root);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void crearSeccion() {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/views/SeccionFormView.fxml")
            );

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Nueva Sección");
            stage.setScene(UiUtils.crearSceneConCss(root));
            stage.showAndWait();

            cargarSecciones();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void crearNota() {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/views/NotaFormView.fxml")
            );

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Nueva Nota");
            stage.setScene(UiUtils.crearSceneConCss(root));
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
