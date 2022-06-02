import java.util.Scanner;

public class filip {
    static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        int A = Integer.parseInt(new StringBuilder(in.next()).reverse().toString());
        int B = Integer.parseInt(new StringBuilder(in.next()).reverse().toString());
        System.out.println(A > B ? A : B);
    }
}

