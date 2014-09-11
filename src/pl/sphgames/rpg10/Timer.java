package pl.sphgames.rpg10;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import pl.sphgames.rpg10.*;

public class Timer {
	private double _startTime;
	private double _endTime;
	private BufferedImage _image;
	private int _x,_y;
	private String _text;
	private boolean isTextFlag;
	
	static public enum STATE{
		NONEXISTING,
		PENDING,
		WORKING,
		DONE
	};

	public Timer(long gameTime, int Lenght, BufferedImage Image, double X, double Y){
		_startTime = gameTime;
		_endTime = gameTime + Lenght*1000000000;
		_image = Image;
		_x = (int) (X - (Image.getWidth()/2));
		_y = (int) (Y - (Image.getHeight()/2));
	}
	public Timer(long gameTime, double Lenght, BufferedImage Image, int XTile, int YTile){
		_startTime = gameTime;
		_endTime = gameTime + Lenght*1000000000;
		_image = Image;
		_x = XTile*64;
		_y = YTile*64;
	}
	public Timer(){
	}
	public Timer(long gameTime, double Lenght, String Text, int X, int Y){
		_endTime = gameTime + Lenght*1000000000;
		_x = X;
		_y = Y;
		_text = Text;
		isTextFlag = true;
	}
	public Timer(long gameTime, double Lenght){
		_endTime = gameTime + Lenght*1000000000;
	}
	public boolean isWaiting(){
		if(Framework.gameTime < _endTime)
			return true;
		else
			return false;
	}
	public boolean hasAlreadyTriggered(){
		if(Framework.gameTime > _startTime)
			return true;
		else
			return false;
	}
	public void draw(Graphics2D g2d){
	
		if(this.isWaiting()){
			if(isTextFlag){
				g2d.setFont(new Font("TimesRoman", Font.BOLD, 30));
				g2d.drawString(_text, _x, _y);
				g2d.setFont(new Font("TimesRoman", Font.PLAIN, 12));
			}			
			else
				g2d.drawImage(_image,_x,_y, null);
		}

	}
		

}
