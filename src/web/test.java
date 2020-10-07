package web;

import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
//        System.out.print("hello");
        GoogleAPI a = new GoogleAPI();
        System.out.print(a.search("hello"));
    }
}
