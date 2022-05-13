import java.util.Arrays;
import java.util.Scanner;

public class sequences {
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		String sequenceQM = in.nextLine();
		
		
		System.out.println(generateAndSumSequences(sequenceQM));
	}
	
	
	static long sortCount(char[] sequence) {
		long count = 0;
		
		int nextTarget = 0;
		
		for (int i = 0; i < sequence.length; i++) {
			if (sequence[i] == '0') {
				count += i - nextTarget;
				nextTarget++;
			}
		}
		
		return count;
	}
	
	
	static long generateAndSumSequences(String sequenceQMStr) {
		// 00111?101?
		char[] sequenceQM = sequenceQMStr.toCharArray();
		
		
		int[] qmIndexes = new int[500000];
		int qmI = 0;
		for (int i = 0; i < sequenceQM.length; i++) {
			if (sequenceQM[i] == '?')
				qmIndexes[qmI++] = i;
		}
		int k = qmI;
		
		
		MyUnsignedBigInteger nbSequences = MyUnsignedBigInteger.powOf2(k);
		
		//System.err.println(nbSequences);
		
		long sumSequence = 0;
		
		for (MyUnsignedBigInteger i = new MyUnsignedBigInteger(0); i.compareTo(nbSequences) < 0; i.add(1)) {
			for (int j = 0; j < k; j++) {
				int qmCurrentIndex = qmIndexes[j];
				boolean isBitOne = i.testBit(j);
				sequenceQM[qmCurrentIndex] = isBitOne ? '1' : '0';
			}
			
			//System.err.println(new String(sequenceQM));
			sumSequence += sortCount(sequenceQM);
			sumSequence %= 1_000_000_007;
			
		}
		
		return sumSequence;
	}
	
	
	static class MyUnsignedBigInteger implements Comparable<MyUnsignedBigInteger> {
		int[] bits = new int[500000 / 32 + 1];
		int mostSigIntPlusOne = 0;
		
		public MyUnsignedBigInteger(int initialValue) {
			bits[0] = initialValue;
			if (initialValue != 0)
				mostSigIntPlusOne = 1;
		}
		
		public void add(int v) { // must be positive
			int oldV = bits[0];
			bits[0] += v;
			
			if (mostSigIntPlusOne == 0 && bits[0] != 0)
				mostSigIntPlusOne = 1;
			
			
			if (bits[0] < oldV) {
				for (int i = 1; i < bits.length; i++) {
					bits[i]++;
					if (i >= mostSigIntPlusOne)
						mostSigIntPlusOne = i + 1;
					if (bits[i] != 0) {
						break;
					}
				}
			}
			
		}
		
		public int compareTo(MyUnsignedBigInteger o) {
			int c = Integer.compare(mostSigIntPlusOne, o.mostSigIntPlusOne);
			if (c != 0)
				return c;
			for (int i = mostSigIntPlusOne - 1; i >= 0; i--) {
				c = Integer.compareUnsigned(bits[i], o.bits[i]);
				if (c != 0)
					return c;
			}
			return 0;
		}
		
		public boolean testBit(int index) {
			return ((bits[index / 32] >>> (index % 32)) & 1) != 0;
		}
		
		@Override
		public String toString() {
			return Arrays.toString(Arrays.copyOfRange(bits, 0, mostSigIntPlusOne));
		}
		
		public static MyUnsignedBigInteger powOf2(int power) {
			MyUnsignedBigInteger i = new MyUnsignedBigInteger(0);
			i.bits[power / 32] = 1 << (power % 32);
			i.mostSigIntPlusOne = power / 32 + 1;
			return i;
		}
	}
	
}
