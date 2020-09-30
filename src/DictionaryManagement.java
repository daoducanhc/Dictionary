import java.io.File;
import java.io.IOException;
import java.lang.invoke.SwitchPoint;
import java.util.Scanner;

public class DictionaryManagement {

    public Dictionary dictionary = new Dictionary();

    public Word getWordByIndex(int index){
        return dictionary.getDict().get(index);
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
        //alo cai dmm
        String FILE_URL = "./data/dictionaries.txt";
        File file = new File(FILE_URL);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String temp = sc.nextLine();
            String[] words = temp.split("\\t");
            if(words.length == 2){
                dictionary.add(new Word(words[0], words[1]));
            }

        }
    }

    void INset(){

    }
}
