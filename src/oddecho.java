import java.util.Scanner;

public class oddecho {
    static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String s = in.next();
            if (i % 2 == 0)
            	System.out.println(s);
        }
    }
}

