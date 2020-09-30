import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;

public class DictionaryManagement {

    public Dictionary dictionary = new Dictionary();

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
        String FILE_URL = "./data/dictionaries.txt";
//        File file = new File(FILE_URL);
//        Scanner sc = new Scanner(file);
        BufferedReader reader = new BufferedReader(new FileReader(FILE_URL));
        String temp;
        while ((temp = reader.readLine()) != null) {
            String[] words = temp.split("\t");
            System.out.println(temp);
            System.out.println(words[1]);
//            for(String each : words){
//                if(!"".equals(each)){
//                    System.out.println(each);
//                }
//            }

//            try{
//                String target = words[0];
//                String explain = words[1];
//                System.out.println(target);
//                System.out.println(explain);
//                dictionary.add(new Word(target, explain));
//
//            }
//            catch (Exception e){
//                System.out.println(e);
//            }

        }
    }
}
