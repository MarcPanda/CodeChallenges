import java.util.Scanner;

public class nastyhacks {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			int r = in.nextInt(); // revenues sans pub
			int e = in.nextInt(); // revenues avec pub
			int c = in.nextInt(); // couts pub
			
			int p = e - c;
			
			if (r > p)
				System.out.println("do not advertise");
			else if (r < p)
				System.out.println("advertise");
			else
				System.out.println("does not matter");
			
			
		}
		
	}
}
