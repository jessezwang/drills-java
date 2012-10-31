import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class NCR {
	static final long M = 142857;
	static long nCr(long N, long R){
		HashSet<Long> set = new HashSet<Long>();
		long ret = 1;
		long r=R;
		while (r>1){
			for (long n=N/r*r, factor=N/r; n>N-R; n-=factor, r--){
				System.out.print(r);
				System.out.print(":");
				System.out.println(factor*r);
				if (!set.contains(n)) {
					ret *= factor%M;
					ret %= M;
					set.add(n);
					System.out.println(ret);
				}
			}
		}
		for (long n=N; n>N-R; n--)
			if (!set.contains(n)){
				ret *= n%M;
				ret %= M;
				System.out.print(n);
				System.out.print(":");
				System.out.println(ret);

			}
		return ret;
	}
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(11);
		pq.add(12);
		System.out.print(pq.peek());
////		Scanner in = new Scanner(System.in);
//		Scanner in = new Scanner(new File("src/input00.txt"));
//		int I = in.nextInt();
//		for (int i=0; i<3; i++){
//			long N = in.nextLong();
//			long R = in.nextLong();
//			if (R>N/2) R=N-R;
//			System.out.println(nCr(N,R));
//		}
	}

}
