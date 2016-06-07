package Monopoly.Logic;

/**
 *  Position Class 
 */
public class Position {
	int x;
	int y;
	
	/**
	 *  Constructor 
	 */
	public Position() {
	}
	
	/**
	 *  Get X position from graphic window
	 * @return X
	 */
	public int getX() {
		return x;
	}
	/**
	 * Update X position from graphic window
	 * @param X
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 *  Get Y position from graphic window
	 * @return Y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Update Y position from graphic window
	 * @param Y
	 */
	public void setY(int y) {
		this.y = y;
	}

}
