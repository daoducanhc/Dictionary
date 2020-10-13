package sample;

import java.util.ArrayList;

public class Dictionary {
    protected ArrayList<Word> Dict = new ArrayList<Word>();

//    public ArrayList<sample.Word> getDict() {
//        return Dict;
//    }

//    public void setDict(ArrayList<sample.Word> Dict) {
//        this.Dict = Dict;
//    }

    public void add(Word word) {
        Dict.add(word);
    }
}
