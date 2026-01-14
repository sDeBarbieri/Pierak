package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import models.Nota;

import java.time.format.DateTimeFormatter;

public class NotaController {

    @FXML
    private Label lblTitulo;

    @FXML
    private TextArea txtContenido;

    @FXML
    private Label lblFechaCreacion;

    @FXML
    private Label lblFechaEdicion;

    private Nota nota;

    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public void setNota(Nota nota) {
        this.nota = nota;

        lblTitulo.setText(nota.getTitulo());
        txtContenido.setText(nota.getContenido());

        lblFechaCreacion.setText(
                "Creada: " + nota.getFechaCreacion().format(formatter)
        );

        if (nota.getUltimaEdicion() != null) {
            lblFechaEdicion.setText(
                    "Última edición: " + nota.getUltimaEdicion().format(formatter)
            );
        } else {
            lblFechaEdicion.setText("Nunca editada");
        }
    }

    @FXML
    private void editarNota() {
        System.out.println("Editar nota: " + nota.getTitulo());
    }

    @FXML
    private void eliminarNota() {
        System.out.println("Eliminar nota: " + nota.getTitulo());
    }
}
