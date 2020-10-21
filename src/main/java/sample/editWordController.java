package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class editWordController {
    @FXML
    private TextField editTarget;
    @FXML
    private TextField editExplain;
    @FXML
    private ListView<String> Listview;

    public void editAction() {

        editTarget.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (Listview.getItems().size() != 0) {
                    Listview.getItems().clear();
                }
                String target = editTarget.getText().trim();

                ArrayList<String> searcher = DictionaryCommandline.dictionaryManagement.dictionarySearcher(target);
                if (searcher != null) {
                    for (String temp : searcher) {
                        Listview.getItems().add(temp);
                    }
                }
            }
        });
        Listview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Listview.setOnMouseClicked(mouseEvent -> {
            ObservableList<String> list1 = Listview.getSelectionModel().getSelectedItems();
            for (String m : list1) {
                editTarget.setText(m);
                break;
            }
        });

    }

    public void edit(ActionEvent event) throws IOException {
        String target = editTarget.getText().trim();
        String explain = editExplain.getText().trim();
        int index = DictionaryCommandline.dictionaryManagement.getIndexByTarget(target);

        if (!target.equals("")) {
            if (index != -1) {
                DictionaryCommandline.dictionaryManagement.editWord(target, explain, index);
                // back to mainGUI
                Parent gui = FXMLLoader.load(getClass().getResource("/fxml/MainGUI.fxml"));
                Scene scene = new Scene(gui, 730, 555);
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

    public void back(ActionEvent event) throws IOException {
        Parent gui = FXMLLoader.load(getClass().getResource("/fxml/MainGUI.fxml"));
        Scene scene = new Scene(gui, 730, 555);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
