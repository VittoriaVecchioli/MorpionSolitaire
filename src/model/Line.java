package model;

import java.util.ArrayList;


/**
* The Line class is used to represent a line in the game.
* It takes an array list of five points as argument.
* @authors Lucie Cubaud, Vittoria  Vecchioli
*/
public class Line {

	private ArrayList<Point> line;
	
	/**
	 * Constructor
	 * @param ligne
	 */
	public Line(ArrayList<Point> ligne) {
		this.line=ligne;
		
	}
	/**
	 * Getter method to get the value of the leftmost point of the line (for vertical, the highest point)
	 * @return Point
	 */
	public Point getP1() {
		return line.get(0);
	}
	
	/**
	 * Getter method to get the value of the rightmost point of the line (for vertical, the lowest point)
	 * @return Point
	 */
	public Point getP2() {
		return line.get(4);
	}
	
	
	/**
	 * This method returns all the possible lines from a given array of points.
	 * Literally, it splits the Array list every 5 points
	 * @param l
	 * @return Line []
	 */
	public static Line[] split(ArrayList<Point> l) {
		Line[] lines = new Line[l.size()-4];
		for(int i=0;i<lines.length;i++) {
			ArrayList<Point> array = new ArrayList<Point>();
			array.add(l.get(i));array.add(l.get(i+1));array.add(l.get(i+2));array.add(l.get(i+3));array.add(l.get(i+4));
			lines[i]=new Line(array);
		}		
		return lines;	
	}
	
	/**
	 * Getter method to get all the point forming the line on which it is called
	 * @return ArrayList<Point>
	 */
	public ArrayList<Point> getAllPointLine(){
		return line;
	}
	
	// used to debug
	public String toString() {
		return "point 1: "+getP1().toString()+"  point  2: "+getP2().toString();
	}
	
}