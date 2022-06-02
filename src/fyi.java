import java.util.Scanner;

public class fyi {
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		String number = in.nextLine();
		System.out.println(number.startsWith("555") ? "1" : "0");
	}
}
