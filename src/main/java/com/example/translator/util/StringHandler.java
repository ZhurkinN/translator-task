package com.example.translator.util;

public class StringHandler {

    public static String[] separateString(String inputString) {

        String[] words = inputString.split("\u0020");
        return words;
    }

    public static String uniteString(String[] words) {

        String outString = String.join("\u0020", words);
        return outString;
    }
}
