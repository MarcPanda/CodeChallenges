import java.util.Scanner;

public class toilet {
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		String sequence = in.nextLine();

		int nbRule1 = 0;
		int nbRule2 = 0;
		int nbRule3 = 0;
		
		/*boolean seatUp = sequence.charAt(0) == 'U';
		
		// règle 1 : siège levée après chaque passage
		for (int i = 1; i < sequence.length(); i++) {
			if (sequence.charAt(i) == 'D') {
				if (seatUp) {
					nbRule1++;
				}
				nbRule1++;
			}
			else { // char == 'U'
				if (!seatUp) {
					nbRule1++;
				}
			}
			seatUp = true;
		}
		
		// règle 2 : siège baissée après chaque passage
		seatUp = sequence.charAt(0) == 'U';
		for (int i = 1; i < sequence.length(); i++) {
			if (sequence.charAt(i) == 'D') {
				if (seatUp) {
					nbRule2++;
				}
			}
			else { // char == 'U'
				if (!seatUp) {
					nbRule2++;
				}
				nbRule2++;
			}
			seatUp = false;
		}
		
		// règle 3 : on laisse comme on veut car flemme
		seatUp = sequence.charAt(0) == 'U';
		for (int i = 1; i < sequence.length(); i++) {
			if (sequence.charAt(i) == 'D') {
				if (seatUp) {
					nbRule3++;
				}
				seatUp = false;
			}
			else { // char == 'U'
				if (!seatUp) {
					nbRule3++;
				}
				seatUp = true;
			}
		}*/
		
		boolean seatUp1 = sequence.charAt(0) == 'U';
		boolean seatUp2 = seatUp1;
		boolean seatUp3 = seatUp1;
		
		for (int i = 1; i < sequence.length(); i++) {
			boolean wantUp = sequence.charAt(i) == 'U';

			if (seatUp1 != wantUp) {
				nbRule1++;
			}
			if (seatUp2 != wantUp) {
				nbRule2++;
			}
			if (seatUp3 != wantUp) {
				nbRule3++;
			}
			
			
			
			if (wantUp) {
				nbRule2++;
			}
			else {
				nbRule1++;
			}
			
			seatUp1 = true;
			seatUp2 = false;
			seatUp3 = wantUp;
			
			
		}

		System.out.println(nbRule1);
		System.out.println(nbRule2);
		System.out.println(nbRule3);
		
	}
}
