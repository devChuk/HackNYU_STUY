import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Mask {
	private BufferedImage _mask;
	private double _xcor, _ycor, _width, _height;
	
	public Mask() {
		this(450,211);
	}
	
	public Mask(int x, int y) {
		_xcor = x;
		_ycor = y;
		_width = 450;
		_height = 211;
		try {
			_mask = ImageIO.read(new File("res/mask.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("lol victor messed up his code\n");
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0,0,0,100));
		double ytop = _ycor;
		double ybot = _ycor + _height;
		double xlef = _xcor;
		double xrit = _xcor + _width;
		g2.fillRect(0, 0, 1080, (int) ytop); // top
		g2.fillRect(0, (int) ybot, 1080, (int) (640 - ybot)); // bottom
		g2.fillRect(0, (int) ytop, (int) xlef, (int) _height); // left
		g2.fillRect((int) xrit, (int) ytop, (int) (1080 - xrit), (int) (_height)); // right
		
		g2.drawImage(_mask, (int) _xcor, (int) _ycor, (int) (_xcor + _width), (int) (_ycor + _height), 0, 0, (int)_width, (int)_height, null);
	}
	
	public void update(Graphics2D g2) {
		
	}
	
	public double setXcor(double x) {
		double buf = _xcor;
		_xcor = x;
		return buf;
	}
	public double setYcor(double y) {
		double buf = _ycor;
		_ycor = y;
		return buf;
	}
	
}
