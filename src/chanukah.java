import java.util.Scanner;

public class chanukah {
    static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        int P = in.nextInt();
        for (int i = 0; i < P; i++) {
            int K = in.nextInt(); // useless, except to print at the end
            int N = in.nextInt(); // number of day
            int c = N + (N * (N + 1)) / 2;
            System.out.println(K + " " + c);
            
            /*
             *  b b b b b b b b
             *  b b b b b b b b
             *    b b b b b b b
             *      b b b b b b
             *        b b b b b
             *          b b b b
             *            b b b
             *              b b
             *                b
             */
        }
    }
}

