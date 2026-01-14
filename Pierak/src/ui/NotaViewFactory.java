package ui;

import controllers.NotaItemController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import models.Nota;

import java.util.function.Consumer;

public class NotaViewFactory {

    public static Node crear(Nota nota, Consumer<Nota> onClick) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                NotaViewFactory.class.getResource("/views/NotaItem.fxml")
        );

        Node root = loader.load();

        NotaItemController controller = loader.getController();
        controller.setNota(nota);
        controller.setOnClick(onClick);

        return root;
    }
}
