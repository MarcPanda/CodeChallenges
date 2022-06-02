import java.util.Scanner;

public class whichisgreater {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		int a = in.nextInt();
		int b = in.nextInt();
		System.out.println(a > b ? "1" : "0");
	}
}
