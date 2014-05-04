import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Player {
	// this is what we're using for the player graphic
	private static final int PLAYER_WIDTH = 32, PLAYER_HEIGHT = 64;
	public static final int KEY_UP = 0, KEY_DOWN = 1, KEY_LEFT = 2, KEY_RIGHT = 3, KEY_STAB = 4;
	public static final double GRAVITY = 0.4, FRICTION = 0.5;
	
	
	
	Rectangle2D _rect;
	private double _xcor, _ycor, _xvel, _yvel;
	private boolean _falling;
	private boolean[] _keys; // up, down, left, right, stab
	private int _dead;
	private int _coneDir; //direction of viewing cone in degs
	public boolean _isRunning;
	private Sprite _sprite;
	private Sprite _spriteback;
	
	
	
	
	public Player() {
		this(400,200);
	}
	public Player(int x, int y) {
		_rect = new Rectangle2D.Double(x,y,PLAYER_WIDTH,PLAYER_HEIGHT);
		_xcor = x;
		_ycor = y;
		_xvel = 0;
		_yvel = 0;
		_coneDir = 0;
		_falling = true;
		_keys = new boolean[5];
		for (int i = 0; i < 5; i++) {
			_keys[i] = false;
		}
		_dead = 0;
		_sprite = new Sprite("../res/running.png");
		_spriteback = new Sprite("../res/runningback.png");
		
		_isRunning = false;
		
	}
	
	public void paint(Graphics2D g2) {
//		g2.setColor(Color.GREEN);
//		g2.fill(_rect);
		if (_xvel >= 0) {
			g2.drawImage(_sprite.tick(_isRunning), (int) _xcor - 16, (int)_ycor, null);
		} else {
			g2.drawImage(_spriteback.tick(_isRunning), (int) _xcor - 16, (int)_ycor, null);
		}
				
	}
	
	public void update() {
		
		if (_dead == 1) {
			return;
		}
		
//		round(_xcor);
//		round(_ycor);
//		round(_xvel);
//		round(_yvel);
		
		if (_keys[KEY_UP] && !_falling) {
			_yvel = -8;
			_falling = true;
		} else {
			
		}
		if (_keys[KEY_DOWN]) {
//				_rect.setFrame(_xcor, _ycor + 32, 32, 32);
			
		} else {
			
//			_rect.setFrame(_xcor, _ycor - 32, PLAYER_WIDTH, PLAYER_HEIGHT);
		}
		if (_keys[KEY_LEFT]) {
			if (_xvel > -4 && _xvel <= .1) {
				_xvel -= .5;
			} else if (_xvel < -4) {
				_xvel = -4;
			} if (_xvel > 0) {
//				_xvel -= .1;
			}
		} else {

			if (_xvel < -.1) {
				_xvel += FRICTION;
				if (Math.abs(round(_xvel)) == 0.2 || Math.abs(round(_xvel)) < 0.09) {
					_xvel = 0;
				}
			}
		}
		if (_keys[KEY_RIGHT]) {
			if (_xvel < 4 && _xvel >= -.1) {
				_xvel += .5;
			} else if (_xvel > 4) {
				_xvel = 4;
			} if (_xvel < 0) {
//				_xvel += .1;
			}
		} else {
			if (_xvel > .1) {
				_xvel -= FRICTION;
				if (Math.abs(round(_xvel)) < 0.09) {
					_xvel = 0;
				}
			}
			
		}
		if (_keys[KEY_STAB]) {
			// TO IMPLEMENT
		} else {
			
		}
		
		
		
		
		if (_falling) {
//			System.out.println("falling");
			if (_yvel < 10) {
				_yvel += GRAVITY;
			}
			//System.out.println(isFalling());
		} else {
//			System.out.println("not falling");
			_yvel = 0;
			//System.out.println(false);
		}
		
		
		
		
		_xcor += _xvel;
		_ycor += _yvel;
	
		
		_rect.setFrame(_xcor, _ycor,PLAYER_WIDTH,PLAYER_HEIGHT);
	}
	
	public void die() {
		_dead = 1;
		_rect.setFrame(_xcor - 32, _ycor + 32, PLAYER_HEIGHT, PLAYER_WIDTH);
		
	}
	
	public int isdead() {
		return _dead;
	}
	
	public int layoutProjectedRectangle(ArrayList<Rectangle> rectangles){
		/*
		0 is no intersections.
		1 is ground intersection
		2 is wall intersection
		*/

		Rectangle projectedRectangle=new Rectangle((int)(_xcor+_xvel),(int)(_ycor+_yvel+GRAVITY+ 0.99),PLAYER_WIDTH,PLAYER_HEIGHT);
		for(int i = 0; i < rectangles.size(); i++) {
			if (rectangles.get(i).intersects(projectedRectangle)) {
//				System.out.println("intersert");
				// if player is above
//				System.out.println("plyr: " + (projectedRectangle.getY()+PLAYER_HEIGHT) + "| rect: " + rectangles.get(i).getY());
				if(_yvel>0){
					if (projectedRectangle.getY()+PLAYER_HEIGHT>=rectangles.get(i).getY() && projectedRectangle.getY()+PLAYER_HEIGHT<=rectangles.get(i).getY() + 10) {
						return 1 + i * 10;
					}
					// if player is to left
					if(_xvel>0){
						if(projectedRectangle.getX()+PLAYER_WIDTH>=rectangles.get(i).getX()) {
//							return 2 + i* 10 ;// implement later
						}
					}
					// if player is to the right
					if(_xvel<0){
						if(projectedRectangle.getX()<=rectangles.get(i).getX()+rectangles.get(i).getWidth()){
//							return 2 + i * 10 ;// implement later
						}
					}
				}

			}
		}
		return 0; //Either the player didn't hit anything or he hit his head, which is fine.
	}
	
	// MOVEMENT FUNCTIONSSSS
	
	public boolean changeKey(int key, boolean value) {
		boolean buf = _keys[key];
		_keys[key] = value;
		return buf;
		
	}
	
	
	
	// GET SET FUNCTIONSSSSS

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
	
	
	public double round(double d) {// nearest 10th
		int ans = (int) (d * 100);
		if (Math.abs(ans % 10) > 4) {
			if (ans > 0) {
//				System.out.println("  ans>0");
				ans += 10;
			}
			if (ans < 0) {
//				System.out.println("  ans<0");
				ans -= 10;
			}
		}
		ans /= 10;
//		System.out.println("before: " + d + "| after: " + (double) ans / 10);
		return (double) ans / 10;
		
	}
}
