package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        DictionaryCommandline.dictionaryManagement.insertFromFile();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainGUI.fxml"));
        primaryStage.setTitle("Dictionary");
        Scene scene = new Scene(root, 730, 555);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
