package model;

import java.util.ArrayList;
//import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
* The abstract Grid class is used to represent the grid of the game. 
* It implements all the functions that are common to both versions 5T and 5D.
* It is inherited by the class Grid5T and Grid5D
* @authors Lucie Cubaud, Vittoria Vecchioli
*/

public abstract class Grid {
	        //0		1		2	3		4	5		6	7		8	9		10	11		12	13		14	15		16	17  
	private boolean[][] gridInit = {
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},  
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},  //0
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},  //1
			{false,false,false,false,false,false,false,true,true,true,true,false,false,false,false,false,false,false},      //2
			{false,false,false,false,false,false,false,true,false,false,true,false,false,false,false,false,false,false},    //3
			{false,false,false,false,false,false,false,true,false,false,true,false,false,false,false,false,false,false},    //4
			{false,false,false,false,true,true,true,true,false,false,true,true,true,true,false,false,false,false},          //5
			{false,false,false,false,true,false,false,false,false,false,false,false,false,true,false,false,false,false},    //6
			{false,false,false,false,true,false,false,false,false,false,false,false,false,true,false,false,false,false},    //7
			{false,false,false,false,true,true,true,true,false,false,true,true,true,true,false,false,false,false},          //8
			{false,false,false,false,false,false,false,true,false,false,true,false,false,false,false,false,false,false},    //9
			{false,false,false,false,false,false,false,true,false,false,true,false,false,false,false,false,false,false},    //10
			{false,false,false,false,false,false,false,true,true,true,true,false,false,false,false,false,false,false},      //11
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},  //12
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false}, 
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false}, //13
 
	};

	
	protected Point[][] grid;
	protected Map<Point,ArrayList<Line>> potentialPoints;
	protected ArrayList<Line> realLine;
	protected ArrayList<Line> pointChoice;
	protected Point temp;
	protected ArrayList<Point> moves;
	
	public Grid() {
		initialisePoint();
		potentialPoints = new HashMap<Point,ArrayList<Line>>();
		realLine = new ArrayList<Line>();
		pointChoice=new ArrayList<Line>();
		moves = new ArrayList<Point>();
		searchAll();
		
	}
	
	/**
	 * This method returns the size of grid.
	 * @return int
	 */
	public int getSizeGrille() {
		return grid.length;
	}
	
	/**
	 * This method initialises the Grid each time a new game is started. 
	 * The Grid is initialised with the shape of the cross.
	 * The shape corresponds to the position of the booleans in the two dimensional array of boolean grilleInit
	 * If the value in the 2D array is True, then the point added has its attribute created = True (meaning it will appear visually on the board)
	 */
	public void initialisePoint() {
		grid = new Point[gridInit.length][gridInit.length];
		for(int i=0; i<grid.length;i++) {
			for(int j=0; j<grid.length ; j++) {
				grid[i][j]=new Point(i,j,gridInit[i][j]);
			}
		}
	}
	
	/**
	 * The method is used to start the search on all intersection of row and column where no point is already created
	 */
	public void searchAll() {
		for(int i=0; i<grid.length;i++) 
			for(int j=0; j<grid.length ; j++) {
				if(!grid[i][j].isCreated())
					search(i,j);
			}
				
	}
	
	/**
	 * This method calls the search in all directions.
	 * @param row
	 * @param col
	 */
	public void search(int row, int col) {
		searchH(row,col);			
		searchV(row,col);
		searchD1(row,col);
		searchD2(row,col);
	}
	
	public abstract void searchH(int row, int col);
	
	public abstract void searchV(int row, int col);
		
	public abstract void searchD1(int row, int col);

	public abstract void searchD2(int row, int col);
	

	/**
	 * This method adds a Point at the intersection of a row and a column. 
	 * It also adds a move and launch the search methods again.
	 * @param row
	 * @param col
	 */
	public void addPoint(int row, int col) {
		
		if(potentialPoints.containsKey(grid[row][col])) {
			if(potentialPoints.get(grid[row][col]).size()==1)	{
				
				grid[row][col].create();
				addLine(potentialPoints.get(grid[row][col]).get(0));
				addMove(grid[row][col]);
				potentialPoints.clear();
				searchAll();
			}else {
				
				for(Line l : potentialPoints.get(grid[row][col])) {
					temp = grid[row][col];
					pointChoice.add(l);
					potentialPoints.clear();
				}
			}		
		}
		else {
			Line l = options(row,col);
			if(l != null) {
				for(Point p : l.getAllPointLine()) {
					if(!p.isCreated()) {
						temp.create();
						addLine(l);
						addMove(p);
					}
				}
				
				potentialPoints.clear();
				pointChoice.clear();
				temp=null;
				searchAll();
			}
		}
	
	}
	
	
	/**
	 * This method return the line associated with the option the player has chose.
	 * It is used when the version of the game is in 5T and there are more than one possible line for a point.
	 * @param row
	 * @param col
	 * @return Line
	 */
	public Line options(int row, int col) {
		for(Line l : pointChoice)
			if(l.getP2()==grid[row][col])
				return l;
		return null;
	}
	
	/**
	 * This method links together the points contained in the line l.
	 * @param l
	 */
	public void linkPoint(Line l) {
		ArrayList<Point> line= l.getAllPointLine();
		for(int i=1;i<line.size();i++) {
			line.get(i).linkTo(line.get(i-1));
			line.get(i-1).linkTo(line.get(i));		
		}
	}
	
	/**
	 * This method adds a line to the grid when a new line is drawn.
	 * It also links the points contained in the line added
	 * @param l
	 */
	public void addLine(Line l) {
		realLine.add(l);
		linkPoint(l);		
	};
	
	
	/**
	 * The method is used to disconnect all the points contained in the line l.
	 * The is to remove a line from the grid
	 * @param l
	 */
	public void disconnectPoint(Line l) {
		ArrayList<Point> line= l.getAllPointLine();
		for(int i=1;i<line.size();i++) {
			line.get(i).disconnect(line.get(i-1));
			line.get(i-1).disconnect(line.get(i));		
		}
	}
	
	/**
	 * The method is used to debug
	 */
	public void affiche() {
		for(int i=0; i<grid.length-1;i++) {
			for(int j=0; j<grid.length-1 ; j++) {
				if(grid[i][j].isCreated())
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println("\n");
		}
	}
	
	/**
	 * This method returns all the points created on the grid
	 * @return ArrayList<Point>
	 */
	public ArrayList<Point> getPointCreated(){
		ArrayList<Point> created = new ArrayList<Point>();
		for(int i=0; i<grid.length;i++) {
			for(int j=0; j<grid.length ; j++) {
				if(grid[i][j].isCreated())
					created.add(grid[i][j]);
			}
		}
		return created;
	}
	
	
	/**
	 * This method returns all the line already created on the grid
	 * @return ArrayList<Point>
	 */
	public ArrayList<Line> getLineCreated(){
		return realLine;
	}
	
	/**
	 * This method returns all the potential points identified and the lines associated with them
	 * @return Map<Point,ArrayList<Line>>
	 */
	public Map<Point,ArrayList<Line>> getPotentialsPoints(){
		return potentialPoints;
	}
	
	/**
	 * This method return the different possible points when more than one line can be drawn
	 * @return ArrayList<Point>
	 */
	public ArrayList<Point> getPointChoice(){
		ArrayList<Point> p = new ArrayList<Point>();
		for(Line l : pointChoice)
			p.add(l.getP2());
		return p;
	} 
	
	/**
	 * This method is used to add a point and the possible lines associated to it in the Map of potential points
	 * @param p
	 * @param list
	 */
	public void addPointPotentiel(Point p,ArrayList<Point> list) {
		Line[] lines = Line.split(list);
		if(potentialPoints.containsKey(p)) {
			ArrayList<Line> temp = potentialPoints.get(p);
			for(int i=0;i<lines.length;i++) 
				temp.add(lines[i]);
			potentialPoints.replace(p, temp);
		}else {
			ArrayList<Line> temp = new ArrayList<Line>();
			for(int i=0;i<lines.length;i++) 
				temp.add(lines[i]);
			potentialPoints.put(p,temp);
		}
		
	}
	
	/**
	 * This methods reinitialise the game to zero.
	 * It clears all the class attributes and starts a new search for the potential moves.
	 */
	public void restart() {
		initialisePoint();
		potentialPoints = new HashMap<Point,ArrayList<Line>>();
		realLine = new ArrayList<Line>();
		moves = new ArrayList<Point>();
		pointChoice.clear();
		
		searchAll();
	}
	
	/**
	 * This method updates the list of moves by adding the last point played.
	 * @param p
	 */
	public void addMove(Point p) {
		moves.add(p);
	}
	
	/**
	 * The method undo the last move performed.
	 * To do so, it removes the last point added in move and it disconnects the points associated.
	 * It starts a new search with the new configuration.
	 */
	public void undo() {
		if(!moves.isEmpty()) {
			if (!pointChoice.isEmpty()) {
				pointChoice.clear();
			}
			else {
				Point p = moves.remove(moves.size()-1);
				Line l = realLine.remove(realLine.size()-1);
				disconnectPoint(l);
				p.remove();
				
				pointChoice.clear();
				
			}
			potentialPoints.clear();
			searchAll();
		}
	}
	
	/**
	 * This method returns the points in moves
	 * @return ArrayList<Point>
	 */
	public ArrayList<Point> getMoves(){
		return moves;
	}
	
	/**
	 * The method performs the random solve algorithm
	 * It solves the game by adding randomly points from the potentialsPoints list
	 * After each point added, it recalculates all the potential points in the new configuration 
	 * 
	 * The function stops when there are no more potential points on the grid
	 */
	public void randomSolve() {
		Map<Point,ArrayList<Line>> pp = getPotentialsPoints();

		while(pp.size()>0) {
			
			Set<Point> keySet = pp.keySet();
			List<Point> keyList = new ArrayList<Point>(keySet);
			
	        int size = keyList.size();
	        int randIdx = new Random().nextInt(size);

	        Point randomKey = keyList.get(randIdx);
	        ArrayList <Line> randomLigne = new ArrayList<Line>();
	        randomLigne.add(pp.get(randomKey).get(0));
	        pp.put(randomKey, randomLigne);

	        addPoint(randomKey.getX(),randomKey.getY() );

	        pp = getPotentialsPoints();
	        
		}

	           
	}
	
	
}