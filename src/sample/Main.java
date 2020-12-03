package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static WStats weatherStats = new WStats();
    public static JSONGetter jsonGetter = new JSONGetter();
    public static Controller guiController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Weather in London");
        primaryStage.setScene(new Scene(root, 800, 620));
        guiController = loader.getController();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        jsonGetter.stopSearch();
        super.stop();
    }
}
