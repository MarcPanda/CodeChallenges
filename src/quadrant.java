import java.util.Scanner;

public class quadrant {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		int x = in.nextInt();
		int y = in.nextInt();
		System.out.println(x > 0 ? (y > 0 ? "1" : "4") : (y > 0 ? "2" : "3"));
	}
}
