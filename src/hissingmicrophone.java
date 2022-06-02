import java.util.Scanner;

public class hissingmicrophone {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		String word = in.next();
		boolean previousIsS = false;
		for (char c : word.toCharArray()) {
			boolean currentIsS = c == 's';
			if (currentIsS && previousIsS) {
				System.out.println("hiss");
				return;
			}
			previousIsS = currentIsS;
		}
		
		System.out.println("no hiss");
	}
}
