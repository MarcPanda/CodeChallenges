import java.util.Scanner;

public class gcvwr {
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		int G = in.nextInt(); // P charge complète
		int T = in.nextInt(); // P à vide
		int N = in.nextInt();
		int wSum = 0;
		for (int i = 0; i < N; i++) {
			wSum += in.nextInt();
		}
		System.out.println(((G - T) / 10) * 9 - wSum);
	}
}
