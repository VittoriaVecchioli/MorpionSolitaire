package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.*;

import javax.swing.JPanel;

/**
* The Board class is used to create the user interface.
* It takes a grid as argument.
* @authors  Lucie Cubaud, Vittoria  Vecchioli
*/
public class Board extends JPanel{
	Grid gr;
	static boolean hint = false;
	
	
	public Board(Grid gr) {
        setBackground(Color.WHITE);
		
		this.gr=gr;		
    }
	
	/**
	 * The method changes the version of the game
	 * @param g
	 */
	public void changeGrid(Grid g) {
		gr=g;
	}
	
	/**
	 * This method sets the value of the class attribute hint to True when the button Hint is pressed
	 */
	public void activHint() {
		hint=true;
		repaint();
	}
	
	/**
	 * This method restarts the game when the button Restart is pressed
	 */
	public void restart() {
		gr.restart();
		repaint();
	}
	
	/**
	 * This method launches the random solver when the button random solve is pressed
	 */
	public void startRandomSolve() {
		gr.randomSolve();
		repaint();	
	}
	
	/**
	 * This method runs the undo method from the class Grid when the button Undo is pressed
	 */
	public void activeUndo() {
		
		gr.undo();
		repaint();
	}
	
	/**
	 * This methods paints all the elements of the board
	 * @param g
	 */
    public void paintComponent(Graphics g) {
    	Color c = new Color(0x90, 0xEE, 0x90);
    	Color h = new Color(0x87, 0xCE, 0xFA); //  
    	super.paintComponent(g);
    	g.setColor(Color.GRAY);
    	for(int i=0;i<50;i++) {
        	
        	g.drawLine(i*40, 0, i*40, 800);
        	g.drawLine(0, i*40, 800, i*40);
        }
        g.setColor(Color.BLACK);
        for(Point p : gr.getPointCreated()) {       	
        	g.fillOval(p.getY()*40-8,p.getX()*40-8,16,16);

        }
          
        Graphics2D gA = (Graphics2D) g;
        gA.setStroke(new BasicStroke(3.5f));
        g.setColor(Color.BLACK);
        for(Line l : gr.getLineCreated()) {
        	g.drawLine(l.getP1().getY()*40, l.getP1().getX()*40, l.getP2().getY()*40, l.getP2().getX()*40);
        	
        }
        
        g.setColor(Color.WHITE);
        for(int i=0; i<gr.getMoves().size();i++) {
        	String s = String.valueOf(i+1);
        	if (Integer.parseInt(s)>9) {
        		g.drawString(s,gr.getMoves().get(i).getY()*40-7, gr.getMoves().get(i).getX()*40+4);	
        		
        	} else {
        		g.drawString(s,gr.getMoves().get(i).getY()*40-4, gr.getMoves().get(i).getX()*40+4);
        	}
        }
        
        g.setColor(c);
        for(Point p : gr.getPointChoice())
        	g.fillOval(p.getY()*40-8,p.getX()*40-8,16,16);
        	
        if(hint) {
        	 g.setColor(h);
             for(Point p : gr.getPotentialsPoints().keySet()) {
            	 g.fillOval(p.getY()*40-8,p.getX()*40-8,16,16);
             }
             hint=false;
        }
        
        
    }

	
 
}