package Exam;

public class Quote {

    public static void main(String[] args) {
        String text = "Это ##тестовый пример## для задачи ##на## строки";
        String[] strArr = text.split("##");
        for (int i = 0; i < strArr.length; i++) {
            if ((i % 2) != 0) {
                String tempStr = "<" + strArr[i] + ">";
                strArr[i] = tempStr;
            }
        }
        String resultString = "";
        for(String str : strArr) {
            resultString += str;
        }
            System.out.println(resultString);
    }
}
