import java.util.Scanner;

public class eyeofsauron {
    static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        String text = in.nextLine();
        int eyePos = text.indexOf("()");
        System.out.println((eyePos * 2 == text.length() - 2) ? "correct" : "fix");
    }
}

