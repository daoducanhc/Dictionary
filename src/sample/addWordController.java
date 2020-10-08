package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
