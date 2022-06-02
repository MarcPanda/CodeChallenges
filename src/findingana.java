import java.util.Scanner;

public class findingana {
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		String containsA = in.nextLine();
		int posA = containsA.indexOf("a");
		System.out.println(containsA.substring(posA));
	}
}
