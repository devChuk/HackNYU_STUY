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
	private int _direction;
	
	public Mask() {
		this(450,211);
	}
	
	public Mask(int x, int y) {
		_xcor = x;
		_ycor = y;
		_width = 640;
		_height = 300;
		try {
			_mask = ImageIO.read(new File("res/blackcone.fw.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("lol victor messed up his code\n");
			e.printStackTrace();
		}
		_direction = 0;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0,0,0));
		double ytop = _ycor;
		double ybot = _ycor + _height;
		double xlef = _xcor - 32;
		double xrit = _xcor + _width -32;
		switch (_direction) {
			case 0:
				xlef = _xcor - 32;
				xrit = _xcor + _width - 32;
				break;
			case 180:
				xlef = _xcor  - _width;
				xrit = _xcor;
		}
		g2.fillRect(0, 0, 1080, (int) ytop); // top
		g2.fillRect(0, (int) ybot, 1080, (int) (640 - ybot)); // bottom
		g2.fillRect(0, (int) ytop, (int) xlef, (int) _height); // left
		g2.fillRect((int) xrit, (int) ytop, (int) (1080 - xrit), (int) (_height)); // right
		switch (_direction) {
			case 0:
				g2.drawImage(_mask, (int) xlef, (int) _ycor, (int) xrit, (int) (_ycor + _height), 20, 0, (int)_width, (int)_height, null);
				break;
			case 180:
				g2.drawImage(_mask, (int) xlef, (int) _ycor, (int) xrit, (int) (_ycor + _height), (int) _width, 0, 0, (int)_height, null);
				break;
		}
	}
	
	
	public void update(Graphics2D g2) {
		
	}
	
	public int setDir(int d) {
		int buf = _direction;
		_direction = d;
		return buf;
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
