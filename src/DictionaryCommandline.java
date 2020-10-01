//import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DictionaryCommandline {
    public DictionaryManagement dictionaryManagement = new DictionaryManagement();

    public void showAllWord() {
        System.out.println(dictionaryManagement.dictionary.Dict.size());
        System.out.println("|English           |Vietnamese");

        for (int i = 0; i < dictionaryManagement.dictionary.Dict.size(); i++) {
            String target = dictionaryManagement.getWordByIndex(i).getWord_target();
            String explain = dictionaryManagement.getWordByIndex(i).getWord_explain();
            System.out.format("|%s\t|%s\n", target, explain);
        }
    }


    public static void main(String[] args) throws IOException {
        DictionaryCommandline a = new DictionaryCommandline();
        //a.dictionaryManagement.insertFromCommandLine();

        a.dictionaryManagement.insertFromFile();
        a.showAllWord();
        a.dictionaryManagement.addWord();
        a.showAllWord();
        a.dictionaryManagement.dictionaryExportToFile();

//        a.dictionaryManagement.editWord();
//        a.showAllWord();
//        a.dictionaryManagement.removeWord();
//        a.showAllWord();
       // a.dictionaryManagement.dictionaryLookup();
        //a.showAllWord();
        //System.out.println(a.dictionaryManagement.getIndexByWord("gas stove"));
//        String x = a.dictionaryManagement.dictionaryLookup();
//        System.out.print(x);
    }
}
