package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class DictionaryManagement {

    public Dictionary dictionary = new Dictionary();

    public Word getWordByIndex(int index) {
        return dictionary.Dict.get(index);
    }

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
            Word word = new Word(word_target, word_explain);
            dictionary.add(word);
        }
    }

    //File file = new File(FILE_URL);
    public void insertFromFile() throws IOException {
        String FILE_URL = "data/dictionaries.txt";
        File file = new File(FILE_URL);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String temp = sc.nextLine();
            String[] words = temp.split("\\t");
            if (words.length == 2) {
                dictionary.add(new Word(words[0], words[1]));
            }

        }
    }

    public String dictionaryLookup(String target) {
//        System.out.print("Nhập từ muốn tìm: ");
//        Scanner sc = new Scanner(System.in);
//        String word = sc.nextLine();
//        System.out.print(word);
        int index = getIndexByWord(target);
        if (index != -1)
            return getWordByIndex(index).getWord_explain();
        return "    ";
    }

    public int getIndexByWord(String word) {
        for (int i = 0; i < dictionary.Dict.size(); i++) {
            if (word.equals(dictionary.Dict.get(i).getWord_target())) {
                return i;
            }
        }
        return -1;
    }

    public void addWord() {
        Word word = new Word();
        Scanner sc = new Scanner(System.in);
        System.out.print("\nThêm từ mới\nNhập từ tiếng Anh: ");
        word.setWord_target(sc.nextLine());
        System.out.print("Nhập nghĩa của từ: ");
        word.setWord_explain(sc.nextLine());
        dictionary.Dict.add(word);
        this.dictionaryExportToFile();
    }

    public void editWord(String target, String explain) {
        int index = getIndexByWord(target);
        dictionary.Dict.get(index).setWord_target(target);
        dictionary.Dict.get(index).setWord_explain(explain);
        this.dictionaryExportToFile();
    }

    public void removeWord(String target) {
        int index = getIndexByWord(target);
        dictionary.Dict.remove(index);
        this.dictionaryExportToFile();
    }

    public ArrayList<String> dictionarySearcher(String target) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < dictionary.Dict.size(); i++) {
            String temp = dictionary.Dict.get(i).getWord_target();
            if (temp.indexOf(target) == 0) {
                result.add(temp);
            }
        }
        Collections.sort(result);
        return result;
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
