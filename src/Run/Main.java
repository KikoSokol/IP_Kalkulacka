package Run;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/IPInfo.fxml"));
        primaryStage.setTitle("IP Info");
        primaryStage.setScene(new Scene(root));
        primaryStage.centerOnScreen();
        Image ikona = new Image("file:Ikona.png");
        primaryStage.getIcons().add(ikona);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
