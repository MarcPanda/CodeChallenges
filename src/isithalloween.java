import java.util.Scanner;

public class isithalloween {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		String month = in.next();
		int day = in.nextInt();
		
		if ((month.equals("OCT") && day == 31)
				|| (month.equals("DEC") && day == 25)) {
			System.out.println("yup");
		}
		else
			System.out.println("nope");
	}
}
