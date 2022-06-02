import java.util.Scanner;

public class qaly {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		int n = in.nextInt();
		
		double sum = 0;
		
		for (int i = 0; i < n; i++) {
			double q = Double.parseDouble(in.next());
			double y = Double.parseDouble(in.next());
			sum += q * y;
		}
		
		System.out.println(sum);
	}
}
