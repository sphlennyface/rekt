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
	private double _endTime;
	private BufferedImage _image;
	private int _x,_y;
	private String _text;
	private boolean isTextFlag;

	public Timer(long gameTime, int Lenght, BufferedImage Image, int X, int Y){
		_endTime = gameTime + Lenght;
		_x = X - (_image.getWidth()/2);
		_y = Y - (_image.getHeight()/2);
	}
	public Timer(long gameTime, double Lenght, String Text, int X, int Y){
		_endTime = gameTime + Lenght*1000000000;
		_x = X;
		_y = Y;
		_text = Text;
		isTextFlag = true;
	}
	public void draw(Graphics2D g2d){
	
		if(Framework.gameTime <= _endTime){
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
