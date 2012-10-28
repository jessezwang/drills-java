import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class PowerOutage {
	private int K;
	private ArrayList<Edge> mst;
	private PriorityQueue<Edge> pq;
	private int[] set;
	private int TotalWeight;
	public EdgeWeightedGraph G;
	
	
    // Edge
	class Edge implements Comparable<Edge>{
		private final int v;
	    private final int w;
	    private final int weight;
		public Edge(int v, int w, int weight){
			 this.v = v;
		     this.w = w;
		     this.weight = weight;
		}
		
		public int weight() {
			return weight;  
		}
	    public int either() {
	    	return v;  
	    }
	     
	    public int other(int vertex) {
	    	if (vertex == v) return w;
	        else return v;
	    }
	    
	    public int compareTo(Edge that) {
	        if (this.weight() < that.weight()) 
	        	return -1;
	        else if (this.weight() > that.weight())
	        	return +1;
	        else 
	        	return  0;
	    }
	} // Edge
	
	// Graph
	public class EdgeWeightedGraph {
		private final int V;
	    private int E;
	    private ArrayList<Edge>[] adj;
	    public EdgeWeightedGraph(int V) {
	    	this.V = V;
	        this.E = 0;
	        adj = (ArrayList<Edge>[]) new ArrayList[V];
	        for (int v = 1; v < V; v++)
	        	adj[v] = new ArrayList<Edge>();
	    }
	    public int V() {  
	    	return V;  
	    }
	    public int E() {  
	    	return E;  
	    }
	    public void addEdge(Edge e) {
	    	int v = e.either();
	    	int w = e.other(v);
	    	adj[v].add(e);
//	    	adj[w].add(e);
	    	E++;
	    }
	    public Iterable<Edge> adj(int v) {
	    	return adj[v];  
	    }
	    
	    public Iterable<Edge> edges() {
	    	ArrayList<Edge> ret = new ArrayList<Edge>();
	    	for (int v=1; v<adj.length; v++)
	    		ret.addAll(adj[v]);
	    	return ret;
	    }
	  } //Graph
	
	// PowerOutage Constructor
	public PowerOutage(int K)
	{
		TotalWeight = 0;
		this.K = K;
		mst = new ArrayList<Edge>();
		pq = new PriorityQueue<Edge>();
	} // Constructor
	
	public String weight(){
		set = new int[G.V()];
		for (int v=1; v<G.V(); v++)
			set[v] = v;
		if (K>=G.V()-1)
			return "0";
		for (int v=1; v<G.V(); v++)
			for (Edge e: G.adj(v))
				pq.add(e);
		while (!pq.isEmpty() && mst.size() < G.V()-K-1) {
			Edge e = pq.poll();
			int either=e.either();
			int count1=0, count2=0;
			int other = e.other(either);
			while (set[either]!=either){
				either = set[either];
				count1++;
			}
			while (set[other]!=other){
				other = set[other];
				count2++;
			}
			if (either!=other){
				mst.add(e);
				if (count1>count2)
					set[other] = either;
				else
					set[either] = other;
			}
		}
		for (int v=1; v<G.V(); v++)
			if (set[v]==v) 
				if (--K<0)
					return "Impossible!";
		for (int i=0; i<mst.size(); i++)
			TotalWeight += mst.get(i).weight();
		return Integer.toString(TotalWeight);
	}
	
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
//		Scanner in = new Scanner(System.in);
		Scanner in = new Scanner(new File("src/input00.txt"));
		int T = in.nextInt();
		for (int t=0; t<T; t++){
			int N = in.nextInt();
			int M = in.nextInt();
			int K = in.nextInt();
			PowerOutage po = new PowerOutage(K);
			po.G = po.new EdgeWeightedGraph(N+1);
			for (int m=0; m<M; m++){
				int one = in.nextInt();
				int other = in.nextInt();
				int weight = in.nextInt();
				po.G.addEdge(po.new Edge(one, other, weight));
			}
			System.out.println(po.weight());
		}
	}

}
