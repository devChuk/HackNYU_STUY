import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Sprite {
	private BufferedImage spriteSheet;
	private int counter;
	
	public Sprite(String path){
		try{
			spriteSheet = ImageIO.read(getClass().getResource(path));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSpriteSheet(){
		return spriteSheet;
	}
	
	public BufferedImage crop(int column, boolean isleft){
//		if (!isleft) {
			System.out.print((column/5)* 32+"\n");
			return spriteSheet.getSubimage((column/5)* 64, 0, 64, 78);
//		}
	}
	
	public BufferedImage tick(boolean _isRunning, boolean _isRunningLeft){
		if(_isRunning){
			if (!_isRunningLeft) {
				counter += 1;
			}
			else 
				counter -=1;
			
		}
		else{
			counter =25;
		}
		if(counter >= 95){
			counter = 0;
		} else if (counter <= 0) {
			counter = 94;
		}
		
		return crop(counter, _isRunningLeft);
	}
	
}
