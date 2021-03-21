package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.MouseControl;
import model.Grid;
import model.Grid5D;
import model.Grid5T;

/**
* The Game class is used to represent a game.
* Each time the user restarts, a new instance of this class is created.
* It extends JPanel.
* @author  Lucie Cubaud, Vittoria  Vecchioli
*/

public class Game extends JPanel{
	Grid gr = new Grid5T();
	JPanel board = new Board(gr);
	JPanel buttons = new Buttons(board);
	MouseListener m = new MouseControl(gr,board,buttons);

	
	public Game() {
		setLayout(new BorderLayout(0,0));
		board.setPreferredSize(new Dimension(680,600));		
		add(buttons, BorderLayout.NORTH);	
		board.addMouseListener(m);		
		add(board, BorderLayout.SOUTH);	
		
	}
	
	/**
	 * This method returns the Grid of the current game 
	 * @return Grid
	 */
	public Grid getGrid() {
		return gr;
	}
	
	/**
	 * This method returns the score of the current game by taking the size of the list of moves
	 * @return int
	 */
	public int getScore() {
		if(gr.getMoves().size()>0) {		
			return gr.getMoves().size();
		} else {
			return 0;
		}
	}
	
	/**
	 * This method indicates whether the game is over or no
	 * @return boolean
	 */
	public boolean isGameOver() {
		return gr.getPotentialsPoints().isEmpty();
	}

	/**
	 * This method is used to change the version of the game 
	 */
	public void changeGame() {
		if(gr instanceof Grid5D) 
			gr = new Grid5T();			
		else 
			gr = new Grid5D();
		((Board) board).changeGrid(gr);
		((MouseControl) m).changeGrid(gr);
		board.repaint();	
	}
	
}