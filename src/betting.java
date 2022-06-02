import java.util.Scanner;

public class betting {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		int percentOpt1 = in.nextInt();
		int percentOpt2 = 100 - percentOpt1;
		System.out.println(100 / (double) percentOpt1);
		System.out.println(100 / (double) percentOpt2);
	}
}
