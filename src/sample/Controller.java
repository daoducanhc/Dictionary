package sample;

import com.gtranslate.Audio;
import com.gtranslate.Language;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {

    @FXML
    private TextField Target;
    @FXML
    private TextField Explain;
    @FXML
    private ListView<String> Listview;


    public void textAction(ActionEvent Event) throws IOException {
        if(Listview.getItems().size() != 0) {
            Listview.getItems().clear();
        }

        DictionaryCommandline a = new DictionaryCommandline();
        String target = Target.getText().trim();
        //      a.dictionaryManagement.insertFromFile();
        a.dictionaryManagement.insertFromFile();

        Target.setOnKeyPressed(keyEvent -> {
            int count = 0;
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                for (int i = 0; i < a.dictionaryManagement.dictionary.Dict.size(); i++) {
                    if (target.equals(a.dictionaryManagement.dictionary.Dict.get(i).getWord_target())) {
                        Explain.setText(a.dictionaryManagement.dictionary.Dict.get(i).getWord_explain());
                        count ++;
                        break;
                    }
                }

                if (count == 0) {
                    Explain.setText("ERRO!!");
                }

                for (int i = 0; i < a.dictionaryManagement.dictionary.Dict.size(); i++) {
                    String temp = a.dictionaryManagement.dictionary.Dict.get(i).getWord_target();
                    if (temp.indexOf(target) == 0) {
                        Listview.getItems().add(temp);
                    }
                }

            }
        });

//        for (int i = 0; i < a.dictionaryManagement.dictionary.Dict.size(); i++) {
//            String temp = a.dictionaryManagement.dictionary.Dict.get(i).getWord_target();
//            if (temp.indexOf(target) == 0) {
//                Listview.getItems().add(temp);
//            }
//        }

        Listview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ObservableList<String> list = Listview.getSelectionModel().getSelectedItems();

        Listview.setOnMouseClicked(mouseEvent -> {
            ObservableList<String> list1 = Listview.getSelectionModel().getSelectedItems();
            String message = "";
            for (String m : list1) {
                for (int i = 0; i < a.dictionaryManagement.dictionary.Dict.size(); i++) {
                    if (m.equals(a.dictionaryManagement.dictionary.Dict.get(i).getWord_target())) {
                        Explain.setText(a.dictionaryManagement.dictionary.Dict.get(i).getWord_explain());
                    }
                }
            }
         //   System.out.print(message);

        });
    }


    public void speak(ActionEvent Event) throws IOException, JavaLayerException {

        Audio audio = Audio.getInstance();
        InputStream sound = null;
        try {
            sound = audio.getAudio(Target.getText(), Language.ENGLISH);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            audio.play(sound);
        } catch (JavaLayerException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}




