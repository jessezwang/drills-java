import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class PowerOutage {
	private int K;
	private Edge[] edgeTo;
	private int[] distTo;
	private boolean[] marked;
	private PriorityQueue<Vertex> pq;
	private int TotalWeight;
	public EdgeWeightedGraph G;
	
	class Vertex implements Comparable<Vertex>{
		private final int v;
		private final int weight;
		public Vertex(int v, int weight){
			this.v = v;
			this.weight = weight;
		}
		public int weight() {
			return weight;  
		}
		
		public int vertex() {
			return v;
		}
		
		public int compareTo(Vertex that){
			if (this.weight() < that.weight())
				return -1;
			else if (this.weight() > that.weight())
				return 1;
			else 
				return 0;
		}
	}
	
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
	    	adj[w].add(e);
	    	E++;
	    }
	    public Iterable<Edge> adj(int v) {
	    	return adj[v];  
	    }
	  } //Graph
	
	private void visit(Vertex v)
    {  // Mark v and add to pq all edges from v to unmarked vertices.
		marked[v.vertex()] = true;
		for (Edge e : G.adj(v.vertex())){
			int w = e.other(v.vertex());
			if (marked[w]) continue;
			if (e.weight() < distTo[w]) {  
				// Edge e is new best connection from tree to w.
				edgeTo[w] = e;
				if (pq.contains(w)) {
					pq.remove(new Vertex(w, distTo[w]) );
					pq.offer(new Vertex(w, e.weight()) );
				} else                
					pq.offer( new Vertex(w, e.weight()) );
				distTo[w] = e.weight();
			}
		}
    }
	
	// PowerOutage Constructor
	public PowerOutage(int K)
	{
		TotalWeight = 0;
		this.K = K;
	} // Constructor
	
	public String weight(){
		edgeTo = new Edge[G.V()];
		distTo = new int[G.V()];
		marked = new boolean[G.V()];
		for (int v = 1; v < G.V(); v++)
			distTo[v] = Integer.MAX_VALUE;
		pq = new PriorityQueue<Vertex>(G.V());
		for (int v=1; v<marked.length; v++)
			if (!marked[v]) {
				if (K--==0)
					break;
				pq.clear();
				distTo[v] = 0;
				pq.offer(new Vertex(v,0));
				while (!pq.isEmpty())
					visit(pq.poll());
			}
		if (K>=marked.length-1)
			return "0";
		for (int v=1; v<marked.length; v++)
			if (!marked[v])
				return "Impossible!";
		for (int v=1; v<marked.length; v++)
			TotalWeight += distTo[v];
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
