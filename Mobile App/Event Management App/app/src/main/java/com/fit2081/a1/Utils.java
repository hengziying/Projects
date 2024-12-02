package com.fit2081.a1;
import java.util.Random;
import java.util.regex.Pattern;

public class Utils {
    public static int getRandomInt(int lowerBound, int upperBound) {
        int range = upperBound - lowerBound + 1;
        return new Random().nextInt(range) + lowerBound;
    }

    public static char getRandomChar(){
        char c = (char)getRandomInt(65,90);
        return c;
    }

    public static String generateRandomDigits(int length){
        String ret = "";
        for (int i = 0; i < length; i++){
            ret = ret + String.valueOf(getRandomInt(0,9));
        }
        return ret;
    }

    public static String generateRandomString(int length){
        String ret = "";
        for (int i = 0; i < length; i++){
            ret = ret + getRandomChar();
        }
        return ret;
    }

    public static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static int countDelimiter(String str,char d){
        int count=0;
        for (int i=0;i<str.length();i++){
            if (str.charAt(i) == d){
                count++;
            }
        }
        return count;
    }

    public static boolean isStringAlphabetic(String str) {
        // Regular expression to match alphabetic characters
        String regex = "^[a-zA-Z\\s]*$";
        // Compile the regex pattern
        Pattern pattern = Pattern.compile(regex);
        // Check if the string matches the pattern
        return pattern.matcher(str).matches();
    }


    public static boolean isStringAlphanumeric(String str) {
        // Regular expression to match alphanumeric characters
        String regex = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d\\s]*$";
        // Compile the regex pattern
        Pattern pattern = Pattern.compile(regex);
        // Check if the string matches the pattern
        return pattern.matcher(str).matches();
    }
}
