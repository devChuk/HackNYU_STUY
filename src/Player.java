import java.awt.*;
import java.awt.geom.Rectangle2D;


public class Player {
	// this is what we're using for the player graphic
	private static final int PLAYER_WIDTH = 32, PLAYER_HEIGHT = 64;
	
	
	Rectangle2D _rect;
	private double _xcor, _ycor, _xvel, _yvel, _xacc, _yacc;
	private boolean _falling;
	private int _coneDir; //direction of viewing cone in degs
	
	public Player() {
		this(400,200);
	}
	public Player(int x, int y) {
		_rect = new Rectangle2D.Double(x,y,PLAYER_WIDTH,PLAYER_HEIGHT);
		_xcor = x;
		_ycor = y;
		_xvel = 0;
		_yvel = 0;
		_xacc = 0;
		_yacc = 0.6;
		_coneDir = 0;
		_falling = true;
	}
	
	public void paint(Graphics2D g2) {
		g2.setColor(Color.GREEN);
		g2.fill(_rect);
	}
	
	public void update() {
		if (isFalling()) {
			if (_yvel < 10) {
				_yvel += _yacc;
			}
			//System.out.println(isFalling());
		}else{
			_yvel = 0;
			//System.out.println(false);
		}
		_xcor += _xvel;
		_ycor += _yvel;
		
		_rect.setFrame(_xcor, _ycor,PLAYER_WIDTH,PLAYER_HEIGHT);
	}
	
	public void jump() {
		if (!_falling) {
			_yvel = -1;
			_falling = true;
		}
	}
	
	public void moveLeft() {
		_xvel = -5;
		
	}
	
	public void stopMoving() {
			_xvel = 0;
	}

	public void stopRight() {
		if (_xvel > 0)
			_xvel = 0;
	}

	public void stopLeft() {
		if (_xvel < 0)
			_xvel = 0;
	}
	
	public void moveRight() {
		_xvel = 5;
	}
	
	public boolean isFalling(){
		return _falling;
	}
	
	public void setFalling(boolean isFalling){
		_falling = isFalling;
	}
	
	public Rectangle getRect() {
		Rectangle rect = new Rectangle((int)_xcor,(int)_ycor,PLAYER_WIDTH,PLAYER_HEIGHT);
		return rect;
	}
	public double getXcor() {
		return _xcor;
	}
	public double getYcor() {
		return _ycor;
	}
	public double getXvel() {
		return _xvel;
	}
	public double getYvel() {
		return _xvel;
	}
	public double getXcv() {
		return _xcor + _xvel;
	}
	public double getYcv() {
		return _ycor + _yvel;
	}
	public double getXacc() {
		return _xacc;
	}
	public double getYacc() {
		return _yacc;
	}
	public double setXacc(double x) {
		double buf = _xacc;
		_xacc = x;
		return _xacc;
	}
	public double setYacc(double y) {
		double buf = _yacc;
		_yacc = y;
		return _yacc;
	}
	public int getConeDir() {
		return _coneDir;
		
	}
	public double setXcor(double x) {
		double buf = _xcor;
		_xcor = x;
		return buf;
		
	}
	public double setYcor(double y) {
		double buf = _ycor;
		_ycor = y;
		//System.out.println(y);
		return buf;
	}
	public double setXvel(double xvel) {
		double buf = _xvel;
		_xvel = xvel;
		return buf;
		
	}
	public double setYvel(double yvel) {
		double buf = _yvel;
		_yvel = yvel;
		return buf;
	}
	public int setConeDir(int dir) {
		int buf = _coneDir;
		_coneDir = dir;
		return buf;
		
	}
}
