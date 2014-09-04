package pl.sphgames.rpg10;

import java.io.File;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Point;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class Crosshair {
	public static BufferedImage crosshImg;
	public int _x;
	public int _y;
	public static int middleX, middleY;
	public Point _mousePosition;
	public Crosshair(){
		_x =  Framework.frameWidth/2;
		_y =  Framework.frameHeight/2;
		try{
		crosshImg = ImageIO.read(new File("sight.png"));
		}
			catch (IOException ex) {
				Logger.getLogger(Object.class.getName()).log(Level.SEVERE, null, ex);
		}
		}

	public void update(Graphics2D g2d){
	 
 }
 public void draw(Graphics2D g2d, Point mousePosition){
	 _x = mousePosition.x;
	 _y = mousePosition.y;
	 middleX = _x+(crosshImg.getWidth()/2);
	 middleY = _y+(crosshImg.getHeight()/2);
	 _mousePosition = mousePosition;
	 g2d.drawImage(crosshImg,_x,_y, null);

 }
 }
