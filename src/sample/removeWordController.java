package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class removeWordController {
    @FXML
    private TextField removeTarget;
    @FXML
    private Button removeButton;
    @FXML
    private ListView<String> Listview;

    public void removeAction(ActionEvent event) throws IOException {
        if(Listview.getItems().size() != 0) {
            Listview.getItems().clear();
        }
        String target = removeTarget.getText().trim();

        DictionaryCommandline a = new DictionaryCommandline();
        a.dictionaryManagement.insertFromFile();

        removeTarget.setOnKeyPressed(keyEvent -> {
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
                removeTarget.setText(m);
            }
        });
    }

    public void remove(ActionEvent event) throws IOException{
        DictionaryCommandline a = new DictionaryCommandline();
        a.dictionaryManagement.insertFromFile();
        String target = removeTarget.getText().trim();
        int index = a.dictionaryManagement.getIndexByWord(target);

        if (!target.equals("")) {
            if(index != -1){
                a.dictionaryManagement.removeWord(target);
                // back to mainGUI
                Parent gui = FXMLLoader.load(getClass().getResource("MainGUI.fxml"));
                Scene scene = new Scene(gui, 600, 400);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            } else {
                //Alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Don't have that word in dictionary yet!!!");
                alert.show();
            }
        }
    }

    public void back(ActionEvent event) throws IOException{
        Parent gui = FXMLLoader.load(getClass().getResource("MainGUI.fxml"));
        Scene scene = new Scene(gui, 600, 400);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
