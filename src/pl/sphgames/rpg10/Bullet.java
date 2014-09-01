package pl.sphgames.rpg10;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;


public class Bullet {
	private double bulletSpeed;
	public double x, y;
	private int damagePower;
	private double movingXSpeed, movingYSpeed;
	public static BufferedImage bulletImgN, bulletImgE, bulletImgW, bulletImgS;
	public BufferedImage bulletImg;
	public final static long timeBetweenNewBullets = Framework.secInNanosec / 3;
	public static long timeOfLastCreatedBullet = 0;
	private static Face face;
	private int[][] trajectory;
	private double _angle;

	
	
	public enum Face {
		NORTH,
		EAST,
		WEST,
		SOUTH
	};
	

	public Bullet(double x, double y, int dmg, Point mousePosition)
	    {
			this.damagePower = dmg;
	        this.x = x;
	        this.y = y;
	        	        
	        this.trajectory = new int[2][2];
			this.trajectory[0][0] = mousePosition.x - 14;
			this.trajectory[1][0] = mousePosition.y - 14;
			this.trajectory[0][1] = (int)x;
			this.trajectory[1][1] = (int)y;
			this._angle = 0;
			bulletSpeed = 5;
			setBulletVector(trajectory);
			this.bulletImg = getBulletImage();
	       
	        
	    }
	
	
	public void setBulletVector(int[][] trajectory){

		_angle = -90 + Math.atan2(trajectory[0][0] - trajectory[0][1],trajectory[1][0]-trajectory[1][1]) * 180/Math.PI;
		double _rad = Math.toRadians(_angle);
	
		movingXSpeed = bulletSpeed * Math.cos(_rad);
		movingYSpeed = bulletSpeed * -Math.sin(_rad);
	}
	
	
	
	public BufferedImage getBulletImage() {
	
			return bulletImgW;

		
	}
	
	public void setDirectionAndSpeed() {
		switch (Player.face) {
		case WEST:
			movingXSpeed = -bulletSpeed;
			movingYSpeed = 0;
			
		break;
		case NORTH:
			movingXSpeed = 0;
			movingYSpeed = -bulletSpeed;
			
		break;
		case EAST:
			movingXSpeed = bulletSpeed;
			movingYSpeed = 0;
			
		break;
		case SOUTH:
			movingXSpeed = 0;
			movingYSpeed = bulletSpeed;
			
		break;
		}
	}
	
	public void update() {
		x += movingXSpeed;
		y += movingYSpeed;
	}
	
	public boolean isItLeftScreen() {
	    if(x > 0 && x < Framework.frameWidth && y > 0 && y < Framework.frameHeight)
	        return false;
	    else
	        return true;
	    }
	
	public boolean hitUnpassable(double x, double y) {
		double xH, yH;
		yH = y;
		xH = x;
		
		int xH2, yH2;
		
		xH2 = (int) xH / 64;
		yH2 = (int) yH / 64;
		
		if (xH2 > 15 || yH2 > 11)
			return true;
		if (World.background[xH2][yH2].isPassable())		
			return false;
		
		return true;
	}
	
	 public void Draw(Graphics2D g2d)
	    {
		 AffineTransform at;
			at = AffineTransform.getRotateInstance(Math.toRadians(-_angle + 180),bulletImg.getHeight()/2,bulletImg.getWidth()/2);
		AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		g2d.drawImage(atop.filter(bulletImg, null), (int)x, (int)y, null);
	    }
	
}
