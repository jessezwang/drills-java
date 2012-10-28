import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class RandomNumberGenerator {
	static long gcd(long a, long b) {
		   if (b==0) return a;
		   return gcd(b,a%b);
		}
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
//		Scanner in = new Scanner(System.in);
		Scanner in = new Scanner(new File("src/input00.txt"));
		int K = in.nextInt();
		long a, b, c;
		long denom, num;
		for (int i=0; i<K; i++){
			a = in.nextInt();
			b = in.nextInt();
			c = in.nextInt();
			if (a>b){
				long tmp = a;
				a = b;
				b = tmp;
			}
			if (a+b<=c){
				num=denom=1;
			} else if (b<=c) {
				// result = (c-a)/b + (b+a-c)/b * ( (c-b)/a + (a+b-c)/2a )
				denom = 2*a*b;
				num = 2*a*(c-a) + (b+a-c)*(c-b+a);
			} else if (a<=c) {
				// result = c/b * ( (c-a)/b+ (b-c+a)/2b )
				// denom = 2*b*b;
				// num = c*(c-a+b);
				denom = 2*b;
				num = 2*c-a;
			} else {
				// result = c^2/2ab
				denom = 2*a*b;
				num = c*c;
			}
			long common = gcd(denom, num);
			denom/=common;
			num/=common;
			StringBuilder builder = new StringBuilder();
			builder.append(Long.toString(num));
			builder.append("/");
			builder.append(Long.toString(denom));
			System.out.println(builder.toString());
		}
	}

}
