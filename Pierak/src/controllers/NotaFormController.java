package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Nota;
import models.Seccion;
import persistencia.NotaRepositorio;
import persistencia.SeccionRepositorio;

public class NotaFormController {

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextArea txtContenido;

    @FXML
    private ComboBox<Seccion> cmbSeccion;

    private Seccion seccionFijada; // opcional

    @FXML
    public void initialize() {
        try {
            cmbSeccion.getItems().addAll(
                SeccionRepositorio.cargarSecciones()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Modo: desde Sección
    public void setSeccion(Seccion seccion) {
        this.seccionFijada = seccion;
        cmbSeccion.setValue(seccion);
        cmbSeccion.setDisable(true);
    }

    @FXML
    private void guardarNota() {
        try {
            String titulo = txtTitulo.getText();
            String contenido = txtContenido.getText();
            Seccion seccion = cmbSeccion.getValue();

            if (titulo == null || titulo.isBlank() || seccion == null) {
                System.out.println("Datos incompletos");
                return;
            }

            Nota nota = new Nota(
                titulo,
                contenido,
                seccion.getId()
            );

            NotaRepositorio.crearNota(nota);
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
        Stage stage = (Stage) txtTitulo.getScene().getWindow();
        stage.close();
    }
}
