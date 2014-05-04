import java.util.Random; 
import java.util.ArrayList;
import java.awt.*;

public class Terrain{
	ArrayList<Rectangle> rectangles;

	public Terrain(){
		rectangles = new ArrayList<Rectangle>();
		rectangles.add(new Rectangle(0,521,1080,30));
		rectangles.add(new Rectangle(313,465,51,5));
		rectangles.add(new Rectangle(745,465,195,5));
		rectangles.add(new Rectangle(313,465,51,5));
		rectangles.add(new Rectangle(96,408,297,5));
		rectangles.add(new Rectangle(0,358,393,5));
		rectangles.add(new Rectangle(1080,521,1080,30));
		rectangles.add(new Rectangle(1080+313,465,51,5));
		rectangles.add(new Rectangle(1080+745,465,195,5));
		rectangles.add(new Rectangle(1080+313,465,51,5));
		rectangles.add(new Rectangle(1080+96,408,297,5));
		rectangles.add(new Rectangle(1080,358,393,5));

	}

	public void paint(Graphics2D g2d){
		
		for(Rectangle rect : rectangles){
//			g2d.setColor(Color.BLACK);
//			g2d.fillRect((int)rect.getX(),(int)rect.getY(),(int)rect.getWidth(),(int)rect.getHeight());
		}
	}
	
}	