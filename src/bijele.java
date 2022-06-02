import java.util.Scanner;

public class bijele {
    static Scanner in = new Scanner(System.in);
    
    static int[] correct = new int[] { 1, 1, 2, 2, 2, 8 };
    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            System.out.print((i > 0 ? " " : "") + (correct[i] - in.nextInt()));
        }
        System.out.println();
    }
}

