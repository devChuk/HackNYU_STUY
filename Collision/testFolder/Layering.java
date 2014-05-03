import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Layering extends Canvas{
	Terrain terr = new Terrain();

	public Layering(){
		super();
	}

	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D)g;

		//Layer 1: Background Layer-- for now, simply a white background.
		g2d.setColor(Color.white);
		g2d.fillRect(0,0,Board.getWidth(),Board.getHeight());

		//Layer 2: Terrain layer. Hardcoded for now.
		terr.drawTerrain(g2d);

		//Layer 3: Cone shape. For victor to do.
	}

}