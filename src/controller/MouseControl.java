package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import model.*;
import view.Board;
import view.Buttons;
import view.Game;


/**
* The MouseControl class is used to connect the actions taken by the user with the right methods.
* It implements MouseListener.
* It takes in arguments a Grid and two JPanel (one is for the buttons and the other for the Grid).
* @authors Lucie Cubaud, Vittoria Vecchioli
*/
public class MouseControl implements MouseListener{
	JPanel br;
	JPanel bu;
	Grid gr;
	public MouseControl(Grid gr, JPanel br, JPanel bu) {
		this.gr=gr;
		this.br=br;
		this.bu=bu;
		
	}

	/**
	 * This method check if the spot where the mouse clicked corresponds to a potential point and if yes, it adds the point to the Grid and to the board.
	 * We used a formula to increase the size of the area in which a click is detected around the point (the intersections of a row and a column).
	 */
	public void mouseClicked(MouseEvent me) {	
		int x = Math.round((me.getX()+20)/ 10) * 10;
		int y = Math.round((me.getY()+20)/ 10) * 10;
		if(x/40<=gr.getSizeGrille() && y/40<=gr.getSizeGrille()) {
			System.out.println("Adding"+y/40+"; "+x/40);
			gr.addPoint(y/40,x/40);
			((Buttons) bu).setScore();
		}
		
		br.repaint();
	}
	
	/**
	 * This method is used to change the grid if we change the version of the game
	 * @param g
	 */
	public void changeGrid(Grid g) {
		gr=g;
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}