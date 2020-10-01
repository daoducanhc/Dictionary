import java.util.ArrayList;

public class Dictionary {
    protected ArrayList<Word> Dict = new ArrayList<>();

//    public ArrayList<Word> getDict() {
//        return Dict;
//    }

//    public void setDict(ArrayList<Word> Dict) {
//        this.Dict = Dict;
//    }

    public void add(Word word) {
        Dict.add(word);
    }
}
