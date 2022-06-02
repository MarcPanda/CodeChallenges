import java.util.Scanner;

public class dicecup {
    static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        int min = Math.min(n, m);
        int max = Math.max(n, m);
        for (int i = min + 1; i <= max + 1; i++) {
        	System.out.println(i);
        }
    }
}

