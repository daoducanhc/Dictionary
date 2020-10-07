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
import java.util.ArrayList;
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
        a.dictionaryManagement.insertFromFile();

        Target.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                Explain.setText(a.dictionaryManagement.dictionaryLookup(target));

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
                Explain.setText(a.dictionaryManagement.dictionaryLookup(m));
            }
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

    @FXML
    public void add(ActionEvent event) {
        //todo
    }

    @FXML
    public void delete(ActionEvent event) {
        //todo
    }
}




