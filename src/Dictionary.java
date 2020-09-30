import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
