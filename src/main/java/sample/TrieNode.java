package sample;

public class TrieNode {
    protected static final int ALPHABET_LETTERS = 27; // 26 and 1 for space
    private final TrieNode[] children;
    private int idArray;

    public TrieNode() {
        children = new TrieNode[ALPHABET_LETTERS];
        idArray = -1;
        for (int i = 0; i < ALPHABET_LETTERS; i++) {
            children[i] = null;
        }
    }

    public int getIdArray() {
        return idArray;
    }

    public void setIdArray(int idArray) {
        this.idArray = idArray;
    }

    public TrieNode[] getChildren() {
        return children;
    }

}
