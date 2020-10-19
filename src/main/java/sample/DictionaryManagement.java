package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {

    public static Dictionary dictionary = new Dictionary();

    public void insertFromCommandLine() {
        int num_word;
        System.out.print("Bạn muốn nhập bao nhiêu từ mới: ");
        Scanner sc = new Scanner(System.in);
        num_word = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < num_word; i++) {
            System.out.print("Nhập từ tiếng anh: ");
            String word_target = sc.nextLine();
            System.out.print("Nhập nghĩa của từ: ");
            String word_explain = sc.nextLine();
            dictionary.Dict.add(new Word(word_target, word_explain));
            dictionary.storeTargetTrie.add(word_target, dictionary.Dict.size() - 1);
        }
    }

    public void insertFromFile() throws IOException {
        String FILE_URL = "data/dictionaries.txt";
        File file = new File(FILE_URL);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String temp = sc.nextLine();
            String[] words = temp.split("\\t");
            if (words.length == 2 && dictionary.storeTargetTrie.isValid(words[0])) {
                dictionary.Dict.add(new Word(words[0], words[1]));
                dictionary.storeTargetTrie.add(words[0], dictionary.Dict.size() - 1);
            }
        }
    }

    /**
     * Optimize searching function by Trie.
     */
    public int getIndexByTarget(String target) {
        return dictionary.storeTargetTrie.search(target);
    }

    public Word getWordByIndex(int index) {
        return dictionary.Dict.get(index);
    }

    public String dictionaryLookup(String target) {
        int index = getIndexByTarget(target);
        if (index != -1)
            return getWordByIndex(index).getWord_explain();
        return "";
    }

    public void addWord(String target, String explain) {
        Word word = new Word(target, explain);
        dictionary.Dict.add(word);
        dictionary.storeTargetTrie.add(target, dictionary.Dict.size() - 1);
//        this.dictionaryExportToFile();
    }

    public void editWord(String target, String explain, int index) {
//        int index = getIndexByTarget(target);
        dictionary.Dict.get(index).setWord_target(target);
        dictionary.Dict.get(index).setWord_explain(explain);
//        this.dictionaryExportToFile();
    }

    public void removeWord(String target, int index) {
//        int index = getIndexByTarget(target);
        dictionary.Dict.remove(index);
        dictionary.storeTargetTrie.remove(target);
//        this.dictionaryExportToFile();
    }

    public ArrayList<String> dictionarySearcher(String target) {
        return dictionary.storeTargetTrie.suggest(target);
    }

    public void dictionaryExportToFile() {
        try {
            FileWriter myWriter = new FileWriter("data/dictionaries.txt");
            for (Word word : dictionary.Dict) {
                myWriter.write(String.format("%s\t%s\n", word.getWord_target(), word.getWord_explain()));
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
