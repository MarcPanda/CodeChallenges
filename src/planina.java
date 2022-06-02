import java.util.Scanner;

public class planina {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		int n = in.nextInt();
		int sidePts = 2;
		for (int i = 0; i < n; i++) {
			sidePts += sidePts - 1;
		}
		
		System.out.println(sidePts * sidePts);
	}
}
