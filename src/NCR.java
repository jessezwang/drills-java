import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class NCR {
	static final Long M = 142857L;
	static Map<Long, Long> special = new HashMap<Long, Long>();
	static Map<Long, ArrayList<Long>> shortcut = new HashMap<Long,  ArrayList<Long>>();
	static Map<Long, Long> cycle = new HashMap<Long, Long>();

	static void initSpecial(){
		long N = M;
		long i = N-1;
	    while (i>1){
	    	if (N%i==0){
	    		if (special.containsKey(N/i))
	    			special.put(N/i, special.get(N/i)+1);
	    		else
	    			special.put(N/i, 1L);
		        N=i;
		    }
	    	i--;
	    }
	    if (special.containsKey(N/i))
			special.put(N/i, special.get(N/i)+1);
		else
			special.put(N/i, 1L);
	}
	
	static void initShortcut(){
		for (long prime: special.keySet()){
			long k = special.get(prime);
			long P_K = longPow(prime,k);
			shortcut.put(P_K, new ArrayList<Long>());
			long remain=1;
			for (long i=1L; i<(P_K==prime?prime*prime:P_K*prime); i++){
				long ii=i;
				while(ii%prime==0){
					ii/=prime;
				}
				remain*=ii;
				remain%=P_K;
				shortcut.get(P_K).add(remain);	
			}
		}
	}
	
	static long powerOfFactor(long prime, long total){
		long count = 0;
		while(total>=prime){
			total = total/prime;
			count += total;
		}
		return count;
	}
	

	static long calcNonPrime(long P_K, long N){
		long loop;
		long remain;
		long ret = 1L;
		int len = shortcut.get(P_K).size();
		if (P_K==27){
			loop = N%(P_K*P_K);
			remain = loop%81;
			loop = loop/81;
		}
		else{
			remain = N%(P_K*P_K);
			loop = 0;
		}
		for (long i=0; i<loop; i++){
			ret*=shortcut.get(P_K).get(len-1);
			ret%=P_K;
		}
		if (remain!=0){	
			ret*=shortcut.get(P_K).get((int)(remain-1) );
			ret%=P_K;
		}
		return ret;
	}
	
	static long reverse(long P_K, long num){
		long ret=1;
		for (long i=1; i<P_K; i++)
			if ( (i*num)%P_K==1) { ret=i; break; }
		return ret;
	}
	
	static long longPow(long base, long k){
		long ret = 1;
		for (long i=0; i<k; i++){
			ret *=base;
		}
		return ret;
	}
	
	static long bigPow(long base, long u){
		if (u==1) return base;
		if (u==0) return 1;
		if (u%2==0) return (bigPow(base, u/2)%M) * (bigPow(base, u/2)%M) %M;
		else return ((bigPow(base, u/2)%M) * (bigPow(base, u/2)%M)) %M  * base;
	}
	
	static long gcd(long a, long b){
		if (a<b) {
			long tmp = a;
			a = b;
			b= tmp;
		}
		while(b!=0){
			long tmp = b;
			b = a%b;
			a = tmp;
		}
		return a;
	}
	
	static long nCr(long N, long R){
		long ret = 0;
		
		Map<Long, Long> factors = new HashMap<Long, Long>();		
		for (long prime: special.keySet()){
			long P_K = longPow(prime, special.get(prime));
			long u = powerOfFactor(prime, N)-powerOfFactor(prime,R)-powerOfFactor(prime, N-R);
			long P_U = bigPow(prime, u);
			if (u>=special.get(prime)){
				factors.put(P_K, 0L);
			}
			else{
				long a = calcNonPrime(P_K, N);
				long b = calcNonPrime(P_K, N-R);
				long c = calcNonPrime(P_K, R);
				long tmp = a*reverse(P_K, b)*reverse(P_K, c) % P_K;
				tmp = tmp * P_U % P_K;
				factors.put(P_K, tmp);
			}
		}
		for (long P_K: factors.keySet()){
			long factor = 1L;
			for (long P_KK: factors.keySet())
				if (P_KK!=P_K) factor*=P_KK;
			for (long i=1; i<P_K; i++)
				if ( (i*factor)%P_K == 1 ){
					ret+=i*factor*factors.get(P_K);
					ret%=M;
					break;
				}
		}
		return ret;
	}
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		initSpecial();
		initShortcut();	
//		Scanner in = new Scanner(System.in);
		Scanner in = new Scanner(new File("src/input00.txt"));
		int I = in.nextInt();
		for (int i=0; i<I; i++){
			long N = in.nextLong();
			long R = in.nextLong();
			System.out.println(nCr(N,R));
		}
	}

}
