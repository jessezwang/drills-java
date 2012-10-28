import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ConcatenatedPalindrome {
	static int longestPalindrome(String s){
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
		for (int i=1; i<P.length-1; i++)
			if (P[i]>ret) ret=P[i];
		return ret;
	}
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
//		Scanner in = new Scanner(System.in);
		Scanner in = new Scanner(new File("src/input00.txt"));
		int N = in.nextInt();
		int M = in.nextInt();
		int ret = 0;
		String[] data = new String[N*2];
		for (int i=0; i<N; i++){
			data[i] = in.next();
			data[i+N] = new StringBuffer(data[i]).reverse().toString();
		}
		for (int i=0; i<N; i++)
			for (int j=N; j<2*N; j++){
				int p=0;
				while (data[i].charAt(p)==data[j].charAt(p)) p++;
				int q=p, r=0, t=0;
				int tmp = p;
				
				while (p+1<M && data[i].charAt(p)==data[i].charAt(p+1)) {r++; p++;}
				while (q+1<M && data[j].charAt(q)==data[j].charAt(q+1)) {t++; q++;}
				int u = longestPalindrome(data[i].substring(tmp));
				int v = longestPalindrome(data[j].substring(tmp));
				int max=u;
				if (v>max) max=v;
				if (r>max) max=r;
				if (t>max) max=t;
				if (max+2*p>ret) ret=max+2*p;
			}
		System.out.println(ret);			
	}

}
