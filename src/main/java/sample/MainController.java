package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TextField Target;
    @FXML
    private TextArea Explain;
    @FXML
    private ListView<String> Listview;
    @FXML
    private Button exitButton;

    private static boolean isCanQuery = true;


    public void textAction(ActionEvent event) {
//        if (Listview.getItems().size() != 0) {
//            Listview.getItems().clear();
//        }
//        String target = Target.getText().trim();
//        Target.setOnKeyPressed(keyEvent -> {
//            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
//                String explain = DictionaryCommandline.dictionaryManagement.dictionaryLookup(target);
//
//                // Could find target or wanna suggested same 1-2 first words
//                if (!explain.equals("") || (target.length() == 1 || target.length() == 2)) {
//                    Explain.setText(explain);
//                    ArrayList<String> searcher = DictionaryCommandline.dictionaryManagement.dictionarySearcher(target);
//                    if (searcher != null) {
//                        for (String temp : searcher) {
//                            Listview.getItems().add(temp);
//                        }
//                    }
//
//                }
//                // Couldn't find target
//                else {
//                    ArrayList<String> all = DictionaryCommandline.dictionaryManagement.dictionarySearcher("");
//                    for (String temp : all) {
//                        if (lcs(target, temp).length() >= target.length() - 1) {
//                            Listview.getItems().add(temp);
//                        }
//                    }
//                }
//            }
//        });
        Target.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (Listview.getItems().size() != 0) {
                    Listview.getItems().clear();
                }
                String target = Target.getText().trim();
                String explain = DictionaryCommandline.dictionaryManagement.dictionaryLookup(target);
                if (explain.equals("")){
                    Explain.clear();
                }

                // Could find target or wanna suggested same 1-2 first words
                if (!explain.equals("") || (target.length() == 1 || target.length() == 2)) {
                    Explain.setText(explain);
                    ArrayList<String> searcher = DictionaryCommandline.dictionaryManagement.dictionarySearcher(target);
                    if (searcher != null) {
                        for (String temp : searcher) {
                            Listview.getItems().add(temp);
                        }
                    }

                }
                // Couldn't find target
                else {
                    ArrayList<String> all = DictionaryCommandline.dictionaryManagement.dictionarySearcher("");
                    for (String temp : all) {
                        if (lcs(target, temp).length() >= target.length() - 1) {
                            Listview.getItems().add(temp);
                        }
                    }
                }

            }

        });

        //click and find explain
        Listview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Listview.setOnMouseClicked(mouseEvent -> {
            ObservableList<String> list1 = Listview.getSelectionModel().getSelectedItems();
            for (String m : list1) {
                //Target.setText(m);
                Explain.setText(DictionaryCommandline.dictionaryManagement.dictionaryLookup(m));
                Target.setText(m);
                break;
            }
        });
    }

    private class MyTask extends Task {

        @Override
        protected Object call() throws Exception {
            isCanQuery = false;
            System.out.println(isCanQuery);
            Thread.sleep(2000);
            isCanQuery = true;
            System.out.println(isCanQuery);
            return null;
        }
    }


    public void add(ActionEvent event) throws IOException {
        Parent add_gui = FXMLLoader.load(getClass().getResource("/fxml/addWordGUI.fxml"));
        Scene scene = new Scene(add_gui, 600, 400);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void delete(ActionEvent event) throws IOException {
        Parent add_gui = FXMLLoader.load(getClass().getResource("/fxml/removeWordGUI.fxml"));
        Scene scene = new Scene(add_gui, 600, 400);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void edit(ActionEvent event) throws IOException {
        Parent add_gui = FXMLLoader.load(getClass().getResource("/fxml/editWordGUI.fxml"));
        Scene scene = new Scene(add_gui, 600, 400);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void exit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        DictionaryCommandline.dictionaryManagement.dictionaryExportToFile();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> listTarget = DictionaryCommandline.dictionaryManagement.dictionarySearcher("");
        for (String m : listTarget) {
            Listview.getItems().add(m);
        }
        Listview.setOnMouseClicked(mouseEvent -> {
            ObservableList<String> list1 = Listview.getSelectionModel().getSelectedItems();
            for (String m : list1) {
                Target.setText(m);
                Explain.setText(DictionaryCommandline.dictionaryManagement.dictionaryLookup(m));
            }
        });
    }

    public String lcs(String str1, String str2) {
        int l1 = str1.length();
        int l2 = str2.length();

        int[][] arr = new int[l1 + 1][l2 + 1];

        for (int i = l1 - 1; i >= 0; i--) {
            for (int j = l2 - 1; j >= 0; j--) {
                if (str1.charAt(i) == str2.charAt(j))
                    arr[i][j] = arr[i + 1][j + 1] + 1;
                else
                    arr[i][j] = Math.max(arr[i + 1][j], arr[i][j + 1]);
            }
        }

        int i = 0, j = 0;
        StringBuilder sb = new StringBuilder();
        while (i < l1 && j < l2) {
            if (str1.charAt(i) == str2.charAt(j)) {
                sb.append(str1.charAt(i));
                i++;
                j++;
            } else if (arr[i + 1][j] >= arr[i][j + 1])
                i++;
            else
                j++;
        }

        return sb.toString();

    }

    @FXML
    public void SpeakOnAction(ActionEvent event) throws IOException {
        GoogleAPI a = new GoogleAPI();
        a.speak(Target.getText());
    }

}