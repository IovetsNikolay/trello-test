package Exam;

public class FileName {

    public static void main(String[] args) {
        String filePath = "c:\\WebServers\\home\\testsite\\www\\myfile.txt";
        System.out.println("File name without resolution: " + filePath.substring(filePath.lastIndexOf('\\')+1, (filePath.lastIndexOf('.'))));
    }
}
