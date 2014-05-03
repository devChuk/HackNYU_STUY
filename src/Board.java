import javax.swing.*; 

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

//The file that holds the JFrame.

public class Board extends Canvas implements MouseListener, KeyListener, MouseMotionListener{
	// this is a terrain
	private Terrain _terrain;
	private Player _player;
	private Mask _mask;
	
	//Dimensions
	private static final int WIDTH=800,
						HEIGHT=400,
						X_LOC=30,
						Y_LOC=30;
	// SKIP_TICKS is how much time to wait for each ms.
	public static final int FPS = 60, SKIP_TICKS = 1000 / FPS;

	// private BufferedImage _background;
	private boolean _running;
	
	public Board(){
		init();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		loadResources();
	}
	
	public void loadResources() {
		
	}
	
	public void init(){
		_running = false;	
		_terrain = new Terrain();
		_player = new Player();
		_mask = new Mask();
	}

	public void run() {
		long nextTick = System.currentTimeMillis();
		long sleepTime = 0;
		_running = true;
		createBufferStrategy(2);
		BufferStrategy strategy = getBufferStrategy();
		while (_running) {
			Graphics2D graphics = (Graphics2D) strategy.getDrawGraphics();
			update(graphics);
			
			nextTick += SKIP_TICKS;
			sleepTime = nextTick - System.currentTimeMillis();
			if (sleepTime >= 0) {
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			graphics.dispose();
			strategy.show();
		}
	}
	
	public void shutdown() {
		_running = false;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.BLACK);
		_terrain.paint(g2);
		_player.paint(g2);
		_mask.paint(g2);
	}
	
	public void update(Graphics2D g) {
		//Check if the projected rectangle will intersect anything.
		int report = layoutProjectedRectangle(_terrain.rectangles);
		
		/*
		0 is no intersections.
		1 is ground intersection
		2 is wall intersection
		*/

		// update stuff
		
		switch(report){
			case 1:
			_player.setFalling(false);
			break;
			case 2:
			_player.
			break:
			default:

			break;
		}



		_player.update();
		_mask.setXcor(_player.getXcor() + 32);
		_mask.setYcor(_player.getYcor() - 132);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////
		for (Rectangle rect: _terrain.rectangles) {
			if (rect.intersects(_player.getRect())) {
				// if player is above
				if (rect.y >= (_player.getYcor())) {

					_player.setFalling(false);
					_player.setYcor(rect.y - 75);
				}
				// if terrain is above
				_player.setYvel(0);
				// if player is to left
				//_player.stopMoving();
				// if player is to right
				//_player.stopMoving();
			}else{
				_player.setFalling(true);
			}
			
		}
		
		// double buffer
		Graphics offgc;
		Image offscreen = null;
		offscreen = createImage(1080,640);
		offgc = offscreen.getGraphics();
		
		paint(offgc);
		
		g.drawImage(offscreen,0,0,this); // not sure what this line does
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				_player.jump();
				break;
			case KeyEvent.VK_LEFT:
				_player.moveLeft();
				break;
			case KeyEvent.VK_RIGHT:
				_player.moveRight();
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_RIGHT:
				_player.stopMoving();
				break;
			case KeyEvent.VK_ESCAPE:
			case KeyEvent.VK_Q:
				shutdown();
				_running = false;
				break;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

