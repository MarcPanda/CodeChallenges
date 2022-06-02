import java.util.Scanner;

public class tarifa {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		int x = in.nextInt();
		int n = in.nextInt();
		
		int cumul = 0;
		
		for (int i = 0; i < n; i++) {
			cumul += x;
			int p = in.nextInt();
			cumul -= p;
		}
		
		cumul += x;
		
		System.out.println(cumul);
	}
}
