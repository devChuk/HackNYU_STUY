import java.util.Random; 
import java.util.ArrayList;
import java.awt.*;

public class Terrain{
	ArrayList<Rectangle> rectangles;

	public Terrain(){
		rectangles = new ArrayList<Rectangle>();
		rectangles.add(new Rectangle(200,521,600,50));
		rectangles.add(new Rectangle(313,465,51,5));
		rectangles.add(new Rectangle(313,465,51,5));
		rectangles.add(new Rectangle(313,465,51,5));

	}

	public void paint(Graphics2D g2d){
		
		for(Rectangle rect : rectangles){
			g2d.setColor(Color.BLACK);
			g2d.fillRect((int)rect.getX(),(int)rect.getY(),(int)rect.getWidth(),(int)rect.getHeight());
		}
	}
	
}	