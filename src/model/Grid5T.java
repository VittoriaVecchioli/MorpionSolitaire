package model;

import java.util.ArrayList;
import java.util.Collections;


/**
* The Grid5T class inherit the functions from the abstract class Grid 
* It implements all the search functions specific to the version of the game
* @authors  Lucie Cubaud, Vittoria Vecchioli
*/

public class Grid5T extends Grid{

	/**
	 * This method finds all the potential points associated with horizontal lines
	 * It scans the entire board and checks at every intersection of a row and a column if a point can be added
	 * It starts by scanning all points to the right and then to the left, adding to a list every point that satisfies the conditions
	 * A point satisfies the conditions if: it is already created and it is not already attached to two points horizontally 
	 * (it can be attached to one other point horizontally in this version of the game)
	 * At the end, if the list of points identified is longer than 4, then the method order those points from left to right and call the addPointPotentiel method
	 * @param row, col
	 */
	@Override
	public void searchH(int row, int col) {
		ArrayList<Point> lineH = new ArrayList<Point>();
		lineH.add(super.grid[row][col]);
		for(int xPrime=col-1;xPrime>=col-4;xPrime--) {
			if(xPrime==-1)
				break;
			boolean next = grid[row][xPrime].isCreated();	
			if(xPrime==0) {
				if(next)
					lineH.add(grid[row][xPrime]);
				break;
			}
			boolean attache = grid[row][xPrime].isLinkedAt(grid[row][xPrime-1]);
			if( !next || attache) {
				//NEW from 5D
				if(attache)
					lineH.add(grid[row][xPrime]);
				break;
			}
			lineH.add(grid[row][xPrime]);
			
		}
		for(int Xprime=col+1;Xprime<=col+4;Xprime++) {
			
			if(Xprime==grid[row].length)
				break;
			boolean next = grid[row][Xprime].isCreated();
			if(Xprime==grid[row].length-1) {
				if(next)
					lineH.add(grid[row][Xprime]);
				break;
			}
			boolean attache = grid[row][Xprime].isLinkedAt(grid[row][Xprime+1]);
			if( !next || attache) {
				//NEW from 5D
				if(attache)
					lineH.add(grid[row][Xprime]);
				break;
			}
			lineH.add(grid[row][Xprime]);
		}
		
		if(lineH.size()>=5) {
			Collections.sort(lineH);
			addPointPotentiel(grid[row][col],lineH);
			
		}
		
	}
	
	/**
	 * This method does the same as searchH but for vertical lines
	 * @param row, col
	 */
	@Override
	public void searchV(int row, int col) {
		ArrayList<Point> lineV = new ArrayList<Point>();
		lineV.add(grid[row][col]);
		for(int yPrime=row-1;yPrime>=row-4;yPrime--) {
			if(yPrime==-1)break;
			boolean next = grid[yPrime][col].isCreated();	
			if(yPrime==0) {
				if(next)
					lineV.add(grid[yPrime][col]);
				break;
			}
			boolean attache = grid[yPrime][col].isLinkedAt(grid[yPrime-1][col]);
			if( !next || attache) {
				if(attache)
					lineV.add(grid[yPrime][col]);
				break;
			}
			lineV.add(grid[yPrime][col]);
			
		}
		for(int Yprime=row+1;Yprime<=row+4;Yprime++) {
			if(Yprime==grid.length)break;
			boolean next = grid[Yprime][col].isCreated();
			if(Yprime==grid.length-1) {
				if(next)
					lineV.add(grid[Yprime][col]);
				break;
			}
			boolean attache = grid[Yprime][col].isLinkedAt(grid[Yprime+1][col]);
			if( !next || attache) {
				if(attache)
					lineV.add(grid[Yprime][col]);
				break;
			}
			lineV.add(grid[Yprime][col]);
		}
		if(lineV.size()>=5) {
			Collections.sort(lineV);
			addPointPotentiel(grid[row][col],lineV);			
		}
		
	}
	
	/**
	 * This method does the same as searchH but for vertical lines
	 * @param row, col
	 */
	@Override
	public void searchD1(int row, int col) {
		ArrayList<Point> lineD = new ArrayList<Point>();
		lineD.add(grid[row][col]);
		for(int i=1;i<=4;i++) {
			if(col-i == -1 || row-i == -1)break;
			boolean next = grid[row-i][col-i].isCreated();	
			if(col-i == 0 || row-i == 0) {
				if(next)
					lineD.add(grid[row-i][col-i]);
				break;
			}
			boolean attache = grid[row-i][col-i].isLinkedAt(grid[row-i-1][col-i-1]);
			if( !next || attache) {
				if(attache)
					lineD.add(grid[row-i][col-i]);
				break;
			}
			lineD.add(grid[row-i][col-i]);	
		}
		for(int i=1;i<=4;i++) {
			if(col+i == grid[row].length || row+i == grid[row].length)break;
			boolean next = grid[row+i][col+i].isCreated();	
			if(col+i == grid[row].length-1 || row+i == grid[row].length-1) {
				if(next)
					lineD.add(grid[row+i][col+i]);
				break;
			}
			boolean attache = grid[row+i][col+i].isLinkedAt(grid[row+i+1][col+i+1]);
			if( !next || attache) {
				if(attache)
					lineD.add(grid[row+i][col+i]);
				break;
			}
			lineD.add(grid[row+i][col+i]);	
		}
		
		
		if(lineD.size()>=5) {
			Collections.sort(lineD);
			addPointPotentiel(grid[row][col],lineD);
			
		}

		
	}
	
	/**
	 * This method does the same as searchH but for vertical lines
	 * @param row, col
	 */
	@Override
	public void searchD2(int row, int col) {
		ArrayList<Point> lineD = new ArrayList<Point>();
		lineD.add(grid[row][col]);
		for(int i=1;i<=4;i++) {
			if(col-i == -1 || row+i == grid[row].length)break;
			boolean next = grid[row+i][col-i].isCreated();	
			if(col-i == 0 || row+i == grid[row].length-1) {
				if(next)
					lineD.add(grid[row+i][col-i]);
				break;
			}
			boolean attache = grid[row+i][col-i].isLinkedAt(grid[row+i+1][col-i-1]);
			if( !next || attache) {
				if(attache)
					lineD.add(grid[row+i][col-i]);
				break;
			}
			lineD.add(grid[row+i][col-i]);	
		}
		for(int i=1;i<=4;i++) {
			if(col+i == grid[row].length || row-i == -1)break;
			boolean next = grid[row-i][col+i].isCreated();	
			if(col+i == grid[row].length-1 || row-i == 0) {
				if(next)
					lineD.add(grid[row-i][col+i]);
				break;
			}
			boolean attache = grid[row-i][col+i].isLinkedAt(grid[row-i-1][col+i+1]);
			if( !next || attache) {
				if(attache)
					lineD.add(grid[row-i][col+i]);
				break;
			}
			lineD.add(grid[row-i][col+i]);	
		}
		
		
		if(lineD.size()>=5) {
			Collections.sort(lineD);
			addPointPotentiel(grid[row][col],lineD);
			
		}
		
	}

}