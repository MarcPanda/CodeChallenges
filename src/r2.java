import java.util.Scanner;

public class r2 {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		int r1 = in.nextInt();
		int s = in.nextInt();
		/*
		 * s = (r1 + r2) / 2
		 * 2s = r1 + r2
		 * 2s - r1 = r2
		 */
		System.out.println(2 * s - r1);
	}
}
