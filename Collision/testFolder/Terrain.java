import java.util.Random;
import java.util.ArrayList;
import java.awt.*;

public class Terrain{
	ArrayList<Rectangle> rectangles;

	public Terrain(){
		rectangles = new ArrayList<Rectangle>();
		rectangles.add(new Rectangle(300,300,50,50));
		rectangles.add(new Rectangle(200,300,50,50));
		rectangles.add(new Rectangle(100,300,50,50));
		rectangles.add(new Rectangle(250,300,50,50));
		rectangles.add(new Rectangle(300,350,50,50));
		rectangles.add(new Rectangle(400,400,50,50));
		rectangles.add(new Rectangle(0,0,50,50));
		rectangles.add(new Rectangle(0,300,50,50));
		rectangles.add(new Rectangle(300,0,50,50));
		rectangles.add(new Rectangle(50,50,50,50));
		rectangles.add(new Rectangle(100,100,50,50));
	}

	public void drawTerrain(Graphics2D g2d){
		
		for(Rectangle rect : rectangles){
			Random rand = new Random();
			int r = rand.nextInt(255);
			int g = rand.nextInt(255);
			int b = rand.nextInt(255);
		g2d.setColor(new Color(r,g,b));
			g2d.fillRect((int)rect.getX(),(int)rect.getY(),(int)rect.getWidth(),(int)rect.getHeight());
		}
	}
}