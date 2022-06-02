import java.util.Scanner;

public class fizzbuzz {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		int X = in.nextInt();
		int Y = in.nextInt();
		int N = in.nextInt();
		for (int i = 1; i <= N; i++) {
			if (i % X == 0 && i % Y == 0)
				System.out.println("FizzBuzz");
			else if (i % X == 0)
				System.out.println("Fizz");
			else if (i % Y == 0)
				System.out.println("Buzz");
			else
				System.out.println(i);
		}
	}
}
