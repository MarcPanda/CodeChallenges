import java.util.Scanner;

public class faktor {
    static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        int A = in.nextInt();
        int I = in.nextInt();
        System.out.println((A * (I-1)) + 1);
    }
}

