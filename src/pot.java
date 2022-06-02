import java.util.Scanner;

public class pot {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		int n = in.nextInt();
		
		int sum = 0;
		
		for (int i = 0; i < n; i++) {
			int p = in.nextInt();
			int number = p / 10;
			int pow = p % 10;
			
			long result = 1;
			for (int j = 1; j <= pow; j++) {
			   result *= number;
			}
			
			sum += result;
		}
		
		System.out.println(sum);
	}
}
