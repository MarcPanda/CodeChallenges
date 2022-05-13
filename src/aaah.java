import java.util.Scanner;

public class aaah {
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		String john = in.nextLine();
		String doctor = in.nextLine();
		
		// 1ère option : compter le nombre de a
		
		/*int johnNbA = 0;
		for (int i = 0; i < john.length(); i++) {
			if (john.charAt(i) == 'a')
				johnNbA++;
		}

		int docNbA = 0;
		for (int i = 0; i < doctor.length(); i++) {
			if (doctor.charAt(i) == 'a')
				docNbA++;
		}
		
		//System.out.println(johnNbA + " " + docNbA);
		if (johnNbA < docNbA) {
			System.out.println("no");
		}
		else {
			System.out.println("go");
		}*/
		
		
		// 2ème solution : comparer la longueur des chaines de caractères
		
		if (john.length() < doctor.length()) {
			System.out.println("no");
		}
		else {
			System.out.println("go");
		}
		
	}
}
