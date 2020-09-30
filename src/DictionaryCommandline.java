//import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import java.io.File;
import java.io.IOException;

public class DictionaryCommandline {
    public DictionaryManagement dictionaryManagement = new DictionaryManagement();

    public void showAllWord() {
        System.out.println("|English           |Vietnamese");
        for (int i = 0; i < dictionaryManagement.dictionary.getDict().size(); i++) {
            System.out.println("|"+ dictionaryManagement.dictionary.getDict().get(i).getWord_target()
                    + "         "
                    + "|"
                    + dictionaryManagement.dictionary.getDict().get(i).getWord_explain());
        }
    }


    public static void main(String[] args) throws IOException {
        DictionaryCommandline a = new DictionaryCommandline();
        //a.dictionaryManagement.insertFromCommandLine();

        a.dictionaryManagement.insertFromFile();
        a.showAllWord();
    }
}
