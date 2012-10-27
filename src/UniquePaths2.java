public class UniquePaths2 {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    	int X=obstacleGrid.length;
		int Y=obstacleGrid[0].length;
        if (obstacleGrid[X-1][Y-1]==1) return 0;
		int[][] dfa = new int[X+1][Y+1];
		for (int i=0; i<X; i++)
			for (int j=0; j<Y; j++)
				if (obstacleGrid[i][j]==1) dfa[i][j]=0;
				else dfa[i][j]=-1;
		dfa[X-1][Y-1]=1;
		for (int i=0; i<=X; i++)
			dfa[i][Y]=0;
		for (int i=0; i<=Y; i++)
			dfa[X][i]=0;
		for (int j=Y-2, i=X-1; j>=0; j--)	
			if (dfa[i][j]==-1)
				dfa[i][j]=dfa[i+1][j]+dfa[i][j+1];
		for (int i=X-2; i>=0; i--)
			for (int j=Y-1; j>=0; j--)
				if (dfa[i][j]==-1)
					dfa[i][j]=dfa[i+1][j]+dfa[i][j+1];
        return dfa[0][0];    
    }	
	
	public static void main(String[] args) {
		UniquePaths2 up = new UniquePaths2();
		System.out.println(up.uniquePathsWithObstacles(new int[][]{{0},{0}}));
		
	}
}