package assistant;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Test {
	static long TIME_LIMIT = 3000;
	
	public static boolean test(String id) {
		
		File samplesDir = OpenKattis.downloadSampleDataFile(id);
		
		List<String> testCases = getAllTestCases(samplesDir);
		
		System.out.println("Found " + testCases.size() + " test cases: " + testCases);
		
		if (testCases.isEmpty()) {
			System.out.println("Not testing anything.");
			return true;
		}

		boolean oneFail = false;
		PrintStream defaultOut = System.out;
		InputStream defaultIn = System.in;
		for (String testCase : testCases) {
			File inFile = new File(samplesDir, testCase + ".in");
			File outFile = new File(samplesDir, testCase + ".ans");
			
			System.out.println("Testing program " + id + " on test case " + testCase + "...");
			
			try (BufferedInputStream progIn = new BufferedInputStream(new FileInputStream(inFile));
					BufferedInputStream goodAns = new BufferedInputStream(new FileInputStream(outFile));
					PipedInputStream progAns = new PipedInputStream((int) outFile.length() * 2 + 1024);
					PipedOutputStream progOut = new PipedOutputStream()) {
				progAns.connect(progOut);

				AtomicBoolean progTimeout = new AtomicBoolean(false);
				AtomicReference<Throwable> progException = new AtomicReference<>();
				
				try (PrintStream psProgOut = new PrintStream(progOut, true)) {
					System.setIn(progIn);
					System.setOut(psProgOut);
					
					AtomicBoolean progStarted = new AtomicBoolean(false);
					Thread thread = new Thread(() -> {
						try {
							progStarted.set(true);
							
							URLClassLoader cl = new URLClassLoader(new URL[] { new File("bin").toURI().toURL() }, null);
							Class<?> testClass = cl.loadClass(id);
							Method main = testClass.getDeclaredMethod("main", String[].class);
							main.invoke(null, new Object[] { new String[0] });
							cl.close();
						} catch (InvocationTargetException e) {
							progException.set(e.getCause());
						} catch (Exception e) {
							System.err.println("Error while trying to test the program:");
							e.printStackTrace();
						}
					}, "Program thread");
					
					thread.start();
					

					SleepUtil.waitUntil(progStarted::get, 10);
					
					SleepUtil.waitWhile(thread::isAlive, 10, TIME_LIMIT);
					
					if (thread.isAlive()) {
						thread.interrupt();
						progTimeout.set(true);
						
						SleepUtil.waitWhile(thread::isAlive, 10, 100);
						if (thread.isAlive()) {
							thread.stop();
						}
					}
					
				} finally {
					System.setIn(defaultIn);
					System.setOut(defaultOut);
					
					progOut.flush();
				}
				
				if (progTimeout.get()) {
					oneFail = true;
					System.err.println("Test case " + testCase + " failed. Took more than " + (TIME_LIMIT / 1000f) + " seconds.");
					continue;
				}
				
				if (progException.get() != null) {
					oneFail = true;
					System.err.println("Test case " + testCase + " failed with exception:");
					progException.get().printStackTrace();
					continue;
				}
				
				List<String> linesGood = getAnswerLines(goodAns);
				List<String> linesProg = getAnswerLines(progAns);
				
				if (linesGood.equals(linesProg)) {
					System.out.println("Test case " + testCase + " succeed:");
				}
				else {
					System.out.println("Test case " + testCase + " failed with invalid answer:");
					oneFail = true;
				}
					
				displaySideBySide(linesGood, linesProg);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (oneFail) {
			System.err.println("At least one test failed.");
		}
		else {
			System.out.println("All tests succeed!");
		}
		
		return !oneFail;
	}
	

	
	static List<String> getAllTestCases(File samplesDir) {
		List<String> testCases = new ArrayList<>();
		for (File file : samplesDir.listFiles()) {
			String fileName = file.getName();
			if (fileName.endsWith(".in"))
				testCases.add(fileName.substring(0, fileName.length() - 3));
		}
		
		return testCases;
	}
	
	
	
	
	static List<String> getAnswerLines(InputStream stream) {
		try (Scanner scanner = new Scanner(stream)) {
			List<String> lines = new ArrayList<>();
			while (scanner.hasNextLine()) {
				lines.add(scanner.nextLine());
			}
			return lines;
		}
	}
	
	
	
	
	static void displaySideBySide(List<String> good, List<String> prog) {
		int maxWidthGood = 0;
		for (String lineGood : good) {
			maxWidthGood = Math.max(maxWidthGood, lineGood.length());
		}
		
		int longestAns = Math.max(good.size(), prog.size()),
				shortestAns = Math.min(good.size(), prog.size());
		for (int i = 0; i < longestAns; i++) {
			if (i < shortestAns) {
				String lineGood = good.get(i);
				String lineProg = prog.get(i);
				System.out.println("good: " + lineGood + " ".repeat(maxWidthGood - lineGood.length()) + " | ans: " + lineProg);
			}
			else if (i >= good.size()) {
				String lineProg = prog.get(i);
				System.out.println("      " + " ".repeat(maxWidthGood) + "   ans: " + lineProg);
			}
			else if (i >= prog.size()) {
				String lineGood = good.get(i);
				System.out.println("good: " + lineGood);
			}
		}
	}
	
	
	
	
	
}
