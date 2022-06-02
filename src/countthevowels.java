import java.util.Arrays;
import java.util.Scanner;

public class countthevowels {
    static Scanner in = new Scanner(System.in);
    static char[] vowels = new char[] { 'A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u' };
    public static void main(String[] args) {
        char[] text = in.nextLine().toCharArray();
        int vowelCount = 0;
        for (char c : text) {
            if (Arrays.binarySearch(vowels, c) >= 0)
            	vowelCount++;
        }
        System.out.println(vowelCount);
    }
}

