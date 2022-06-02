import java.util.Scanner;

public class heimavinna {
    static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        String input = in.nextLine();
        String[] split = input.split(";");
        int count = 0;
        for (String s : split) {
        	if (s.contains("-")) {
        		String[] intervalSplit = s.split("-");
        		int iBegin = Integer.parseInt(intervalSplit[0]);
        		int iEnd = Integer.parseInt(intervalSplit[1]);
        		count += iEnd - iBegin + 1;
        	}
        	else {
        		count++;
        	}
        }
        System.out.println(count);
    }
}

