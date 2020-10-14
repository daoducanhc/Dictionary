package sample;

import java.util.ArrayList;
import java.util.Collections;

public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void add(String target, int indexOfArray) {
        target = target.toLowerCase();
        TrieNode pointer = root;
        for (int i = 0; i < target.length(); i++) {
            int index = target.charAt(i) - 'a';
            if (index == -65) index = TrieNode.ALPHABET_LETTERS - 1;
            if (pointer.getChildren()[index] == null) {
                pointer.getChildren()[index] = new TrieNode();
            }
            pointer = pointer.getChildren()[index];
        }
        pointer.setIdArray(indexOfArray);
    }

    public void remove(String target) {
        target = target.toLowerCase();
        TrieNode pointer = root;
        for (int i = 0; i < target.length(); i++) {
            int index = target.charAt(i) - 'a';
            if (index == -65) index = TrieNode.ALPHABET_LETTERS - 1;
            pointer = pointer.getChildren()[index];
        }
        pointer.setIdArray(-1);
    }

    public int search(String target) {
        target = target.toLowerCase();
        TrieNode pointer = root;
        for (int i = 0; i < target.length(); i++) {
            int index = target.charAt(i) - 'a';
            if (index == -65) index = TrieNode.ALPHABET_LETTERS - 1;
            if (pointer.getChildren()[index] != null) {
                pointer = pointer.getChildren()[index];
            } else return -1;
        }
        if (pointer.getIdArray() != -1) {
            return pointer.getIdArray();
        } else return -1;
    }

    public ArrayList<String> suggest(String target) {
        ArrayList<String> result = new ArrayList<>();

        target = target.toLowerCase();
        TrieNode pointer = root;
        for (int i = 0; i < target.length(); i++) {
            int index = target.charAt(i) - 'a';
            if (index == -65) index = TrieNode.ALPHABET_LETTERS - 1;
            if (pointer.getChildren()[index] != null) {
                pointer = pointer.getChildren()[index];
            } else return null;
        }
        if(pointer.getIdArray() != -1){
            result.add(target);
        }

        for (int i = 0; i < TrieNode.ALPHABET_LETTERS; i++) {
            if (pointer.getChildren()[i] != null) {
                char letter;
                if (i == TrieNode.ALPHABET_LETTERS - 1) {
                    letter = ' ';
                } else {
                    letter = (char) ((int) ('a') + i);
                }
                ArrayList<String> recursive = suggest(target + letter);
                result.addAll(recursive);
            }
        }
        Collections.sort(result);
        return result;
    }
}
