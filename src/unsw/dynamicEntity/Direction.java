package unsw.dynamicEntity;

import java.awt.Point;

/**
 * An enum which stores the 4 different directions that a player can face.
 * 
 * Implementation of advanced enums borrowed from 
 * dzone.com/articles/java-enums-how-to-make-much-useful
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public enum Direction {
	/**
	 * Up direction
	 */
	UP {
		@Override public Point getPosition(Point point) {
			//System.out.println("up");
			point.setLocation(point.x, point.y - 1);
			return point;
		}
	},
	/**
	 * Down direction
	 */
	DOWN {
		
		@Override public Point getPosition(Point point) {
			//System.out.println("down");
			point.setLocation(point.x, point.y + 1);
			return point;
		}
	},
	/**
	 * Right direction
	 */
	RIGHT {
		
		@Override public Point getPosition(Point point) {
			//System.out.println("right");
			point.setLocation(point.x + 1, point.y);
			return point;
		}
	},
	/**
	 * Left direction
	 */
	LEFT {
		
		@Override public Point getPosition(Point point) {
			//System.out.println("left");
			point.setLocation(point.x - 1, point.y);
			return point;
		}
	};

	/**
	 * Method gets the position directly in front of a set of points.
	 * @param point the current position
	 * @return the position in front of the point
	 */
	abstract public Point getPosition(Point point);
	
}

