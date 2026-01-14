package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Seccion;
import persistencia.SeccionRepositorio;

public class SeccionFormController {

    @FXML
    private Label lblTitulo;

    @FXML
    private TextField txtNombre;

    private Seccion seccionActual; // null → crear / no null → editar

    // =========================
    // Modo edición
    // =========================
    public void setSeccion(Seccion seccion) {
        this.seccionActual = seccion;
        lblTitulo.setText("Editar Sección");
        txtNombre.setText(seccion.getNombre());
    }

    // =========================
    // Guardar
    // =========================
    @FXML
    private void guardarSeccion() {
        try {
            String nombre = txtNombre.getText();

            if (nombre == null || nombre.isBlank()) {
                System.out.println("El nombre es obligatorio");
                return;
            }

            if (seccionActual == null) {
                // Crear
                SeccionRepositorio.crearSeccion(nombre);
            } else {
                // Editar
                SeccionRepositorio.editarSeccion(
                    seccionActual.getId(),
                    nombre,
                    null
                );
            }

            cerrarVentana();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cancelar() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }
}
