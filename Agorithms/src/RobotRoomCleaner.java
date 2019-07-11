
public class RobotRoomCleaner {

	public static void main(String[] args) {
		//Given a robot cleaner in a room modeled as a grid.
		//Each cell in the grid can be empty or blocked.
		//The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
		//When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.
		//Design an algorithm to clean the entire room using only the 4 given APIs shown below.
		System.out.println();
	}
	/**
	 * // This is the robot's control interface.
	 * // You should not implement it, or speculate about its implementation
	 * interface Robot {
	 *     // Returns true if the cell in front is open and robot moves into the cell.
	 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
	 *     public boolean move();
	 *
	 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
	 *     // Each turn will be 90 degrees.
	 *     public void turnLeft();
	 *     public void turnRight();
	 *
	 *     // Clean the current cell.
	 *     public void clean();
	 * }
	 */
    private static final int[][] DIRECTION = {{1,0},{0,-1},{-1,0},{0,1}};
    
    private static class Point{
        public int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int hashCode() {
           return x * 31 + y;
        }
        
        @Override
        public boolean equals(Object o) {
            if (o instanceof Point) {
                Point that = (Point) o;
                return that.x == this.x && that.y == this.y;
            }
            else {
                return false;
            }
        }
    }
    
    public static void cleanRoom(Robot robot) {
        dfs(robot, new Point(0,0), new HashSet<Point>(), true, 0);
    }
    
    private void dfs(Robot robot, Point cur, HashSet<Point> visited, boolean isInitial, int direction) {
        robot.clean();
        int curDir = direction; 
        for (int d = 0; d < DIRECTION.length; d++) {
            int targetDir = (direction + d) % DIRECTION.length; 
            if (!isInitial && Math.abs(targetDir - direction) == 2) continue;
            
            turn(robot, curDir, targetDir);
            
            Point nextPoint = new Point(cur.x + DIRECTION[targetDir][0], cur.y + DIRECTION[targetDir][1]);
            if (visited.add(nextPoint) && robot.move()) {
                dfs(robot, nextPoint, visited, false, targetDir);
                curDir = getDirctionIndex(targetDir + 2);
            }
            else curDir = targetDir;
        }
        
        //go back
        if (!isInitial) {
            turn(robot, curDir, getDirctionIndex(direction + 2));
            robot.move();
        }
    }
    private static void turn(Robot robot, int curDir, int targetDir) {
        if (curDir == targetDir) return;
        if (getDirctionIndex(curDir + 1) == targetDir) {
            robot.turnRight();
        }
        else if (getDirctionIndex(curDir - 1) == targetDir) {
            robot.turnLeft();
        }
        else if (getDirctionIndex(curDir + 2) == targetDir) {
            robot.turnRight();
            robot.turnRight();
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    private static int getDirctionIndex(int curDir) {
        return (curDir + DIRECTION.length) % DIRECTION.length; 
    }
}
