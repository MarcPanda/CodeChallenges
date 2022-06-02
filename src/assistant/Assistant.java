package assistant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Assistant {

	static File SRC_FOLDER = new File("src");
	static File BIN_FOLDER = new File("bin");
	
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		String id = null;
		
		if (id == null && args.length > 0) {
			id = args[0];
		}
		
		while (id == null || !OpenKattis.problemExists(id)) {
			if (id != null) {
				System.out.println("The problem '" + id + "' doesn’t exist on Kattis website.");
			}
			System.out.println("ID of the Kattis problem?");
			id = in.nextLine();
		}
		
		
		boolean canTestNow = checkOrPrepareJavaFiles(id);
		
		if (!canTestNow) {
			System.out.println("Press enter when you want to test the program.");
			in.nextLine();
		}
		
		for(;;) {
			Test.test(id);
			System.out.println("Press enter to re-run the test.");
			in.nextLine();
		}
		
	}
	
	
	
	public static boolean checkOrPrepareJavaFiles(String id) {
		File javaF = new File(SRC_FOLDER, id + ".java");
		if (!javaF.exists()) {
			System.out.println("Creating Java file... You may have to refresh the IDE.");
			try (PrintStream javaOut = new PrintStream(javaF)) {
				javaOut.println("import java.util.Scanner;\n"
						+ "\n"
						+ "public class " + id + " {\n"
						+ "    static Scanner in = new Scanner(System.in);\n"
						+ "    \n"
						+ "    public static void main(String[] args) {\n"
						+ "        String text = in.nextLine();\n"
						+ "        int n = in.nextInt();\n"
						+ "        for (int i = 0; i < n; i++) {\n"
						+ "            \n"
						+ "        }\n"
						+ "    }\n"
						+ "}\n");
				
				//Runtime.getRuntime().exec(new String[] { "C:\\Users\\Marc\\eclipse\\java-2020-03\\eclipse\\eclipse.exe", "--launcher.openFile", javaF.getAbsolutePath() });
				
				return false; // cannot test right now
			} catch (FileNotFoundException e) {
				System.err.println("Unable to create the java file. You may need to create it yourself.");
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				System.err.println("Unable to open the java file in eclipse.");
				e.printStackTrace();
				return false;
			}
		}
		else {
			File classF = new File(BIN_FOLDER, id + ".class");
			if (!classF.exists()) {
				System.out.println("Compiled class file not found. You may have to refresh the IDE or compile the Java file.");
				return false;
			}
		}
		
		return true;
	}
	
	
	
	
	
	
	
}
