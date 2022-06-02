package assistant;

import java.util.function.BooleanSupplier;

public class SleepUtil {

	public static void waitUntil(BooleanSupplier condition, long testInterval) {
		waitWhile(() -> !condition.getAsBoolean(), testInterval);
	}
	
	public static void waitWhile(BooleanSupplier condition, long testInterval) {
		try {
			while (condition.getAsBoolean()) {
					Thread.sleep(testInterval);
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	public static void waitUntil(BooleanSupplier condition, long testInterval, long timeout) {
		waitWhile(() -> !condition.getAsBoolean(), testInterval, timeout);
	}
	
	public static void waitWhile(BooleanSupplier condition, long testInterval, long timeout) {
		try {
			long endTime = System.currentTimeMillis() + timeout;
			while (System.currentTimeMillis() < endTime && condition.getAsBoolean()) {
				Thread.sleep(testInterval);
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
