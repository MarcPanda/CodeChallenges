import java.util.Scanner;

public class heartrate {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			
			int b = in.nextInt();
			double p = Double.parseDouble(in.next());
			
			double avg = 60 * b / p;
			double min = 60 * (b-1) / p;
			double max = 60 * (b+1) / p;
			
			System.out.println(min + " " + avg + " " + max);
			
		}
		
	}
}
