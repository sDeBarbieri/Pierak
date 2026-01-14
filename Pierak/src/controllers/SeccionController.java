package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Nota;
import models.Seccion;
import persistencia.NotaRepositorio;
import ui.NotaViewFactory;

public class SeccionController {

    private Seccion seccionActual;

    @FXML
    private Label lblNombreSeccion;

    @FXML
    private VBox contenedorNotas;

    // =========================
    // Inicialización
    // =========================

    public void setSeccion(Seccion seccion) {
        this.seccionActual = seccion;
        lblNombreSeccion.setText(seccion.getNombre());
        cargarNotas();
    }
    
    private void cargarNotas() {
        contenedorNotas.getChildren().clear();

        try {
            var notas = NotaRepositorio.buscarPorSeccion(seccionActual.getId());
            notas.sort((a, b) -> b.getFechaCreacion().compareTo(a.getFechaCreacion()));

            for (var nota : notas) {
                contenedorNotas.getChildren().add(
                    NotaViewFactory.crear(nota, t -> {
						try {
							abrirNota(t);
						} catch (Exception e) {
							e.printStackTrace();
						}
					})
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    @FXML
    private void abrirNota(Nota nota) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/views/NotaView.fxml")
        );

        Parent root = loader.load();

        NotaController controller = loader.getController();
        controller.setNota(nota);

        Stage stage = (Stage) contenedorNotas.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    
    @FXML
    private void editarSeccion() {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/views/SeccionFormView.fxml")
            );

            Parent root = loader.load();

            SeccionFormController controller = loader.getController();
            controller.setSeccion(seccionActual);

            Stage stage = new Stage();
            stage.setTitle("Editar Sección");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            lblNombreSeccion.setText(seccionActual.getNombre());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void vaciarSeccion() {
        // confirmar + borrar notas
    }

    @FXML
    private void eliminarSeccion() {
        // confirmar + eliminar sección
    }

    @FXML
    private void crearNota() {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/views/NotaFormView.fxml")
            );

            Parent root = loader.load();

            NotaFormController controller = loader.getController();
            controller.setSeccion(seccionActual);

            Stage stage = new Stage();
            stage.setTitle("Nueva Nota");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Al volver, recargamos notas
            cargarNotas();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}