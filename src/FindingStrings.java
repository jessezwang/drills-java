/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FindingStrings {
	public static class TST{
		private static class Node
		{
		   char c;
		   Node left, mid, right;
		   long val;
		}
		private static Node root = null;
		
		public static String get(long k){
			return get(root, k, new String());
		}
		
		public static Node get(String key) {
			return get(root, key, 0);
		}
		
		private static Node get(Node x, String key, int d)
		{
		   if (x == null) return null;
		   char c = key.charAt(d);
		   if      (c < x.c) return get(x.left,  key, d);
		   else if (c > x.c) return get(x.right, key, d);
		   else if (d < key.length() - 1) return get(x.mid, key, d+1);
		   else return x;
		}		
		
		private static String get(Node x, long k, String part){
			class NodeComparator implements Comparator{
			    public int compare(Object o1, Object o2) {
			        Node p1 = (Node) o1;
			        Node p2 = (Node) o2; 
			        return p1.c - p2.c;
			    }
			}
			Map<Node, Long> values = new HashMap<Node, Long>();
			Queue<Node> candidates = new LinkedList<Node>();
			candidates.offer(x);
			values.put(x, x.val);
			while(!candidates.isEmpty()){
				x = candidates.poll();
				if (x.left!=null) {
					candidates.offer(x.left);
					values.put(x.left, x.left.val);
				}
				if (x.right!=null) {
					candidates.offer(x.right);
					values.put(x.right, x.right.val);
				}
			}	
			List<Object> list = new ArrayList<Object>(values.keySet());
		    Object[] objects = list.toArray();

			Arrays.sort(objects, new NodeComparator());

//			for (Object obj: objects){
//				Node n = (Node)obj;
//				System.out.println(n.c);
//			}
			Node n = null;
			for (Object obj: objects){
				n = (Node)obj;
//				System.out.print(n.c);
//				System.out.print(k);
//				System.out.println();
				if (k==1) return new String(part+n.c);
				if (k>n.val) k-=n.val; 
				else return get(n.mid, k-1, part+n.c);
			}
			return new String("INVALID");
		}
		
		public static void put(String key){
			root = put(root, key, 0);  
		}
		
		public static boolean check(String key){
			return check(root, key, 0);
		}
		
		private static boolean check(Node x, String key, int d){
			char c = key.charAt(d);
			if (x == null) return false;
			if      (c < x.c) return check(x.left,  key, d);
			else if (c > x.c) return check(x.right, key, d);
			else if (d < key.length() - 1) return check(x.mid, key, d+1); 
		   	else return true;
		}
		
		private static Node put(Node x, String key, int d){
		   char c = key.charAt(d);
		   if (x == null) { x = new Node(); x.c = c; }
		   if      (c < x.c) x.left  = put(x.left,  key, d);
		   else if (c > x.c) x.right = put(x.right, key, d);
		   else if (d < key.length() - 1) {x.mid = put(x.mid, key, d+1); x.val++;}
		   else x.val++;
		   return x; 
		}
	};
		
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
//		Scanner in = new Scanner(new File("src/input00.txt"));
		int n = in.nextInt();
		for (int i=0; i<n; i++){
			
			String s = in.next();
			for(int sz=1; sz<=s.length(); sz++)
				for (int beg=0; beg<s.length()-sz+1; beg++)
					if (!TST.check(s.substring(beg, beg+sz)) )
							TST.put(s.substring(beg, beg+sz));
		}
		int q = in.nextInt();
		for (int j=0; j<q; j++) {
			int k = in.nextInt();
			System.out.println( TST.get(k) );
		}
	}

}
