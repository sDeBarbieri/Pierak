package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import models.Nota;
import models.Seccion;
import persistencia.NotaRepositorio;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

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
    
    private Seccion seccion;
    
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public void setContexto(Nota nota, Seccion seccion) {
        this.nota = nota;
        this.seccion = seccion;

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
    private void renombrarNota() {
        TextInputDialog dialog = new TextInputDialog(nota.getTitulo());
        dialog.setTitle("Renombrar nota");
        dialog.setHeaderText(null);
        dialog.setContentText("Nuevo título:");

        dialog.showAndWait().ifPresent(nuevoTitulo -> {
            if (nuevoTitulo.isBlank() || nuevoTitulo.equals(nota.getTitulo())) return;

            try {
                NotaRepositorio.editarNota(
                    nota.getId(),
                    nuevoTitulo,
                    null,
                    -1
                );

                nota.actualizarTitulo(nuevoTitulo);
                lblTitulo.setText(nuevoTitulo);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }



    @FXML
    private void eliminarNota() {
    	
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar nota");
        alert.setHeaderText("¿Eliminar esta nota?");
        alert.setContentText("Esta acción no se puede deshacer.");

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.isEmpty() || resultado.get() != ButtonType.OK) {
            return; // el usuario canceló
        }
    	
    	try {
			NotaRepositorio.eliminarNota(nota.getId());
			volver();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    private void volver() {
        guardarContenido();

        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/views/SeccionView.fxml")
            );

            Parent root = loader.load();
            
            SeccionController controller = loader.getController();
            controller.setSeccion(seccion);

            Stage stage = (Stage) lblTitulo.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    @FXML
    public void initialize() {

        txtContenido.focusedProperty().addListener((obs, estabaEnFoco, ahoraEnFoco) -> {
            if (estabaEnFoco && !ahoraEnFoco) {
                guardarContenido();
            }
        });
    }

    private void guardarContenido() {
        try {
            String nuevoContenido = txtContenido.getText();

            if (nota == null) return;

            if (!nuevoContenido.equals(nota.getContenido())) {
                NotaRepositorio.editarNota(nota.getId(), null, nuevoContenido, -1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

}
