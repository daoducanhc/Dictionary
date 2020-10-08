package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
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
        String explain = addExplain.getText().trim();
        a.dictionaryManagement.addWord(target, explain);
    }
}
