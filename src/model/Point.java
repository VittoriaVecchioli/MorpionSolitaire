package model;
import java.util.ArrayList;

/**
* The Point class is used to represent a point on the grid.
* It takes in argument two integers (the coordinates x and y) and a boolean to indicate whether it exists on the board. 
* It implements the Comparable interface
* @authors Lucie Cubaud, Vittoria  Vecchioli
*/
public class Point implements Comparable<Point>{
	private int x;
	private int y;
	
	private boolean created;
	private ArrayList<Point> linked;
	
	/**
	 * Constructor 
	 * @param x
	 * @param y
	 * @param create
	 */
	public Point(int x, int y, boolean create) {
		this.x = x;
		this.y = y;
		this.created = create;
		linked = new ArrayList<Point>();
		
	}
	/**
	 * Getter method to get the value of x.
	 * @return int
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Getter method to get the value of y.
	 * @return int
	 */
	public int getY() {
		return this.y;
		
	}

	/**
	 * Check whether a point is already on the Grid
	 */
	public boolean isCreated() {

		return created;
	}
	
	/**
	 * The method set the value of the created attribute to True when a point is added on the grid
	 */
	public void create() {

		created=true;
	}
	/**
	 * This method updates the created attribute to False when a point is removed on the grid.
	 * The method is used when we undo a move.
	 */
	public void remove() {
		created=false;
	}
	/**
	 * This method checks whether the point on which it is called is on the same line as the point in argument. 
	 * This method is used in search.
	 * @param p
	 * @return boolean
	 */
	public boolean isLinkedAt(Point p) {
		return linked.contains(p);
	}
	
	
	/**
	 * This method links the point on which it is called to the point in argument to form a line. 
	 * This method is used when a line is added to the Grid.
	 * @param p
	 */
	public void linkTo(Point p) {
		linked.add(p);
	}
	
	/**
	 * This method disconnects the point on which it is called from the point passed in argument. 
	 * This method is used when we undo a move.
	 * @param p
	 */
	public void disconnect(Point p) {
		if(linked.contains(p))
			linked.remove(p);
	}
	
	/**
	 * method for debugging.
	 */
	public String toString() {
		return "x="+x+"   y="+y+"  cree="+created + "attache nb = "+linked.size();
	}

	/**
	 * Override method from Comparable to compare two points together according to their coordinates. 
	 * It returns the difference between their abscissa or their ordinate depending on their relative positions   
	 * @param p
	 * @return int
	 */
	@Override
	public int compareTo(Point p) {
		//if horizontal
		if(this.x == p.x)
			return(this.y-p.y);
		//if vertical
		if(this.y == p.y)
			return(this.x-p.x);
		return (this.y-p.y);
	}

}