import java.util.Scanner;

public class greetings2 {
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		String hey = in.nextLine();
		System.out.println("h" + "e".repeat((hey.length() - 2) * 2) + "y");
	}
}
