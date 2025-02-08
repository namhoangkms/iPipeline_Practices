package iPipelines.com.utilities

public class Utils {
	/**
	 * Generates a random number.
	 * @return int - A random number.
	 * 
	 * @author namhoang
	 */
	def static int generateRandomNumber() {
		int randomNumber = (int)(Math.random() * 90000) + 10000
		return randomNumber
	}
}
