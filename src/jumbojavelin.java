import java.util.Scanner;

public class jumbojavelin {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		
		int n = in.nextInt();
		
		int sum = in.nextInt();
		
		for (int i = 1; i < n; i++) {
			sum += in.nextInt() - 1;
		}
		
		System.out.println(sum);
	}
}
