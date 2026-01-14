package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import models.Nota;

import java.util.function.Consumer;

public class NotaItemController {

    @FXML
    private HBox root;

    @FXML
    private Label lblTitulo;

    @FXML
    private Label lblFecha;

    private Nota nota;
    private Consumer<Nota> onClick;

    public void setNota(Nota nota) {
        this.nota = nota;
        lblTitulo.setText(nota.getTitulo());
        lblFecha.setText(nota.getFechaCreacion().toString());
    }

    public void setOnClick(Consumer<Nota> onClick) {
        this.onClick = onClick;
    }

    @FXML
    private void onMouseClicked(MouseEvent event) {
        if (onClick != null) {
            onClick.accept(nota);
        }
    }
}
