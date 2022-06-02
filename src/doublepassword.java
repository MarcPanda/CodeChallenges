import java.util.Scanner;

public class doublepassword {
    static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        String pw1 = in.next();
        String pw2 = in.next();
        int nbValidPw = 1;
        for (int i = 0; i < 4; i++) {
            if (pw1.charAt(i) != pw2.charAt(i))
            	nbValidPw *= 2;
        }
        System.out.println(nbValidPw);
    }
}

