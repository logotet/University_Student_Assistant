package com.project.universitystudentassistant.utils;

public class AppStringFormatter {

    public static String replaceSymbols(String value){
        return value.replace("|", ",")
                .replace("�", "'")
                .replace("\"", "");
    }

    public static String replaceZeros(int number){
        return String.valueOf(number).replace("000", "k");
    }
}
