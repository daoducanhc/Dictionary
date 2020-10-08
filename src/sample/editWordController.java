package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class editWordController {
    @FXML
    private TextField editTarget;
    @FXML
    private TextField editExplain;
    @FXML
    private Button editButton;
    @FXML
    private Button backButton;
    @FXML
    private ListView<String> Listview;

    public void editAction(ActionEvent event) throws IOException {
        if(Listview.getItems().size() != 0) {
            Listview.getItems().clear();
        }
        String target = editTarget.getText().trim();

        DictionaryCommandline a = new DictionaryCommandline();
        a.dictionaryManagement.insertFromFile();

        editTarget.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                ArrayList<String> searcher = a.dictionaryManagement.dictionarySearcher(target);
                for (String temp : searcher){
                    Listview.getItems().add(temp);
                }
            }
        });

        Listview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Listview.setOnMouseClicked(mouseEvent -> {
            ObservableList<String> list1 = Listview.getSelectionModel().getSelectedItems();
            for (String m : list1) {
                editTarget.setText(m);
            }
        });

    }
    public void edit(ActionEvent event) throws IOException{
        DictionaryCommandline a = new DictionaryCommandline();
        a.dictionaryManagement.insertFromFile();
        a.dictionaryManagement.editWord(editTarget.getText().trim(), editExplain.getText().trim());
        // back to mainGUI
        Parent gui = FXMLLoader.load(getClass().getResource("MainGUI.fxml"));
        Scene scene = new Scene(gui, 600, 400);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void back(ActionEvent event) throws IOException{
        Parent gui = FXMLLoader.load(getClass().getResource("MainGUI.fxml"));
        Scene scene = new Scene(gui, 600, 400);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
