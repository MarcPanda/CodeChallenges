import java.util.Scanner;

public class twostones {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		int n = in.nextInt();
		System.out.println((n % 2 == 0) ? "Bob" : "Alice");
	}
}
