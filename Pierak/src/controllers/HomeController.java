package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Seccion;
import persistencia.SeccionRepositorio;

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

        btn.setPrefSize(200, 120);
        btn.setWrapText(true);
        btn.setStyle("""
            -fx-font-size: 16px;
            -fx-font-weight: bold;
        """);

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

            Scene scene = new Scene(root);

            Stage stage = (Stage) gridSecciones.getScene().getWindow();
            stage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void crearSeccion() {
        System.out.println("Click en + Sección");
    }

    @FXML
    private void crearNota() {
        System.out.println("Click en + Nota");
    }
}
