import java.io.IOException;

import javax.swing.JFrame;


public class Game {
	private static JFrame f;
	private static Board _board;
	public static void main(String args[]) {
		_board = new Board();
		// import images and stuff here
		f = new JFrame();
		f.setTitle("Blackout");
		f.setSize(1080,640);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.add(_board);
		f.setVisible(true);
		try {
			_board.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		f.setVisible(false);
		f.dispose();
	}
}
