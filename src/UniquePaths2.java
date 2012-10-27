
public class UniquePaths2 {
	 private int dfs(int x, int y, int[][] obstacleGrid){
	        if (1==obstacleGrid[0][0]) return 0;
	        if (x==obstacleGrid.length-1 && y==obstacleGrid[x].length-1 && obstacleGrid[x][y]==0) return 1;
	        if (x<obstacleGrid.length-1 && obstacleGrid[x+1][y]==0 && y<obstacleGrid[x].length-1 && obstacleGrid[x][y+1]==0) 
	            return dfs(x+1,y, obstacleGrid)+dfs(x,y+1, obstacleGrid);
	        if (x<obstacleGrid.length-1 && obstacleGrid[x+1][y]==0) return dfs(x+1,y, obstacleGrid);
			if (y<obstacleGrid[x].length-1 && obstacleGrid[x][y+1]==0) return dfs(x,y+1, obstacleGrid);
			return 0;
		}
		public int uniquePathsWithObstacles(int[][] obstacleGrid) {
			return dfs(0, 0, obstacleGrid);
	    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}