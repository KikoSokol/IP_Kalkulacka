package Okna;

import Controllers.VlsmSieteController;
import Objekty.Siet;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class VlsmSieteOkno
{
    public VlsmSieteOkno(ObservableList<Siet> siete) throws IOException {
        zobraz(siete);
    }

    private void zobraz(ObservableList<Siet> siete) throws IOException
    {
        FXMLLoader sieteLoader = new FXMLLoader();
        sieteLoader.setLocation(getClass().getResource("../View/VlsmSiete.fxml"));
        Parent root = sieteLoader.load();

        Scene scene = new Scene(root);

        VlsmSieteController controller = sieteLoader.getController();
        controller.setSieteDoTabulky(siete);


        Stage sieteStage = new Stage();
        sieteStage.setTitle("VLSM - Rozdelené siete");
        sieteStage.setScene(scene);
        Image ikona = new Image("file:Ikona.png");
        sieteStage.getIcons().add(ikona);
        sieteStage.show();
    }
}
