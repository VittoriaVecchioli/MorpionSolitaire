package view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


import model.Grid5T;

/**
* The Button class is used to represent the buttons as well as the score labels and the game over message at the top of the user interface.
* It extends JPanel and implements ActionListener.
* It takes a JPanel as argument.
* @author  Lucie Cubaud, Vittoria  Vecchioli
*/
public class Buttons extends JPanel implements ActionListener{
	
	JButton restart = new JButton("Restart");
	JButton hint = new JButton("Hint");
	JButton randomSolve = new JButton("Random Solve");
	JButton changeGame = new JButton("Switch to 5D");
	JButton undo = new JButton("Undo");
	JLabel scoreString = new JLabel( "Score :");
	JLabel scoreInt = new JLabel();
	JLabel gameOver = new JLabel();
	JLabel bestScore = new JLabel();
	BestScore bs = new BestScore();
	int best;
	JPanel b;
	
	
	public Buttons(JPanel b) {
		setBackground(Color.WHITE);
		add(gameOver);
		add(restart,BorderLayout.NORTH);
		add(hint,BorderLayout.SOUTH);
		add(randomSolve);
		add(changeGame);
		add(undo);
		add(scoreString);
		setScore();
		add(scoreInt);
		add(bestScore);
		readBestScore();
		
		changeGame.addActionListener(this);
		restart.addActionListener(this);
		hint.addActionListener(this);
		randomSolve.addActionListener(this);
		undo.addActionListener(this);
		this.b=b;
	
	}
	
	/**
	 * This method calls the read method in the BestScore class to read the best score and displays it.
	 */
	public void readBestScore() {
		try {
			String s = bs.readScore();
			best = Integer.parseInt(s);
			System.out.println(s);
			bestScore.setText("Best: "+s);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * This method prints a Game Over message when no more moves are possible.
	 * If the current highest score is exceeded, it updates the value of the Best score.
	 */
	public void setGameOver() {
		if (((Game) getParent())!=null) {
			if( ((Game) getParent()).isGameOver()) {
				gameOver.setForeground(Color.RED);
				gameOver.setText("Game Over");
				//if best score exceeded
				readBestScore();
			}
		}
			
	}
	
	/**
	 * This method get the score of the current game and prints it.
	 * If the best score is exceeded, it updates the text file with the score value.
	 */
	public void setScore() {
		if (((Game) getParent())!=null) {
			int currentScore = ((Game) getParent()).getScore();
			scoreInt.setText(String.valueOf(currentScore));
			if(currentScore>best)
				try {
					bs.writeScore(String.valueOf(currentScore));
				} catch (IOException e) {
					e.printStackTrace();
				}
			//Check if game over
			setGameOver();
		}else {
			scoreInt.setText("0");
			
		}
	}

	/**
	 * This method call the right methods when a button pressed.
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==hint) {
			((Board) b).activHint();
		}		
		if(e.getSource()==restart) {
			((Board) b).restart();
			setScore();
			gameOver.setText("");
			readBestScore();
		}
		if(e.getSource()==randomSolve) {		
			((Board) b).startRandomSolve();
			setScore();
		}			
		if(e.getSource()==changeGame) {
			
			((Game) getParent()).changeGame();
			if(((Game) getParent()).getGrid() instanceof Grid5T) {
				changeGame.setText("Switch to 5D");
			}else
				changeGame.setText("Switch to 5T");
			setScore();
			gameOver.setText("");
		} 
		
		if(e.getSource()==undo) {
			((Board) b).activeUndo();
			 setScore();
			 gameOver.setText("");
		}
		
	}
}