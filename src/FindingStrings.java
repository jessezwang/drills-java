/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FindingStrings {
	public static class TrieST{
		private static int R = 26; // radix
	    private static Node root;          // root of trie
	     
		private static class Node {
	        private long val;
	        private Node[] next = new Node[R];
	    }
		
		public static String get(long k){
			return get(root, k, new String());
		}	
		private static String get(Node x, long k, String part){
//			for (int i=0; i<R; i++){
//				if (x.next[i]!=null) System.out.println(x.next[i].val);
//			}
//			System.out.println();
			for (int i=0; i<R; i++){
				if (x.next[i]==null) continue;
//				System.out.println((char)('a'+i));
//				System.out.println(x.val);
//				System.out.println(k);
				if (k==0) return part+(char)('a'+i);
				if (k>x.next[i].val) k-=x.next[i].val;
				else return get(x.next[i], k-1, part+(char)('a'+i));
			}
			if (part.isEmpty()) return "INVALID";
			return part;
		}
		
		public static void put(String key)
	    {
			root = put(root, key, 0);  
		}
		private static Node put(Node x, String key, int d)
	    {  // Change value associated with key if in subtrie rooted at x.
	        if (x == null) x = new Node();
	        x.val++;
	        if (d == key.length()) {  return x; }
	        char c = key.charAt(d); // Use dth key char to identify subtrie.
	        x.next[c-'a'] = put(x.next[c-'a'], key, d+1);
	        return x;
	    }
		
//		public static boolean check(String key){
//			return check(root, key, 0);
//		}
		
//		private static boolean check(Node x, String key, int d){
//			char c = key.charAt(d);
//			if (d == key.length()) return 
//			if (x == null) return false;
//			if      (x.next[c-'a']!=null) return check(x.next[c-'a'],  key, d);
//			else if (d < key.length() - 1) return check(x.mid, key, d+1); 
//		   	else return true;
//		}
//		
//		private static Node put(Node x, String key, int d){
//		   char c = key.charAt(d);
//		   if (x == null) { x = new Node(); }
//		   if (d == key.length()) {  x.val = val; return x; }
//		   
//		   
//		   else if (c > x.c) x.right = put(x.right, key, d);
//		   else if (d < key.length() - 1) {x.mid = put(x.mid, key, d+1); x.val++;}
//		   else x.val++;
//		   return x; 
//		}
	};
		
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
//		Scanner in = new Scanner(System.in);
		Scanner in = new Scanner(new File("src/input00.txt"));
		int n = in.nextInt();
		String[] strings = new String[n];
		int len = 0;
		HashSet<String> set = new HashSet<String>();
		for (int i=0; i<n; i++){
			strings[i] = in.next();
			if (strings[i].length()>len) len=strings[i].length();
		}
		
		for(int sz=1; sz<=len; sz++){
			set.clear();
			for (String s: strings){
				if (s.length()<sz) continue;
				for (int beg=0; beg<s.length()-sz+1; beg++)
					if (!set.contains(s.substring(beg, beg+sz)) ){
							TrieST.put(s.substring(beg, beg+sz));
							set.add(s.substring(beg,beg+sz));
					}
			}				
		}
		
		int q = in.nextInt();
		for (int j=0; j<q; j++) {
			int k = in.nextInt();
			System.out.println( TrieST.get(k) );
		}
	}

}
