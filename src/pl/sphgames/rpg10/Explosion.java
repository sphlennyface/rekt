package pl.sphgames.rpg10;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class Explosion{
	private int _radius;
	private int _currentXTile, _currentYTile;
	private Timer[] _graphicsArray;
	public static BufferedImage explosionImg;
	private boolean[] show;


	public Explosion(double lenght, int damage, int X, int Y, int radius){
		_radius = radius;
		_currentXTile = X;
		_currentYTile = Y;
		
		try{
			explosionImg = ImageIO.read(new File("explosion.png"));
		}
		catch (IOException ex) {
			Logger.getLogger(Object.class.getName()).log(Level.SEVERE, null, ex);
		}
		_graphicsArray = new Timer[4*radius + 2];
		int i=0;
		int j=-radius;

		for(i=0;i<radius*2 + 1;i++){
			_graphicsArray[i]= new Timer(Framework.gameTime,lenght,explosionImg,X+j,Y);
			if((X+j!=0 && Y!=0) && (X+j!=15 && Y!=11))
			Game.timerList.add(_graphicsArray[i]);
			j++;
		}
		j=-radius;
		for(i=3;i<radius*2 +3 + 1;i++){
			_graphicsArray[i]= new Timer(Framework.gameTime,lenght,explosionImg,X,Y+j);
			if((Y+j!=0 && X!=0) && (X!=15 && Y+j!=11))
			Game.timerList.add(_graphicsArray[i]);
			j++;
		}

		}
	public int getXTile(){
		return _currentXTile;
	}
	public int getYTile(){
		return _currentYTile;
	}
	public int getRadius(){
		return _radius;
	}
}