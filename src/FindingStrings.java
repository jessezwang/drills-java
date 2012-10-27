import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FindingStrings {
public	HashSet<String> substrings = new HashSet<String>();
public	String[] sorted_substrings;
	String[] aux;
	
	void sortSubstrings(int low, int high, int d) {
		if (low+10>=high){
			Arrays.sort(sorted_substrings, low, high);
			return;
		}
		final int R=26;
		int[] count = new int[R+2];
		for (int i=0; i<R+2; i++)
			count[i]=0;
		for (int i=low; i<high; i++)
			count[sorted_substrings[i].length()==d?1:(sorted_substrings[i].charAt(d)-'a'+2)]++;
		for (int i=0; i<R+1; i++)
			count[i+1]+=count[i];
		for (int i=low; i<high; i++)
			aux[count[sorted_substrings[i].length()==d?0:(sorted_substrings[i].charAt(d)-'a'+1)]++]=sorted_substrings[i];
		for (int i=low; i<high; i++)
			sorted_substrings[i]=aux[i-low];
		for (int i=0; i<R; i++)
			sortSubstrings(low+count[i],low+count[i+1],d+1);
	}
	
	void findSubstring(int k) {
		if (k<=sorted_substrings.length)
			System.out.println(sorted_substrings[k-1]);
		else
			System.out.println("INVALID");
	}
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		FindingStrings fs = new FindingStrings();
		Scanner in = new Scanner(System.in);
//		Scanner in = new Scanner(new File("src/input00.txt"));
		int n = in.nextInt();
		for (int i=0; i<n; i++){
			String s = in.next();
			for(int sz=1; sz<=s.length(); sz++)
				for (int beg=0; beg<s.length()-sz+1; beg++)
					fs.substrings.add(s.substring(beg, beg+sz));
		}
		Object[] objArray = fs.substrings.toArray();
		fs.sorted_substrings = Arrays.copyOf(objArray, objArray.length, String[].class);
		fs.sortSubstrings(0, fs.sorted_substrings.length, 0);
		int q = in.nextInt();
		for (int j=0; j<q; j++) {
			int k = in.nextInt();
			fs.findSubstring(k);
		}
	}

}
