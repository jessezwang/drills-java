import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class SaveHumanity {
	private static String P;
	private static String V;
	private static int[] next;
	private static void genNext()
	{
		int m=P.length();
		next = new int[m];
		int i=0, j=-1;
		next[i]=j;
		while( i<m ){
			while(j>=0 && P.charAt(i)!=P.charAt(j)) j=next[j];
			i++;
			j++;
			next[i]=j;
		}
	}
	public static List<Integer> findMatches(){
		List<Integer> result = new ArrayList<Integer>();
		genNext();
		int count = 0;
		return result;
	}
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(new File("src/input00.txt"));
		int T = in.nextInt();
		for (int t=0; t<T; t++){
			 P = in.next();
			 V = in.next();
			 List<Integer> result = findMatches();
			 for (int i : result){
				 System.out.print(i);
				 if (i!=result.size()) System.out.print(' ');
			 }
			 System.out.println();
		}
		
	}

}
