import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class RandomNumberGenerator {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("src/input00.txt"));
		int K = in.nextInt();
		int a, b, c;
		for (int i=0; i<K; i++){
			a = in.nextInt();
			b = in.nextInt();
			c = in.nextInt();
			System.out.println();
		}
	}

}
