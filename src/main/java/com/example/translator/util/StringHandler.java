package com.example.translator.util;

public class StringHandler {

    public static String[] separateString(String inputString) {

        return inputString.split("\u0020");
    }

    public static String uniteString(String[] words) {

        return String.join("\u0020", words);
    }
}
