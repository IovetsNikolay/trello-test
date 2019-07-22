package Exam;

import java.util.ArrayList;
import java.util.List;

public class CamelCase {

    public static void main(String[] args) {
        String[] camelCaseArr = {"myAwsomePhrase", "iLoveJustinBieber", "captainJackSparrow"};
        String[] snakeCaseArr = {"", "", "", ""};
        for (int i = 0; i < camelCaseArr.length; i++) {
            char[] charArr = camelCaseArr[i].toCharArray();
            List<Character> charList = new ArrayList<>();
            for (char c : charArr) {
                charList.add(c);
            }
            for (int j = 0; j < charList.size(); j++) {
                if (Character.isUpperCase(charList.get(j))) {
                    snakeCaseArr[i] += ("_" + Character.toLowerCase(charList.get(j)));
                } else {
                    snakeCaseArr[i] += charList.get(j);
                }
            }
        }
        for (String s : snakeCaseArr) {
            System.out.println(s);
        }
    }
}
