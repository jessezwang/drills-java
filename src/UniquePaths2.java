import java.util.*;
import java.awt.Point;
public class UniquePaths2 {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0]==1) return 0;
        int x=0, y=0, count=0;
        Queue<Point> queue = new LinkedList<Point>();
        queue.offer(new Point(0,0));
        while(!queue.isEmpty()){
        	Point p = queue.poll();
        	x=p.x;
        	y=p.y;
        	if (x==obstacleGrid.length-1 && y==obstacleGrid[x].length-1) count++;
        	else{
        		if(x<obstacleGrid.length-1 && obstacleGrid[x+1][y]==0)
        			queue.offer(new Point(x+1,y));
        		if(y<obstacleGrid[x].length-1 && obstacleGrid[x][y+1]==0)
        			queue.offer(new Point(x, y+1));
        	}
        }
        return count;    
    }	
	
	public static void main(String[] args) {
		UniquePaths2 up = new UniquePaths2();
		System.out.println(up.uniquePathsWithObstacles(new int[][]{{0},{0}}));
		
	}
}