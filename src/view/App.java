package view;

import java.awt.Dimension;
import javax.swing.JFrame;


/**
* The class App is the class used to launch the game
* @authors  Lucie Cubaud, Vittoria  Vecchioli
*/
public class App {
	public static void main(String arg[]) {
		JFrame frame = new JFrame("Morpion Solitaire");
		frame.setContentPane(new Game());
		frame.setSize(new Dimension(900,900));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	}
}
