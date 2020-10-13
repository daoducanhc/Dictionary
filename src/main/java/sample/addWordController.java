package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class addWordController {
    @FXML
    private TextField addTarget;
    @FXML
    private TextField addExplain;
    @FXML
    private Button addButton;

    public void add(ActionEvent event) throws IOException {
        DictionaryCommandline a = new DictionaryCommandline();
        a.dictionaryManagement.insertFromFile();
        String target = addTarget.getText().trim();

        if(!target.equals("")){
            String explain = addExplain.getText().trim();
            a.dictionaryManagement.addWord(target, explain);

            //back to main_gui
            Parent gui = FXMLLoader.load(getClass().getResource("/fxml/MainGUI.fxml"));
            Scene scene = new Scene(gui, 600, 400);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }

    }

    public void back(ActionEvent event) throws IOException{
        Parent gui = FXMLLoader.load(getClass().getResource("/fxml/MainGUI.fxml"));
        Scene scene = new Scene(gui, 600, 400);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
