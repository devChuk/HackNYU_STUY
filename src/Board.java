import javax.imageio.ImageIO; 
import javax.swing.*; 

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//The file that holds the JFrame.

public class Board extends Canvas implements MouseListener, KeyListener, MouseMotionListener{
	BufferedReader in;
	BufferedWriter out;
	

	
	// this is a terrain
	private Terrain _terrain;
	private Player _player;
	private HashMap<String,Player> _enemies;
	private Mask _mask;
	private BufferedImage _background;// drawing dis twice
	private int _name;
	private boolean _isRegistered;
	private int _shank;
	
	private String _address;
	private int _port;
	
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
	
	public Board(String  ad, int por){
		init();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		loadResources();
		_address = ad;
		_port = por;
	}
	
	public void loadResources() {
		try {
			_background = ImageIO.read(new File("res/finalbg.fw.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("lol victor messed up his code\n");
			e.printStackTrace();
		}
	}
	
	public void init(){
		_running = false;	
		_terrain = new Terrain();
		_player = new Player();
		_mask = new Mask();
		_enemies = new HashMap<String,Player>();
		_shank = 0;
	}

	public void run() throws IOException {
//		String serverAddress = "homer.stuy.edu";
		Socket socket = new Socket(_address, _port);
		socket.setTcpNoDelay(true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//		out = new PrintWriter(socket.getOutputStream(), true);
		
		// how to add things to enemies/players list...
		
		
		
		long nextTick = System.currentTimeMillis();
		long sleepTime = 0;
		_running = true;
		createBufferStrategy(2);
		BufferStrategy strategy = getBufferStrategy();
		_isRegistered = false;
		int namecounter = 0;
		while (!_isRegistered) {
			System.out.println("heyc");
			String line = in.readLine();
			System.out.println(line + "heyd");
            if (line.startsWith("SUBMITNAME")) {
                out.write(""+namecounter++,0,1);
                out.newLine();
                out.flush();
                System.out.println(namecounter++);
            } else if (line.startsWith("NAMEACCEPTED")) {
            	_isRegistered = true;
            	namecounter--;
            	_name = namecounter;
            	
            }
		}
		
		while (_running) {
			String data = "name:" + _name + "/x:" + (int) _player.getXcor() + "/y:" + (int) _player.getYcor() + "/s:" + _shank + "/d:" + _player.isdead(); // s checks for stab
//			out.println(data);
			_shank = 0;
			out.write(data,0,data.length());
			out.newLine();
			out.flush();
			System.out.println("hey");
			String line = in.readLine();
			System.out.println("heya +" + line);
			if (_name == 0)
				System.out.println(line);
			
//            if (isRegistered) {
			int x = 0;
			int y = 0;
			int s = 0;
			int d = 0;
			int name = -1;
			String pairs[] = line.split("/",0);
			for (String pair: pairs) {
				String couple[] = pair.split(":");
				if (couple.length > 0) {
					switch(couple[0]) {
						case "name": // should be last in pairs
							
							if ((couple.length > 1)) {
								name = Integer.parseInt(couple[1]);
								if (name == _name) {
									break;
								}
								
								
								if (!_enemies.containsKey(name)) {
									
									if (_enemies.get("" + name) == null) {
										Player enemy = new Player();
									
										_enemies.put(""+name, enemy);
										
										System.out.println("created enemy with name:" + name);
									}
								}
								
							}
							break;
						case "x":
							if (couple.length > 1)
								x = Integer.parseInt(couple[1]);
							break;
						case "y":
							if (couple.length > 1)
								y = Integer.parseInt(couple[1]);
							break;
						case "s":
							if (couple.length > 1) {
								s = Integer.parseInt(couple[1]);
							}
							break;
						case "d":
							if (couple.length > 1) {
								d = Integer.parseInt(couple[1]);
							}
					}
				}
			}
			Player enemy = _enemies.get("" + name);
			if (enemy != null && name != _name) {
			
				enemy.setXcor(x);
				enemy.setYcor(y);
				if (s == 1 && ( Math.abs(_player.getXcor() - (x)) < 64) && Math.abs(_player.getYcor()-(y)) < 96)
					_player.die();
				// enemy.setS lol
				if (_name == 0)
//				System.out.println(x);
				
				if (d == 1)
					enemy.die();
				_enemies.put("" +name, enemy);
			}
		
			
//            } // end isRegistered if
		
			Graphics2D graphics = (Graphics2D) strategy.getDrawGraphics();
//			System.out.printf("update");
			update(graphics);
			
			nextTick += SKIP_TICKS;
			sleepTime = nextTick - System.currentTimeMillis();
			if (sleepTime >= 0) {
//				try {
					while (sleepTime >= 0) {
						sleepTime = nextTick - System.currentTimeMillis();
					}
//				} 
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
	
		
		
		g2.drawImage(_background, 0, 0, 1080, 640, 0, 0, 1080, 640, null);
		
		g2.setColor(Color.BLACK);
		_terrain.paint(g2);
		g2.setColor(Color.green);
		_player.paint(g2);
		g2.setColor(new Color(180,10,10));
		for (Player enemy: _enemies.values()) {
			
			// if enemy is visible
			if (_name == 0) {
//				System.out.println("x:" + enemy.getXcor() + "|  y:" + enemy.getYcor());
			}
//			enemy.paint(g2);
			Rectangle2D asdf = new Rectangle2D.Double(enemy.getXcor(),enemy.getYcor(),32,64);
			g2.fill(asdf);
		}
		_mask.paint(g2);
		g2.setColor(Color.red);
	}
	
	public void update(Graphics2D g) {
		// do some enemy attack checking here ish
//		System.out.println("---=======----");
		//Check if the projected rectangle will intersect anything.
		_player.setFalling(true);
//		_player.update();
		int report = _player.layoutProjectedRectangle(_terrain.rectangles);
		int rect = report / 10;
		report = report % 10;
		
		
		/*
		0 is no intersections.
		1 is ground intersection
		2 is wall intersection
		*/

		// update stuff
//		System.out.println("" + report + "|||" + _player.getXvel());
		switch(report) {
		
			case 0:
	//			System.out.println("fallingssss");
				break;
			case 1:
	//			System.out.println("notfallingssss");
				_player.setFalling(false);
				// the player intersected ground at 
				// _ycor + 64 - width, _xcor
			
				_player.setYcor(_terrain.rectangles.get(rect).getY() - 64.0);
				break;
			case 2:
	//			System.out.println("notfallingssss");
				_player.setXvel(0);
				_player.setYvel(0);
				_player.setFalling(false);
				System.out.println("wall");
				break;
			default:
	
				break;
		}
		_player.update();
//		System.out.println("player update");
		_mask.setXcor(_player.getXcor() + 32);
		_mask.setYcor(_player.getYcor() - 110);
		if (_player.getXvel() > 0) {
			_mask.setDir(0);
		} else  if (_player.getXvel() < 0){
			_mask.setDir(180);
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////////////
		
//		
//		for (Rectangle rect: _terrain.rectangles) {
//			if (rect.intersects(_player.getRect())) {
//				// if player is above
//				if (rect.y >= (_player.getYcor())) {
//
//					_player.setFalling(false);
//					_player.setYcor(rect.y - 63);
//				}
//				// if terrain is above
//				_player.setYvel(0);
//				// if player is to left
//				//_player.stopMoving();
//				// if player is to right
//				//_player.stopMoving();
//			}else{
//				_player.setFalling(true);
//			}
//			
//		}
		
		
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
				_player.changeKey(Player.KEY_UP,true);
				break;
			case KeyEvent.VK_LEFT:
				_player._isRunning = true;
				_player.changeKey(Player.KEY_LEFT,true);
				break;
			case KeyEvent.VK_RIGHT:
				_player._isRunning = true;
				_player.changeKey(Player.KEY_RIGHT,true);
				break;
			case KeyEvent.VK_DOWN:
				_player.changeKey(Player.KEY_DOWN,true);
				break;
			case KeyEvent.VK_S:
				_shank = 1;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				_player.changeKey(Player.KEY_UP,false);
				break;
			case KeyEvent.VK_LEFT:
				_player._isRunning = false;
				_player.changeKey(Player.KEY_LEFT,false);
				break;
			case KeyEvent.VK_DOWN:
				_player.changeKey(Player.KEY_DOWN,false);
				break;
			case KeyEvent.VK_RIGHT:
				_player._isRunning = false;
				_player.changeKey(Player.KEY_RIGHT,false);
				break;
			case KeyEvent.VK_ESCAPE:
			case KeyEvent.VK_Q:
				shutdown();
				_running = false;
				break;
			case KeyEvent.VK_S:
				_shank = 0;
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

