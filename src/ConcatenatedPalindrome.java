import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class ConcatenatedPalindrome {
	//private long Q = ((2L<<32)-1)*((2L<<32)-1)-2;
	static private String[] data;
	static private long[] hash;
	static private int[] longestPrefix;
	static int M;
	static int N;
	static private int[][] longestPalin; 
	
	static int[] longestPalindrome(String s){
		int ret = 0;
		char[] T = new char[s.length()*2+1];
		int[] P = new int[s.length()*2+1];
		for (int i=0; i<s.length(); i++){
			T[2*i]='#';
			T[2*i+1]=s.charAt(i);
		}
		T[2*s.length()]='#';
		T[0]=T[T.length-1]=0;
		for (int i=1; i<T.length-1; i++){
			int k=0;
			while(i-k-1>=0 && i+k+1<=T.length-1 && T[i-k-1]==T[i+k+1]) k++;
			P[i]=k;
			for (int j=i+1; j<=i+P[i]; j++)
				if (j+P[2*i-j]<=i+P[i]) P[j]=P[2*i-j];
				else {i=j-1; break;}
		}
		int ind = 0;
		for (int i=1; i<P.length-1; i++)
			if (P[i]>ret) {ret=P[i]; ind=i;}
		int[] result = new int[2];
		if (ret==1) {
			result[0] = 0; 
			result[1] = 0;
		}
		else{
			result[0] = (ind-ret)/2;
			result[1] = (ind+ret)/2-1;
		}
		return result;
	}
	
	static void findLongestPalindrome(){
		for (int i=0; i<N*2; i++){
			int[] result = longestPalindrome(data[i]);
			longestPalin[i][0] = result[0];
			longestPalin[i][1] = result[1];
		}		
	}
	
	static void findLongestPrefix(int d, ArrayList<Integer> candidates) {
		HashMap<Long, ArrayList<Integer>> hashMap = 
				new HashMap<Long,ArrayList<Integer>>();
		int count1=0, count2=0;
		for (int i: candidates){
			if (i<N) count1++;
			if (i>=N) count2++;
		}
		if (count1==0 && count2==0) return;
		if (count1==0 || count2==0) {
			for (int j: candidates)
				longestPrefix[j] = d-1;
			return;
			}
		for (int i : candidates){
			hash[i] = ((hash[i] << 5) - hash[i]) + data[i].charAt(d);
			if (hashMap.containsKey(hash[i]))
				hashMap.get(hash[i]).add(i);
			else{
				ArrayList<Integer> item = new ArrayList<Integer>();
				item.add(i);
				hashMap.put(hash[i], item);
			}
		}
		if (d<M-1)
			for (Long newCandidates: hashMap.keySet())
				findLongestPrefix(d+1, hashMap.get(newCandidates));
	}
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
//		Scanner in = new Scanner(System.in);
		Scanner in = new Scanner(new File("src/input00.txt"));
		N = in.nextInt();
		M = in.nextInt();
		hash = new long[N*2];
		data = new String[N*2];
		longestPrefix = new int[N*2];
		longestPalin = new int[N*2][2];
		for (int i=0; i<N; i++){
			data[i] = in.next();
			data[i+N] = new StringBuffer(data[i]).reverse().toString();
		}
		ArrayList<Integer> candidates = new ArrayList<Integer>();
		for (int i=0; i<2*N; i++)
			candidates.add(i);
		findLongestPrefix(0, candidates);
		findLongestPalindrome();
		int ret = findLongestConcatenatedPalindrome();
		System.out.println(ret);			
	}

	private static int findLongestConcatenatedPalindrome() {
		int ret=0;
		for (int i=0; i<2*N; i++){
			int tmp = 2* longestPrefix[i]+2;
			if (longestPrefix[i]>=longestPalin[i][0]-1){
				int overlap = longestPrefix[i]-longestPalin[i][0]+1;
				tmp += longestPalin[i][1]-longestPalin[i][0]+1-2*overlap;
			}
			if (tmp>ret) ret=tmp;
		}	
		return ret;
	}
}
