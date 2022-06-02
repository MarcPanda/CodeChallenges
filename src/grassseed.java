import java.util.Scanner;

public class grassseed {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		double C = Double.parseDouble(in.next());
		int L = in.nextInt();
		double sumArea = 0;
		for (int i = 0; i < L; i++) {
			double w = Double.parseDouble(in.next());
			double l = Double.parseDouble(in.next());
			sumArea += w * l;
		}
		
		System.out.println(sumArea * C);
	}
}
