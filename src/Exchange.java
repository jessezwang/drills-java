import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Exchange {
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		StringBuilder builder = new StringBuilder();
//		Scanner in = new Scanner(System.in);
		Scanner in = new Scanner(new File("src/input00.txt"));
		int K = in.nextInt();
		int[] set = new int[K];
		
		int[] permutation = new int[K];
		for (int i=0; i<K; i++)
			permutation[i]=in.nextInt();
		for (int i=0; i<K; i++)
			set[i]=i;
		for (int i=0; i<K; i++){
			String line = in.next();
			for (int j=i+1; j<K; j++)
				if (line.charAt(j)=='Y'){
					int tmp = set[j];
					for (int m=0; m<K; m++)
						if (set[m]==tmp)
							set[m]=set[i];
				}
		}
		Stack<Integer> done = new Stack<Integer>();
		ArrayList<Integer> toSort = new ArrayList<Integer>();
		for (int i=0; i<K; i++){
			if (!done.contains(set[i])){
				done.push(set[i]);
				toSort.clear();
				toSort.add(i);				
				for (int j=i+1; j<K; j++)
					if (set[j]==done.peek())
						toSort.add(j);
				for (int m=0, min=m; m<toSort.size()-1; m++){
					for (int n=m+1; n<toSort.size(); n++){
						min=m;
						if (permutation[toSort.get(n)]<permutation[toSort.get(min)])
							min = n;
						int tmp = permutation[toSort.get(m)];
						permutation[toSort.get(m)] = permutation[toSort.get(min)];
						permutation[toSort.get(min)] = tmp;
					}
				}
			}
		}
		for (int i=0; i<K; i++){
			builder.append(Integer.toString(permutation[i]));
			if (i!=K-1) builder.append(' ');
		}
		System.out.println(builder.toString());
	}

}
