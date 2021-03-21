package view;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
* The BestScore class updates and prints the best score from a txt file
* @authors  Lucie Cubaud, Vittoria  Vecchioli
*/
public class BestScore {
	
	/**
	 * This method reads the score from the txt file 
	 * @return String
	 * @throws IOException
	 */
	public String readScore() throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("BestScoreFile.txt"));
		String bestScore = in.readLine();
		in.close();
		return bestScore;
		
	}
	
	/**
	 * This method updates the value in the txt file with the new highest best score
	 * @param g
	 * @throws IOException
	 */
	public void writeScore(String g) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter("BestScoreFile.txt"));
		out.write(g);
		out.close();
	}
}
