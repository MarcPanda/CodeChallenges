import java.util.Scanner;

public class electricaloutlets {
    static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int k = in.nextInt();
            int res = 1;
            for (int j = 0; j < k; j++) {
            	res += in.nextInt() - 1;
            }
            System.out.println(res);
        }
    }
}

