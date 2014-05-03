import javax.swing.*;
import java.awt.*;

//The file that holds the JFrame.

public class Board{
	private static JFrame board; //The main frame on which everything is painted on.
	private static Layering canvas; //The canvas with essentially all fo the paint methods.

	//Dimensions
	private static final int width=800,
						height=400,
						xLoc=30,
						yLoc=30;

	public Board(){
		prepareGui();

	}

	public void prepareGui(){
		board = new JFrame("Assassin");
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setSize(width,height);
		board.setLocation(30,30);

		canvas = new Layering();

		board.getContentPane().add(canvas);
	}

	public int getWidth(){ return x; }
	public int getHeight(){ return y; }
}

