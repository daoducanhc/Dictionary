package sample;

import java.util.ArrayList;

public class Dictionary {
    protected ArrayList<Word> Dict;
    protected Trie storeTargetTrie;

    public Dictionary() {
        Dict = new ArrayList<>();
        storeTargetTrie = new Trie();
    }


}
