package sample;//import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import java.io.IOException;

public class DictionaryCommandline {
    public static DictionaryManagement dictionaryManagement = new DictionaryManagement();

    public static void main(String[] args) throws IOException {

    }

    public void showAllWord() {
        System.out.println(DictionaryManagement.dictionary.Dict.size());
        System.out.println("|English           |Vietnamese");

        for (int i = 0; i < DictionaryManagement.dictionary.Dict.size(); i++) {
            String target = dictionaryManagement.getWordByIndex(i).getWord_target();
            String explain = dictionaryManagement.getWordByIndex(i).getWord_explain();
            System.out.format("|%s\t|%s\n", target, explain);
        }
    }
}
